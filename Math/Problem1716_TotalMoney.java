package DSA.Math;

public class Problem1716_TotalMoney {

    public static void main(String[] args) {
        // Example test cases
        System.out.println("Example 1: " + totalMoney(4));   // Output: 10
        System.out.println("Example 2: " + totalMoney(10));  // Output: 37
        System.out.println("Example 3: " + totalMoney(20));  // Output: 96
    }

    // ---------------- Optimized Approach ----------------
    // Each full week increases the base by +1 (Monday increases each week)
    // Pattern: Week 1 sum = 28, Week 2 sum = 35, Week 3 sum = 42, ...
    // Time: O(1) for arithmetic part + O(7) for remainder part
    // Space: O(1)
    public static int totalMoney(int n) {
        int fullWeeks = n / 7;
        int baseWeekSum = 28; // Sum of first week (1+2+...+7)
        int lastWeekSum = baseWeekSum + (fullWeeks - 1) * 7; // Sum of the last complete week
        int result = fullWeeks * (baseWeekSum + lastWeekSum) / 2; // Sum of arithmetic series

        // Remaining days (not forming a full week)
        int remainingDays = n % 7;
        if (remainingDays != 0) {
            int start = fullWeeks + 1;
            for (int i = 0; i < remainingDays; i++) {
                result += start;
                start++;
            }
        }
        return result;
    }

    // ---------------- Brute Force Approach ----------------
    // Simulate the process day by day
    // Time: O(n), Space: O(1)
    public static int bruteForce(int n) {
        int total = 0;
        int weekStart = 1;
        for (int i = 1; i <= n; i++) {
            total += weekStart + (i - 1) % 7;
            if (i % 7 == 0) weekStart++;
        }
        return total;
    }
}
