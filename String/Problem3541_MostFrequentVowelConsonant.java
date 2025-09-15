package DSA.String;

public class Problem3541_MostFrequentVowelConsonant {

    public static void main(String[] args) {
        String s1 = "leetcodeisawesome";
        String s2 = "programming";

        System.out.println("Brute Force (\"" + s1 + "\"): " + bruteForceApproach(s1));
        System.out.println("Optimized (\"" + s1 + "\"): " + optimizedApproach(s1));

        System.out.println("Brute Force (\"" + s2 + "\"): " + bruteForceApproach(s2));
        System.out.println("Optimized (\"" + s2 + "\"): " + optimizedApproach(s2));
    }

    // ---------------- Brute Force ----------------
    // Idea:
    // 1. Count frequency of each character using a HashMap.
    // 2. Iterate over characters to separate vowels and consonants.
    // 3. Find max frequency among vowels and consonants.
    // Time Complexity: O(n)
    // Space Complexity: O(26) ~ O(1)
    public static String bruteForceApproach(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                freq[c - 'a']++;
            }
        }

        int maxVowel = 0, maxCons = 0;
        for (char c : new char[]{'a', 'e', 'i', 'o', 'u'}) {
            maxVowel = Math.max(maxVowel, freq[c - 'a']);
        }

        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            if ("aeiou".indexOf(c) == -1) { // consonant
                maxCons = Math.max(maxCons, freq[i]);
            }
        }

        return "Most Frequent Vowel: " + maxVowel + ", Consonant: " + maxCons;
    }

    // ---------------- Optimized Approach ----------------
    // Idea:
    // 1. Single scan to compute max freq for vowel and consonant directly.
    // 2. Maintain freq array, update vowel/consonant max on the fly.
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static String optimizedApproach(String s) {
        int[] freq = new int[26];
        int maxVowel = 0, maxCons = 0;

        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                int idx = c - 'a';
                freq[idx]++;

                if (isVowel(c)) {
                    maxVowel = Math.max(maxVowel, freq[idx]);
                } else {
                    maxCons = Math.max(maxCons, freq[idx]);
                }
            }
        }

        return "Most Frequent Vowel: " + maxVowel + ", Consonant: " + maxCons;
    }

    private static boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1;
    }
}
