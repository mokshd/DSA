package DSA.Arrays;

import java.util.*;

public class Problem18_4Sum {
    // ---------------- Brute Force ----------------
    // Time Complexity: O(n^4)
    // Space Complexity: O(k) where k = number of unique quadruplets
    private static List<List<Integer>> bruteForceApproach(int[] nums, int target) {
        int n = nums.length;
        Set<List<Integer>> finalList = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        if (nums[i] + nums[j] + nums[k] + nums[l] == target) {
                            List<Integer> subList = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                            Collections.sort(subList); // ensure uniqueness
                            finalList.add(subList);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(finalList);
    }

    // ---------------- Optimized Approach (Two Pointers) ----------------
    // Time Complexity: O(n^3)
    // Space Complexity: O(1) (excluding result list)
    public static List<List<Integer>> optimizeApproach(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // skip duplicates for i
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // skip duplicates for j

                int l = j + 1;
                int r = n - 1;

                while (l < r) {
                    long sum = (long) nums[i] + nums[j] + nums[l] + nums[r];

                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));

                        while (l < r && nums[l] == nums[l + 1]) l++;
                        while (l < r && nums[r] == nums[r - 1]) r--;

                        l++;
                        r--;
                    } else if (sum < target) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }
        return res;
    }

    // ---------------- Driver Code ----------------
    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;

        System.out.println("Input: " + Arrays.toString(nums) + ", Target: " + target);

        List<List<Integer>> bruteForceResult = bruteForceApproach(nums, target);
        System.out.println("Brute Force Result: " + bruteForceResult);

        List<List<Integer>> optimizedResult = optimizeApproach(nums, target);
        System.out.println("Optimized Result: " + optimizedResult);
    }
}
