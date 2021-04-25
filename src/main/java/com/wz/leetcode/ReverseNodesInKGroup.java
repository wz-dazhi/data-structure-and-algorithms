package com.wz.leetcode;

/**
 * @projectName: data-structure-and-algorithms
 * @package: com.wz.leetcode
 * @className: ReverseNodesInKGroup
 * @description: 给一个链表, 每k个节点为一组进行反转. 力扣: https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 * <p>
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 进阶：
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * </p>
 * @author: zhi
 * @date: 2021/4/9
 * @version: 1.0
 */
public class ReverseNodesInKGroup {

    /**
     * 不要提交该类, 力扣中有
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

        @Override
        public String toString() {
            return "val=" + val;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        // 找出第一组要反转的node
        ListNode end = returnKNode(start, k);
        // 第一组不够, 返回老head
        if (null == end) {
            return head;
        }
        // 第一组凑够了, 将head指向end
        head = end;
        // 反转第一组; head指向end, head不动, start end 反转, end进行移动. 反转后head为 2 1 3 4
        reverseStartAndEnd(start, end);
        // 循环反转
        ListNode lastEnd = start;
        while (lastEnd.next != null) {
            // start重新设置下一组
            start = lastEnd.next;
            // 凑一组
            end = returnKNode(start, k);
            // 凑不齐, 直接返回head
            if (end == null) {
                return head;
            }
            // 反转
            reverseStartAndEnd(start, end);
            // 准备下一组
            lastEnd.next = end;
            lastEnd = start;
        }

        return head;
    }

    /**
     * 从start位置反转到end位置
     */
    private static void reverseStartAndEnd(ListNode start, ListNode end) {
        // 记录end下一个位置
        end = end.next;
        ListNode cur = start;
        ListNode pre = null;
        ListNode next;
        // cur 不等于end 一直循环反转
        while (cur != end) {
            // 记录cur.next
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 改变start.next位置, start.next 指向end
        start.next = end;
    }

    /**
     * 返回k位置上的node
     */
    private static ListNode returnKNode(ListNode start, int k) {
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }

    private static void print(ListNode head) {
        var len = 0;
        while (head != null) {
            len++;
            if (len > 5) {
                return;
            }
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        final ListNode n2 = new ListNode(2);
        n1.next = n2;
        final ListNode n3 = new ListNode(3);
        n1.next.next = n3;
        final ListNode n4 = new ListNode(4);
        n1.next.next.next = n4;
        //System.out.println(returnKNode(n1, 4));

//        print(n1);
//        reverseStartAndEnd(n1, n3);

    }

}
