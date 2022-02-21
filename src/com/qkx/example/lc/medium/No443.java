package com.qkx.example.lc.medium;

import java.util.Arrays;

/**
 * @author kaixin
 * @since 2021-11-18 11:21
 */
//Given an array of characters chars, compress it using the following algorithm:
//
//
// Begin with an empty string s. For each group of consecutive repeating
//characters in chars:
//
//
// If the group's length is 1, append the character to s.
// Otherwise, append the character followed by the group's length.
//
//
// The compressed string s should not be returned separately, but instead, be
//stored in the input character array chars. Note that group lengths that are 10 or
//longer will be split into multiple characters in chars.
//
// After you are done modifying the input array, return the new length of the
//array.
//You must write an algorithm that uses only constant extra space.
//
// Example 1:
//
//
//Input: chars = ["a","a","b","b","c","c","c"]
//Output: Return 6, and the first 6 characters of the input array should be: [
//"a","2","b","2","c","3"]
//Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3
//".
//
//
// Example 2:
//
//
//Input: chars = ["a"]
//Output: Return 1, and the first character of the input array should be: ["a"]
//Explanation: The only group is "a", which remains uncompressed since it's a
//single character.
//
//
// Example 3:
//
//
//Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
//Output: Return 4, and the first 4 characters of the input array should be: [
//"a","b","1","2"].
//Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".
//
//
// Example 4:
//
//
//Input: chars = ["a","a","a","b","b","a","a"]
//Output: Return 6, and the first 6 characters of the input array should be: [
//"a","3","b","2","a","2"].
//Explanation: The groups are "aaa", "bb", and "aa". This compresses to "a3b2a2
//". Note that each group is independent even if two groups have the same
//character.
//
//
//
// Constraints:
//
//
// 1 <= chars.length <= 2000
// chars[i] is a lowercase English letter, uppercase English letter, digit, or
//symbol.
//
// Related Topics Two Pointers String 👍 1690 👎 3821


//leetcode submit region begin(Prohibit modification and deletion)
public class No443 {

    /**
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:38.9 MB,击败了53.33% 的Java用户
     */
    public static void main(String[] args) {
//        char[] chars = {'a', 'a', 'a', 'b', 'b', 'a', 'a'};
        char[] chars = {'a', 'b', 'b', 'b', 'c'};
        int len = new No443().compress(chars);
        System.out.println(len);
        System.out.println(Arrays.toString(chars));
    }

    int writeIndex = 0;
    public int compress(char[] chars) {
        int count = 1;
        for (int i = 1; i <= chars.length - 1; i++) {
            if (chars[i] == chars[i - 1]) {
                count++;
            } else {
                write(chars, chars[i - 1], count);
                count = 1;
            }
        }
        // write last char
        write(chars, chars[chars.length - 1], count);
        return writeIndex;
    }

    private void write(char[] chars, char preChar, int count) {
        chars[writeIndex++] = preChar;
        int countStart = writeIndex;
        if (count > 1) {
            while (count > 0) {
                chars[writeIndex++] = (char) ('0' + count % 10);
                count /= 10;
            }
            // switch [countStart, writeIndex - 1]
            int countEnd = writeIndex - 1;
            reverse(chars, countStart, countEnd);
        }
    }

    private void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char t = chars[start];
            chars[start] = chars[end];
            chars[end] = t;
            start++;
            end--;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
