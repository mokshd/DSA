package DSA.Stack;

import java.util.Stack;

public class Problem155_MinStack {

    public static void main(String[] args) {
        // Example run with LinkedList Optimized
        MinStackLinkedListOptimized optLL = new MinStackLinkedListOptimized();
        optLL.push(-2);
        optLL.push(0);
        optLL.push(-3);
        System.out.println("LinkedList Optimized Min: " + optLL.getMin()); // -3
        optLL.pop();
        System.out.println("LinkedList Optimized Top: " + optLL.top());    // 0
        System.out.println("LinkedList Optimized Min: " + optLL.getMin()); // -2
    }

    // ---------------- Brute Force Approach ----------------
    // getMin scans entire stack -> O(n)
    static class MinStackBruteForce {
        private Stack<Integer> stack = new Stack<>();

        public void push(int val) {
            stack.push(val);
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            int min = Integer.MAX_VALUE;
            for (int num : stack) {
                min = Math.min(min, num);
            }
            return min;
        }
    }

    // ---------------- Optimized Approach (Two Stacks) ----------------
    static class MinStackOptimized {
        private Stack<Integer> stack = new Stack<>();
        private Stack<Integer> minStack = new Stack<>();

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty()) {
                minStack.push(val);
            } else {
                minStack.push(Math.min(val, minStack.peek()));
            }
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    // ---------------- Optimized Approach (Linked List) ----------------
    // Each Node stores its value, current min, and next pointer.
    // Time Complexity: O(1) for all operations
    // Space Complexity: O(n)
    static class MinStackLinkedListOptimized {

        private static class Node {
            int val;
            int minVal;
            Node next;

            Node(int val, int minVal, Node next) {
                this.val = val;
                this.minVal = minVal;
                this.next = next;
            }
        }

        private Node head;

        public void push(int val) {
            if (head == null) {
                head = new Node(val, val, null);
            } else {
                head = new Node(val, Math.min(val, head.minVal), head);
            }
        }

        public void pop() {
            if (head != null) {
                head = head.next;
            }
        }

        public int top() {
            return head.val;
        }

        public int getMin() {
            return head.minVal;
        }
    }
}
