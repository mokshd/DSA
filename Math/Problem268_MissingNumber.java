package DSA.Math;

import java.util.*;

public class Problem268_MissingNumber {

    public static void main(String[] args) {
        int[] nums1 = {3, 0, 1};      // missing = 2
        int[] nums2 = {0, 1};         // missing = 2
        int[] nums3 = {9,6,4,2,3,5,7,0,1}; // missing = 8

        System.out.println("Brute Force: " + bruteForce(nums1));
        System.out.println("Sorting: " + sortingApproach(nums1));
        System.out.println("HashSet: " + hashSetApproach(nums1));
        System.out.println("Math Formula: " + mathApproach(nums1));
        System.out.println("XOR Trick: " + xorApproach(nums1));

        System.out.println();

        System.out.println("XOR Trick (nums2): " + xorApproach(nums2));
        System.out.println("XOR Trick (nums3): " + xorApproach(nums3));
    }

    // ---------------- Brute Force ----------------
    // For each number in range [0..n], check if exists in nums
    // Time: O(n^2), Space: O(1)
    public static int bruteForce(int[] nums) {
        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            boolean found = false;
            for (int num : nums) {
                if (num == i) {
                    found = true;
                    break;
                }
            }
            if (!found) return i;
        }
        return -1;
    }

    // ---------------- Sorting ----------------
    // Sort array and check index vs value mismatch
    // Time: O(n log n), Space: O(1) or O(log n) depending on sort
    public static int sortingApproach(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) return i;
        }
        return nums.length; // missing is n
    }

    // ---------------- HashSet ----------------
    // Store nums in set and check missing
    // Time: O(n), Space: O(n)
    public static int hashSetApproach(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        for (int i = 0; i <= n; i++) {
            if (!set.contains(i)) return i;
        }
        return -1;
    }

    // ---------------- Math Formula ----------------
    // Expected sum = n*(n+1)/2, subtract actual sum
    // Time: O(n), Space: O(1)
    public static int mathApproach(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        for (int num : nums) actualSum += num;
        return expectedSum - actualSum;
    }

    // ---------------- XOR Trick ----------------
    // XOR all numbers from [0..n] and nums[]
    // The missing number will remain
    // Time: O(n), Space: O(1)
    public static int xorApproach(int[] nums) {
        int n = nums.length;
        int xor = 0;
        for (int i = 0; i <= n; i++) xor ^= i;
        for (int num : nums) xor ^= num;
        return xor;
    }
}
