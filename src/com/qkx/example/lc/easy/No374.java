package com.qkx.example.lc.easy;

/**
 * Created by qkx on 17/7/5.
 */
public class No374 {
    public int guessNumber(int n) {
        if (n <= 0) return 0;

        int left = 1;
        int right = n;
        int middle;
        while (left <= right) {
            middle = left + ((right - left) >> 1);
            switch (guess(middle)) {
                case 0:
                    return middle;
                case 1:
                    right = middle - 1;
                    break;
                case -1:
                    left = middle + 1;
                    break;
            }
        }

        return 0;
    }

    private int guess(int num) {
        return 0;
    }
}
