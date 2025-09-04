package DSA.BinarySearch;

import java.util.*;

public class Problem74_Searcha2DMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target1 = 3;
        int target2 = 13;

        System.out.println("Brute Force Result (target=3): " + bruteForce(matrix, target1));       // true
        System.out.println("Better Approach Result (target=3): " + betterForce(matrix, target1)); // true
        System.out.println("Better + Binary Search Result (target=3): " + betterForce2(matrix, target1)); // true
        System.out.println("Optimized Binary Search Result (target=3): " + optimizedForce(matrix, target1)); // true

        System.out.println("Brute Force Result (target=13): " + bruteForce(matrix, target2));       // false
        System.out.println("Better Approach Result (target=13): " + betterForce(matrix, target2)); // false
        System.out.println("Better + Binary Search Result (target=13): " + betterForce2(matrix, target2)); // false
        System.out.println("Optimized Binary Search Result (target=13): " + optimizedForce(matrix, target2)); // false
    }

    // ---------------- Brute Force ----------------
    // Idea: Check every element in the matrix.
    // Time Complexity: O(m*n)
    // Space Complexity: O(1)
    private static boolean bruteForce(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == target) return true;
            }
        }
        return false;
    }

    // ---------------- Better Approach ----------------
    // Idea: Start from top-right corner:
    //   - If current > target → move left
    //   - If current < target → move down
    // Time Complexity: O(m + n)
    // Space Complexity: O(1)
    private static boolean betterForce(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int row = 0, col = cols - 1;

        while (row < rows && col >= 0) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] < target) row++;
            else col--;
        }
        return false;
    }

    // ---------------- Better + Binary Search ----------------
    // Idea:
    // 1. Identify the possible row where target could exist.
    // 2. Perform binary search on that row.
    // Time Complexity: O(m + log n)
    // Space Complexity: O(1)
    private static boolean betterForce2(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int row = 0;

        while (row < rows && matrix[row][cols - 1] < target) row++;
        if (row >= rows) return false;

        return binarySearch(matrix[row], 0, cols - 1, target);
    }

    private static boolean binarySearch(int[] arr, int start, int end, int target) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) return true;
            else if (arr[mid] < target) start = mid + 1;
            else end = mid - 1;
        }
        return false;
    }

    // ---------------- Fully Optimized Binary Search ----------------
    // Idea: Treat 2D matrix as a 1D sorted array for binary search.
    // Time Complexity: O(log(m*n))
    // Space Complexity: O(1)
    private static boolean optimizedForce(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int start = 0, end = rows * cols - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int value = matrix[mid / cols][mid % cols];

            if (value == target) return true;
            else if (value < target) start = mid + 1;
            else end = mid - 1;
        }

        return false;
    }
}
