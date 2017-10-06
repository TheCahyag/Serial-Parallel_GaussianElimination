package parallel;

import main.GaussianElimination;

/**
 * File: ReadyPivot.java
 *
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class ReadyPivot implements Runnable {

    private double[][] matrix;
    private double[] vector;
    private int row;
    private GaussianElimination.Parallel parent;

    public ReadyPivot(double[][] matrix, double[] vector, int row, GaussianElimination.Parallel parent) {
        this.matrix = matrix;
        this.vector = vector;
        this.row = row;
        this.parent = parent;
    }

    @Override
    public void run() {
        int len = this.matrix.length;
        while (!this.parent.canModRow(row)){
            try {
                this.parent.wait();
            } catch (InterruptedException e) { }
        }
        double divisor = this.matrix[row][row];
        for (int i = 0; i < len; i++) {
            this.matrix[row][i] /= divisor;
        }
        this.vector[row] /= divisor;
        this.parent.doneWithRow(row);
    }
}
