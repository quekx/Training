package com.qkx.example.solutions.LeetCode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kaixin
 * @since 2020-12-15 20:47
 */
//Suppose we have a file system that stores both files and directories. An examp
//le of one system is represented in the following picture:
//
//
//
// Here, we have dir as the only directory in the root. dir contains two subdire
//ctories, subdir1 and subdir2. subdir1 contains a file file1.ext and subdirectory
// subsubdir1. subdir2 contains a subdirectory subsubdir2, which contains a file f
//ile2.ext.
//
// In text form, it looks like this (with ⟶ representing the tab character):
//
//
//dir
//⟶ subdir1
//⟶ ⟶ file1.ext
//⟶ ⟶ subsubdir1
//⟶ subdir2
//⟶ ⟶ subsubdir2
//⟶ ⟶ ⟶ file2.ext
//
//
// If we were to write this representation in code, it will look like this: "dir
//
//\tsubdir1
//\t\tfile1.ext
//\t\tsubsubdir1
//\tsubdir2
//\t\tsubsubdir2
//\t\t\tfile2.ext". Note that the '
//' and '\t' are the new-line and tab characters.
//
// Every file and directory has a unique absolute path in the file system, which
// is the order of directories that must be opened to reach the file/directory its
//elf, all concatenated by '/'s. Using the above example, the absolute path to fil
//e2.ext is "dir/subdir2/subsubdir2/file2.ext". Each directory name consists of le
//tters, digits, and/or spaces. Each file name is of the form name.extension, wher
//e name and extension consist of letters, digits, and/or spaces.
//
// Given a string input representing the file system in the explained format, re
//turn the length of the longest absolute path to a file in the abstracted file sy
//stem. If there is no file in the system, return 0.
//
//
// Example 1:
//
//
//Input: input = "dir
//\tsubdir1
//\tsubdir2
//\t\tfile.ext"
//Output: 20
//Explanation: We have only one file, and the absolute path is "dir/subdir2/file
//.ext" of length 20.
//
//
// Example 2:
//
//
//Input: input = "dir
//\tsubdir1
//\t\tfile1.ext
//\t\tsubsubdir1
//\tsubdir2
//\t\tsubsubdir2
//\t\t\tfile2.ext"
//Output: 32
//Explanation: We have two files:
//"dir/subdir1/file1.ext" of length 21
//"dir/subdir2/subsubdir2/file2.ext" of length 32.
//We return 32 since it is the longest absolute path to a file.
//
//
// Example 3:
//
//
//Input: input = "a"
//Output: 0
//Explanation: We do not have any files, just a single directory named "a".
//
//
// Example 4:
//
//
//Input: input = "file1.txt
//file2.txt
//longfile.txt"
//Output: 12
//Explanation: There are 3 files at the root directory.
//Since the absolute path for anything at the root directory is just the name it
//self, the answer is "longfile.txt" with length 12.
//
//
//
// Constraints:
//
//
// 1 <= input.length <= 104
// input may contain lowercase or uppercase English letters, a new line characte
//r '
//', a tab character '\t', a dot '.', a space ' ', and digits.
//
// 👍 661 👎 1607


//leetcode submit region begin(Prohibit modification and deletion)
public class No388 {

    public static void main(String[] args) {
        String s = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        System.out.println(new No388().lengthLongestPath(s));
    }

    public int lengthLongestPath(String input) {
        int max = 0;
        int i = 0;
        // 目前为止，前面几层的长度
        // level -> length
        Map<Integer, Integer> lengthPre = new HashMap<>();
        Map<Integer, String> strPre = new HashMap<>();
        while (i < input.length()) {
            int level = genLevel(input, i);

            int len = 0;
            boolean isFile = false;
            int start = i + level;
            for (; start < input.length(); start++) {
                char x = input.charAt(start);
                if (x == '\n') {
                    break;
                }
                len++;
                if (x == '.') {
                    isFile = true;
                }
            }
            int preLen = lengthPre.getOrDefault(level, 0);
            int curLen = preLen + len + 1;
            if (isFile) {
                max = Math.max(max, curLen);
            } else {
                lengthPre.put(level + 1, curLen);
            }
            i = start + 1;
        }
        return Math.max(max - 1, 0);
    }

    private int genLevel(String s, int i) {
        int level = 0;
        for (; i < s.length(); i++) {
            if (s.charAt(i) != '\t') {
                break;
            }
            level++;
        }
        return level;
    }

    /**
     * 遍历层级 level 对应起始需要多少个\t
     * level0: 0个tab
     * level1: 1个tab
     * index 成员变量维护当前字符串的指针
     *
     * 开始时校验层级和开头的tab数，不满足就返回
     * tab 校验通过，index 开始遍历，遍历到\n
     * 期间如果遇到.，则代表是文件
     *
     * @param level
     * @param pre
     * @param s
     * @return
     */
    int index;
    private void traverse(int level, int pre, String s) {
        int start = index;
        // tab 个数校验 == level
        for (; start < level; start++) {
            if (s.charAt(start) != '\t') {
                return;
            }
        }
        boolean isFile = false;
        // 通过开始遍历
        for (; index < s.length(); index++) {
            char x = s.charAt(index);
            if (x == '\n') {
                break;
            } else if (x == '.') {

            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
