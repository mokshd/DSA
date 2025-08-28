package DSA.Arrays.TwoPointer;

import java.util.Arrays;

public class Problem26_RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        int[] nums1 = {0,0,1,1,1,2,2,3,3,4};
        int[] nums2 = nums1.clone();

        int bruteForceResult = bruteForceApproach(nums1);
        System.out.println("\nBrute Force Result Length: " + bruteForceResult);
        System.out.println("Array after removing duplicates (Brute Force): "
                + Arrays.toString(Arrays.copyOf(nums1, bruteForceResult)));

        int optimizedResult = optimizeApproach(nums2);
        System.out.println("\nOptimized Result Length: " + optimizedResult);
        System.out.println("Array after removing duplicates (Optimized): "
                + Arrays.toString(Arrays.copyOf(nums2, optimizedResult)));
    }

    // ---------------- Optimized Approach (Two Pointers) ----------------
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private static int optimizeApproach(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int l = 0; // last unique index
        for (int r = 1; r < n; r++) {
            if (nums[l] != nums[r]) {
                nums[++l] = nums[r]; // place next unique element
            }
        }
        return l + 1; // number of unique elements
    }

    // ---------------- Brute Force ----------------
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    private static int bruteForceApproach(int[] nums) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            boolean isDuplicate = false;
            for (int j = 0; j < count; j++) {
                if (nums[i] == nums[j]) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                nums[count++] = nums[i]; // keep only first occurrence
            }
        }
        return count;
    }
}
