package com.qkx.example.lc.medium;

//Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9,
// 10, 11, ...
//
// Note:
//n is positive and will fit within the range of a 32-bit signed integer (n < 23
//1).
//
//
// Example 1:
//
//Input:
//3
//
//Output:
//3
//
//
//
// Example 2:
//
//Input:
//11
//
//Output:
//0
//
//Explanation:
//The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0,
//which is part of the number 10.
//
// Related Topics Math
// 👍 418 👎 1141


//leetcode submit region begin(Prohibit modification and deletion)
public class No400 {
    public static int findNthDigit(int n) {
        if (n <= 9) {
            return n;
        }

        // 分组(1 ~ 9) (10 ~ 99) (100 ~ 999) (1000 ~ 9999)
        // 位数
        int weight = 1;
        // 相同位数为一组
        // 统计数字的个数 9, 90, 900, 9000
        long multiple = 1;
        while (n > 9 * multiple * weight) {
            n = n - (int) (9 * multiple * weight);
            weight += 1;
            multiple *= 10;
            System.out.println("n: " + n + ", multiple: " + multiple + ", weight: " + weight);
        }

        // x为一组内的第几个数
        int x = (n - 1) / weight + 1;
        // y为x的具体第几位
        int y = n - (x - 1) * weight;
        // num为数值
        // multiple 为一组内的第一个数
        long num = multiple + (x - 1);
        // 最后转换为，数据num的第y位数字
        System.out.println("num: " + num + ", x: " + x + ", y: " + y + ", multiple: " + multiple);
        long r = 0;
        for (int i = 1; i <= y; i++) {
            r = num / multiple;
            num -= r * multiple;
            multiple /= 10;
        }
        return (int) r;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

