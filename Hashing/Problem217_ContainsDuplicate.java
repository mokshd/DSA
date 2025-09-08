package DSA.Hashing;

import java.util.*;

public class Problem217_ContainsDuplicate {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 1};   // true
        int[] nums2 = {1, 2, 3, 4};   // false
        int[] nums3 = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2}; // true

        System.out.println("Brute Force: " + bruteForce(nums1));
        System.out.println("Sorting: " + sortingApproach(nums1));
        System.out.println("HashSet: " + hashSetApproach(nums1));

        System.out.println();

        System.out.println("HashSet (nums2): " + hashSetApproach(nums2));
        System.out.println("HashSet (nums3): " + hashSetApproach(nums3));
    }

    // ---------------- Brute Force ----------------
    // Compare each pair of elements
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    public static boolean bruteForce(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    // ---------------- Sorting ----------------
    // Sort array and check consecutive elements
    // Time Complexity: O(n log n)
    // Space Complexity: O(1) or O(log n) (depending on sort)
    public static boolean sortingApproach(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }

    // ---------------- HashSet ----------------
    // Store numbers in set; if already present → duplicate
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static boolean hashSetApproach(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }
}
