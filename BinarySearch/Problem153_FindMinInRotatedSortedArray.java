package DSA.BinarySearch;

public class Problem153_FindMinInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums1 = {3,4,5,1,2};
        int[] nums2 = {4,5,6,7,0,1,2};
        int[] nums3 = {11,13,15,17};

        System.out.println("Brute Force Result (nums1): " + bruteForce(nums1));   // 1
        System.out.println("Optimized Result (nums1): " + findMin(nums1));       // 1

        System.out.println("Brute Force Result (nums2): " + bruteForce(nums2));   // 0
        System.out.println("Optimized Result (nums2): " + findMin(nums2));        // 0

        System.out.println("Brute Force Result (nums3): " + bruteForce(nums3));   // 11
        System.out.println("Optimized Result (nums3): " + findMin(nums3));        // 11
    }

    // ---------------- Brute Force ----------------
    // Scan entire array and pick min
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private static int bruteForce(int[] nums) {
        int min = nums[0];
        for (int num : nums) {
            if (num < min) min = num;
        }
        return min;
    }

    // ---------------- Optimized Binary Search ----------------
    // Idea: The minimum element is the "pivot"
    // If nums[mid] > nums[end], min lies in right half
    // Else, min lies in left half (including mid)
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
    public static int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;

        // If already sorted, first element is min
        if (nums[start] < nums[end]) return nums[start];

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] > nums[end]) {
                start = mid + 1; // min lies in right half
            } else {
                end = mid; // min lies in left half (including mid)
            }
        }

        // Start == End, pointing to min
        return nums[start];
    }
}
