package DSA.BinarySearch;

public class Problem81_SearchInRotatedSortedArrayII {

    public static void main(String[] args) {
        int[] nums1 = {2,5,6,0,0,1,2};
        int target1 = 0;
        int target2 = 3;

        System.out.println("Brute Force (target=0): " + bruteForce(nums1, target1)); // true
        System.out.println("Optimized (target=0): " + search(nums1, target1));       // true

        System.out.println("Brute Force (target=3): " + bruteForce(nums1, target2)); // false
        System.out.println("Optimized (target=3): " + search(nums1, target2));       // false

        int[] nums2 = {1,0,1,1,1};
        System.out.println("Optimized (nums2, target=0): " + search(nums2, 0));      // true
    }

    // ---------------- Brute Force ----------------
    // Check each element
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private static boolean bruteForce(int[] nums, int target) {
        for (int num : nums) {
            if (num == target) return true;
        }
        return false;
    }

    // ---------------- Optimized Binary Search ----------------
    // Same as Problem 33 but handle duplicates:
    // If nums[start] == nums[mid] == nums[end], we can’t decide → shrink search space
    // Time Complexity: O(log n), but O(n) in worst case (all duplicates)
    // Space Complexity: O(1)
    public static boolean search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) return true;

            // If duplicates make it ambiguous
            if (nums[start] == nums[mid] && nums[mid] == nums[end]) {
                start++;
                end--;
            }
            // Left half sorted
            else if (nums[start] <= nums[mid]) {
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            // Right half sorted
            else {
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return false;
    }
}
