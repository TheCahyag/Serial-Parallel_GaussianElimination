import model.Matrix;
import model.Result;
import model.Vector;

/**
 * File: GaussianElimination.java
 *
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class GaussianElimination {

    /**
     *
     * @param matrix
     * @param vector
     * @return
     */
    public static Result serialExecution(double[][] matrix, double[] vector){
        Result result = new Result();
        result.setStartingMatrix(Matrix.getDeepCopy(matrix));
        int dim = matrix.length;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < dim; i++) {
            double divisor = matrix[i][i];
            for (int j = i; j < dim; j++) {
                matrix[i][j] /= divisor;
                vector[i] /= divisor;
            }
            for (int j = i + 1; j < dim; j++) {
                double multiplier = matrix[j][i];
                for (int k = 0; k < dim; k++) {
                    matrix[j][k] -= matrix[i][k] * multiplier;
                    vector[j] -= vector[i] * multiplier;
                }
            }
        }
        //Matrix.printMatrix(matrix);
        //Vector.printVector(vector);

        long endTime = System.currentTimeMillis();
        result.setTimeInMilli(endTime - startTime);
        result.setResultingVector(vector);
        result.setResultingMatrix(matrix);
        return result;
    }

    /**
     *
     * @param matrix
     * @param vector
     * @return
     */
    public static Result parallelExecution(double[][] matrix, double[] vector){
        Result result = new Result();
        result.setStartingMatrix(matrix.clone());
        long startTime = System.currentTimeMillis();



        long endTime = System.currentTimeMillis();
        result.setTimeInMilli(endTime - startTime);
        return result;
    }
}
