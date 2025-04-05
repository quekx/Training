package com.qkx.example.lc.achive.easy.No6x_7x;

/**
 * Created by qkx on 16/5/26.
 */
public class No67 {
    public static String addBinary(String a, String b) {

        int aLen = a.length();
        int bLen = b.length();

        char[] ac = a.toCharArray();
        char[] bc = b.toCharArray();

        StringBuilder builder = new StringBuilder();

        int c = 0;
        for (int i = 0; i < Math.max(aLen, bLen); i++) {
            int ai = i < aLen ? ac[aLen - 1 - i] - '0' : 0;
            int bi = i < bLen ? bc[bLen - 1 - i] - '0' : 0;
            switch (ai + bi + c) {
                case 0:
                    builder.append('0');
                    c = 0;
                    break;
                case 1:
                    builder.append('1');
                    c = 0;
                    break;
                case 2:
                    builder.append('0');
                    c = 1;
                    break;
                case 3:
                    builder.append('1');
                    c = 1;
                    break;
            }
            if (i == Math.max(aLen, bLen) - 1 && c == 1) {
                builder.append('1');
            }
        }

        return builder.reverse().toString();
    }
}
