package model;

/**
 * File: Result.java
 *
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class Result {
    private double[][] startingMatrix;
    private double[][] resultingMatrix;
    private double[] startingVector;
    private double[] resultingVector;
    private long timeInMilli;

    public double[][] getStartingMatrix() {
        return startingMatrix;
    }

    public void setStartingMatrix(double[][] startingMatrix) {
        this.startingMatrix = startingMatrix;
    }

    public double[][] getResultingMatrix() {
        return resultingMatrix;
    }

    public void setResultingMatrix(double[][] resultingMatrix) {
        this.resultingMatrix = resultingMatrix;
    }

    public double[] getStartingVector() {
        return startingVector;
    }

    public void setStartingVector(double[] startingVector) {
        this.startingVector = startingVector;
    }

    public double[] getResultingVector() {
        return resultingVector;
    }

    public void setResultingVector(double[] resultingVector) {
        this.resultingVector = resultingVector;
    }

    public long getTimeInMilli() {
        return timeInMilli;
    }

    public void setTimeInMilli(long timeInMilli) {
        this.timeInMilli = timeInMilli;
    }

    public void print(){
        System.out.println("----- START MATRIX -----");
        if (startingMatrix[0].length > 48){
            System.out.println("TOO BIG TO PRINT");
        } else {
            Matrix.printMatrix(startingMatrix);
        }
        System.out.println("----- RESULT MATRIX -----");
        if (resultingMatrix[0].length > 48){
            System.out.println("TOO BIG TO PRINT");
        } else {
            Matrix.printMatrix(resultingMatrix);
        }
        System.out.println("----- START VECTOR -----");
        if (startingVector.length > 48){
            System.out.println("TOO BIG TO PRINT");
        } else {
            Vector.printVector(startingVector);
        }
        System.out.println("----- RESULT VECTOR -----");
        if (resultingVector.length > 48){
            System.out.println("TOO BIG TO PRINT");
        } else {
            Vector.printVector(resultingVector);
        }
        System.out.println("----- EXEC TIME (ms) -----");
        System.out.println(timeInMilli);
    }
}
