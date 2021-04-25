package com.wz.struct;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @projectName: data-structure-and-algorithms
 * @package: com.wz.struct
 * @className: MyQueue
 * @description:
 * @author: zhi
 * @date: 2021/4/8
 * @version: 1.0
 */
public class NodeQueue {

    private static class Node<V> {
        V v;
        Node<V> next;

        public Node(V v) {
            this.v = v;
            this.next = null;
        }
    }

    /**
     * 单链表实现队列, 先进先出
     * 尾巴插入数据
     * 从头取出数据
     */
    private static class MyQueue<V> {
        Node<V> head;
        Node<V> tail;
        int size;

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void offer(V v) {
            // 构造节点
            var node = new Node<>(v);
            // 判断队列中是否存在节点. 尾巴为空, head指向node, tail指向node(tail = node)
            if (tail == null) {
                head = node;
            } else {
                // node挂到尾巴后面. 最后tail向后指node(tail = node)
                tail.next = node;
            }
            // tail向后移动
            tail = node;
            size++;
        }

        public V poll() {
            V res = null;
            if (head == null) {
                return res;
            }
            // 弹出head, 将head指向后一个节点
            res = head.v;
            head = head.next;
            size--;

            // 判断head是否为空, 将tail跟head同时指向null
            if (head == null) {
                tail = null;
            }
            return res;
        }

        /**
         * 弹出头节点
         */
        public V peek() {
            return head == null ? null : head.v;
        }
    }

    private static <V> void print(MyQueue<V> queue) {
        while (!queue.isEmpty()) {
            System.out.print("my Queue" + queue.poll() + " ");
        }
        System.out.println();
    }

    private static <V> void print(Queue<V> queue) {
        while (!queue.isEmpty()) {
            System.out.print("queue" + queue.poll() + " ");
        }
        System.out.println();
    }

    private static <V> boolean equalsNodeValue(V v1, V v2) {
        if (v1 == null && v2 == null) {
            return false;
        } else if (v1 == null) {
            return true;
        } else if (v2 == null) {
            return true;
        }
        return !v1.equals(v2);
    }

    public static void main(String[] args) {
        var timeCount = 10000000;
        var maxValue = 500;
        var myQueue = new MyQueue<Integer>();
        var queue = new LinkedList<Integer>();
        for (int i = 0; i < timeCount; i++) {
            if (myQueue.isEmpty() != queue.isEmpty()) {
                System.out.println("isEmpty 测试失败. ");
                print(myQueue);
                print(queue);
                break;
            }
            if (myQueue.size() != queue.size()) {
                System.out.println("size 测试失败. ");
                print(myQueue);
                print(queue);
                break;
            }

            // 随机等概率测试
            var random = Math.random();
            // 往队列加数据
            if (random < 0.33) {
                var v = (int) (Math.random() * maxValue);
                myQueue.offer(v);
                queue.offer(v);
            } else if (random < 0.66) {
                // 取数据对比
                var v1 = myQueue.poll();
                var v2 = queue.poll();
                if (equalsNodeValue(v1, v2)) {
                    System.out.println("poll 测试失败. ");
                    print(myQueue);
                    print(queue);
                    break;
                }
            } else {
                // 测试peek
                var v1 = myQueue.peek();
                var v2 = queue.peek();
                if (equalsNodeValue(v1, v2)) {
                    System.out.println("peek 测试失败. ");
                    print(myQueue);
                    print(queue);
                    break;
                }
            }
        }

        // 跳出循环对比
        if (myQueue.size() != queue.size()) {
            System.out.println("跳出循环对比-size 测试失败. ");
            print(myQueue);
            print(queue);
        }

        // 遍历每一个元素对比
        while (!myQueue.isEmpty()) {
            var v1 = myQueue.poll();
            var v2 = queue.poll();
            if (equalsNodeValue(v1, v2)) {
                System.out.println("跳出循环对比-poll 测试失败. ");
                print(myQueue);
                print(queue);
                break;
            }
        }
        System.out.println("单向队列测试结束.");
    }

}
