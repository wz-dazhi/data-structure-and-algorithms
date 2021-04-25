package com.wz.struct;

/**
 * @projectName: data-structure-and-algorithms
 * @package: com.wz.struct
 * @className: ForeachTree
 * @description:
 * @author: zhi
 * @date: 2021/4/20
 * @version: 1.0
 */
public class ForeachTree {

    /**
     * 树
     */
    private static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * 递归序
     */
    private static void rec(Node head) {
        if (head == null) {
            return;
        }
        // 1
        rec(head.left);
        // 2
        rec(head.right);
        // 3
    }

    /**
     * 先序遍历; 头左右
     */
    private static void pre(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val + " ");
        pre(head.left);
        pre(head.right);
    }

    /**
     * 中序遍历; 左头右
     */
    private static void in(Node head) {
        if (head == null) {
            return;
        }
        in(head.left);
        System.out.print(head.val + " ");
        in(head.right);
    }

    /**
     * 后序遍历; 左右头
     */
    private static void post(Node head) {
        if (head == null) {
            return;
        }
        post(head.left);
        post(head.right);
        System.out.print(head.val + " ");
    }

    public static void main(String[] args) {
        var n1 = new Node(1);
        n1.left = new Node(2);
        n1.right = new Node(3);
        n1.left.left = new Node(4);
        n1.left.right = new Node(5);
        n1.right.left = new Node(6);
        n1.right.right = new Node(7);

        pre(n1);
        System.out.println();
        System.out.println("-----");
        in(n1);
        System.out.println();
        System.out.println("-----");
        post(n1);
    }
}
