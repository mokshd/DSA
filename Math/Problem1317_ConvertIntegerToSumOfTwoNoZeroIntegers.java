package DSA.Math;

import java.util.Arrays;

public class Problem1317_ConvertIntegerToSumOfTwoNoZeroIntegers {

    public static void main(String[] args) {
        int n1 = 11;
        System.out.println("Brute Force Result (n=11): " + Arrays.toString(getNoZeroIntegersBrute(n1)));      // [2, 9]
        System.out.println("Better Result (n=11): " + Arrays.toString(getNoZeroIntegersBetter(n1)));          // [2, 9]

        int n2 = 1010;
        System.out.println("Brute Force Result (n=1010): " + Arrays.toString(getNoZeroIntegersBrute(n2)));    // [11, 999]
        System.out.println("Better Result (n=1010): " + Arrays.toString(getNoZeroIntegersBetter(n2)));        // [11, 999]

        int n3 = 69;
        System.out.println("Brute Force Result (n=69): " + Arrays.toString(getNoZeroIntegersBrute(n3)));      // [1, 68]
        System.out.println("Better Result (n=69): " + Arrays.toString(getNoZeroIntegersBetter(n3)));          // [1, 68]

    }

    // ---------------- Brute Force ----------------
    // Try all pairs (a, b), check if they sum to n and both contain no zero.
    //
    // Time Complexity: O(n^2 * log n)
    // Space Complexity: O(1)
    public static int[] getNoZeroIntegersBrute(int n) {
        for (int a = 1; a < n; a++) {
            for (int b = 1; b < n; b++) {
                if (a + b == n && !String.valueOf(a).contains("0") && !String.valueOf(b).contains("0")) {
                    return new int[]{a, b};
                }
            }
        }
        return new int[]{-1, -1};
    }

    // ---------------- Better Approach ----------------
    // Fix a, compute b = n - a, then check validity.
    //
    // Time Complexity: O(n * log n)
    // Space Complexity: O(1)
    public static int[] getNoZeroIntegersBetter(int n) {
        for (int a = 1; a < n; a++) {
            int b = n - a;
            if(!String.valueOf(a).contains("0") && !String.valueOf(b).contains("0")){
                return new int[]{a, b};
            }
        }
        return new int[]{-1, -1};
    }


}
