package com.wz.sort;

/**
 * @projectName: data-structure-and-algorithms
 * @package: com.wz.sort
 * @className: SelectionSort
 * @description: 选择排序
 * @author: zhi
 * @date: 2021/3/17
 * @version: 1.0
 */
public class SelectionSort {

    private static void sort(int[] arr) {
        // 小于2, 不用排序
        if (arr == null || arr.length < 2) {
            return;
        }
        // 选择一个最小数的下标
        // 0 ~ n-1  0和 ... n-1个数做比较, 找到最小数
        // 1 ~ n-1  1和 ... n-1个数做比较, 找到最小数
        // 2 ~ n-1  2和 ... n-1个数做比较, 找到最小数
        // i ~ n-1  i和 ... n-1个数做比较, 找到最小数
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            // 选择最小数的下标
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            // 交换
            if (minIndex != i) {
                int tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
            }
        }
    }

    private static void print(int[] arr) {
        for (int value : arr) {
            System.out.print(value + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {13, 2, 5, 11, 6, 1};
        print(arr);
        sort(arr);
        print(arr);
    }
}
