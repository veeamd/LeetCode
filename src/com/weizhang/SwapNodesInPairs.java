package com.weizhang;

/**
 * Created by Wei Zhang on 4/15/15.
 *
 * Given a linked list, swap every two adjacent nodes and return its head.

 For example,
 Given 1->2->3->4, you should return the list as 2->1->4->3.

 Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 *
 */
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode P1, P2, P3;
        if (head != null) {
            P3 = head;
            P1 = P3;
            if (P3.next != null) {
                P3 = P3.next;
                P2 = P3;
                P3 = P3.next;

                // do swapping
                P2.next = P1;
                P1.next = swapPairs(P3);
                return P2;
            } else {
                return P1;
            }
        } else {
            return head;
        }
    }

    // iterative
    public ListNode swapPairsII(ListNode head) {
        if (head == null) return null;
        ListNode P1, P2, Pre, Nex;
        // Pre->P1->P2->Nex, swapping the positions of P1 and P2
        // so that the result becomes Pre->P2->P1->Nex

        // initially Pre is a dummy head, reset the head, and return head.next as function result
        Pre = new ListNode(0);
        Pre.next = head;
        P1 = head; P2 = P1.next;
        head = Pre;
        while (P1 != null && P2 != null) {
            Nex = P2.next;
            // do swapping
            Pre.next = P2;
            P2.next = P1;
            P1.next = Nex;
            // moving on to next pair
            Pre = P1;
            P1 = Nex;
            if (P1 != null) P2 = P1.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        SwapNodesInPairs test = new SwapNodesInPairs();
        ListNode head = ListNode.createListNodeList(1, 2, 3, 4, 5);
        head = test.swapPairsII(head);
    }
}
