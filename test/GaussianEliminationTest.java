import main.GaussianElimination;
import model.Matrix;
import model.Result;
import model.Vector;

/**
 * File: GaussianEliminationTest.java
 *
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class GaussianEliminationTest {
    static double[][] matrix;
    static double[] vector;

    public static void main(String[] args) {
        if (args.length == 3){
            int N = Integer.parseInt(args[1]);
            int runTimes = Integer.parseInt(args[2]);
            if (args[0].charAt(0) == 's')
                runSample(false, N, runTimes);
            if (args[0].charAt(0) == 'p')
                runSample(true, N, runTimes);
            return;
        } else if (args.length == 0){

        } else {
            System.err.println("Usage: GaussianEliminationTest [<p|s> <N> <run times>]");
        }
        matrix = new double[3][3];
        vector = new double[3];

        matrix[0] = new double[]{1, 2, 3};
        matrix[1] = new double[]{4, 5, 6};
        matrix[2] = new double[]{7, 8, 9};

        vector = new double[]{10, 11, 12};
        GaussianElimination.Serial GES = new main.GaussianElimination.Serial(matrix, vector);
        Thread t = new Thread(GES);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Result result = GES.getResult();
        System.out.println("----- RESULTS (Serial) -----");
        result.print();

        GaussianElimination.Parallel GEP = new main.GaussianElimination.Parallel(matrix, vector);
        Thread t1 = new Thread(GEP);
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Result result1 = GEP.getResult();
        System.out.println("----- RESULTS (Parallel) -----");
        result1.print();
    }

    public static void runSample(boolean isParallel, int N, int runs){
        if (isParallel){
            long avgTime = 0;
            for (int i = 0; i < runs; i++) {
                GaussianElimination.Parallel GEP = new main.GaussianElimination.Parallel(Matrix.randomMatrix(N), Vector.randomVector(N));
                Thread t1 = new Thread(GEP);
                t1.start();
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Result result = GEP.getResult();
                if (runs == 1){
                    System.out.println("----- RESULTS (Parallel) -----");
                    result.print();
                }
                avgTime = ((avgTime * i) + result.getTimeInMilli()) / (i + 1);
            }
            System.out.println("----- AVG TIME (" + runs + " runs)-----\n" + avgTime + " ms");
        } else {
            long avgTime = 0;
            for (int i = 0; i < runs; i++) {
                GaussianElimination.Serial GES = new main.GaussianElimination.Serial(Matrix.randomMatrix(N), Vector.randomVector(N));
                Thread t = new Thread(GES);
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Result result = GES.getResult();
                if (runs == 1){
                    System.out.println("----- RESULTS (Serial) -----");
                    result.print();
                }
                avgTime = ((avgTime * i) + result.getTimeInMilli()) / (i + 1);
            }
            System.out.println("----- AVG TIME (" + runs + " runs) -----\n" + avgTime + " ms");
        }
    }
}
