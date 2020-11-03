package com.qkx.example.solutions.hiho;

import java.util.Scanner;

public class No1993 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int N = sc.nextInt();
            int C = sc.nextInt();
            int[] a = new int[N];
            int[] b = new int[N];
            for (int k = 0; k < N; k++) {
                a[k] = sc.nextInt();
                b[k] = sc.nextInt();
            }

            int curDiscount = 0;
            int nextDiscount = 0;
            int gap = 0;
            for (int k = 0; k < N; k++) {
                if (C >= a[k]) {
                    curDiscount = b[k];
                } else {
                    gap = a[k] - C;
                    nextDiscount = b[k];
                    break;
                }
            }
            System.out.println(curDiscount + " " + gap + " " + nextDiscount);
        }
    }
}