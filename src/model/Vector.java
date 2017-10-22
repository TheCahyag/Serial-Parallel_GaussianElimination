package model;

import java.util.Random;

/**
 * File: Vector.java
 *
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class Vector {

    /**
     * TODO
     * @param dim
     * @param lowBound
     * @param highBound
     * @return
     */
    public static double[] randomVector(int dim, double lowBound, double highBound){
        double[] vector = new double[dim];
        Random random = new Random();
        for (int i = 0; i < dim; i++) {
            double randomDouble = lowBound + (highBound - lowBound) * random.nextDouble();
            if (Double.valueOf(randomDouble).isInfinite()){
                // Ensure an infinite number hasn't bee generated, if so use 1 as a default
                randomDouble = 1;
            }
            vector[i] = randomDouble;
        }
        return vector;
    }

    /**
     * TODO
     * @param dim
     * @return
     */
    public static double[] randomVector(int dim){
        return randomVector(dim, Matrix.DEFAULT_LOW, Matrix.DEFAULT_HIGH);
    }

    public static void printVector(double[] vector){
        String result = "[";
        int dim = vector.length;
        for (int i = 0; i < dim; i++) {
            Double num = vector[i];
            if (num.isInfinite() || num.isNaN()){
                num = 0d;
            }
            if (i != dim -1)
                result += "\t" + num + "\n";
            else
                result += "\t" + num;
        }
        result += "\t]";
        System.out.println(result);

    }

    public static double[] getDeepCopy(double[] vector){
        int dim = vector.length;
        double[] newVector = new double[dim];
        for (int i = 0; i < dim; i++) {
            newVector[i] = vector[i];

        }
        return newVector;
    }

    public static String toString(double[] vector){
        String result = "[";
        int dim = vector.length;
        for (int i = 0; i < dim; i++) {
            Double num = vector[i];
            if (num.isInfinite() || num.isNaN()){
                num = 0d;
            }
            if (i != dim -1)
                result += "\t" + num + "\n";
            else
                result += "\t" + num;
        }
        result += "\t]";
        return result;
    }
}
