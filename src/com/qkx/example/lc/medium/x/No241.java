package com.qkx.example.lc.medium.x;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by qkx on 17/4/30.
 */
public class No241 {
    /**
     * Given a string of numbers and operators, return all possible results
     * from computing all the different possible ways to group numbers and operators.
     * The valid operators are +, - and *.
     *
     * Example 1
     * Input: "2-1-1".
     *
     * ((2-1)-1) = 0
     * (2-(1-1)) = 2
     *
     * Output: [0, 2]
     */

    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> result = new LinkedList<>();
        if (input == null || input.length() == 0) return result;



        return result;
    }

    private List<Integer> find(String input, int start) {

        int num = 0;
        for (int i = start; i <= input.length() - 1; i++) {
            char cur = input.charAt(i);
            if (cur >= '0' && cur <= '9') {
                num = num * 10 + cur - '0';
            } else {
                List<Integer> next = find(input, i + 1);
                break;
            }
        }

        return null;
    }

    private int calculate(int a, int b, char operator) {
        if (operator == '+') return a + b;
        if (operator == '-') return a - b;
        if (operator == '*') return a * b;
        throw new IllegalStateException("operator is illegal!");
    }
}
