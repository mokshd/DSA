package DSA.String;

import java.util.*;

public class Problem13_RomanToInteger {

    public static void main(String[] args) {
        String input1 = "III";
        String input2 = "LVIII";
        String input3 = "MCMXCIV";

        System.out.println("Brute Force Result (III): " + bruteForceApproach(input1));   // 3
        System.out.println("Optimized Result (III): " + optimizedApproach(input1));      // 3

        System.out.println("Brute Force Result (LVIII): " + bruteForceApproach(input2)); // 58
        System.out.println("Optimized Result (LVIII): " + optimizedApproach(input2));    // 58

        System.out.println("Brute Force Result (MCMXCIV): " + bruteForceApproach(input3)); // 1994
        System.out.println("Optimized Result (MCMXCIV): " + optimizedApproach(input3));    // 1994
    }

    // ---------------- Brute Force ----------------
    // Idea: Check two characters at a time for special subtractive patterns
    // (IV, IX, XL, XC, CD, CM). If found, add their value and skip 2 chars.
    // Otherwise, add single character value.
    //
    // Time Complexity: O(n) (but substring creation makes it slightly heavier)
    // Space Complexity: O(1) (fixed 13 Roman numeral patterns in map)
    private static int bruteForceApproach(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1); map.put("IV", 4); map.put("V", 5); map.put("IX", 9);
        map.put("X", 10); map.put("XL", 40); map.put("L", 50); map.put("XC", 90);
        map.put("C", 100); map.put("CD", 400); map.put("D", 500); map.put("CM", 900);
        map.put("M", 1000);

        int res = 0;
        for (int i = 0; i < s.length();) {
            if (i < s.length() - 1 && map.containsKey(s.substring(i, i + 2))) {
                res += map.get(s.substring(i, i + 2));
                i += 2; // skip two chars
            } else {
                res += map.get(s.substring(i, i + 1));
                i += 1; // skip one char
            }
        }
        return res;
    }

    // ---------------- Optimized Approach ----------------
    // Idea: Traverse left to right. If current < next → subtract, else add.
    // This avoids substring checks and directly uses comparison logic.
    //
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private static int optimizedApproach(String s) {
        Map<Character, Integer> map = Map.of(
                'I', 1, 'V', 5, 'X', 10,
                'L', 50, 'C', 100, 'D', 500, 'M', 1000
        );

        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1 && map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                res -= map.get(s.charAt(i));
            } else {
                res += map.get(s.charAt(i));
            }
        }
        return res;
    }
}
