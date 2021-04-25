package com.wz.leetcode;

/**
 * @projectName: data-structure-and-algorithms
 * @package: com.wz.leetcode
 * @className: MergeTwoSortedLists
 * @description: 合并两个有序链表. 力扣: https://leetcode-cn.com/problems/merge-two-sorted-lists/
 * <p>
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * </p>
 * @author: zhi
 * @date: 2021/4/13
 * @version: 1.0
 */
public class MergeTwoSortedLists {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        // 边界检查, 不为空的直接返回
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        // 比较两个链表的值, 小的当头
        ListNode head = head1.val <= head2.val ? head1 : head2;
        // 重定向两个cur 作为下一次的比较; cur1 从head第二个开始, cur2 从另一个链表的头部开始
        ListNode cur1 = head.next;
        ListNode cur2 = head == head1 ? head2 : head1;
        ListNode pre = head;
        while (cur1 != null && cur2 != null) {
            // cur1 小于 cur2, cur1向后移动
            if (cur1.val <= cur2.val) {
                pre.next = cur1;
                cur1 = cur1.next;
            } else {
                // cur2 小于 cur1, cur2移动
                pre.next = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;
        }
        // pre.next 挂上不为空的cur
        pre.next = cur1 == null ? cur2 : cur1;

        return head;
    }

    private static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // [1,2,4]
        // [1,3,4]
        ListNode head1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);
        node2.next = node3;
        head1.next = node2;

        ListNode head2 = new ListNode(1);
        ListNode node22 = new ListNode(3);
        ListNode node23 = new ListNode(4);
        node22.next = node23;
        head2.next = node22;

        ListNode head = new MergeTwoSortedLists().mergeTwoLists(head1, head2);
        print(head);
    }
}
