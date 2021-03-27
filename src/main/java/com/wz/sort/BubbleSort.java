package com.wz.sort;

/**
 * @projectName: data-structure-and-algorithms
 * @package: com.wz.sort
 * @className: BubbleSort
 * @description: 冒泡排序
 * @author: zhi
 * @date: 2021/3/17
 * @version: 1.0
 */
public class BubbleSort {

    private static void sort(int[] arr) {
        // 小于2, 不用排序
        if (arr == null || arr.length < 2) {
            return;
        }
        // 两个相邻的数进行比较, 并交换
        // 0 ~ n-1
        // 0 ~ n-2
        // 0 ~ n-3
        // 0-1 1-2 2-3  两两交换, 最大的数交换到末尾
        // 每次找到最大的数放到末尾
        for (var end = arr.length - 1; end > 0; end--) {
            findMax(arr, end);
        }
    }

    private static void findMax(int[] arr, int n) {
        // 找到最大数, 交换到末尾
        for (int i = 0; i < n; i++) {
            if (arr[i] > arr[i + 1]) {
                int tmp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = tmp;
            }
        }
    }

    private static void print(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {13, 2, 5, 11, 6, 1};
        print(arr);
//        findMax(arr, 5);
//        print(arr);
//        findMax(arr, 4);
//        print(arr);
//        findMax(arr, 3);
//        print(arr);
//        findMax(arr, 2);
//        print(arr);
//        findMax(arr, 1);
        sort(arr);
        print(arr);
    }
}
