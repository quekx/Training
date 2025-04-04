package contest.lc2024;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class LC1229 {
    public String answerString(String word, int numFriends) {
        if (numFriends == 1) {
            return word;
        }
        String ans = "";
        for (int i = 0; i < word.length(); i++) {
            int curLen = Math.min(word.length() - numFriends + 1, word.length() - i);
            String cur = word.substring(i, i + curLen);
            if (cur.compareTo(ans) > 0) {
                ans = cur;
            }
        }
        return ans;
    }

    public long numberOfSubsequences(int[] nums) {
        int[] sortNums = Arrays.copyOf(nums, nums.length);
        Integer[] p = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            p[i] = i;
        }
        Arrays.sort(p, Comparator.comparingInt(a -> nums[a]));
        Arrays.sort(sortNums);

        return 0;
    }
}
