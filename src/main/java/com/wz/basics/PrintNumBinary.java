package com.wz.basics;

/**
 * @projectName: data-structure-and-algorithms
 * @package: com.wz.basics
 * @className: PrintNumBinary
 * @description: 打印数字的二进制
 * @author: zhi
 * @date: 2021/3/16
 * @version: 1.0
 */
public class PrintNumBinary {

    private static void printInt(int num) {
        for (int i = 31; i >= 0; i--) {
            // 有符号向左移动 i位
            final int n = num & (1 << i);
            System.out.print(n == 0 ? "0" : "1");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int num = 1;
        printInt(num);
    }
}
