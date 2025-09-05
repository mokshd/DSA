package DSA.BinarySearch;

public class Problem704_BinarySearch {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target1 = 9;
        int target2 = 2;

        System.out.println("Brute Force (target=9): " + bruteForce(nums, target1));  // 4
        System.out.println("Iterative Binary Search (target=9): " + iterativeBinarySearch(nums, target1)); // 4
        System.out.println("Recursive Binary Search (target=9): " + recursiveBinarySearch(nums, target1)); // 4
        System.out.println("Lower Bound Binary Search (target=9): " + lowerBoundBinarySearch(nums, target1)); // 4

        System.out.println("Brute Force (target=2): " + bruteForce(nums, target2));  // -1
        System.out.println("Iterative Binary Search (target=2): " + iterativeBinarySearch(nums, target2)); // -1
        System.out.println("Recursive Binary Search (target=2): " + recursiveBinarySearch(nums, target2)); // -1
        System.out.println("Lower Bound Binary Search (target=2): " + lowerBoundBinarySearch(nums, target2)); // -1
    }

    // ---------------- Brute Force ----------------
    // Check every element one by one
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private static int bruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) return i;
        }
        return -1;
    }

    // ---------------- Iterative Binary Search ----------------
    // Classic binary search using loop
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
    private static int iterativeBinarySearch(int[] nums, int target) {
        int start = 0, end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) start = mid + 1;
            else end = mid - 1;
        }

        return -1;
    }

    // ---------------- Recursive Binary Search ----------------
    // Divide array and search recursively
    // Time Complexity: O(log n)
    // Space Complexity: O(log n) → recursion stack
    private static int recursiveBinarySearch(int[] nums, int target) {
        return binarySearchHelper(nums, target, 0, nums.length - 1);
    }

    private static int binarySearchHelper(int[] nums, int target, int start, int end) {
        if (start > end) return -1;

        int mid = start + (end - start) / 2;

        if (nums[mid] == target) return mid;
        else if (nums[mid] < target) return binarySearchHelper(nums, target, mid + 1, end);
        else return binarySearchHelper(nums, target, start, mid - 1);
    }

    // ---------------- Lower Bound Style Binary Search ----------------
    // Shrinks end = mid instead of mid-1
    // Ensures start == end at the end, then check element
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
    private static int lowerBoundBinarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;  // shrink towards the target
            }
        }

        // Now start == end (single element left)
        return nums[start] == target ? start : -1;
    }
}
