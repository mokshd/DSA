package DSA.Arrays;

import java.util.*;

public class Problem57_InsertInterval {

    public static void main(String[] args) {
        int[][] intervals1 = {{1,3},{6,9}};
        int[] newInterval1 = {2,5};

        int[][] intervals2 = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval2 = {4,8};

        System.out.println("Brute Force (case1): " + Arrays.deepToString(bruteForce(intervals1, newInterval1)));
        System.out.println("Optimized (case1): " + Arrays.deepToString(insert(intervals1, newInterval1)));

        System.out.println("Brute Force (case2): " + Arrays.deepToString(bruteForce(intervals2, newInterval2)));
        System.out.println("Optimized (case2): " + Arrays.deepToString(insert(intervals2, newInterval2)));
    }

    // ---------------- Brute Force ----------------
    // Add new interval → then use merge intervals (like Problem56)
    // Time Complexity: O(n log n) (due to sorting + merge)
    // Space Complexity: O(n)
    private static int[][] bruteForce(int[][] intervals, int[] newInterval) {
        int[][] newArr = new int[intervals.length + 1][2];
        for (int i = 0; i < intervals.length; i++) {
            newArr[i] = intervals[i];
        }
        newArr[intervals.length] = newInterval;

        // Reuse merge logic from Problem56
        Arrays.sort(newArr, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();
        int[] current = newArr[0];
        merged.add(current);

        for (int[] interval : newArr) {
            if (interval[0] <= current[1]) {
                current[1] = Math.max(current[1], interval[1]);
            } else {
                current = interval;
                merged.add(current);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    // ---------------- Optimized ----------------
    // Since input is already sorted & non-overlapping:
    // 1. Add all intervals ending before newInterval starts.
    // 2. Merge all overlapping intervals with newInterval.
    // 3. Add remaining intervals.
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0, n = intervals.length;

        // Step 1: Add all intervals before newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Step 2: Merge overlaps
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // Step 3: Add remaining intervals
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }
}
