package DSA.BinarySearch;

public class Problem540_SingleElementInSortedArray {

    public static void main(String[] args) {
        int[] nums1 = {1,1,2,3,3,4,4,8,8};
        int[] nums2 = {3,3,7,7,10,11,11};

        System.out.println("Brute Force (nums1): " + bruteForce(nums1));  // 2
        System.out.println("XOR Trick (nums1): " + xorMethod(nums1));     // 2
        System.out.println("Binary Search (nums1): " + singleNonDuplicate(nums1)); // 2

        System.out.println("Brute Force (nums2): " + bruteForce(nums2));  // 10
        System.out.println("XOR Trick (nums2): " + xorMethod(nums2));     // 10
        System.out.println("Binary Search (nums2): " + singleNonDuplicate(nums2)); // 10
    }

    // ---------------- Brute Force ----------------
    // Time: O(n), Space: O(1)
    private static int bruteForce(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];

        for (int i = 0; i < n; i += 2) {
            if (i == n - 1 || nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    // ---------------- XOR Method ----------------
    // Time: O(n), Space: O(1)
    private static int xorMethod(int[] nums) {
        int xor = 0;
        for (int num : nums) xor ^= num;
        return xor;
    }

    // ---------------- Optimized Binary Search ----------------
    // Time: O(log n), Space: O(1)
    public static int singleNonDuplicate(int[] nums) {
        int start = 0, end = nums.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            // Ensure mid is even (check pairs properly)
            if (mid % 2 == 1) mid--;

            if (nums[mid] == nums[mid + 1]) {
                // single element is on the right side
                start = mid + 2;
            } else {
                // single element is on the left side (including mid)
                end = mid;
            }
        }

        return nums[start];
    }
}
