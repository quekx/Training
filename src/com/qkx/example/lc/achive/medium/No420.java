package com.qkx.example.lc.achive.medium;

//A password is considered strong if the below conditions are all met:
//
//
// It has at least 6 characters and at most 20 characters.
// It contains at least one lowercase letter, at least one uppercase letter,
//and at least one digit.
// It does not contain three repeating characters in a row (i.e., "Baaabb0" is
//weak, but "Baaba0" is strong).
//
//
// Given a string password, return the minimum number of steps required to make
//password strong. if password is already strong, return 0.
//
// In one step, you can:
//
//
// Insert one character to password,
// Delete one character from password, or
// Replace one character of password with another character.
//
//
//
// Example 1:
// Input: password = "a"
//Output: 5
//
// Example 2:
// Input: password = "aA1"
//Output: 3
//
// Example 3:
// Input: password = "1337C0d3"
//Output: 0
//
//
// Constraints:
//
//
// 1 <= password.length <= 50
// password consists of letters, digits, dot '.' or exclamation mark '!'.
//
//
// Related Topics String Greedy Heap (Priority Queue) 👍 661 👎 1503


import java.util.Comparator;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class No420 {
    /**
     * 1. 删除字符
     * 2. 添加字符
     * 3. 替换字符
     * 1 操作 和 2 操作不会同时发生
     * 1) 添加只有在长度不够的时候，并且肯定是添加不存在的字符；因为其他情况可以用替换代替
     * 2）删除只有在长度过长的时候，并且肯定是删除重复的字符；因为其他情况可以用替换代替
     *
     * @param password
     * @return
     */
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了30.77% 的Java用户
     * 	内存消耗:40.4 MB,击败了17.58% 的Java用户
     */
    public int strongPasswordChecker(String password) {
        char[] words = password.toCharArray();
        int n = words.length;
        int lower = 0;
        int upper = 0;
        int digit = 0;
        for (char w : words) {
            if (w >= 'a' && w <= 'z') {
                lower++;
            } else if (w >= 'A' && w <= 'Z') {
                upper++;
            } else if (w >= '0' && w <= '9') {
                digit++;
            }
        }
        int diff = 0;
        diff += lower == 0 ? 1 : 0;
        diff += upper == 0 ? 1 : 0;
        diff += digit == 0 ? 1 : 0;

        PriorityQueue<Segment> queue = new PriorityQueue<>(new SegmentComparator());
        int i = 0, j = 0;
        while (i < n && j < n) {
            if (words[i] == words[j]) {
                j++;
            } else {
                // [i ~ j - 1]
                if (j - i - 2 >= 1) {
                    queue.add(new Segment(words[i], j - i - 2));
                }
                i = j;
            }
        }
        if (j - i - 2 >= 1) {
            queue.add(new Segment(words[i], j - i - 2));
        }

        if (n < 6) {
            return Math.max(6 - n, diff);
        }

        int ans = 0;
        if (n > 20) {
            // 删除操作
            int del = n - 20;
            ans += del;
            for (int k = 0; k < del; k++) {
                // 删除重复字符
                if (!queue.isEmpty()) {
                    Segment s = queue.poll();
                    if (s.count - 1 >= 1) {
                        queue.add(new Segment(s.w, s.count - 1));
                    }
                }
            }
        }
        while (!queue.isEmpty()) {
            Segment s = queue.poll();
            int num = s.count / 3 + (s.count % 3 != 0 ? 1 : 0);
            ans += num;
            diff -= num;
        }
        return ans + Math.max(diff, 0);
    }

    class SegmentComparator implements Comparator<Segment> {
        @Override
        public int compare(Segment o1, Segment o2) {
            return o1.priority - o2.priority;
        }
    }

    class Segment {
        private char w;
        private int count;
        private int priority;

        public Segment(char w, int count) {
            this.w = w;
            this.count = count;
            int p = count % 3;
            priority = p != 0 ? p : 3;
        }
    }

    public static void main(String[] args) {
        String word = "1111111111";
        System.out.println(new No420().strongPasswordChecker(word));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

