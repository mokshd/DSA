package DSA.Stack;

import java.util.Stack;

public class Problem20_ValidParentheses {

    public static void main(String[] args) {
        String input = "()";

        boolean bruteForceResult = bruteForceApproach(input);
        System.out.println("Brute Force Result: " + bruteForceResult);

        boolean optimizedResult = optimizeApproach(input);
        System.out.println("Optimized Result: " + optimizedResult);
    }

    // ---------------- Brute Force ----------------
    // Idea: Repeatedly remove valid pairs "()", "{}", "[]" until no more changes.
    // If final string is empty -> valid, else invalid.
    // Time Complexity: O(n^2) (each replace O(n), repeated up to n times)
    // Space Complexity: O(1) (ignoring internal new strings)
    private static boolean bruteForceApproach(String input) {
        boolean changed = true;
        while (changed) {
            String before = input;
            input = input.replace("()", "")
                    .replace("{}", "")
                    .replace("[]", "");
            changed = !before.equals(input);
        }
        return input.isEmpty();
    }

    // ---------------- Optimized Approach ----------------
    // Idea: Use a stack to track expected closing brackets.
    // Push the expected closing bracket when an opening bracket is found.
    // When encountering a closing bracket, check against stack top.
    // Time Complexity: O(n) (each char pushed/popped at most once)
    // Space Complexity: O(n) (in worst case, all open brackets in stack)
    private static boolean optimizeApproach(String input) {
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                return false; // mismatch or empty stack
            }
        }
        return stack.isEmpty();
    }
}
