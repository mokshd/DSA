package DSA.BinarySearch;

public class Problem33_SearchInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        int target2 = 3;

        System.out.println("Brute Force (target=0): " + bruteForce(nums1, target1));   // 4
        System.out.println("Optimized (target=0): " + search(nums1, target1));         // 4
        System.out.println("Pivot + Binary Search (target=0): " + pivotBinarySearch(nums1, target1)); // 4

        System.out.println("Brute Force (target=3): " + bruteForce(nums1, target2));   // -1
        System.out.println("Optimized (target=3): " + search(nums1, target2));         // -1
        System.out.println("Pivot + Binary Search (target=3): " + pivotBinarySearch(nums1, target2)); // -1
    }

    // ---------------- Brute Force ----------------
    // Just scan linearly
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private static int bruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) return i;
        }
        return -1;
    }

    // ---------------- Optimized Binary Search ----------------
    // Decide whether left or right half is sorted
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
    public static int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) return mid;

            // Check if left half is sorted
            if (nums[start] <= nums[mid]) {
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid - 1; // target lies in left half
                } else {
                    start = mid + 1; // target lies in right half
                }
            }
            // Otherwise, right half is sorted
            else {
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1; // target lies in right half
                } else {
                    end = mid - 1; // target lies in left half
                }
            }
        }
        return -1;
    }

    // ---------------- Pivot + Binary Search ----------------
    // Step 1: Find pivot (index of smallest element)
    // Step 2: Do normal binary search in the correct half
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
    public static int pivotBinarySearch(int[] nums, int target) {
        int n = nums.length;

        // Find pivot (smallest element index)
        int pivot = findPivot(nums);

        // If not rotated, just do normal binary search
        if (pivot == 0) return binarySearch(nums, target, 0, n - 1);

        // Decide in which half to search
        if (target >= nums[0]) {
            return binarySearch(nums, target, 0, pivot - 1);
        } else {
            return binarySearch(nums, target, pivot, n - 1);
        }
    }

    private static int findPivot(int[] nums) {
        int start = 0, end = nums.length - 1;

        // If already sorted
        if (nums[start] < nums[end]) return 0;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start; // pivot index
    }

    private static int binarySearch(int[] nums, int target, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) start = mid + 1;
            else end = mid - 1;
        }
        return -1;
    }
}
