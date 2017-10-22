package parallel;

/**
 * File: SubtractRowOperation.java
 *
 * @author Brandon Bires-Navel (brandonnavel@outlook.com)
 */
public class SubtractRowOperation implements Runnable {
    private double[][] matrix;
    private double[] vector;
    private int row, refRow;
    private double multiplier;

    public SubtractRowOperation(double[][] matrix, double[] vector, int row, double multiplier, int refRow) {
        this.matrix = matrix;
        this.vector = vector;
        this.row = row;
        this.multiplier = multiplier;
        this.refRow = refRow;
    }

    @Override
    public void run() {
        int len = this.matrix.length;
        for (int i = 0; i < len; i++) {
            this.matrix[row][i] -= this.multiplier * this.matrix[this.refRow][i];
        }
        this.vector[row] -= this.vector[this.refRow] * multiplier;
    }
}
