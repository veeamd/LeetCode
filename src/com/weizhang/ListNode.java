package com.weizhang;

/**
 * Created by Wei Zhang on 3/21/15.
 */

public class ListNode {
    private int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }

    public static ListNode createListNodeList(int...elements) {
        ListNode head = new ListNode(0);
        ListNode pointer = head;
        for (int el : elements) {
            pointer.next = new ListNode(el);
            pointer = pointer.next;
        }

        return head.next;
    }
}