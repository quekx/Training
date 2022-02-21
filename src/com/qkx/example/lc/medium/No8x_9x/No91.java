package com.qkx.example.lc.medium.No8x_9x;

/**
 * Created by qkx on 16/5/29.
 */
public class No91 {
    /**
     * You are here!
     * Your runtime beats 91.28% of javasubmissions
     */
    public static int numDecodings(String s) {
        char[] sc = s.toCharArray();

        if (sc.length == 0 || sc[0] == '0') {
            return 0;
        }

        if (sc.length == 1) {
            return 1;
        }

        int[] count = new int[sc.length];
        count[0] = 1;
        if (sc[0] == '1') {
            count[1] = sc[1] >= '1' ? 2 : 1;
        } else if (sc[0] == '2') {
            count[1] = sc[1] >= '1' && sc[1] <= '6' ? 2 : 1;
        } else if (sc[1] == '0') {
            return 0;
        } else {
            count[1] = 1;
        }
        char last = sc[1];

        for (int i = 2; i < sc.length; i++) {
            switch (last) {
                case '1' :
                    if (sc[i] >= '1') {
                        count[i] = count[i - 1] + count[i - 2];
                    } else {
                        count[i] = count[i - 2];
                    }
                    break;
                case '2' :
                    if (sc[i] >= '1' && sc[i] <= '6') {
                        count[i] = count[i - 1] + count[i - 2];
                    } else if (sc[i] == '0') {
                        count[i] = count[i - 2];
                    } else {
                        count[i] = count[i - 1];
                    }
                    break;
                case '0' :
                    if (sc[i] == '0') {
                        return 0;
                    } else {
                        count[i] = count[i - 1];
                    }
                    break;
                default:
                    if (sc[i] == '0') {
                        return 0;
                    } else {
                        count[i] = count[i - 1];
                    }
                    break;
            }
            last = sc[i];
        }
        return count[sc.length - 1];
    }
}
