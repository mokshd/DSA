package DSA.Matrix;

import java.util.*;

public class Problem59_SpiralMatrixII {

    public static void main(String[] args) {
        int n = 3;
        System.out.println("Brute Force:");
        printMatrix(generateMatrixBrute(n));

        System.out.println("Optimized:");
        printMatrix(generateMatrix(n));
    }

    // ---------------- Brute Force ----------------
    // Use directions and visited[][] to simulate spiral
    // Time Complexity: O(n^2)
    // Space Complexity: O(n^2) (matrix + visited array)
    public static int[][] generateMatrixBrute(int n) {
        int[][] matrix = new int[n][n];
        boolean[][] visited = new boolean[n][n];

        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}}; // right, down, left, up
        int dir = 0; // start moving right
        int row = 0, col = 0;

        for (int num = 1; num <= n * n; num++) {
            matrix[row][col] = num;
            visited[row][col] = true;

            int newRow = row + directions[dir][0];
            int newCol = col + directions[dir][1];

            // If out of bounds or already visited -> turn direction
            if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= n || visited[newRow][newCol]) {
                dir = (dir + 1) % 4; // change direction
                newRow = row + directions[dir][0];
                newCol = col + directions[dir][1];
            }
            row = newRow;
            col = newCol;
        }

        return matrix;
    }

    // ---------------- Optimized Layer-by-Layer ----------------
    // Fill numbers in spiral using 4 boundaries
    // Time Complexity: O(n^2)
    // Space Complexity: O(1) (only result matrix)
    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int top = 0, bottom = n - 1;
        int left = 0, right = n - 1;
        int num = 1;

        while (top <= bottom && left <= right) {
            // Fill top row
            for (int j = left; j <= right; j++) {
                matrix[top][j] = num++;
            }
            top++;

            // Fill right column
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = num++;
            }
            right--;

            // Fill bottom row (if still valid)
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    matrix[bottom][j] = num++;
                }
                bottom--;
            }

            // Fill left column (if still valid)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    matrix[i][left] = num++;
                }
                left++;
            }
        }

        return matrix;
    }

    // Utility function to print matrix
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}
