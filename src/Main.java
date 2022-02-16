import Matrix.*;
import java.util.concurrent.*;
import java.time.Instant;
import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        int m = 20;
        int n = 20;
        int numThreads = 5;

        Matrix matrix1 = new Matrix();
        matrix1.populateMatrix();
        System.out.println("Matrix 1");
        matrix1.printMatrix();

        Matrix matrix2 = new Matrix();
        matrix2.populateMatrix();
        System.out.println("Matrix 2");
        matrix2.printMatrix();

        Matrix resultMatrix1 = new Matrix();
        resultMatrix1.zeroMatrix();
        Matrix resultMatrix2 = new Matrix();
        resultMatrix2.zeroMatrix();

        // threaded workflow
        System.out.println("----Threaded multiplication----");
        System.out.println("Multiplying matrices...");
        Instant startThreaded = Instant.now();
        Thread threads[] = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            int rowStart = n/numThreads*i;
            int rowEnd = n/numThreads*(i+1);
            threads[i] = new Thread(new MatrixMultiplication(matrix1, matrix2, resultMatrix1, rowStart, rowEnd));
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Instant endThreaded = Instant.now();
        long timeElapsedThreaded = Duration.between(startThreaded, endThreaded).toMillis();
        System.out.println("Task finished executing! Printing resulting matrix...");
        System.out.println("Result matrix");
        resultMatrix1.printMatrix();
        System.out.printf("Threaded took %d ms to execute\n\n", timeElapsedThreaded);

        // unthreaded comparison
        System.out.println("----Unthreaded multiplication----");
        System.out.println("Multiplying matrices...");
        Instant startNoThread = Instant.now();
        MatrixMultiplication noThread = new MatrixMultiplication(matrix1, matrix2, resultMatrix2, 0, n);
        noThread.run();
        Instant endNoThread = Instant.now();
        long timeElapsedNoThread = Duration.between(startNoThread, endNoThread).toMillis();
        System.out.println("Task finished executing! Printing resulting matrix...");
        System.out.println("Result matrix");
        resultMatrix2.printMatrix();
        System.out.printf("Unthreaded took %d ms to execute\n", timeElapsedNoThread);

    }
}
