package com.qkx.example;

import java.util.*;

/**
 * Created by qkx on 16/5/10.
 */
public class Solution {
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        if (nums[0] >= target) {
            return 0;
        } else if (nums[len - 1] == target) {
            return len - 1;
        } else if (nums[len - 1] < target) {
            return len;
        }
        int low = 0;
        int high = len - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public boolean isValidSudoku(char[][] board) {
//        Map<Integer, Set<Character>> rowMap = new HashMap<>();
        Map<Integer, Set<Character>> colMap = new HashMap<>();
        Map<Integer, Set<Character>> blockMap = new HashMap<>();

        int rowLen = board.length;
        for (int y = 0; y < rowLen; y++) {
            char[] subBoard = board[y];
            int colLen = subBoard.length;
            Set<Character> rowSet = new HashSet<>();
            for (int x = 0; x < colLen; x++) {
                char c = subBoard[x];
                if (c != '.') {
                    // check row
                    if (rowSet.contains(c)) {
                        return false;
                    } else {
                        rowSet.add(c);
                    }
                    // check col
                    Set<Character> colSet = colMap.get(x);
                    if (colSet == null) {
                        colSet = new HashSet<>();
                    }
                    if (colSet.contains(c)) {
                        return false;
                    } else {
                        colSet.add(c);
                        colMap.put(x, colSet);
                    }
                    // check block
                    int blockId = (y / 3) * 3 + (x / 3);
                    Set<Character> blockSet = blockMap.get(blockId);
                    if (blockSet == null) {
                        blockSet = new HashSet<>();
                    }
                    if (blockSet.contains(c)) {
                        return false;
                    } else {
                        blockSet.add(c);
                        blockMap.put(blockId, blockSet);
                    }
                }
            }
        }
        return true;
    }

    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String[] res = new String[n + 1];
        res[1] = "1";
        for (int i = 2; i <= n; i++) {
            String s = res[i - 1];
            int count = 0;
            char currentChar = '#';
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (c != currentChar) {
                    if (count != 0) {
                        builder.append((char)(count + 48));
                        builder.append(currentChar);
                    }
                    count = 1;
                    currentChar = c;
                } else {
                    count++;
                }
            }
            builder.append((char)(count + 48));
            builder.append(currentChar);

            res[i] = builder.toString();
        }
        return res[n];
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new LinkedList<>();

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < candidates.length; i++) {
            set.add(candidates[i]);

            if (candidates[i] == target) {
                List<Integer> list = new LinkedList<>();
                list.add(candidates[i]);
                res.add(list);
            }
        }

        List<Integer> temp = new LinkedList<>();
        f(candidates, target, temp, res, set, 0);

        return res;
    }
    private void f(int[] candidates, int target, List<Integer> temp, List<List<Integer>> res, Set<Integer> set, int position) {
        for (int i = position; i < candidates.length && candidates[i] * 2 <= target; i++) {
            int n = candidates[i];
            int remain = target - n;

            if (remain == n) {
                List<Integer> list = new LinkedList<>(temp);
                list.add(n);
                list.add(remain);
                res.add(list);
            } else {
                List<Integer> list = new LinkedList<>(temp);
                list.add(n);
                f(candidates, remain, list, res, set, i);
                if (set.contains(remain)) {
                    list.add(remain);
                    res.add(list);
                }
            }
        }
    }

    public List<List<Integer>> combinationSumAnotherSolution(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new LinkedList<>();

        List<Integer> temp = new LinkedList<>();
        f2(candidates, target, temp, res,  0);

        return res;
    }
    private void f2(int[] candidates, int target, List<Integer> temp, List<List<Integer>> res, int position) {
        for (int i = position; i < candidates.length; i++) {
            int n = candidates[i];
            int remain = target - n;

            if (remain == 0) {
                List<Integer> list = new LinkedList<>(temp);
                list.add(n);
                res.add(list);
            } else if (remain >= n){
                List<Integer> list = new LinkedList<>(temp);
                list.add(n);
                f2(candidates, remain, list, res, i);
            }
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new LinkedList<>();

        List<Integer> temp = new LinkedList<>();
        f3(candidates, target, temp, res,  0);

        return res;
    }
    private void f3(int[] candidates, int target, List<Integer> temp, List<List<Integer>> res, int position) {
        int last = 0;
        for (int i = position; i < candidates.length; i++) {
            if (last == candidates[i]) {
                continue;
            }

            int n = candidates[i];
            int remain = target - n;
            last = n;

            if (remain == 0) {
                List<Integer> list = new LinkedList<>(temp);
                list.add(n);
                res.add(list);
            } else if (remain >= n) {
                List<Integer> list = new LinkedList<>(temp);
                list.add(n);
                f3(candidates, remain, list, res, i + 1);
            }
        }
    }

    // Given a collection of distinct numbers, return all possible permutations.
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        List<Integer> temp = new LinkedList<>();
        f(temp, res, set);

        return res;
    }
    private void f(List<Integer> temp, List<List<Integer>> res, Set<Integer> remain) {
        for (Integer i : remain) {
            List<Integer> tempi = new LinkedList<>(temp);
            Set<Integer> remaini = new HashSet<>(remain);
            tempi.add(i);
            remaini.remove(i);
            if (remaini.isEmpty()) {
                res.add(tempi);
            } else {
                f(tempi, res, remaini);
            }
        }
    }

    // Given a collection of numbers that might contain duplicates, return all possible unique permutations.
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> pre = new LinkedList<>();

        perm(nums, 0, nums.length - 1, pre, res);

        return res;
    }
    private void perm(int[] nums, int start, int end, List<Integer> pre, List<List<Integer>> res) {
        if (start == end) {
            List<Integer> t = new LinkedList<>(pre);
            t.add(nums[start]);
            res.add(t);
            return;
        }

        Set<Integer> set = new HashSet<>();
        int i = start;
        while (i <= end) {
            if (!set.contains(nums[i])) {
                int temp = nums[i];
                nums[i] = nums[start];
                nums[start] = temp;

                set.add(temp);

                List<Integer> t = new LinkedList<>(pre);
                t.add(temp);
                perm(nums, start + 1, end, t, res);

                nums[start] = nums[i];
                nums[i] = temp;
            }
            i++;
        }
    }

    /**
     * You are given an n x n 2D matrix representing an image.
     * Rotate the image by 90 degrees (clockwise).
     * Follow up:
     * Could you do this in-place?
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int indexMax = n - 1;
        for (int x = 0; x < n / 2; x++) {
            for (int y = 0; y <= indexMax / 2; y++) {
                int temp = matrix[y][x];

                matrix[y][x] = matrix[indexMax - x][y];
                matrix[indexMax - x][y] = matrix[indexMax - y][indexMax - x];
                matrix[indexMax - y][indexMax - x] = matrix[x][indexMax - y];
                matrix[x][indexMax - y] = temp;
            }
        }

    }

    /**
     * Group Anagrams
     * Given an array of strings, group anagrams together.
     * For the return value, each inner list's elements must follow the lexicographic order.
     * All inputs will be in lower-case.
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map map = new HashMap<>();
//        Collections.sort();
        return null;
    }

}
