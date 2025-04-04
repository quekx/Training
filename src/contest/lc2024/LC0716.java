package contest.lc2024;

import java.util.*;

/**
 * 排名	用户名	得分	完成时间 	题目1 (3)	题目2 (4)	题目3 (4)	题目4 (6)
 * 319 / 3901	QueKaix 	17	1:14:54	0:08:47	0:33:55  2	0:47:30	1:04:54
 */
public class LC0716 {
    public int sumOfSquares(int[] nums) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (n % (i + 1) == 0) {
                ans += nums[i] * nums[i];
            }
        }
        return ans;
    }

    /**
     * 607 / 619 个通过测试用例
     * 状态：超出时间限制
     * 提交时间：几秒前
     */
    public int maximumBeauty2(int[] nums, int k) {
        Arrays.sort(nums);
        Deque<int[]> deque = new ArrayDeque<>();
        int ans = 0;
        for (int num : nums) {
            while (!deque.isEmpty() && deque.getFirst()[0] < num - k) {
                deque.pop();
            }
            for (int[] set : deque) {
                set[1] += 1;
                ans = Math.max(ans, set[1]);
            }
            if (deque.isEmpty() || deque.getLast()[0] != num + k) {
                deque.add(new int[]{num + k, 1});
            }
        }
        return ans;
    }

    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);
        Deque<Integer> deque = new ArrayDeque<>();
        int ans = 0;
        for (int num : nums) {
            while (!deque.isEmpty() && deque.getFirst() < num - k) {
                deque.removeFirst();
            }
            deque.add(num + k);
            ans = Math.max(ans, deque.size());
        }
        return ans;
    }

    public int minimumIndex(List<Integer> nums) {
        if (nums == null || nums.isEmpty()) {
            return -1;
        }
        Map<Integer, Integer> counter = new HashMap<>();
        for (Integer num : nums) {
            int count = counter.getOrDefault(num, 0);
            counter.put(num, count + 1);
        }
        int max = 0;
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            if (entry.getValue() * 2 > nums.size()) {
                max = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        int curCount = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) == max) {
                curCount++;
            }
            // [0, i] vs [i + 1, n - 1]
            if (curCount * 2 > i + 1 && (maxCount - curCount) * 2 > nums.size() - i - 1) {
                return i;
            }
        }
        return -1;
    }

    public int longestValidSubstring(String word, List<String> forbidden) {
        Trie root = new Trie();
        for (String forbiddenWord : forbidden) {
            build(root, forbiddenWord);
        }
        int ans = 0;
        int bound = word.length();
        for (int i = word.length() - 1; i >= 0; i--) {
            // [i, bound - 1]
            int p = check(root, word, i, bound - 1);
            if (p == -1) {
                ans = Math.max(ans, bound - i);
            } else {
                bound = p;
            }
        }
        return ans;
    }

    private int check(Trie root, String word, int start, int end) {
        Trie cur = root;
        for (int i = start; i <= end; i++) {
            int idx = word.charAt(i) - 'a';
            if (cur.next[idx] == null) {
                return -1;
            }
            cur = cur.next[idx];
            if (cur.isEnd) {
                return i;
            }
        }
        return -1;
    }

    private void build(Trie root, String forbiddenWord) {
        Trie cur = root;
        for (int i = 0; i < forbiddenWord.length(); i++) {
            int idx = forbiddenWord.charAt(i) - 'a';
            if (cur.next[idx] == null) {
                cur.next[idx] = new Trie();
            }
            cur = cur.next[idx];
        }
        cur.isEnd = true;
    }

    class Trie {
        Trie[] next = new Trie[26];
        boolean isEnd;
    }
}
