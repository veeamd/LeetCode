package com.weizhang;

/**
 * Created by Wei Zhang on 6/7/15.
 *
 * Reverse a singly linked list.

 click to show more hints.

 Hint:
 A linked list can be reversed either iteratively or recursively. Could you implement both?
 *
 */
public class ReverseLinkedList {
    public class Solution {
        public ListNode reverseList(ListNode head) {
            if (head == null) return null;

            ListNode p1 = head;
            ListNode p2 = head.next;
            if (p2 != null) {
                p1.next = null;
                while (p2 != null) {
                    ListNode p3 = p2.next;
                    p2.next = p1;
                    p1 = p2;
                    p2 = p3;
                }
            }
            return p1;
        }
    }
}
