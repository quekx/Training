import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author kaixin
 * @since 2021-11-09 11:04
 */
//Given a string s, remove duplicate letters so that every letter appears once
//and only once. You must make sure your result is the smallest in lexicographical
//order among all possible results.
//
//
// Example 1:
//
//
//Input: s = "bcabc"
//Output: "abc"
//
//
// Example 2:
//
//
//Input: s = "cbacdcbc"
//Output: "acdb"
//
//
//
// Constraints:
//
//
// 1 <= s.length <= 10⁴
// s consists of lowercase English letters.
//
//
//
// Note: This question is the same as 1081: https://leetcode.com/problems/
//smallest-subsequence-of-distinct-characters/
// Related Topics String Stack Greedy Monotonic Stack 👍 3438 👎 242


//leetcode submit region begin(Prohibit modification and deletion)
public class No316 {

    /**
     * info
     * 解答失败:
     * 测试用例:"abacb"
     * 测试结果:"acb"
     * 期望结果:"abc"
     * stdout:
     *
     * @param args
     */
    public static void main(String[] args) {
        String s = "cbacdcbc";
        System.out.println(new No316().removeDuplicateLetters2(s));
    }

    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }

        return null;
    }

    public String removeDuplicateLetters2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        List<Character> list = new LinkedList<>();
        Set<Character> set = new HashSet<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            Character c = s.charAt(i);
            if (!set.contains(c)) {
                set.add(c);
                list.add(0, c);
            } else {
                Character first = list.get(0);
                if (first > c) {
                    list.remove(c);
                    list.add(0, c);
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        for (Character character : list) {
            builder.append(character);
        }
        return builder.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

