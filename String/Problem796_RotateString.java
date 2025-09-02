package DSA.Strings;

public class Problem796_RotateString {

    public static void main(String[] args) {
        String s1 = "abcde", goal1 = "cdeab";
        System.out.println("Brute Force Result (s=abcde, goal=cdeab): " + bruteForceApproach(s1, goal1));   // true
        System.out.println("Optimized Result (s=abcde, goal=cdeab): " + optimizedApproach(s1, goal1));      // true

        String s2 = "abcde", goal2 = "abced";
        System.out.println("Brute Force Result (s=abcde, goal=abced): " + bruteForceApproach(s2, goal2));   // false
        System.out.println("Optimized Result (s=abcde, goal=abced): " + optimizedApproach(s2, goal2));      // false
    }

    // ---------------- Brute Force ----------------
    // Idea: Try all n possible rotations of s by moving first character to end.
    // After each rotation, check if equals goal.
    //
    // Time Complexity: O(n^2) (n rotations × O(n) string comparison)
    // Space Complexity: O(n) (StringBuilder storage)
    private static boolean bruteForceApproach(String s, String goal) {
        if (s.length() != goal.length()) return false;

        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            // rotate left by 1
            char first = sb.charAt(0);
            sb.deleteCharAt(0);
            sb.append(first);

            if (sb.toString().equals(goal)) return true;
        }
        return false;
    }

    // ---------------- Optimized Approach ----------------
    // Idea: If goal is a rotation of s, then it must be a substring of (s+s).
    // Example: s = "abcde", s+s = "abcdeabcde"
    // All rotations of s exist inside s+s.
    //
    // Time Complexity: O(n) (substring search)
    // Space Complexity: O(n) (s+s storage)
    private static boolean optimizedApproach(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }
}
