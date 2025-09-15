package DSA.String;

import java.util.*;

public class Problem966_VowelSpellchecker {

    public static void main(String[] args) {
        String[] wordlist = {"KiTe", "kite", "hare", "Hare"};
        String[] queries = {"kite", "Kite", "KiTe", "Hare", "HARE", "Hear", "hear", "keti", "keet", "keto"};

        System.out.println("Brute Force:");
        System.out.println(Arrays.toString(bruteForceApproach(wordlist, queries)));

        System.out.println("Optimized:");
        System.out.println(Arrays.toString(optimizedApproach(wordlist, queries)));
    }

    // ---------------- Brute Force ----------------
    // Idea:
    // 1. For each query, check directly in wordlist.
    // 2. If not found, check case-insensitive match.
    // 3. If still not found, check vowel-error match.
    // Time Complexity: O(Q * W)  [Q=queries, W=wordlist length]
    // Space Complexity: O(1) extra
    public static String[] bruteForceApproach(String[] wordlist, String[] queries) {
        List<String> result = new ArrayList<>();

        for (String query : queries) {
            String matched = "";

            // Case 1: Exact match
            for (String word : wordlist) {
                if (word.equals(query)) {
                    matched = word;
                    break;
                }
            }

            // Case 2: Case-insensitive match
            if (matched.isEmpty()) {
                for (String word : wordlist) {
                    if (word.equalsIgnoreCase(query)) {
                        matched = word;
                        break;
                    }
                }
            }

            // Case 3: Vowel-error match
            if (matched.isEmpty()) {
                String transformedQuery = query.toLowerCase().replaceAll("[aeiou]", "*");
                for (String word : wordlist) {
                    String transformedWord = word.toLowerCase().replaceAll("[aeiou]", "*");
                    if (transformedWord.equals(transformedQuery)) {
                        matched = word;
                        break;
                    }
                }
            }

            result.add(matched);
        }

        return result.toArray(new String[0]);
    }

    // ---------------- Optimized Approach ----------------
    // Idea:
    // 1. Preprocess wordlist into three maps:
    //    - Exact matches (HashSet)
    //    - Case-insensitive map (lowerCase → original)
    //    - Vowel-error map (vowelPattern → original)
    // 2. For each query, check in order:
    //    exact → case-insensitive → vowel-error → "" (default)
    // Time Complexity: O(Q + W)
    // Space Complexity: O(W)
    public static String[] optimizedApproach(String[] wordlist, String[] queries) {
        HashSet<String> uniqueWord = new HashSet<>();
        HashMap<String, String> lowerCase = new HashMap<>();
        HashMap<String, String> vowelError = new HashMap<>();

        for (String word : wordlist) {
            uniqueWord.add(word);
            lowerCase.putIfAbsent(word.toLowerCase(), word);

            String temp = word.toLowerCase().replaceAll("[aeiou]", "*");
            vowelError.putIfAbsent(temp, word);
        }

        List<String> result = new ArrayList<>();
        for (String query : queries) {
            if (uniqueWord.contains(query)) {
                result.add(query);
            } else if (lowerCase.containsKey(query.toLowerCase())) {
                result.add(lowerCase.get(query.toLowerCase()));
            } else {
                String temp = query.toLowerCase().replaceAll("[aeiou]", "*");
                result.add(vowelError.getOrDefault(temp, ""));
            }
        }

        return result.toArray(new String[0]);
    }
}
