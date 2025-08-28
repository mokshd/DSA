package DSA.Arrays.TwoPointer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Problem 283: https://leetcode.com/problems/move-zeroes/
//https://www.geeksforgeeks.org/dsa/segregate-0s-and-1s-in-an-array-by-traversing-array-once/
// brute force sorting

public class Problem283_MoveZeros {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};

        System.out.println("Input: " + Arrays.toString(nums));

        int[] bruteForceResult = bruteForceApproach(nums.clone());
        System.out.println("Brute Force Result: " + Arrays.toString(bruteForceResult));

        int[] optimizedResult1 = optimizeApproachShift(nums.clone());
        System.out.println("Optimized Result (Shift Method): " + Arrays.toString(optimizedResult1));

        int[] optimizedResult2 = optimizeApproachSwap(nums.clone());
        System.out.println("Optimized Result (Swap Method): " + Arrays.toString(optimizedResult2));

        int[] optimizedResult3 = optimizeApproachZeroCount(nums.clone());
        System.out.println("Optimized Result (Zero Count Method): " + Arrays.toString(optimizedResult3));
    }

    // ---------------- Brute Force ----------------
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    private static int[] bruteForceApproach(int[] nums) {
        List<Integer> nonZeros = Arrays.stream(nums)
                .filter(x -> x != 0)
                .boxed()
                .collect(Collectors.toList());

        long count = Arrays.stream(nums).filter(x -> x == 0).count();
        for (int i = 0; i < count; i++) {
            nonZeros.add(0);
        }
        return nonZeros.stream().mapToInt(Integer::intValue).toArray();
    }

    // ---------------- Optimized Approach 1 (Shift Method) ----------------
    // Move non-zeros forward, then fill remaining with zeros
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private static int[] optimizeApproachShift(int[] nums) {
        int pos = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[pos++] = num;
            }
        }
        while (pos < nums.length) {
            nums[pos++] = 0;
        }
        return nums;
    }

    // ---------------- Optimized Approach 2 (Swap Method) ----------------
    // Swap current non-zero with position "pos"
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private static int[] optimizeApproachSwap(int[] nums) {
        int pos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[pos];
                nums[pos] = temp;
                pos++;
            }
        }
        return nums;
    }

    // ---------------- Optimized Approach 3 (Zero Count Method) ----------------
    // Count zeros and shift in one pass
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private static int[] optimizeApproachZeroCount(int[] nums) {
        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            } else {
                nums[i - zeroCount] = nums[i];
                if (zeroCount > 0) {
                    nums[i] = 0;
                }
            }
        }
        return nums;
    }
}
