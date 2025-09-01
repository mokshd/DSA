package DSA.String;

public class Problem12_IntegerToRoman {

    public static void main(String[] args) {
        int num = 1994;

        String bruteRes = bruteForceApproach(num);
        System.out.println("Brute Force Result: " + bruteRes);

        String optimizedRes = optimizeApproach(num);
        System.out.println("Optimized Result: " + optimizedRes);
    }

    // ---------------- Brute Force ----------------
    // Idea: Greedily subtract largest possible Roman values
    // until num = 0. Each time append the corresponding symbol.
    // Time Complexity: O(n) where n = number of Roman symbols added (max ~15)
    // Space Complexity: O(1)
    public static String bruteForceApproach(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length && num > 0; i++) {
            while (num >= values[i]) {
                sb.append(symbols[i]);
                num -= values[i];
            }
        }
        return sb.toString();
    }

    // ---------------- Optimized Approach ----------------
    // Idea: Precompute arrays of Roman literals for each digit place
    // (thousands, hundreds, tens, ones) → directly index instead of looping.
    // Time Complexity: O(1)
    // Space Complexity: O(1)
    public static String optimizeApproach(int num) {
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds  = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens      = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones      = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return thousands[num / 1000]
                + hundreds[(num % 1000) / 100]
                + tens[(num % 100) / 10]
                + ones[num % 10];
    }
}
