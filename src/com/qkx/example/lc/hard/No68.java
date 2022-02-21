package com.qkx.example.lc.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kaixin
 * @since 2020-12-02 11:14
 */
//Given an array of words and a width maxWidth, format the text such that each l
//ine has exactly maxWidth characters and is fully (left and right) justified.
//
// You should pack your words in a greedy approach; that is, pack as many words
//as you can in each line. Pad extra spaces ' ' when necessary so that each line h
//as exactly maxWidth characters.
//
// Extra spaces between words should be distributed as evenly as possible. If th
//e number of spaces on a line do not divide evenly between words, the empty slots
// on the left will be assigned more spaces than the slots on the right.
//
// For the last line of text, it should be left justified and no extra space is
//inserted between words.
//
// Note:
//
//
// A word is defined as a character sequence consisting of non-space characters
//only.
// Each word's length is guaranteed to be greater than 0 and not exceed maxWidth
//.
// The input array words contains at least one word.
//
//
//
// Example 1:
//
//
//Input: words = ["This", "is", "an", "example", "of", "text", "justification."]
//, maxWidth = 16
//Output:
//[
//   "This    is    an",
//   "example  of text",
//   "justification.  "
//]
//
// Example 2:
//
//
//Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth =
//16
//Output:
//[
//  "What   must   be",
//  "acknowledgment  ",
//  "shall be        "
//]
//Explanation: Note that the last line is "shall be    " instead of "shall     b
//e", because the last line must be left-justified instead of fully-justified.
//Note that the second line is also left-justified becase it contains only one w
//ord.
//
// Example 3:
//
//
//Input: words = ["Science","is","what","we","understand","well","enough","to","
//explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidt
//h = 20
//Output:
//[
//  "Science  is  what we",
//  "understand      well",
//  "enough to explain to",
//  "a  computer.  Art is",
//  "everything  else  we",
//  "do                  "
//]
//
//
// Constraints:
//
//
// 1 <= words.length <= 300
// 1 <= words[i].length <= 20
// words[i] consists of only English letters and symbols.
// 1 <= maxWidth <= 100
// words[i].length <= maxWidth
//
// Related Topics String
// 👍 818 👎 1742


//leetcode submit region begin(Prohibit modification and deletion)
public class No68 {

    public static void main(String[] args) {
        String[] words = {"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
        int maxWidth = 20;
        List<String> x = new No68().fullJustify(words, maxWidth);
        System.out.println(x);
        for (String s : x) {
            System.out.println(s);
            System.out.println(s.length());
        }
    }

    /**
     * 遍历生成一行所需数据：单词数num，单词总长度length
     *
     * @param words
     * @param maxWidth
     * @return
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        traverse(words, 0, 0, 0, maxWidth, res);
        return res;
    }

    private void traverse(String[] words, int start, int cur, int length, int maxWidth, List<String> res) {
        // 判断当前是否能加入
        int curLen = words[cur].length();
        // 总单词长度
        int newLength = length + curLen;
        // 加上分隔空格长度
        int total = newLength + cur - start;
        if (total <= maxWidth) {
            // 可以加入当前行
            if (cur == words.length - 1) {
                // 最后一个单词，结算
                res.add(build(words, start, cur, newLength, maxWidth));
                return;
            } else {
                traverse(words, start, cur + 1, newLength, maxWidth, res);
            }
        } else {
            // 不能加入当前行，当前行结算
            res.add(build(words, start, cur - 1, length, maxWidth));
            traverse(words, cur, cur, 0, maxWidth, res);
        }
    }

    private String build(String[] words, int start, int end, int length, int maxWidth) {
        // 1. 最后一行或者单个单词
        if (end == words.length - 1 || start == end) {
            int widthUsed = 0;
            StringBuilder sb = new StringBuilder(maxWidth);
            for (int i = start; i <= end; i++) {
                sb.append(words[i]);
                widthUsed += words[i].length();
                if (widthUsed < maxWidth) {
                    sb.append(" ");
                    widthUsed += 1;
                }
            }
            for (int k = 1; k <= maxWidth - widthUsed; k++) {
                sb.append(" ");
            }
            return sb.toString();
        }

        // 2. 不是最后一行，多个单词
        int num = end - start + 1;
        int spaceNum = num - 1;

        StringBuilder sb = new StringBuilder(maxWidth);
        // 空格长度
        int lenTotalSpace = maxWidth - length;
        // 每个分隔的空格长度（较小）
        int lenPreSpace = lenTotalSpace / spaceNum;
        //
        int more = lenTotalSpace - lenPreSpace * spaceNum;
        int widthUsed = 0;
        System.out.println("num >> " + num + ", spaceLen >> " + lenTotalSpace + ", space >> " + lenPreSpace);
        for (int i = 1; i <= end - start + 1; i++) {
            sb.append(words[start + i - 1]);
            widthUsed += words[start + i - 1].length();
            if (widthUsed < maxWidth) {
                for (int k = 1; k <= lenPreSpace; k++) {
                    sb.append(" ");
                    widthUsed++;
                }
                if (i <= more) {
                    // 对于前more个添加space + 1个空格
                    sb.append(" ");
                    widthUsed++;
                }
            }
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

