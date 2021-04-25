package com.wz.struct;

import java.util.Stack;

/**
 * @projectName: data-structure-and-algorithms
 * @package: com.wz.struct
 * @className: NodeStack
 * @description:
 * @author: zhi
 * @date: 2021/4/8
 * @version: 1.0
 */
public class NodeStack {

    private static class Node<V> {
        V v;
        Node<V> next;

        public Node(V v) {
            this.v = v;
            this.next = null;
        }
    }

    /**
     * 单链表实现栈. 先进后出, 后进先出
     */
    private static class MyStack<V> {
        Node<V> head;
        int size;

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void push(V v) {
            var node = new Node<>(v);
            // 将head指向node的下一个, 将node改为head
            if (head != null) {
                node.next = head;
            }
            head = node;
            size++;
        }

        public V pop() {
            V res = null;
            // head 向后移动
            if (head != null) {
                res = head.v;
                head = head.next;
                size--;
            }
            return res;
        }

        public V peek() {
            return head == null ? null : head.v;
        }
    }

    private static <V> void print(MyStack<V> stack) {
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    private static <V> void print(Stack<V> stack) {
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    private static <V> boolean equalNodeValue(V v1, V v2) {
        if (null == v1 && null == v2) {
            return false;
        }
        if (null == v1) {
            return true;
        } else if (null == v2) {
            return true;
        }
        return !v1.equals(v2);
    }

    public static void main(String[] args) {
        var testCount = 10000000;
        var maxValue = 200;
        var myStack = new MyStack<Integer>();
        var stack = new Stack<Integer>();
        for (int i = 0; i < testCount; i++) {
            if (myStack.isEmpty() != stack.isEmpty()) {
                System.out.println("isEmpty 失败.");
                print(myStack);
                print(stack);
                break;
            }
            if (myStack.size() != stack.size()) {
                System.out.println("size 失败.");
                print(myStack);
                print(stack);
                break;
            }

            var random = Math.random();
            if (random < 0.33) {
                var v = (int) (Math.random() * maxValue + 1);
                myStack.push(v);
                stack.push(v);
            } else if (random < 0.66) {
                if (!stack.isEmpty()) {
                    var v1 = myStack.pop();
                    var v2 = stack.pop();
                    if (equalNodeValue(v1, v2)) {
                        System.out.println("pop 失败.");
                        print(myStack);
                        print(stack);
                        break;
                    }
                }
            } else {
                if (!stack.isEmpty()) {
                    var v1 = myStack.peek();
                    var v2 = stack.peek();
                    if (equalNodeValue(v1, v2)) {
                        System.out.println("peek 失败.");
                        print(myStack);
                        print(stack);
                        break;
                    }
                }
            }
        }

        if (myStack.size() != stack.size()) {
            System.out.println("跳出循环-size 失败.");
            print(myStack);
            print(stack);
        }
        while (!stack.isEmpty()) {
            var v1 = myStack.pop();
            var v2 = stack.pop();
            if (equalNodeValue(v1, v2)) {
                System.out.println("跳出循环-pop 失败.");
                print(myStack);
                print(stack);
                break;
            }
        }
        System.out.println("单向链表栈测试结束.");
    }

}
