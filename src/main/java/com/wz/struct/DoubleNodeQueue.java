package com.wz.struct;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @projectName: data-structure-and-algorithms
 * @package: com.wz.struct
 * @className: DoubleNodeQueue
 * @description:
 * @author: zhi
 * @date: 2021/4/8
 * @version: 1.0
 */
public class DoubleNodeQueue {

    private static class DoubleNode<V> {
        V v;
        DoubleNode<V> next;
        DoubleNode<V> last;

        public DoubleNode(V v) {
            this.v = v;
            this.last = null;
            this.next = null;
        }
    }

    private static class MyDoubleQueue<V> {
        DoubleNode<V> head;
        DoubleNode<V> tail;
        int size;

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        /**
         * 头插入
         */
        public void offerHead(V v) {
            var node = new DoubleNode<>(v);
            // head 为空, node指向head, 也指向tail
            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.next = head;
                head.last = node;
                head = node;
            }

            size++;
        }

        /**
         * 尾插入
         */
        public void offerTail(V v) {
            var node = new DoubleNode<>(v);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.last = tail;
                tail = node;
            }
            size++;
        }

        /**
         * 头部弹出
         */
        public V pollHead() {
            V res = null;
            if (head != null) {
                res = head.v;
                head = head.next;
                if (head != null) {
                    head.last = null;
                } else {
                    tail = null;
                }
                size--;
            }
            return res;
        }

        /**
         * 尾巴弹出
         */
        public V pollTail() {
            V v = null;
            if (tail != null) {
                v = tail.v;
                tail = tail.last;
                if (tail != null) {
                    tail.next = null;
                } else {
                    head = null;
                }
                size--;
            }
            return v;
        }

        public V peekHead() {
            return head == null ? null : head.v;
        }

        public V peekTail() {
            return tail == null ? null : tail.v;
        }
    }

    private static <V> void printHead(MyDoubleQueue<V> queue) {
        while (!queue.isEmpty()) {
            System.out.print(queue.pollHead() + " ");
        }
        System.out.println();
    }

    private static <V> void printTail(MyDoubleQueue<V> queue) {
        while (!queue.isEmpty()) {
            System.out.print(queue.pollTail() + " ");
        }
        System.out.println();
    }

    private static <V> void printHead(Deque<V> queue) {
        while (!queue.isEmpty()) {
            System.out.print(queue.pollFirst() + " ");
        }
        System.out.println();
    }

    private static <V> void printTail(Deque<V> queue) {
        while (!queue.isEmpty()) {
            System.out.print(queue.pollLast() + " ");
        }
        System.out.println();
    }

    private static <V> boolean equalsValue(V v1, V v2) {
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
        var maxValue = 500;
        var doubleQueue = new MyDoubleQueue<Integer>();
        var qQueue = new LinkedList<Integer>();
        for (int i = 0; i < testCount; i++) {
            if (doubleQueue.isEmpty() != qQueue.isEmpty()) {
                System.out.println("isEmpty 失败.");
                printHead(doubleQueue);
                printHead(qQueue);
                break;
            }
            if (doubleQueue.size() != qQueue.size()) {
                System.out.println("size 失败.");
                printHead(doubleQueue);
                printHead(qQueue);
                break;
            }

            var random = Math.random();
            if (random < 0.25) {
                var v = (int) (Math.random() * maxValue);
                doubleQueue.offerHead(v);
                qQueue.offerFirst(v);
            } else if (random < 0.5) {
                var v = (int) (Math.random() * maxValue);
                doubleQueue.offerTail(v);
                qQueue.offerLast(v);
            } else if (random < 0.75) {
                var vH1 = doubleQueue.pollHead();
                var vH2 = qQueue.pollFirst();
                if (equalsValue(vH1, vH2)) {
                    System.out.println("pollHead 失败.");
                    printHead(doubleQueue);
                    printHead(qQueue);
                    break;
                }
                var vT1 = doubleQueue.pollTail();
                var vT2 = qQueue.pollLast();
                if (equalsValue(vT1, vT2)) {
                    System.out.println("pollTail 失败.");
                    printTail(doubleQueue);
                    printTail(qQueue);
                    break;
                }
            } else {
                var vH1 = doubleQueue.peekHead();
                var vH2 = qQueue.peekFirst();
                if (equalsValue(vH1, vH2)) {
                    System.out.println("pollHead 失败.");
                    printHead(doubleQueue);
                    printHead(qQueue);
                    break;
                }
                var vT1 = doubleQueue.peekTail();
                var vT2 = qQueue.peekLast();
                if (equalsValue(vT1, vT2)) {
                    System.out.println("peekTail 失败.");
                    printTail(doubleQueue);
                    printTail(qQueue);
                    break;
                }
            }
        }

        if (doubleQueue.size() != qQueue.size()) {
            System.out.println("跳出循环-size 失败.");
            printHead(doubleQueue);
            printHead(qQueue);
        }
        while (!qQueue.isEmpty()) {
            var vH1 = doubleQueue.pollHead();
            var vH2 = qQueue.pollFirst();
            if (equalsValue(vH1, vH2)) {
                System.out.println("跳出循环-pollHead 失败.");
                printHead(doubleQueue);
                printHead(qQueue);
                break;
            }
            var vT1 = doubleQueue.pollTail();
            var vT2 = qQueue.pollLast();
            if (equalsValue(vT1, vT2)) {
                System.out.println("跳出循环-pollTail 失败.");
                printTail(doubleQueue);
                printTail(qQueue);
                break;
            }
        }

        System.out.println("双端队列测试结束.");
    }
}
