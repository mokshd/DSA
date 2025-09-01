package DSA.Stack;

import java.util.*;

public class Problem496_NextGreaterElement {

    public static void main(String[] args) {
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};

        int[] bruteForceRes = bruteForceApproach(nums1, nums2);
        System.out.print("Brute Force Result: ");
        for (int r : bruteForceRes) System.out.print(r + " ");
        System.out.println();

        int[] optimizedRes = optimizeApproach(nums1, nums2);
        System.out.print("Optimized Result: ");
        for (int r : optimizedRes) System.out.print(r + " ");
    }

    // ---------------- Brute Force ----------------
    // Idea: For each element in nums1, find it in nums2
    // then scan right to get the next greater element.
    // Time Complexity: O(n * m)   (n = nums1.length, m = nums2.length)
    // Space Complexity: O(1)
    public static int[] bruteForceApproach(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = -1; // default
            for (int j = 0; j < nums2.length; j++) {
                if (nums2[j] == nums1[i]) {
                    for (int k = j + 1; k < nums2.length; k++) {
                        if (nums2[k] > nums1[i]) {
                            res[i] = nums2[k];
                            break;
                        }
                    }
                    break; // stop searching after we find nums1[i]
                }
            }
        }
        return res;
    }

    // ---------------- Optimized Approach ----------------
    // Idea: Use monotonic decreasing stack on nums2
    // to map each number → its next greater element.
    // Then just look up for nums1.
    // Time Complexity: O(m + n)
    // Space Complexity: O(m) (map + stack)
    public static int[] optimizeApproach(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> grtEle = new HashMap<>();

        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                grtEle.put(stack.pop(), num);
            }
            stack.push(num);
        }

        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = grtEle.getOrDefault(nums1[i], -1);
        }
        return res;
    }
}
