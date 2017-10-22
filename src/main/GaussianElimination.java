package main;

import model.Matrix;
import model.Result;
import model.Vector;
import parallel.SetDiagonal;
import parallel.SubtractRowOperation;


/**
 * File: main.GaussianElimination.java
 *
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class GaussianElimination {

    public static class Serial implements Runnable {

        private double[][] matrix;
        private double[] vector;
        private Result result;

        public Serial(double[][] matrix, double[] vector){
            this.matrix = matrix;
            this.vector = vector;
            this.result = new Result();
            this.result.setStartingMatrix(Matrix.getDeepCopy(matrix));
            this.result.setStartingVector(Vector.getDeepCopy(vector));
        }

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            int dim = this.matrix.length;
            for (int i = 0; i < dim; i++) {
                double divisor = this.matrix[i][i];
                for (int j = i; j < dim; j++) {
                    this.matrix[i][j] /= divisor;
                }
                this.vector[i] /= divisor;
                for (int j = i + 1; j < dim; j++) {
                    double multiplier = this.matrix[j][i];
                    for (int k = 0; k < dim; k++) {
                        this.matrix[j][k] -= this.matrix[i][k] * multiplier;
                    }
                    this.vector[j] -= this.vector[i] * multiplier;
                }
            }
            for (int i = 1; i < dim; i++) {
                if (this.matrix[i][i] == 1){
                    for (int k = i; k > 0; k--) {
                        double multiplier = this.matrix[k - 1][i];
                        for (int j = k; j < dim; j++) {
                            this.matrix[k - 1][j] -= this.matrix[i][j] * multiplier;
                        }
                        this.vector[k - 1] -= this.vector[i] * multiplier;
                    }

                }
            }

            long endTime = System.currentTimeMillis();
            this.result.setTimeInMilli(endTime - startTime);
            this.result.setResultingVector(this.vector);
            this.result.setResultingMatrix(this.matrix);
        }

        public Result getResult() {
            return result;
        }
    }

    public static class Parallel implements Runnable {

        private double[][] matrix;
        private double[] vector;
        private Result result;
        public boolean[] rowLocks;

        public Parallel(double[][] matrix, double[] vector){
            this.matrix = matrix;
            this.vector = vector;
            this.result = new Result();
            this.result.setStartingMatrix(Matrix.getDeepCopy(matrix));
            this.result.setStartingVector(Vector.getDeepCopy(vector));
            this.rowLocks = new boolean[this.matrix.length];
        }

        @Override
        public void run() {
            this.result.setStartingMatrix(this.matrix.clone());
            long startTime = System.currentTimeMillis();
            int len = this.matrix.length;

            for (int i = 0; i < len; i++) {
                // Get the upper triangular matrix
                SetDiagonal rp = new SetDiagonal(this.matrix, this.vector, i, this);
                Thread tmp = new Thread(rp);
                tmp.run();
                try {
                    tmp.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread[] threads = new Thread[len - (i + 1)];
                for (int j = i + 1, k = 0; j < len; j++, k++) {
                    SubtractRowOperation sr = new SubtractRowOperation(this.matrix, this.vector, j, this.matrix[j][i], i);
                    threads[k] = new Thread(sr);
                    sr.run();
                }

                for (int j = 0; j < threads.length; j++) {
                    try {
                        threads[j].join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            for (int i = 1; i < len; i++) {
                // Put into RREF
                if (this.matrix[i][i] != 1)
                    continue;
                Thread[] threads = new Thread[i];
                for (int j = 0, k = 0; j < i; j++, k++) {
                    SubtractRowOperation sr = new SubtractRowOperation(this.matrix, this.vector, j, this.matrix[j][i], i);
                    threads[k] = new Thread(sr);
                    sr.run();
                }
                for (int j = 0; j < threads.length; j++) {
                    try {
                        threads[j].join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            long endTime = System.currentTimeMillis();
            this.result.setTimeInMilli(endTime - startTime);
            this.result.setResultingMatrix(this.matrix);
            this.result.setResultingVector(this.vector);
        }

        public synchronized boolean canModRow(int row){
            if (!this.rowLocks[row]){
                this.rowLocks[row] = true;
                return true;
            }
            return false;
        }

        public synchronized void doneWithRow(int row){
            this.rowLocks[row] = false;
            notifyAll();
        }

        public Result getResult() {
            return this.result;
        }
    }
}
