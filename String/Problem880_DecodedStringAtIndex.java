package DSA.String;

public class Problem880_DecodedStringAtIndex {

    public static void main(String[] args) {
        String s1 = "leet2code3";
        int k1 = 10;
        System.out.println("Brute Force → " + decodeAtIndexBruteForce(s1, k1)); // "o"
        System.out.println("Optimized   → " + decodeAtIndex(s1, k1));           // "o"

        String s2 = "ha22";
        int k2 = 5;
        System.out.println("Brute Force → " + decodeAtIndexBruteForce(s2, k2)); // "h"
        System.out.println("Optimized   → " + decodeAtIndex(s2, k2));           // "h"

        String s3 = "a2345678999999999999999";
        int k3 = 1;
        System.out.println("Optimized   → " + decodeAtIndex(s3, k3));           // "a"
        // Brute force won't work here → too big
    }

    // ---------------- Brute Force Approach ----------------
    // Expand the string literally until we reach >= k length.
    // Then just return s.charAt(k-1).
    // Time Complexity: O(k) in worst case (too slow for large inputs).
    // Space Complexity: O(k).
    public static String decodeAtIndexBruteForce(String s, int k) {
        StringBuilder decoded = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                int count = c - '0';
                String current = decoded.toString();
                for (int i = 1; i < count; i++) {
                    decoded.append(current);
                    if (decoded.length() >= k) break;
                }
            } else {
                decoded.append(c);
            }
            if (decoded.length() >= k) break;
        }

        return String.valueOf(decoded.charAt(k - 1));
    }

    // ---------------- Optimized Approach ----------------
    // (No actual string construction)
    public static String decodeAtIndex(String s, int k) {
        long size = 0;

        // Step 1: Compute total decoded size
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                size *= c - '0';
            } else {
                size++;
            }
        }

        // Step 2: Traverse backward
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                size /= c - '0';
                k %= size;
            } else {
                if (k == 0 || k == size) {
                    return Character.toString(c);
                }
                size--;
            }
        }

        return "";
    }
}
