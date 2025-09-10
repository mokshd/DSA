package DSA.BinarySearch;

import java.util.*;

public class Problem34_FindFirstAndLastPosition {

    public static void main(String[] args) {
        int[] nums1 = {5,7,7,8,8,10};
        int[] nums2 = {5,7,7,8,8,10};
        int[] nums3 = {};

        System.out.println("Brute Force (case1): " + Arrays.toString(bruteForce(nums1, 8)));  // [3,4]
        System.out.println("Optimized (case1): " + Arrays.toString(searchRange(nums1, 8)));   // [3,4]

        System.out.println("Brute Force (case2): " + Arrays.toString(bruteForce(nums2, 6)));  // [-1,-1]
        System.out.println("Optimized (case2): " + Arrays.toString(searchRange(nums2, 6)));   // [-1,-1]

        System.out.println("Brute Force (case3): " + Arrays.toString(bruteForce(nums3, 0)));  // [-1,-1]
        System.out.println("Optimized (case3): " + Arrays.toString(searchRange(nums3, 0)));   // [-1,-1]
    }

    // ---------------- Brute Force ----------------
    // Time: O(n), Space: O(1)
    private static int[] bruteForce(int[] nums, int target) {
        int first = -1, last = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (first == -1) first = i;
                last = i;
            }
        }
        return new int[]{first, last};
    }

    // ---------------- Optimized Binary Search ----------------
    // Time: O(log n), Space: O(1)
    public static int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums.length == 0) return res;

        res[0] = findFirst(nums, target);
        if (res[0] == -1) return res; // target not found
        res[1] = findLast(nums, target);

        return res;
    }

    private static int findFirst(int[] nums, int target) {
        int start = 0, end = nums.length - 1, ans = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                ans = mid;
                end = mid - 1; // move left for first occurrence
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ans;
    }

    private static int findLast(int[] nums, int target) {
        int start = 0, end = nums.length - 1, ans = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                ans = mid;
                start = mid + 1; // move right for last occurrence
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ans;
    }
}
