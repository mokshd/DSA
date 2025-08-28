package DSA.Arrays.TwoPointer;

import java.util.*;

// Problem 15: https://leetcode.com/problems/3sum/description/

public class Problem15_3Sum {

    // ---------------- Brute Force ----------------
    // Time Complexity: O(n^3)
    // Space Complexity: O(k) where k = number of unique triplets
    private static List<List<Integer>> bruteForceApproach(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> finalSet = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], nums[k]);
                        Collections.sort(triplet); // sort to handle duplicates
                        finalSet.add(triplet);      // Set removes duplicates
                    }
                }
            }
        }
        return new ArrayList<>(finalSet);
    }

    // ---------------- Optimized Approach (Two Pointers) ----------------
    // Time Complexity: O(n^2)
    // Space Complexity: O(1) (excluding result list)
    public static List<List<Integer>> optimizeApproach(int[] nums) {
        Arrays.sort(nums); // sort first
        int n = nums.length;
        List<List<Integer>> finalRes = new ArrayList<>();

        for (int i = 0; i < n - 2; i++) { // no need to go till n, last 2 are covered
            if (i > 0 && nums[i] == nums[i - 1]) continue; // skip duplicates for i

            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];

                if (sum == 0) {
                    finalRes.add(Arrays.asList(nums[i], nums[l], nums[r]));

                    // skip duplicates for left and right
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;

                    l++;
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return finalRes;
    }

    // ---------------- Driver Code ----------------
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};

        System.out.println("Input: " + Arrays.toString(nums));

        List<List<Integer>> bruteForceResult = bruteForceApproach(nums);
        System.out.println("Brute Force Result: " + bruteForceResult);

        List<List<Integer>> optimizedResult = optimizeApproach(nums);
        System.out.println("Optimized Result: " + optimizedResult);
    }
}
