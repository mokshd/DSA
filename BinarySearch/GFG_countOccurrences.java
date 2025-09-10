package DSA.BinarySearch;

class GFG_countOccurrences {
    public int countOccurrences(int[] nums, int target) {
        int first = findFirst(nums, target);
        if (first == -1) return 0; // not found
        int last = findLast(nums, target);
        return last - first + 1;
    }

    private int findFirst(int[] nums, int target) {
        int start = 0, end = nums.length - 1, ans = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                ans = mid;
                end = mid - 1; // move left to find earlier occurrence
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ans;
    }

    private int findLast(int[] nums, int target) {
        int start = 0, end = nums.length - 1, ans = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                ans = mid;
                start = mid + 1; // move right to find later occurrence
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ans;
    }

    // test
    public static void main(String[] args) {
        GFG_countOccurrences sol = new GFG_countOccurrences();
        int[] nums = {2, 4, 4, 4, 6, 8};
        int x = 4;
        System.out.println(sol.countOccurrences(nums, x)); // Output: 3
    }
}
