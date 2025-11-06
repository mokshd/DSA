package DSA.Arrays;

import java.util.*;

public class Problem56_MergeIntervals {

    public static void main(String[] args) {
        // Example input: Intervals
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] intervals2 = {{1, 4}, {4, 5}};

        // Merge intervals using different approaches
        System.out.println("Brute Force: " + bruteForce(intervals1));
        System.out.println("Optimized Approach: " + optimizedApproach(intervals1));
        System.out.println("Optimized Approach: " + optimizedApproach(intervals2));
    }

    // ---------------- Brute Force ----------------
    // Try all pair combinations and merge intervals
    // Time: O(n^2), Space: O(1)
    public static List<int[]> bruteForce(int[][] intervals) {
        List<int[]> result = new ArrayList<>(Arrays.asList(intervals));
        for (int i = 0; i < intervals.length; i++) {
            boolean merged = false;
            for (int j = 0; j < result.size(); j++) {
                int[] curr = result.get(j);
                if (intervals[i][0] <= curr[1] && intervals[i][1] >= curr[0]) { // Check overlap
                    curr[0] = Math.min(curr[0], intervals[i][0]);
                    curr[1] = Math.max(curr[1], intervals[i][1]);
                    merged = true;
                    break;
                }
            }
            if (!merged) result.add(intervals[i]);
        }
        return result;
    }



    // ---------------- Optimized Approach ----------------
    // Space optimized: Just a single list of merged intervals
    // Time: O(n log n), Space: O(n)
    public static List<int[]> optimizedApproach(int[][] intervals) {
        if (intervals.length == 0) return new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> result = new ArrayList<>();
        int[] current = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (current[1] >= intervals[i][0]) { // Overlapping
                current[1] = Math.max(current[1], intervals[i][1]);
            } else {
                result.add(current);
                current = intervals[i];
            }
        }
        result.add(current); // Add the last interval
        return result;
    }

}
