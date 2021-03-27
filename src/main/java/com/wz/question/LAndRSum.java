package com.wz.question;

import java.util.HashMap;
import java.util.Map;

/**
 * @projectName: data-structure-and-algorithms
 * @package: com.wz.question
 * @className: LAndRSum
 * @description: 一个数组, 计算 L - R 的和
 * @author: zhi
 * @date: 2021/3/24
 * @version: 1.0
 */
public class LAndRSum {

    /**
     * 给定一个数组, 求 L - R的和
     * 比如:
     * {4, 5, 2, 7, 3}
     * 0  1  2  3  4
     * 计算 0 - 2的和
     * 4+5+2=11
     * 计算 2 - 4的和
     * 2+7+3=12
     */
    public static void main(String[] args) {
        var arr = new int[]{4, 5, 2, 7, 3};
        var l = 1;
        var r = 4;
        System.out.println(new Sum1(arr).sum(l, r));
        System.out.println(new Sum2(arr).sum(l, r));
        System.out.println(new Sum3(arr).sum(l, r));
    }

    /**
     * 遍历计算
     * 从L 遍历到 R 计算累加和
     */
    private static class Sum1 {
        private int[] arr;

        public Sum1(int[] arr) {
            this.arr = arr;
        }

        public int sum(int l, int r) {
            // 边界处理, l 必须大于等于0, l 必须小于等于 r, r 不能大于 arr.length
            if (l < 0 || l > r || r >= arr.length) {
                return 0;
            }
            var sum = 0;
            for (var i = l; i <= r; i++) {
                sum += arr[i];
            }
            return sum;
        }
    }

    /**
     * {4, 5, 2, 7, 3}
     * 0  1  2  3  4
     * 创建一个表, 存储L - R 的和; 浪费 n^2/2 (二分之n的平方)
     * 行: R
     * 列: L
     * *** 0  1  2  3  4
     * **----------------
     * 0 | 4| 9|11|18|21|
     * **----------------
     * 1 | x| 5| 7|14|17|
     * **----------------
     * 2 | x| x| 2| 9|12|
     * **----------------
     * 3 | x| x| x| 7|10|
     * **----------------
     * 4 | x| x| x| x| 3|
     * **---------------
     */
    private static class Sum2 {
        private int[] arr;
        private Map<String, Integer> SUM_MAP = new HashMap<>();

        public Sum2(int[] arr) {
            this.arr = arr;

            for (var i = 0; i < arr.length; i++) {
                var sum = 0;
                for (var j = i; j < arr.length; j++) {
                    sum += arr[j];
                    SUM_MAP.put(i + "-" + j, sum);
                }
            }
        }

        public int sum(int l, int r) {
            // 边界处理, l 必须大于等于0, l 必须小于等于 r, r 不能大于 arr.length
            if (l < 0 || l > r || r >= arr.length) {
                return 0;
            }
            return SUM_MAP.get(l + "-" + r);
        }
    }

    /**
     * 使用1维数组计算L - R 的累加和
     * {4, 5, 2, 7, 3}
     * *0  1  2  3  4
     * 计算结果:
     * *4  9  11 18 21
     * <p>
     * 取L - R的累加和
     * 当L == 0 时, R == i时, 取sum[i]; 比如: L=0, R == 3, sum[3]=18
     * 当L > 0时, R == i, 取sum[i] - sum[L - 1]; 比如: L=1, R == 3, sum[3] - sum[1 - 1]= 18-4=14
     */
    private static class Sum3 {
        private int[] arr;
        private int[] sums;

        public Sum3(int[] arr) {
            this.arr = arr;
            this.sums = new int[arr.length];

            var sum = 0;
            for (int i = 0; i < arr.length; i++) {
                sum += arr[i];
                sums[i] = sum;
            }
        }

        public int sum(int l, int r) {
            // 边界处理, l 必须大于等于0, l 必须小于等于 r, r 不能大于 arr.length
            if (l < 0 || l > r || r >= arr.length) {
                return 0;
            }
            return l == 0 ? sums[r] : sums[r] - sums[l - 1];
        }
    }
}
