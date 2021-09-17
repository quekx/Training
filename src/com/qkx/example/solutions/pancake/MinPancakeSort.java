package com.qkx.example.solutions.pancake;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author kaixin
 * @since 2021-04-12 上午11:17
 */
public class MinPancakeSort {

    /**
     * n 遍历次数量级
     * 3 -> 43
     * 4 -> 1756
     * 5 -> 342168
     * 6 -> 60558574
     * @param args
     */
    public static void main(String[] args) {
        int[] pancakes = new int[4];
        for (int i = 0; i < pancakes.length; i++) {
            pancakes[i] = new Random().nextInt(10000);
        }
        System.out.println(Arrays.toString(pancakes));
        System.out.println("--------------------");
        new MinPancakeSort().sort(pancakes);
        System.out.println("--------------------");
        System.out.println(Arrays.toString(pancakes));
    }

    /**
     * 最短的路径搜索
     * @param pancakes
     */
    public void sort(int[] pancakes) {
        PancakeSorter pancakeSorter = new PancakeSorter(pancakes);
        pancakeSorter.search();
        pancakeSorter.printMinOptList();
    }
}

/**
 * 通过递归生成搜索树+调用剪枝减小递归次数
 */
class PancakeSorter {

    private final int[] pancakes;
    /**
     * 遍历到当前位置，最小次数记录
     * @return
     */
    private int minOptTime;
    /**
     * 最大的操作次数，用于遍历剪枝
     * 最大次数为 2 * (len - 1)
     * 即通过2次翻转，每次把最大的数翻转到最后
     * @return
     */
    private int maxOptTime;
    private Object[] minOptList;
    private Object[] minProcess;

    private int currentOptTime = 0;
    private List<Integer> curOptList;
    private List<int[]> curProcess;

    private int searchTime = 0;

    public PancakeSorter(int[] pancakes) {
        this.pancakes = pancakes;
        minOptTime = Integer.MAX_VALUE;
        minOptList = new Object[0];
        minProcess = new Object[0];

        maxOptTime = 2 * (pancakes.length - 1);
        curOptList = new LinkedList<>();
        curProcess = new LinkedList<>();
    }

    public void search() {
        if (isOk()) {
            if (currentOptTime < minOptTime) {
                minOptTime = currentOptTime;
                minOptList = curOptList.toArray();
                minProcess = curProcess.toArray();
            }
            return;
        }

        searchTime++;

        // 超过操作次数，剪枝
        if (currentOptTime > maxOptTime) {
            return;
        }

        // 遍历
        for (int i = 1; i < pancakes.length; i++) {
            reverse(i);
            curOptList.add(i);
            currentOptTime++;
            curProcess.add(pancakes.clone());
//            System.out.println(Arrays.toString(pancakes) + " " + currentOptTime);

            search();

            reverse(i);
            curOptList.remove(curOptList.size() - 1);
            curProcess.remove(curProcess.size() - 1);
            currentOptTime--;
        }
    }

    private void reverse(int end) {
        int start = 0;
        while (start < end) {
            swap(start, end);
            start++;
            end--;
        }
    }

    private void swap(int a, int b) {
        int t = pancakes[a];
        pancakes[a] = pancakes[b];
        pancakes[b] = t;
    }

    private boolean isOk() {
        for (int i = 1; i < pancakes.length; i++) {
            if (pancakes[i - 1] > pancakes[i]) {
                return false;
            }
        }
        return true;
    }

    public void printMinOptList() {
        System.out.println(Arrays.toString(minOptList));
        for (Object process : minProcess) {
            System.out.println(Arrays.toString((int[]) process));
        }
        System.out.println(searchTime);
    }
}
