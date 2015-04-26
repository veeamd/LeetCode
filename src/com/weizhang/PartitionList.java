package com.weizhang;


/**
 * Created by Wei Zhang on 4/26/15.
 *
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

 You should preserve the original relative order of the nodes in each of the two partitions.

 For example,
 Given 1->4->3->2->5->2 and x = 3,
 return 1->2->2->4->3->5.
 *
 */
public class PartitionList {
    private ListNode lt, lte, gt, gte;
    public ListNode partition(ListNode head, int x) {
        lt = new ListNode(0);
        lt.next = head;
        ListNode p;
        lte = lt;
        p = head;
        while (p != null) {
            if (p.val < x) {
                ListNode pnext = p.next;
                moveLessThanNodeBeforeGreaterThanNodes(p);
                p = pnext;
            } else {
                appendNodeToGreatThanNodes(p);
                p = p.next;
            }
        }
        return lt.next;
    }

    private void moveLessThanNodeBeforeGreaterThanNodes(ListNode p) {
        if (gt != null) {
            ListNode pnext = p.next;
            lte.next = p;
            gte.next = pnext;
            p.next = gt;
        }
        lte = p;
    }

    private void appendNodeToGreatThanNodes(ListNode p) {
        if (gt == null) {
            gt = p;
            gte = p;
        } else {
            gte = p;
        }
    }

    public static void main(String[] args) {
        PartitionList test = new PartitionList();
        ListNode list = ListNode.createListNodeList(1, 4, 3, 2 , 5, 2);
        test.partition(list, 3);
    }
}
