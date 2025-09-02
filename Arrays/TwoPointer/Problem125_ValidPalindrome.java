package DSA.Arrays.TwoPointer;

public class Problem125_ValidPalindrome {

    public static void main(String[] args) {
        String s1 = "A man, a plan, a canal: Panama";
        String s2 = "race a car";

        System.out.println("Brute Force (\"" + s1 + "\"): " + bruteForceApproach(s1));
        System.out.println("Optimized (\"" + s1 + "\"): " + optimizedApproach(s1));

        System.out.println("Brute Force (\"" + s2 + "\"): " + bruteForceApproach(s2));
        System.out.println("Optimized (\"" + s2 + "\"): " + optimizedApproach(s2));
    }

    // ---------------- Brute Force ----------------
    // Idea:
    // 1. Clean string: keep only alphanumeric, lowercase all.
    // 2. Reverse cleaned string and compare.
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static boolean bruteForceApproach(String s) {
        String filtered = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(filtered).reverse().toString();
        return filtered.equals(reversed);
    }

    // Alternative brute force using streams (less efficient due to O(n^2) reduce):
    public static boolean bruteForceStream(String s) {
        String filtered = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversed = filtered.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .reduce("", (a, b) -> b + a);  // inefficient string concat
        return filtered.equals(reversed);
    }

    // ---------------- Optimized Approach ----------------
    // Idea:
    // Use two pointers from both ends,
    // skip non-alphanumeric characters,
    // compare characters directly.
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static boolean optimizedApproach(String s) {
        int start = 0;
        int end = s.length() - 1;

        while (start < end) {
            while (start < end && !Character.isLetterOrDigit(s.charAt(start))) start++;
            while (end > start && !Character.isLetterOrDigit(s.charAt(end))) end--;

            if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
