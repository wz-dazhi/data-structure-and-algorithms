package com.wz.basics;

import java.util.Arrays;

/**
 * @projectName: data-structure-and-algorithms
 * @package: com.wz.basics
 * @className: Dichotomy
 * @description: 二分法
 * @author: zhi
 * @date: 2021/3/26
 * @version: 1.0
 */
public class Dichotomy {

    /**
     * 二分法检索; arr必须有序
     */
    private static boolean isExist(int[] arr, int num) {
        // 边界检查
        if (arr == null || arr.length == 0) {
            return false;
        }
        // 只有一个数据, 比较
        if (arr.length == 1) {
            return arr[0] == num;
        }
        var l = 0;
        var r = arr.length - 1;
        while (l <= r) {
            var mid = (l + r) / 2;
            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] < num) {
                l = mid + 1;
            } else if (arr[mid] > num) {
                r = mid - 1;
            }
        }
        return false;
    }

    /**
     * 暴力对数器
     */
    private static boolean testNumIndex(int[] arr, int num) {
        for (int value : arr) {
            if (value == num) {
                return true;
            }
        }
        return false;
    }

    private static int[] copyArray(int[] arr) {
        var len = arr.length;
        var arrCopy = new int[len];
        // 系统函数copy
        //System.arraycopy(arr, 0, arrCopy, 0, arr.length);

        // 手动copy
        for (int i = 0; i < arr.length; i++) {
            arrCopy[i] = arr[i];
        }
        return arrCopy;
    }

    private static int[] randomSoredArray(int maxLen, int maxValue) {
        var len = (int) (Math.random() * maxLen);
        if (len == 0) {
            return randomSoredArray(maxLen, maxValue);
        }
        var arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        Arrays.sort(arr);
        return arr;
    }

    public static void main(String[] args) {
        var maxLen = 30;
        var maxValue = 100;
        // 二分法测试, 给一个值找出是否存在
//        var b = true;
//        // 测试
//        for (int i = 0; i < 100_000_000L; i++) {
//            var arr = randomSoredArray(maxLen, maxValue);
//            var arr2 = copyArray(arr);
//            var numIndex = (int) (arr.length * Math.random());
//            var num = arr[numIndex];
//            // 对比
//            if (isExist(arr, num) != testNumIndex(arr2, num)) {
//                b = false;
//                System.out.println("对比失败. num: " + num);
//                for (int a : arr) {
//                    System.out.print(a + " ");
//                }
//                System.out.println();
//                for (int a2 : arr2) {
//                    System.out.print(a2 + " ");
//                }
//                break;
//            }
//        }
//        System.out.println(b ? "成功" : "失败");


        // 练习1:
        // 找出(一个) >= num 最左边的下标
        // 例如:
        // {1, 2, 2, 4, 5, 7}
        //  ↑              ↑
        //0 l              r 5
        //        ↑ 2 mid = (0 + 5) / 2
        //结果: ↑ 1
//        for (int i = 0; i < 100_000_000L; i++) {
//            var arr = randomSoredArray(maxLen, maxValue);
//            var arr2 = copyArray(arr);
//            var numIndex = (int) (arr.length * Math.random());
//            var num = arr[numIndex];
//            // 对比
//            if (findNumLessLeftIndex(arr, num) != testFindNumLessLeftIndex(arr2, num)) {
//                b = false;
//                System.out.println("对比失败. num: " + num);
//                for (int a : arr) {
//                    System.out.print(a + " ");
//                }
//                System.out.println();
//                for (int a2 : arr2) {
//                    System.out.print(a2 + " ");
//                }
//                break;
//            }
//        }
//        System.out.println(b ? "成功" : "失败");

        // 练习2:
        // 找出(一个) <= num 最右边的下标; 与上题同理
//        for (int i = 0; i < 100_000_000L; i++) {
//            var arr = randomSoredArray(maxLen, maxValue);
//            var arr2 = copyArray(arr);
//            var numIndex = (int) (arr.length * Math.random());
//            var num = arr[numIndex];
//            // 对比
//            final int numMoreRightIndex = findNumMoreRightIndex(arr, num);
//            final int testFindNumMoreRightIndex = testFindNumMoreRightIndex(arr2, num);
//            if (numMoreRightIndex != testFindNumMoreRightIndex) {
//                b = false;
//                System.out.println("对比失败. num: " + num + ", arr1: " + numMoreRightIndex + ", arr2: " + testFindNumMoreRightIndex);
//                for (int a : arr) {
//                    System.out.print(a + " ");
//                }
//                System.out.println();
//                for (int a2 : arr2) {
//                    System.out.print(a2 + " ");
//                }
//                break;
//            }
//        }
//        System.out.println(b ? "成功" : "失败");
    }

    private static int findNumLessLeftIndex(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        var l = 0;
        var r = arr.length - 1;
        var t = -1;
        while (l <= r) {
            var mid = (l + r) / 2;
            // 更新 t
            if (arr[mid] >= num) {
                t = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return t;
    }

    /**
     * 有序数组暴力遍历, 从小到大最左边>=num 直接返回
     */
    private static int testFindNumLessLeftIndex(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= num) {
                return i;
            }
        }
        return -1;
    }

    private static int findNumMoreRightIndex(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        var l = 0;
        var r = arr.length - 1;
        var t = -1;
        while (l <= r) {
            var mid = (l + r) / 2;
            if (arr[mid] <= num) {
                t = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return t;
    }

    private static int testFindNumMoreRightIndex(int[] arr, int num) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= num) {
                return i;
            }
        }
        return -1;
    }
}
