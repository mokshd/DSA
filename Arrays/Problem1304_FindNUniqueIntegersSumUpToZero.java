package DSA.Arrays;

import java.util.*;

public class Problem1304_FindNUniqueIntegersSumUpToZero {

    public static void main(String[] args) {
        int n1 = 5;
        System.out.println("Result for n=5: " + Arrays.toString(sumZero(n1))); // Example output: [-2,-1,0,1,2]

        int n2 = 4;
        System.out.println("Result for n=4: " + Arrays.toString(sumZero(n2))); // Example output: [-2,-1,1,2]
    }

    // ---------------- Approach ----------------
    // Idea:
    // - To make the sum zero, just use numbers in pairs: -i and +i.
    // - If n is even: pick n/2 pairs.
    // - If n is odd: pick (n-1)/2 pairs, and add 0 at the end.
    //
    // Example:
    // n = 5 → [-2,-1,0,1,2]
    // n = 4 → [-2,-1,1,2]
    //
    // Time Complexity: O(n) (filling array)
    // Space Complexity: O(n) (output array)
    public static int[] sumZero(int n) {
        int[] result = new int[n];
        int index = 0;

        // Fill pairs (-i, +i)
        for (int i = 1; i <= n / 2; i++) {
            result[index++] = -i;
            result[index++] = i;
        }

        // If n is odd, add 0
        if (n % 2 == 1) {
            result[index] = 0;
        }

        return result;
    }
}
