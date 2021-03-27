package com.wz.question;

/**
 * @projectName: data-structure-and-algorithms
 * @package: com.wz.question
 * @className: AAndBRandom
 * @description: a-b 随机数, to d-e随机数
 * @author: zhi
 * @date: 2021/3/25
 * @version: 1.0
 */
public class AAndBRandom {

    /**
     * 等概率返回 3~7   3 4 5 6 7
     */
    private static int f() {
        // [0, 1) 左闭右开 >=0  <1
        //Math.random();
        return (int) (Math.random() * 5) + 3;
    }

    /**
     * 将3~7 随机数只返回 0 1
     */
    private static int f2() {
        var r = f();
        while (r == 5) {
            r = f();
        }
        return r < 5 ? 0 : 1;
    }

    /**
     * 计算 8 ~ 13的二进制位
     */
    private static int f3() {
        // 8二进制位: 1000
        // 13二进制位: 1101
        var r = (f2() << 3) + (f2() << 2) + (f2() << 1) + f2();
        while (r < 8 || r > 13) {
            r = (f2() << 3) + (f2() << 2) + (f2() << 1) + f2();
        }
        return r;
    }

    private static void counts() {
        // 打印 3 ~ 7 的等概率
        var len = 100000000;
        var counts = new int[6];
        for (var i = 0; i < len; i++) {
            var f = f();
            if (f == 3) {
                counts[0]++;
            } else if (f == 4) {
                counts[1]++;
            } else if (f == 5) {
                counts[2]++;
            } else if (f == 6) {
                counts[3]++;
            } else if (f == 7) {
                counts[4]++;
            } else {
                // 其他情况
                counts[5]++;
            }
        }
        for (int i : counts) {
            System.out.println((double) i / (double) len);
        }
    }

    public static void main(String[] args) {
        // counts();

        // 根据f函数3~7随机数, 生成8~13随机数; 不能修改f函数
        // 1. 将3~7弄成 0-1
        System.out.println(f2());
        // 2. 根据0-1生成8~13
        //System.out.println((f2() << 3) + (f2() << 2) + (f2() << 1) + f2());
        System.out.println(f3());

        // 遍历8~13的等概率
        var len = 100000000L;
        var counts = new int[7];
        for (var i = 0; i < len; i++) {
            var f3 = f3();
            if (f3 == 8) {
                counts[0]++;
            } else if (f3 == 9) {
                counts[1]++;
            } else if (f3 == 10) {
                counts[2]++;
            } else if (f3 == 11) {
                counts[3]++;
            } else if (f3 == 12) {
                counts[4]++;
            } else if (f3 == 13) {
                counts[5]++;
            } else {
                counts[6]++;
            }
        }

        for (int c : counts) {
            System.out.println((double) c / (double) len);
        }
    }
}
