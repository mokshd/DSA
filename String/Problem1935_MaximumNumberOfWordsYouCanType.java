package DSA.String;

public class Problem1935_MaximumNumberOfWordsYouCanType {

    public static void main(String[] args) {
        String text1 = "hello world";
        String broken1 = "ad";
        // Expected: 1 ("world" can be typed, "hello" can't because of 'a'?)

        String text2 = "leet code";
        String broken2 = "lt";
        // Expected: 1 ("leet" can't because of 'l', "code" can)

        String text3 = "leet code";
        String broken3 = "e";
        // Expected: 0 (both words contain 'e')

        System.out.println("Brute Force (\"" + text1 + "\", \"" + broken1 + "\"): "
                + bruteForceApproach(text1, broken1));
        System.out.println("Optimized (\"" + text1 + "\", \"" + broken1 + "\"): "
                + optimizedApproach(text1, broken1));

        System.out.println("Brute Force (\"" + text2 + "\", \"" + broken2 + "\"): "
                + bruteForceApproach(text2, broken2));
        System.out.println("Optimized (\"" + text2 + "\", \"" + broken2 + "\"): "
                + optimizedApproach(text2, broken2));

        System.out.println("Brute Force (\"" + text3 + "\", \"" + broken3 + "\"): "
                + bruteForceApproach(text3, broken3));
        System.out.println("Optimized (\"" + text3 + "\", \"" + broken3 + "\"): "
                + optimizedApproach(text3, broken3));
    }

    // ---------------- Brute Force ----------------
    // Idea:
    // 1. Split text into words.
    // 2. For each word, check if it contains any broken letter.
    // 3. If not, count it as typeable.
    // Time Complexity: O(n * m)  (n = text length, m = broken letters length)
    // Space Complexity: O(1)
    public static int bruteForceApproach(String text, String brokenLetters) {
        String[] words = text.split(" ");
        int count = 0;
        for (String word : words) {
            boolean canType = true;
            for (char b : brokenLetters.toCharArray()) {
                if (word.indexOf(b) != -1) {
                    canType = false;
                    break;
                }
            }
            if (canType) count++;
        }
        return count;
    }

    // ---------------- Optimized Approach ----------------
    // Idea:
    // 1. Use boolean[26] to store broken letters for O(1) lookup.
    // 2. For each word, check if it contains any broken letter using lookup.
    // 3. Count only words that are fully typeable.
    // Time Complexity: O(n)  (single scan of all characters)
    // Space Complexity: O(1)
    public static int optimizedApproach(String text, String brokenLetters) {
        boolean[] broken = new boolean[26];
        for (char c : brokenLetters.toCharArray()) {
            broken[c - 'a'] = true;
        }

        int count = 0;
        for (String word : text.split(" ")) {
            boolean canType = true;
            for (char c : word.toCharArray()) {
                if (broken[c - 'a']) {
                    canType = false;
                    break;
                }
            }
            if (canType) count++;
        }
        return count;
    }
}
