package DSA.Stack;

import java.util.Stack;

public class Problem739_DailyTemperatures {

    public static void main(String[] args) {
        int[] temps = {73,74,75,71,69,72,76,73};

        int[] bruteForceRes = bruteForceApproach(temps);
        System.out.print("Brute Force Result: ");
        for (int r : bruteForceRes) System.out.print(r + " ");
        System.out.println();

        int[] optimizedRes = optimizeApproach(temps);
        System.out.print("Optimized Result: ");
        for (int r : optimizedRes) System.out.print(r + " ");
    }

    // ---------------- Brute Force ----------------
    // Idea: For each day, look ahead until we find a warmer day.
    // Time Complexity: O(n^2) (nested loop)
    // Space Complexity: O(1)
    public static int[] bruteForceApproach(int[] t) {
        int n = t.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (t[j] > t[i]) {
                    res[i] = j - i;
                    break; // stop when first warmer day found
                }
            }
        }
        return res;
    }

    // ---------------- Optimized Approach ----------------
    // Idea: Use a stack to store indices of decreasing temperatures.
    // When we find a warmer day, resolve for all earlier colder days.
    // Time Complexity: O(n) (each index pushed/popped at most once)
    // Space Complexity: O(n) (stack for indices)
    public static int[] optimizeApproach(int[] t) {
        int n = t.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>(); // stores indices

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && t[stack.peek()] < t[i]) {
                int prevIndex = stack.pop();
                res[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return res;
    }
}
