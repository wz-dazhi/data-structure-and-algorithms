package com.wz.leetcode;

/**
 * @projectName: data-structure-and-algorithms
 * @package: com.wz.leetcode
 * @className: AddTwoNumbers
 * @description: 两个链表的节点相加. 力扣: https://leetcode-cn.com/problems/add-two-numbers/
 * <p>
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * </p>
 * @author: zhi
 * @date: 2021/4/13
 * @version: 1.0
 */
public class AddTwoNumbers {

    /**
     * 不要提交该类, 力扣上有
     */
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

    /**
     * 两个链表相加
     * 个 十 百
     * 3  5  6
     * 2  4
     * 5  9  6
     * 返回 596
     * 653+42=695
     * <p>
     * 分三个阶段
     * 第一阶段:
     * 1. 长链表有
     * 2. 短链表有
     * 第二阶段:
     * 1. 长链表有
     * 2. 短链表无
     * 第三阶段
     * 1. 长链表无
     * 2. 短链表无
     * </p>
     *
     * @param head1 链表1
     * @param head2 链表2
     * @return 相加后的链表
     */
    public ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        // 求两个链表长度
        int len1 = listNodeLength(head1);
        int len2 = listNodeLength(head2);
        // 重定向, 找出长链表 短链表
        ListNode longHead = len1 > len2 ? head1 : head2;
        ListNode shortHead = longHead == head1 ? head2 : head1;
        // 使用临时变量进行计算
        int carry = 0;
        ListNode currL = longHead;
        ListNode currS = shortHead;
        // last 跟随长节点
        ListNode last = currL;
        // 第一阶段
        while (currS != null) {
            int sum = currS.val + currL.val + carry;
            currL.val = sum % 10;
            carry = sum / 10;
            last = currL;
            currS = currS.next;
            currL = currL.next;
        }
        // 第二阶段
        while (currL != null) {
            int sum = currL.val + carry;
            currL.val = sum % 10;
            carry = sum / 10;
            last = currL;
            currL = currL.next;
        }
        // 第三阶段, 进位不为0, 补节点
        if (carry > 0) {
            last.next = new ListNode(1);
        }
        return longHead;
    }

    /**
     * 计算链表长度
     */
    private static int listNodeLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    public static void main(String[] args) {

    }
}
