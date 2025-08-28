package DSA.Arrays.SlidingWindow;

// Problem 121: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

public class Problem121_BestTimetoBuyandSellStock {
    public static void main(String[] args) {
        int[] nums = {7, 1, 5, 3, 6, 4};

        int bruteForceResult = bruteForceApproach(nums);
        System.out.println("Brute Force Result: " + bruteForceResult);

        int optimizedResult = optimizeApproach(nums);
        System.out.println("Optimized Result: " + optimizedResult);
    }

    // ---------------- Brute Force ----------------
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    private static int bruteForceApproach(int[] nums) {
        int n = nums.length;
        int maxProfit = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j] > nums[i]) {
                    maxProfit = Math.max(maxProfit, nums[j] - nums[i]);
                }
            }
        }
        return maxProfit;
    }

    // ---------------- Optimized Approach (Single Pass) ----------------
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private static int optimizeApproach(int[] nums) {
        int minPrice = nums[0];
        int maxProfit = 0;

        for (int i = 1; i < nums.length; i++) {
            // update minimum price so far
            if (nums[i] < minPrice) {
                minPrice = nums[i];
            }
            // check profit if selling at nums[i]
            else {
                maxProfit = Math.max(maxProfit, nums[i] - minPrice);
            }
        }
        return maxProfit;
    }
}
