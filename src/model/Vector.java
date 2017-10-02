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
            if (i != dim -1)
                result += "\t" + vector[i] + "\n";
            else
                result += "\t" + vector[i];
        }
        result += "\t]";
        System.out.println(result);

    }
}
