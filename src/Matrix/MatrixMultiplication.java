package Matrix;

public class MatrixMultiplication extends Thread {
    private int m = 20;
    private int n = 20;
    Matrix matrix1;
    Matrix matrix2;
    Matrix resultMatrix;
    int rowStart;
    int rowEnd;

    public MatrixMultiplication (Matrix _matrix1, Matrix _matrix2, Matrix _resultMatrix, int _rowStart, int _rowEnd) {
        matrix1 = _matrix1;
        matrix2 = _matrix2;
        rowStart = _rowStart;
        rowEnd = _rowEnd;
        resultMatrix = _resultMatrix;
    }

    public MatrixMultiplication (Matrix _matrix1, Matrix _matrix2, Matrix _resultMatrix, int _rowStart, int _rowEnd, int _m, int _n) {
        matrix1 = _matrix1;
        matrix2 = _matrix2;
        rowStart = _rowStart;
        rowEnd = _rowEnd;
        m = _m;
        n = _n;
        resultMatrix = _resultMatrix;
    }

    public int multiplyCell (int row, int col) {
        int result = 0;
        for (int i = 0; i < m; i++) {
            result += matrix1.getValue(row, i) * matrix2.getValue(i, col);
        }
        return result;
    }

    public void run() {
        // traverse resultMatrix
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                int result = 0;
                // traverse matrix 1 and matrix 2
                for (int i = rowStart; i < rowEnd; i++) {
                    for (int j = 0; j < n; j++) {
                        resultMatrix.setValue(i,j,multiplyCell(i, j));
                    }
                }
            }
        }
    }
}
