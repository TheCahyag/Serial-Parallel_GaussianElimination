import model.Matrix;
import model.Result;

/**
 * File: GaussianElimination.java
 *
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class GaussianElimination {

    public static Result serialExecution(int dimension, double lowBounds, double highBounds){
        double[][] matrix = Matrix.randomMatrix(dimension, lowBounds, highBounds);
        long startTime = System.currentTimeMillis();



        long endTime = System.currentTimeMillis();
        return new Result(null, endTime - startTime);
    }

    public static Result parallelExecution(int dimension, double lowBounds, double highBounds){
        double[][] matrix = Matrix.randomMatrix(dimension, lowBounds, highBounds);
        long startTime = System.currentTimeMillis();



        long endTime = System.currentTimeMillis();
        return new Result(null, endTime - startTime);
    }

}
