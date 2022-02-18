package Matrix;

import java.util.Random;

public class Matrix {
    private int m = 20;
    private int n = 20;
    private int randMax = 10;
    private int[][] matrix;

    public Matrix () {
        matrix = new int[m][n];
    }
    public Matrix (int rows, int cols) {
        m = rows;
        n = cols;
        matrix = new int[m][n];
    }

    public void zeroMatrix() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public void populateMatrix() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Random rand = new Random();
                matrix[i][j] = rand.nextInt(randMax);
            }
        }
    }

    public void printMatrix() {
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == n-1) {
                    System.out.printf("%d\n", matrix[i][j]);
                } else {
                    System.out.printf("%d,", matrix[i][j]);
                }
            }
        }
        System.out.println("======================================================");
    }

    public int getValue(int i, int j) {return matrix[i][j];}

    public void setValue(int i, int j, int val) {matrix[i][j] = val;}
}
