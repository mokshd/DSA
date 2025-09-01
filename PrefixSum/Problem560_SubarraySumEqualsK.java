package DSA.PrefixSum;

import java.util.*;

public class Problem560_SubarraySumEqualsK {

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1};
        int k1 = 2;
        System.out.println("Brute Force Result (nums=[1,1,1], k=2): " + bruteForceApproach(nums1, k1));   // 2
        System.out.println("Optimized Result (nums=[1,1,1], k=2): " + optimizedApproach(nums1, k1));      // 2

        int[] nums2 = {1, 2, 3};
        int k2 = 3;
        System.out.println("Brute Force Result (nums=[1,2,3], k=3): " + bruteForceApproach(nums2, k2));   // 2
        System.out.println("Optimized Result (nums=[1,2,3], k=3): " + optimizedApproach(nums2, k2));      // 2
    }

    // ---------------- Brute Force ----------------
    // Idea: For every subarray (i..j), compute sum and check if equals k.
    // Use nested loops → O(n^2).
    //
    // Time Complexity: O(n^2) (all subarrays checked)
    // Space Complexity: O(1)
    private static int bruteForceApproach(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // ---------------- Optimized Approach ----------------
    // Idea: Use Prefix Sum + HashMap.
    // preSum = sum of elements from 0..i
    // If preSum - k exists in map → found a subarray ending at i with sum = k.
    // Store frequency of prefix sums to handle multiple subarrays.
    //
    // Time Complexity: O(n) (each element processed once)
    // Space Complexity: O(n) (HashMap stores prefix sums)
    private static int optimizedApproach(int[] nums, int k) {
        int count = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        int preSum = 0;

        for (int num : nums) {
            preSum += num;

            // Case 1: subarray from 0..i has sum = k
            if (preSum == k) {
                count++;
            }

            // Case 2: subarray (j..i) has sum = k → preSum - k = preSum[j-1]
            if (freq.containsKey(preSum - k)) {
                count += freq.get(preSum - k);
            }

            // Update frequency of current prefix sum
            freq.put(preSum, freq.getOrDefault(preSum, 0) + 1);
        }

        return count;
    }
}
