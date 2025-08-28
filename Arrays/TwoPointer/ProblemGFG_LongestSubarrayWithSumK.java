package DSA.Arrays.TwoPointer;

import java.util.HashMap;

public class ProblemGFG_LongestSubarrayWithSumK {

    public static void main(String[] args) {
        int[] nums = {10, 5, 2, 7, 1, -10};
        int target = 15;

        int bruteForceResult = bruteForceApproach(nums, target);
        System.out.println("Brute Force Result: " + bruteForceResult);

        int optimizedResult = optimizeApproach(nums, target);
        System.out.println("Optimized Result: " + optimizedResult);
    }

    // ---------------- Optimized Approach (Prefix Sum + HashMap) ----------------
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    private static int optimizeApproach(int[] nums, int target) {
        int n = nums.length;
        HashMap<Integer, Integer> preSum = new HashMap<>();
        int maxLen = 0;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i];

            // Case 1: subarray from 0..i has sum == target
            if (sum == target) {
                maxLen = i + 1;
            }

            // Case 2: check if (sum - target) was seen before
            int diff = sum - target;
            if (preSum.containsKey(diff)) {
                maxLen = Math.max(maxLen, i - preSum.get(diff));
            }

            // Only store first occurrence of prefix sum
            preSum.putIfAbsent(sum, i);
        }
        return maxLen;
    }

    // ---------------- Brute Force ----------------
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    private static int bruteForceApproach(int[] nums, int target) {
        int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == target) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }
}
