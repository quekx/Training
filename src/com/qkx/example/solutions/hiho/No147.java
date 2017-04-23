package com.qkx.example.solutions.hiho;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;

/**
 * Created by qkx on 17/4/23.
 */
public class No147 {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Scanner sc = new Scanner(System.in);
        int courseNum = 5;
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[][] turns = new int[n][courseNum];
            for (int i = 0; i < n; i++) {
                for (int k = 0; k < courseNum; k++) {
                    turns[i][k] = sc.nextInt();
                }
            }

            for (int i = 0; i < n; i++) {
                BitSet bitSet = null;
                for (int k = 0; k < courseNum; k++) {
                    BitSet cur = new BitSet();
                    for (int j = 0; j < n; j++) {
                        if (turns[i][k] > turns[j][k]) {
                            cur.set(j);
                        }
                    }

                    if (bitSet == null) {
                        bitSet = cur;
                    } else {
                        bitSet.and(cur);
                    }
                }

                if (bitSet == null) {
                    System.out.println(0);
                } else {
                    System.out.println(bitSet.cardinality());
                }
            }
        }
    }
}
