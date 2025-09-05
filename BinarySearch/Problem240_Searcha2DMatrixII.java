package DSA.BinarySearch;

public class Problem240_Searcha2DMatrixII {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target1 = 5;
        int target2 = 20;

        System.out.println("Brute Force (target=5): " + bruteForce(matrix, target1));       // true
        System.out.println("Row-wise Binary Search (target=5): " + rowWiseBinarySearch(matrix, target1)); // true
        System.out.println("Top-Right Search (target=5): " + topRightSearch(matrix, target1)); // true

        System.out.println("Brute Force (target=20): " + bruteForce(matrix, target2));       // false
        System.out.println("Row-wise Binary Search (target=20): " + rowWiseBinarySearch(matrix, target2)); // false
        System.out.println("Top-Right Search (target=20): " + topRightSearch(matrix, target2)); // false
    }

    // ---------------- Brute Force ----------------
    // Check every element in the matrix.
    // Time Complexity: O(n*m)
    // Space Complexity: O(1)
    private static boolean bruteForce(int[][] matrix, int target) {
        for (int[] row : matrix) {
            for (int num : row) {
                if (num == target) return true;
            }
        }
        return false;
    }

    // ---------------- Row-wise Binary Search ----------------
    // For each row, perform binary search.
    // Time Complexity: O(n * log m)
    // Space Complexity: O(1)
    private static boolean rowWiseBinarySearch(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        for (int i = 0; i < n; i++) {
            if (binarySearch(matrix[i], target)) return true;
        }
        return false;
    }

    private static boolean binarySearch(int[] arr, int target) {
        int start = 0, end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) return true;
            else if (arr[mid] < target) start = mid + 1;
            else end = mid - 1;
        }
        return false;
    }

    // ---------------- Optimized Top-Right Search ----------------
    // Start from top-right corner:
    //   - if current == target → found
    //   - if current < target → move down
    //   - if current > target → move left
    // Time Complexity: O(n + m)
    // Space Complexity: O(1)
    private static boolean topRightSearch(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        int row = 0;
        int col = m - 1;

        while (row < n && col >= 0) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] < target) row++;
            else col--;
        }

        return false;
    }
}
