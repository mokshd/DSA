package DSA.String;

public class Problem3227_VowelsGameInString {

    public static void main(String[] args) {
        String s1 = "abcde";   // contains vowels
        String s2 = "rhythm";  // no vowels
        String s3 = "leetcode"; // contains vowels

        System.out.println("Brute Force (\"" + s1 + "\"): " + bruteForceApproach(s1));
        System.out.println("Optimized (\"" + s1 + "\"): " + optimizedApproach(s1));

        System.out.println("Brute Force (\"" + s2 + "\"): " + bruteForceApproach(s2));
        System.out.println("Optimized (\"" + s2 + "\"): " + optimizedApproach(s2));

        System.out.println("Brute Force (\"" + s3 + "\"): " + bruteForceApproach(s3));
        System.out.println("Optimized (\"" + s3 + "\"): " + optimizedApproach(s3));
    }

    // ---------------- Brute Force ----------------
    // Idea:
    // 1. Scan through string.
    // 2. If any char is a vowel -> Alice wins, else Bob wins.
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static String bruteForceApproach(String s) {
        for (char c : s.toCharArray()) {
            if (isVowel(c)) {
                return "Alice";
            }
        }
        return "Bob";
    }

    // ---------------- Optimized Approach ----------------
    // Idea:
    // 1. Pre-check using regex or string search for vowels.
    // 2. If found -> Alice, else Bob.
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static String optimizedApproach(String s) {
        return s.matches(".*[aeiou].*") ? "Alice" : "Bob";
    }

    private static boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1;
    }
}
