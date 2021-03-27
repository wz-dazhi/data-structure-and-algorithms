package com.wz.sort;

/**
 * @projectName: data-structure-and-algorithms
 * @package: com.wz.sort
 * @className: InsertSort
 * @description: 插入排序
 * @author: zhi
 * @date: 2021/3/18
 * @version: 1.0
 */
public class InsertSort {

    private static void sort(int[] arr) {
        // 小于2, 不用排序
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0 ~ 0 完成有序
        // 0 ~ 1 排序交换
        // 0 ~ 2 排序交换
        // 0 ~ 3 排序交换
        // 0 ~ n 排序交换
        var len = arr.length;
        for (var i = 1; i < len; i++) {
            var j = i;
            // 左边都已经排好序了
            // 左边大于等于0, 如果小于0代表左边没有数据了
            // 左边的大于右边的, 进行交换
            while (j - 1 >= 0 && arr[j - 1] > arr[j]) {
                var tmp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = tmp;
                j--;
            }
        }
    }

    private static void sort2(int[] arr) {
        // 小于2, 不用排序
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0 ~ 0  0比0 完成
        // 0 ~ 1  0比1 需要比较
        // 0 ~ 2  0比2 需要比较
        // 0 ~ n
        for (var i = 1; i < arr.length; i++) {
            // 左边(边界)不低于0 && 左边比右边的大, 就进行交换
            for (var j = i; j > 0 && arr[j - 1] > arr[j]; j--) {
                var tmp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = tmp;
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
//        sort(arr);
        sort2(arr);
        print(arr);
    }
}
