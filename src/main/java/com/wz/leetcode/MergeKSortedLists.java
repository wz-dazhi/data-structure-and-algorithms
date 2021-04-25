package com.wz.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @projectName: data-structure-and-algorithms
 * @package: com.wz.leetcode
 * @className: MergeKSortedLists
 * @description: 合并K个有序链表. 力扣: https://leetcode-cn.com/problems/merge-k-sorted-lists/
 * <p>
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 *
 * </p>
 * @author: zhi
 * @date: 2021/4/20
 * @version: 1.0
 */
public class MergeKSortedLists {

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

    private static class ListNodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }


    /**
     * 使用优先级队列
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        } else if (lists.length == 1) {
            return lists[0];
        }
        // 放入优先级队里中, 定义排序规则
        //final PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        final PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(new ListNodeComparator());
        for (ListNode n : lists) {
            while (n != null) {
                priorityQueue.add(n);
                n = n.next;
            }
        }
        if (priorityQueue.isEmpty()) {
            return null;
        }
        // 取出最小头部
        ListNode head = priorityQueue.poll();
        // pre抓住头部, 队列循环取出, pre向后移动
        ListNode pre = head;
        while (pre != null) {
            pre.next = priorityQueue.poll();
            pre = pre.next;
        }

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
//        ListNode[] lists = [[1,4,5],[1,3,4],[2,6]];
        var n1 = new ListNode(1);
        n1.next = new ListNode(4);
        n1.next.next = new ListNode(5);
        print(n1);
        var n2 = new ListNode(1);
        n2.next = new ListNode(3);
        n2.next.next = new ListNode(4);
        print(n2);
        var n3 = new ListNode(2);
        n3.next = new ListNode(6);
        print(n3);

        System.out.println("-------");
        var head = mergeKLists(new ListNode[]{n1, n2, n3});
        print(head);
    }
}
