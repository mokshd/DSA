package DSA.Design;

import java.util.*;

public class Problem1472_DesignBrowserHistory {

    public static void main(String[] args) {
        // Example run with Stack-based approach
        BrowserHistoryStack browserHistory = new BrowserHistoryStack("leetcode.com");
        browserHistory.visit("google.com");       // visit google
        browserHistory.visit("facebook.com");     // visit facebook
        browserHistory.visit("youtube.com");      // visit youtube
        System.out.println(browserHistory.back(1));    // -> "facebook.com"
        System.out.println(browserHistory.back(1));    // -> "google.com"
        System.out.println(browserHistory.forward(1)); // -> "facebook.com"
        browserHistory.visit("linkedin.com");     // visit linkedin, clears forward history
        System.out.println(browserHistory.forward(2)); // -> "linkedin.com"
        System.out.println(browserHistory.back(2));    // -> "google.com"
        System.out.println(browserHistory.back(7));    // -> "leetcode.com"
    }

    // ---------------- Approach 1 (ArrayList + two pointers) ----------------
    // Idea:
    // - Keep a list of URLs and an index 'curr' pointing to the current page.
    // - Also keep 'last' which is the last valid index in history (forward pages beyond last are invalid).
    // - On visit(url): increment curr and overwrite/add url at curr, set last = curr (cutting off forward history logically).
    // - back(steps): move curr = max(0, curr - steps) and return history.get(curr).
    // - forward(steps): move curr = min(last, curr + steps) and return history.get(curr).
    //
    // Time Complexity:
    // - visit: O(1) amortized (no expensive removals if we overwrite/append)
    // - back / forward: O(1)
    // Space Complexity: O(n) for storing history
    public static class BrowserHistory {
        private final List<String> history;
        private int curr;
        private int last;

        public BrowserHistory(String homepage) {
            history = new ArrayList<>();
            history.add(homepage);
            curr = 0;
            last = 0;
        }

        public void visit(String url) {
            curr++;
            if (curr == history.size()) {
                history.add(url);
            } else {
                history.set(curr, url);
            }
            last = curr;
        }

        public String back(int steps) {
            curr = Math.max(0, curr - steps);
            return history.get(curr);
        }

        public String forward(int steps) {
            curr = Math.min(last, curr + steps);
            return history.get(curr);
        }
    }

    // ---------------- Approach 2 (Doubly linked list) ----------------
    // Idea:
    // - Maintain a doubly-linked list of nodes storing URL.
    // - Keep a pointer 'curr' to the current node.
    // - visit(url): create new node after curr, drop curr.next chain (GC will reclaim), set curr = newNode.
    // - back/forward: move curr left/right up to steps.
    //
    // Time Complexity:
    // - visit: O(1)
    // - back / forward: O(steps)
    // Space Complexity: O(n)
    public static class BrowserHistoryDLL {
        private static class Node {
            String url;
            Node prev, next;
            Node(String url) { this.url = url; }
        }
        private Node curr;

        public BrowserHistoryDLL(String homepage) {
            curr = new Node(homepage);
        }

        public void visit(String url) {
            Node node = new Node(url);
            curr.next = node;
            node.prev = curr;
            curr = node;
        }

        public String back(int steps) {
            while (steps > 0 && curr.prev != null) {
                curr = curr.prev;
                steps--;
            }
            return curr.url;
        }

        public String forward(int steps) {
            while (steps > 0 && curr.next != null) {
                curr = curr.next;
                steps--;
            }
            return curr.url;
        }
    }

    // ---------------- Approach 3 (Two Stacks) ----------------
    // Idea:
    // - Maintain two stacks:
    //    backStack  : pages on the left
    //    forwardStack: pages on the right
    // - Always keep `curr` = the current page.
    // - On visit: push curr into backStack, clear forwardStack, set curr=new page.
    // - On back: move from backStack -> forwardStack, updating curr.
    // - On forward: move from forwardStack -> backStack, updating curr.
    //
    // Time Complexity:
    // - visit: O(1) + clearing forwardStack (O(1) if we just reassign new stack)
    // - back / forward: O(steps)
    // Space Complexity: O(n)
    public static class BrowserHistoryStack {
        private final Deque<String> backStack;
        private final Deque<String> forwardStack;
        private String curr;

        public BrowserHistoryStack(String homepage) {
            backStack = new ArrayDeque<>();
            forwardStack = new ArrayDeque<>();
            curr = homepage;
        }

        public void visit(String url) {
            backStack.push(curr);
            curr = url;
            forwardStack.clear(); // all forward history invalid
        }

        public String back(int steps) {
            while (steps > 0 && !backStack.isEmpty()) {
                forwardStack.push(curr);
                curr = backStack.pop();
                steps--;
            }
            return curr;
        }

        public String forward(int steps) {
            while (steps > 0 && !forwardStack.isEmpty()) {
                backStack.push(curr);
                curr = forwardStack.pop();
                steps--;
            }
            return curr;
        }
    }
}
