package com.qkx.example.lc.medium;

import java.util.*;

/**
 * @author kaixin
 * @since 2021-09-16 20:22
 */
//A gene string can be represented by an 8-character long string, with choices
//from 'A', 'C', 'G', and 'T'.
//
// Suppose we need to investigate a mutation from a gene string start to a gene
//string end where one mutation is defined as one single character changed in the
//gene string.
//
//
// For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
//
//
// There is also a gene bank bank that records all the valid gene mutations. A
//gene must be in bank to make it a valid gene string.
//
// Given the two gene strings start and end and the gene bank bank, return the
//minimum number of mutations needed to mutate from start to end. If there is no
//such a mutation, return -1.
//
// Note that the starting point is assumed to be valid, so it might not be
//included in the bank.
//
//
// Example 1:
//
//
//Input: start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
//Output: 1
//
//
// Example 2:
//
//
//Input: start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA",
//"AAACGGTA"]
//Output: 2
//
//
// Example 3:
//
//
//Input: start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC",
//"AACCCCCC"]
//Output: 3
//
//
//
// Constraints:
//
//
// start.length == 8
// end.length == 8
// 0 <= bank.length <= 10
// bank[i].length == 8
// start, end, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].
//
//
// Related Topics Hash Table String Breadth-First Search 👍 646 👎 80


//leetcode submit region begin(Prohibit modification and deletion)
public class No433 {

    public static void main(String[] args) {
        String start = "AAAACCCC";
        String end = "CCCCCCCC";
        String[] bank = {"AAAACCCA","AAACCCCA","AACCCCCA","AACCCCCC","ACCCCCCC","CCCCCCCC","AAACCCCC","AACCCCCC"};
        System.out.println(new No433().minMutation(start, end, bank));
    }

    /**
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:37 MB,击败了72.14% 的Java用户
     * @return
     */
    public int minMutation(String start, String end, String[] bank) {
        Set<String> remain = new HashSet<>();
        remain.addAll(Arrays.asList(bank));
        remain.remove(start);
        if (!remain.contains(end)) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (isAdjust(cur, end)) {
                    return step;
                }

                Iterator<String> it = remain.iterator();
                while (it.hasNext()) {
                    String r = it.next();
                    if (isAdjust(cur, r)) {
                        queue.offer(r);
                        it.remove();;
                    }
                }
            }
            step++;
        }

        return -1;
    }

    private boolean isAdjust(String s1, String s2) {
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
        }
        return diff == 1;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

