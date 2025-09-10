package DSA.LinkedList;

import java.util.*;

public class Problem160_IntersectionOfTwoLinkedLists {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; next = null; }
    }

    public static void main(String[] args) {
        // Example setup
        ListNode common = new ListNode(8);
        common.next = new ListNode(4);
        common.next.next = new ListNode(5);

        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = common;

        ListNode headB = new ListNode(5);
        headB.next = new ListNode(6);
        headB.next.next = new ListNode(1);
        headB.next.next.next = common;

        System.out.println("Brute Force: " + (bruteForce(headA, headB) != null ? bruteForce(headA, headB).val : "null"));
        System.out.println("Hashing: " + (hashing(headA, headB) != null ? hashing(headA, headB).val : "null"));
        System.out.println("Optimized (Two Pointers): " + (getIntersectionNode(headA, headB) != null ? getIntersectionNode(headA, headB).val : "null"));
    }

    // ---------------- Brute Force ----------------
    // Time: O(m * n), Space: O(1)
    private static ListNode bruteForce(ListNode headA, ListNode headB) {
        for (ListNode a = headA; a != null; a = a.next) {
            for (ListNode b = headB; b != null; b = b.next) {
                if (a == b) return a;
            }
        }
        return null;
    }

    // ---------------- Hashing ----------------
    // Time: O(m + n), Space: O(m)
    private static ListNode hashing(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) return headB;
            headB = headB.next;
        }
        return null;
    }

    // ---------------- Optimized Two Pointers ----------------
    // Time: O(m + n), Space: O(1)
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode pA = headA, pB = headB;

        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }

        return pA; // either intersection node or null
    }
}
