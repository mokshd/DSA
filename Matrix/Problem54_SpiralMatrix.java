package DSA.Matrix;

import java.util.*;

public class Problem54_SpiralMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("Brute Force: " + spiralOrderBrute(matrix));
        System.out.println("Optimized: " + spiralOrder(matrix));
    }

    // ---------------- Brute Force ----------------
    // Use directions + visited boolean[][] array
    // Time Complexity: O(m * n)
    // Space Complexity: O(m * n)
    public static List<Integer> spiralOrderBrute(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}}; // right, down, left, up
        int dir = 0; // start going right
        int row = 0, col = 0;

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < m * n; i++) {
            result.add(matrix[row][col]);
            visited[row][col] = true;

            int newRow = row + directions[dir][0];
            int newCol = col + directions[dir][1];

            // check bounds
            if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n || visited[newRow][newCol]) {
                dir = (dir + 1) % 4; // change direction
                newRow = row + directions[dir][0];
                newCol = col + directions[dir][1];
            }
            row = newRow;
            col = newCol;
        }
        return result;
    }

    // ---------------- Optimized Layer-by-Layer ----------------
    // Traverse top → right → bottom → left, then shrink boundaries
    // Time Complexity: O(m * n)
    // Space Complexity: O(1) (only output list)
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        if (matrix == null || matrix.length == 0) return result;

        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // Traverse top row
            for (int j = left; j <= right; j++) {
                result.add(matrix[top][j]);
            }
            top++;

            // Traverse right column
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            // Traverse bottom row (if still valid)
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    result.add(matrix[bottom][j]);
                }
                bottom--;
            }

            // Traverse left column (if still valid)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }

        return result;
    }
}
