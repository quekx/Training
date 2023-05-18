package com.qkx.example;

import com.qkx.example.utils.ArrayUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * // 实现方法
 * List<String> split(String src, String splitStr);
 * // 要求：不能直接调用split，但可以使用其它库函数
 * // 结果demo1：
 * param1："asd+++ttt,y33+++666"
 * param2："+++"
 * result：[
 * "asd",
 * "ttt,y33",
 * "666"
 * ]
 * // 结果demo2：
 * param1："oie--wtewosdn--fsdfk--hhkaskd-"
 * param2："--"
 * result：[
 * "oie",
 * "wtewosdn",
 * "fsdfk",
 * "hhkaskd-"
 * ]
 */
public class MeiTuanProblem {

    public static void main(String[] args) {
//        String src = "oie--wtewosdn--fsdfk--hhkaskd-";
//        String splitStr = "--";
        String src = "oiexyxyzxywtewosdn--fsdfk--hhkaskdxyzxy-";
        String splitStr = "xyzxy";
        System.out.println(split(src, splitStr));


        System.out.println(split1(src, splitStr));
        System.out.println(split2(src, splitStr));

//        String p = "abcabcdabcabc";
//        // kmp
//        int[] next = buildNext(p);
//        ArrayUtil.print(next);
//        int[] next2 = buildNext2(p);
//        ArrayUtil.print(next2);
//        ArrayUtil.equal(next, next2);
    }

    static List<String> split1(String src, String splitStr) {
        List<String> ans = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        int i = 0;
        int j = 0;
        while (i < src.length()) {
            int k = i;
            j = 0;
            while (k < src.length() && j < splitStr.length()) {
                if (src.charAt(k) == splitStr.charAt(j)) {
                    k++;
                    j++;
                } else {
                    builder.append(src.charAt(i));
                    break;
                }
            }
            if (j == splitStr.length()) {
                ans.add(builder.toString());
                builder.delete(0, builder.length());
                i = i + splitStr.length();
            } else {
                i++;
            }
        }
//        if (builder.length() != 0) {
//            ans.add(builder.toString());
//        }
        if (builder.length() != 0 || j != 0) {
            ans.add(builder.toString() + splitStr.subSequence(0, j));
        }
        return ans;
    }

    static List<String> split2(String src, String splitStr) {
        int[] next = buildNext(splitStr);
        List<String> ans = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        int i = 0;
        int j = 0;
        while (i < src.length()) {
            if (src.charAt(i) == splitStr.charAt(j)) {
                i++;
                j++;
                if (j == splitStr.length()) {
                    ans.add(builder.toString());
                    builder.delete(0, builder.length());
                    j = 0;
                }
            } else if (j > 0) {
                int nextJ = next[j - 1];
                // split[0, j - nextJ - 1]
                builder.append(splitStr, 0, j - nextJ);
                j = nextJ;
            } else {
                builder.append(src.charAt(i));
                i++;
            }
        }
        if (builder.length() != 0 || j != 0) {
            ans.add(builder.toString() + splitStr.subSequence(0, j));
        }
        return ans;
    }

    private static int[] buildNext(String p) {
        int n = p.length();
        int[] next = new int[n];
        int x = 1;
        int px = 0;
        while (x < n) {
            // px 为 s[0, x - 1] 相同前后缀最大长度
            if (p.charAt(x) == p.charAt(px)) {
                next[x] = px + 1;
                x++;
                px++;
            } else if (px > 0) {
                px = next[px - 1];
            } else {
                next[x] = 0;
                x++;
            }
        }
        return next;
    }

    private static int[] buildNext2(String p) {
        int n = p.length();
        int[] next = new int[n];
        int x = 1;
        while (x < n) {
            // 初始 x 为 s[0, x - 1] 相同前后缀最大长度
            int px = next[x - 1];
            while (px > 0) {
                if (p.charAt(x) == p.charAt(px)) {
                    next[x] = px + 1;
                    break;
                } else {
                    px = next[px - 1];
                }
            }
            if (px == 0) {
                next[x] = p.charAt(0) == p.charAt(x) ? 1 : 0;
            }
            x++;
        }
        return next;
    }

    static List<String> split(String src, String splitStr) {
        List<String> ans = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        int i = 0;
        int j = 0;
        while (i < src.length()) {
            char ch = src.charAt(i);
            if (ch == splitStr.charAt(j)) {
                if (j == splitStr.length() - 1) {
                    ans.add(builder.toString());
                    builder.delete(0, builder.length());
                    j = 0;
                } else {
                    j++;
                }
                i++;
            } else {
                builder.append(splitStr.subSequence(0, j));
                builder.append(ch);
                j = 0;
                i++;
            }
        }
        if (builder.length() != 0 || j != 0) {
            ans.add(builder.toString() + splitStr.subSequence(0, j));
        }
        return ans;
    }
}
