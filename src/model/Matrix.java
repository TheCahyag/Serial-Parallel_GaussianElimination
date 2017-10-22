package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * File: Matrix.java
 *
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class Matrix {

    public final static double DEFAULT_LOW = -5000.0d;
    public final static double DEFAULT_HIGH = 5000.0d;

    public Matrix(){

    }

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

    /**
     * Prints a square 2d primitive array to System.out
     * @param matrix double[][] to print (must be square)
     */
    public static void printMatrix(double[][] matrix){
        int dim = matrix.length;
        String result = "[";
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                Double num = matrix[i][j];
                if (num.isInfinite() || num.isNaN()){
                    num = 0d;
                }
                if (j == 0)
                    result += "\t" + num + ", ";
                else if (j != dim -1)
                    result += num + ", ";
                else
                    result += num;
            }
            if (i != dim - 1)
                result += "\n";
        }
        result += "\t]";
        System.out.println(result);
    }

    public static double[][] getDeepCopy(double[][] matrix){
        int dim = matrix.length;
        double[][] newMatrix = new double[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    public static String toString(double[][] matrix){
        int dim = matrix.length;
        String result = "[";
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                Double num = matrix[i][j];
                if (num.isInfinite() || num.isNaN()){
                    num = 0d;
                }
                if (j == 0)
                    result += "\t" + num + ", ";
                else if (j != dim -1)
                    result += num + ", ";
                else
                    result += num;
            }
            if (i != dim - 1)
                result += "\n";
        }
        result += "\t]";
        return result;
    }
}
