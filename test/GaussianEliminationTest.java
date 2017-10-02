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
        matrix = new double[3][3];
        vector = new double[3];

        matrix[0] = new double[]{1, 2, 3};
        matrix[1] = new double[]{4, 5, 6};
        matrix[2] = new double[]{7, 8, 9};

        vector = new double[]{10, 11, 12};
        Matrix.printMatrix(matrix);
        Vector.printVector(vector);

        Result result = GaussianElimination.serialExecution(matrix, vector);
        System.out.println("----- RESULTS -----");
        result.print();
    }
}
