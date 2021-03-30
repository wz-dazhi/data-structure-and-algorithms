package com.wz.question;

/**
 * @projectName: data-structure-and-algorithms
 * @package: com.wz.question
 * @className: DichotomyOneMinIndex
 * @description: 使用二分法, 查找局部最小值
 * @author: zhi
 * @date: 2021/3/30
 * @version: 1.0
 */
public class DichotomyOneMinIndex {

    /**
     * 一组相邻不等的数组, 使用二分查找(一个)局部最小值
     * 局部最小值解释:
     * 1. 0 位置上的数比 1位置上的数小, 返回0; arr[0] < arr[1] 返回0
     * 2. n - 1 位置上的数比 n - 2位置上的数小, 返回 n-1; arr[n - 1] < arr[n - 2] 返回n-1
     * 3. arr[mid - 1] > arr[mid] < arr[mid + 1] 返回mid
     * 0, 1 ... n - 2, n - 1
     * 0 到 1下降趋势 ... n - 2到n - 1上升趋势
     * \               /
     * \ ... ... ... /
     * 在趋势中, 我们断定比存在一个局部最小值
     */
    public static void main(String[] args) {
        var maxLen = 80;
        var maxValue = 1000;
        var testCount = 10000000;
        for (int i = 0; i < testCount; i++) {
            var arr = randomArray(maxLen, maxValue);
            var minIndex = oneMinIndex(arr);
            // 检查是否属于局部最小
            if (!checkMinIndex(arr, minIndex)) {
                System.out.println("--- 失败 ---");
                print(arr);
                break;
            }
        }
        System.out.println("--- 结束 ---");
    }

    private static int oneMinIndex(int[] arr) {
        // 边界检查
        if (null == arr || arr.length == 0) {
            return -1;
        }
        // 1个 或 2个元素
        var len = arr.length;
        if (len == 1) {
            return 0;
        }
        if (len == 2) {
            return arr[0] < arr[1] ? 0 : 1;
        }

        // 2个元素以上
        var l = 0;
        var r = len - 1;
        // 条件为3个及3个元素以上的才循环, 不然会存在边界问题(当只有2个数时)
//        while (l < r) {
        while (l < r - 1) {
            var mid = (l + r) / 2;
            // arr[mid - 1] > arr[mid] < arr[mid + 1]
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                return mid;
            } else {
                // 1. mid - 1 > mid > mid + 1
                // 2. mid - 1 < mid > mid + 1
                // 3. mid - 1 < mid < mid + 1
                // 中 2 3条件, 切掉右边, 找左边
                if (arr[mid - 1] < arr[mid]) {
                    r = mid - 1;
                } else {
                    // 切掉左边, 找右边
                    l = mid + 1;
                }
            }
        }
        return arr[l] < arr[r] ? l : r;
    }

    /**
     * 随机生成相邻不等的数组
     */
    private static int[] randomArray(int maxLen, int maxValue) {
        var len = (int) (Math.random() * maxLen);
        var arr = new int[len];
        if (len > 0) {
            arr[0] = (int) (Math.random() * maxValue);
            for (int i = 1; i < len; i++) {
                do {
                    arr[i] = (int) (Math.random() * maxValue);
                } while (arr[i] == arr[i - 1]);
                // arr[i] == arr[i - 1]相等, 就重做
            }
        }
        return arr;
    }

    private static boolean checkMinIndex(int[] arr, int minIndex) {
        if (null == arr || arr.length == 0) {
            return minIndex == -1;
        }
        var leftIndex = minIndex - 1;
        var rightIndex = minIndex + 1;
        // 判断是否属于有效的下标, 有效的下标比较. 无效的给true
        var leftGreater = leftIndex < 0 || arr[leftIndex] > arr[minIndex];
        var rightGreater = rightIndex >= arr.length || arr[rightIndex] > arr[minIndex];
        return leftGreater && rightGreater;
    }

    private static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
