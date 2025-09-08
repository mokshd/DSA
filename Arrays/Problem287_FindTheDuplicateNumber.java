package DSA.Array;

import java.util.*;

public class Problem287_FindTheDuplicateNumber {

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 4, 2, 2};       // duplicate = 2
        int[] nums2 = {3, 1, 3, 4, 2};       // duplicate = 3

        System.out.println("Brute Force: " + bruteForce(nums1));
        System.out.println("Sorting: " + sortingApproach(nums1));
        System.out.println("HashSet: " + hashSetApproach(nums1));
        System.out.println("Binary Search: " + binarySearchApproach(nums1));
        System.out.println("Floyd’s Cycle Detection: " + floydCycleDetection(nums1));

        System.out.println();

        System.out.println("Floyd’s Cycle Detection (nums2): " + floydCycleDetection(nums2));
    }

    // ---------------- Brute Force ----------------
    // Check all pairs for duplicates
    // Time: O(n^2), Space: O(1)
    public static int bruteForce(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }
        return -1;
    }

    // ---------------- Sorting ----------------
    // Sort and check adjacent elements
    // Time: O(n log n), Space: O(1) or O(log n)
    public static int sortingApproach(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) return nums[i];
        }
        return -1;
    }

    // ---------------- HashSet ----------------
    // Store visited numbers
    // Time: O(n), Space: O(n)
    public static int hashSetApproach(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) return num;
            seen.add(num);
        }
        return -1;
    }

    // ---------------- Binary Search on Value Range ----------------
    // Count how many numbers ≤ mid. If count > mid → duplicate in [1..mid]
    // Time: O(n log n), Space: O(1)
    public static int binarySearchApproach(int[] nums) {
        int low = 1, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) count++;
            }
            if (count > mid) {
                high = mid;  // duplicate is in [low..mid]
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    // ---------------- Floyd’s Tortoise and Hare (Cycle Detection) ----------------
    // Think of nums as a linked list where index → next node = nums[index]
    // Duplicate creates a cycle → detect entry point of cycle
    // Time: O(n), Space: O(1)
    public static int floydCycleDetection(int[] nums) {
        // Phase 1: Find intersection point
        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // Phase 2: Find entrance to cycle (duplicate)
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
