package model;

import java.util.Vector;

/**
 * File: Result.java
 *
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class Result {
    private Vector<Double> resultingVector;
    private long timeInMilli;

    /**
     * TODO
     * @param resultingVector
     * @param timeInMilli
     */
    public Result(Vector<Double> resultingVector, long timeInMilli){
        this.resultingVector = resultingVector;
        this.timeInMilli = timeInMilli;
    }

    /**
     * TODO
     * @return
     */
    public Vector<?> getResultingVector() {
        return resultingVector;
    }

    /**
     * TODO
     * @return
     */
    public long getTimeInMilli() {
        return timeInMilli;
    }
}
