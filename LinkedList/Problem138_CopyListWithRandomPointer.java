package DSA.LinkedList;

import java.util.*;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class Problem138_CopyListWithRandomPointer {

    public static void main(String[] args) {
        // Testcase 1
        Node head = new Node(7);
        head.next = new Node(13);
        head.next.next = new Node(11);
        head.next.next.next = new Node(10);
        head.next.next.next.next = new Node(1);

        head.random = null;
        head.next.random = head;
        head.next.next.random = head.next.next.next.next;
        head.next.next.next.random = head.next.next;
        head.next.next.next.next.random = head;

        System.out.println("Original List:");
        printList(head);

        Node copied1 = copyRandomListHashMap(head);
        System.out.println("\nDeep Copy (HashMap Approach):");
        printList(copied1);

        Node copied2 = copyRandomListOptimized(head);
        System.out.println("\nDeep Copy (Optimized O(1) Space):");
        printList(copied2);
    }

    // ---------------- Approach 1: HashMap ----------------
    // Map original -> copy nodes
    // Time: O(n), Space: O(n)
    public static Node copyRandomListHashMap(Node head) {
        if (head == null) return null;

        Map<Node, Node> map = new HashMap<>();
        Node curr = head;

        // Step 1: Clone nodes (just values)
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        // Step 2: Assign next and random
        curr = head;
        while (curr != null) {
            map.get(curr).next = map.get(curr.next);
            map.get(curr).random = map.get(curr.random);
            curr = curr.next;
        }

        return map.get(head);
    }

    // ---------------- Approach 2: Optimized (Interweaving) ----------------
    // Time: O(n), Space: O(1)
    public static Node copyRandomListOptimized(Node head) {
        if (head == null) return null;

        Node curr = head;

        // Step 1: Create new nodes interleaved with originals
        while (curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }

        // Step 2: Set random pointers for new nodes
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        // Step 3: Separate the two lists
        curr = head;
        Node dummy = new Node(0);
        Node copyCurr = dummy;
        while (curr != null) {
            copyCurr.next = curr.next;
            curr.next = curr.next.next;
            curr = curr.next;
            copyCurr = copyCurr.next;
        }

        return dummy.next;
    }

    // Utility function to print list
    public static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            int randomVal = (curr.random != null) ? curr.random.val : -1;
            System.out.print("[" + curr.val + ", random=" + randomVal + "] -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }
}
