package model;

import java.util.Random;

/**
 * File: Matrix.java
 *
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class Matrix {

    public final static double DEFAULT_LOW = -5000;
    public final static double DEFAULT_HIGH = 5000;

    /**
     * Create a NxN matrix with random values
     * @param dimension dimension of the square matrix
     * @param lowBound lower bound of values that are in the matrix
     * @param highBound upper bound of values that are in the matrix
     * @return NxN matrix with random values
     */
    public static double[][] randomMatrix(int dimension, double lowBound, double highBound){
        double[][] matrix = new double[dimension][dimension];
        Random random = new Random();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                double randomDouble = lowBound + (highBound - lowBound) * random.nextDouble();
                if (Double.valueOf(randomDouble).isInfinite()){
                    // Ensure an infinite number hasn't bee generated, if so use 1 as a default
                    randomDouble = 1;
                }
                matrix[i][j] = randomDouble;
            }
        }
        return matrix;
    }

    /**
     * Overloaded method to generate a matrix containing random values
     * @param dimension dimension of the square matrix
     * @return NxN matrix with random values
     */
    public static double[][] randomMatrix(int dimension){
        return randomMatrix(dimension, DEFAULT_LOW, DEFAULT_HIGH);
    }



}
