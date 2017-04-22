package com.qkx.example;

import com.qkx.example.exercise.algorithm.list.ListMethod;
import com.qkx.example.exercise.algorithm.sorted.*;
import com.qkx.example.exercise.treenew.MyRBTree;
import com.qkx.example.model.ListNode;
import com.qkx.example.solutions.AlibabaTakeStoneGame;
import com.qkx.example.solutions.easy.No190;
import com.qkx.example.solutions.medium.*;
import com.qkx.example.solutions.medium.No12x_13x.No134;
import com.qkx.example.utils.CharUtil;
import com.qkx.example.utils.NumberUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {

    public static void main(String[] args) {
//        testPre();
//        testX();
//        testY();

//        testTree();

//        testDfs();
//        No145.main(args);
        String s = '\ufeff' + "";
        System.out.println(s.length());

        System.out.println(AlibabaTakeStoneGame.solve(20));
    }

    private static void testDfs() {
        String[] strings = new String[]{"11110", "11010", "11000", "00000"};
        char[][] grid = CharUtil.getChars(strings);
        System.out.println(No200.numIslands(grid));
    }

    private static void testTree() {
        MyRBTree myRBTree = new MyRBTree();
        myRBTree.insert(1);
        myRBTree.insert(2);
        myRBTree.insert(3);
        myRBTree.insert(4);
        myRBTree.insert(5);
        myRBTree.insert(6);


        System.out.println(No190.reverseBits(43261596));
    }

    private static void testY() {
//        int[] nums = new int[] {2, 3, 1, 2, 4, 3};
//        System.out.println(No209.minSubArrayLen(7, nums));

//        List<List<Integer>> list = No216.combinationSum3(3, 11);
//        System.out.println(list);

//        Integer
    }

    private static void testX() {
        BlockingDeque blockingDeque = new LinkedBlockingDeque<>();


//        List list = new LinkedList<>();
//        new Thread(new MyConsumer(list), "consumer").start();
//        new Thread(new MyProducer(list, 5), "producer1").start();
//        new Thread(new MyProducer(list, 5), "producer2").start();

//        MyBlockingList myBlockingList = new MyBlockingList(5);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    myBlockingList.add(1);
//                }
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    myBlockingList.remove();
//                }
//            }
//        }).start();
    }

    private static void testPre() {
        //        Solution s = new Solution();
//        System.out.println(s.searchInsert(new int[]{1,3,5,7}, 9));
//
//        System.out.println(new Integer(123).hashCode());
//
//        // HashSet test
//        // 添加对象时,会调用hashCode()和equal()方法,定义对象时需重写
//        // 缺一不可
//
//        Set<String> set1 = new HashSet<>();
//        set1.add("123");
//        set1.add("123");
//        set1.add(new String("123"));
//
//        Set<String> set2 = new HashSet<>();
//        set2.addAll(set1);
//
//        Set<String> set3 = new HashSet<>(set1);
//        set1.remove("123");
//
//        System.out.println("set1-->" + set1.size());
//        System.out.println("set2-->" + set2.size());
//        System.out.println("set3-->" + set3.size());
//
//        List<List<Integer>> l = s.permuteUnique(new int[]{1, 1, 2, 3});
//
//        String[] aa = "insert       hello".split("");
//
//        aa[0] = "";
//
//        char A = 88;
//
//        System.out.println(A);
//
//        float f = 0.5f;
//        int f1 = Float.floatToIntBits(f);
//        System.out.println(f1);
//        System.out.println(Integer.toBinaryString(f1));
//
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Long.MAX_VALUE);
//
//        System.out.println();
//
//        String sss = "/123/12345/1";
//        String[] ssss = sss.split("/");
//
//        System.out.println("123".substring(0, 3));
//
//        System.out.println((int) Character.MAX_VALUE);
//        System.out.println((int) Character.MIN_VALUE);
//        System.out.println(Integer.MAX_VALUE);
//
//        TreeNode root = new TreeNode('A');
//        TreeNode nb = new TreeNode('B');
//        TreeNode nc = new TreeNode('C');
//        TreeNode nd = new TreeNode('D');
//        TreeNode ne = new TreeNode('E');
//        TreeNode nf = new TreeNode('F');
//        root.left = nb;
//        root.right = nc;
//        nb.left = nd;
//        nb.right = ne;
//        nc.left = nf;
//
//
//        MyTreeTraversal.inorderTraverse(root);
//        MyTreeTraversal.preorderTraverse(root);
//        MyTreeTraversal.postorderTraverse(root);
//
//        System.out.println(No93.restoreIpAddresses("1111"));

//        A aa = new B();
//        aa.f();
//
//        B bb = new B();
//        A ab = bb;
//        ab.f();
//
//
//        int key = 1;
//        System.out.println((-key % 10));
//        System.out.println((char) 53);
//        System.out.println((char) 50);
//        System.out.println((char) 48);
//
//        System.out.println(3 + (9 - 3) >> 1);
//        System.out.println(3 + ((9 - 3) >> 1));


//        testGet();
//        System.out.println(123);
////        ArrayList a = new ArrayList();
////        a.get(1);
//        TreeNode root = new TreeNode(-40);
//        root.left = new TreeNode(50);
//        root.right = new TreeNode(80);
//        System.out.println((int) 'P');
//        System.out.println((int) '0');
//
//        String ss = "123\n";
//        for (String sb : ss.split("\n")) {
//            System.out.println(ss.split("\n").length);
//        }
//
//        int Ai = 0, B = 0;
//
//        int A1 = 0;
//        A1 = Ai & ~B ^ A1;   // X ^ Y
//        B = Ai & ~A1 ^ B;    // X & ~Y
//
//        //["hot","dot","dog","lot","log"]
//        Set<String> set = new HashSet<>();
//        set.add("hot");
//        set.add("dot");
//        set.add("dog");
//        set.add("lot");
//        set.add("log");
//        System.out.println(set.remove("2454535"));
//        System.out.println(No127.ladderLength33("hit", "cog", set));
//        System.out.println(No127.ladderLength33("hot", "log", set));


//        int N = 25;
//        double res = 800;
//        for (int i = 0; i < N; i++) {
//            res = 1.02 * res + 5;
////            System.out.println("res >> " + res);
//        }
//        System.out.println("res >> " + res);

//        sort();

//        System.out.println("test is " + test());

//        A a = null;
//        A b = null;
//        b = new A();
//        System.out.print(a == b);

//        byte b = Byte.MAX_VALUE;
//        short c = Short.MAX_VALUE;
//        int d = Integer.MAX_VALUE;
//        System.out.println(b);
//        System.out.println(c);
//        System.out.println(d);

//        System.out.println(getNum());

//        System.out.println(~0);
//        System.out.println(Integer.toBinaryString(~0));


//        int[] nums = new int[]{-4, -3, -2};
//        System.out.println(No152.maxProduct(nums));
//
//        /**
//         * 1 0 1 0 0
//         * 1 0 1 1 1
//         * 1 1 1 1 1
//         * 1 0 0 1 0
//         */
//        char[][] matrix = new char[][]{
//                {'1', '0', '1', '0', '0'},
//                {'1', '0', '1', '1', '1'},
//                {'1', '1', '1', '1', '1'},
//                {'1', '0', '0', '1', '0'}
//        };
//        System.out.println(No221.maximalSquare(matrix));
//
//        String s = ")(()())";
//        System.out.println(No32.longestValidParentheses(s));
//
//        UndirectedGraphNode node = new UndirectedGraphNode(0);
//        node.neighbors.add(node);
//        node.neighbors.add(node);
//
//        UndirectedGraphNode newNode = No133.cloneGraph(node);
//        System.out.println();
//
//        System.out.println("xxxxx");
////        System.gc();
//
//        StringBuilder builder = new StringBuilder();
//        builder.append(1);
//        builder.append(2);
//        builder.append(50);
//        System.out.println(No166.fractionToDecimal(-22, -2));
//
//
////        gas();
//
//        listTest();
    }

    private static void listTest() {
        ListNode l1 = ListMethod.getList(10);
        System.out.println(ListMethod.toListString(l1));

        ListNode l2 = ListMethod.invert(l1);
        System.out.println(ListMethod.toListString(l2));
    }

    private static void gas() {
        int[] gas = NumberUtil.getRandomNumbers(10000);
        int[] cost = NumberUtil.getRandomNumbers(10000);
        System.out.println("gas :: " + No134.canCompleteCircuit(gas, cost));
    }

    private static int getNum() {
        try {
            int a = 1 / 0;
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            return 3;
        }
    }

    private static void sort() {
        int[] nums = getRandomNums(100);
//        int[] nums = getSortedNums(100);
        PopSorted.popSortedWithFlag(nums);
        PopSorted.popSortedWithLastPos(nums);
        QuickSorted.quickSorted(nums, 0, nums.length - 1);
        MergeSorted.mergeSorted(nums, 0, nums.length - 1, new int[nums.length]);
        InsertSorted.insertSort(nums);
        SelectSorted.selectSorted(nums);
        HeapSorted.heapSorted(nums);
        ShellSorted.shellSorted(nums);
        printNums(nums);
    }

    private static int test() {
        int a = 11;
        try {
            a = 22;
            System.out.println("try is " + a);
            int b = 1 / 0;
            return a;
        } catch (Exception e) {
            a = 33;
            System.out.println("catch is " + a);
            return a;
        } finally {
            a = 44;
            System.out.println("finally is " + a);
        }
    }

    private static void printNums(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    private static int[] getRandomNums(int n) {
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = (int) (Math.random() * 1000);
        }
        return res;
    }

    private static int[] getSortedNums(int n) {
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = i;
        }
        return res;
    }

    private static void testGet() {
        try {
            URL url = new URL("http://www.baidu.com");
            URLConnection connection = url.openConnection();

            InputStream inputStream = connection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }

            System.out.println(stringBuffer.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
