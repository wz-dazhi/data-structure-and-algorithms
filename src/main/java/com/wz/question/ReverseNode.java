package com.wz.question;

import java.util.LinkedList;
import java.util.List;

/**
 * @projectName: data-structure-and-algorithms
 * @package: com.wz.question
 * @className: ReverseNode
 * @description: 反转链表
 * @author: zhi
 * @date: 2021/4/7
 * @version: 1.0
 */
public class ReverseNode {

    public static void main(String[] args) {
        var testCount = 1000000;
        var maxLen = 100;
        var maxValue = 200;
        // 单链表测试
//        for (int i = 0; i < testCount; i++) {
//            var n = randomNode(maxLen, maxValue);
//            var list = copyNode(n);
//            n = reverseNode(n);
//            if (!testReverseNode(n, list)) {
//                System.out.println("单向---> 失败.");
//                print(n);
//                System.out.println(list);
//                break;
//            }
//        }
//        System.out.println("单链表反转结束.");

        // 双向链表测试
        for (int i = 0; i < testCount; i++) {
            var n = randomDoubleNode(maxLen, maxValue);
            var list = copyDoubleNode(n);
            n = reverseDoubleNode(n);
            if (!testReverseDoubleNode(list, n)) {
                System.out.println("双向---> 失败");
                print(n);
                System.out.println(list);
                break;
            }
        }
        System.out.println("双向链表反转结束.");
    }

    //----------------------------单链表---------------------------------
    private static class Node {
        int v;
        Node next;

        public Node(int v) {
            this.v = v;
        }
    }

    /**
     * 反转单向链表
     */
    private static Node reverseNode(Node head) {
        Node pre = null;
        Node next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 随机生成单向链表
     */
    private static Node randomNode(int maxLen, int maxValue) {
        var len = (int) (Math.random() * maxLen);
        if (len <= 0) {
            randomNode(maxLen, maxValue);
        }

        var i = 1;
        var head = new Node((int) (Math.random() * maxValue));
        var next = head;
        while (i < len) {
            i++;
            var cur = new Node((int) (Math.random() * maxValue));
            next.next = cur;
            next = cur;
        }
        return head;
    }

    /**
     * copy单向链表
     */
    private static List<Integer> copyNode(Node head) {
        List<Integer> list = new LinkedList<>();
        while (head != null) {
            list.add(head.v);
            head = head.next;
        }

        return list;
    }

    /**
     * test反转
     */
    private static boolean testReverseNode(Node head, List<Integer> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) != head.v) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    private static void print(Node head) {
        while (head != null) {
            System.out.print(head.v + " ");
            head = head.next;
        }
        System.out.println();
    }

    //-----------------------------双向链表------------------------------------------
    private static class DoubleNode {
        int v;
        DoubleNode last;
        DoubleNode next;

        public DoubleNode(int v) {
            this.v = v;
        }

        @Override
        public String toString() {
            return "v=" + v;
        }
    }

    /**
     * 反转双向链表
     */
    private static DoubleNode reverseDoubleNode(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            pre.last = next;
            head = next;
        }
        return pre;
    }

    /**
     * 验证反转之后的node
     */
    private static boolean testReverseDoubleNode(List<Integer> list, DoubleNode head) {
        // 记录一下最后的节点, 用于反向验证
        DoubleNode end = null;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) != head.v) {
                return false;
            }
            end = head;
            head = head.next;
        }
        if (end != null) {
            // 反向验证
            for (Integer value : list) {
                if (value != end.v) {
                    return false;
                }
                end = end.last;
            }
        }
        return true;
    }

    private static List<Integer> copyDoubleNode(DoubleNode head) {
        List<Integer> list = new LinkedList<>();
        while (head != null) {
            list.add(head.v);
            head = head.next;
        }

        return list;
    }

    /**
     * 随机生成双向链表数据
     */
    private static DoubleNode randomDoubleNode(int maxLen, int maxValue) {
        var len = (int) (Math.random() * maxLen);
        if (len <= 0) {
            randomDoubleNode(maxLen, maxValue);
        }
        var i = 0;
        var head = new DoubleNode((int) (Math.random() * maxValue));
        var pre = head;
        while (i < len) {
            i++;
            var node = new DoubleNode((int) (Math.random() * maxValue));
            pre.next = node;
            node.last = pre;
            pre = node;
        }

        return head;
    }

    private static void print(DoubleNode head) {
        while (head != null) {
            System.out.print(head + "->" + head.next + " ");
            head = head.next;
        }
        System.out.println();
    }
}
