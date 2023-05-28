package contest;

import com.qkx.example.utils.ArrayUtil;

import java.util.*;

public class LC0527 {
    public int buyChoco(int[] prices, int money) {
        if (prices.length <= 1) {
            return money;
        }
        Arrays.sort(prices);
        int cost = prices[0] + prices[1];
        return cost <= money ? money - cost : money;
    }

    public int minExtraChar(String s, String[] dictionary) {
        Set<String> set = new HashSet<>();
        for (String word : dictionary) {
            set.add(word);
        }
        Integer[] dp = new Integer[s.length()];
        dfs(s, 0, set, dp);
        ArrayUtil.print(dp);
        return dfs(s, 0, set, dp);
    }

    private int dfs(String s, int i, Set<String> set, Integer[] dp) {
        if (i == s.length()) {
            return 0;
        }
        if (dp[i] != null) {
            return dp[i];
        }
        int min = s.length() - i;
        StringBuilder builder = new StringBuilder();
        for (int k = i; k < s.length(); k++) {
            builder.append(s.charAt(k));
            if (set.contains(builder.toString())) {
                min = Math.min(min, dfs(s, k + 1, set, dp));
            } else {
                min = Math.min(min, k - i + 1 + dfs(s, k + 1, set, dp));
            }
        }
        dp[i] = min;
        return min;
    }

    private void build(Trie root, String word) {
        Trie cur = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (cur.next[ch - 'a'] == null) {
                cur.next[ch - 'a'] = new Trie();
            }
            cur = cur.next[ch - 'a'];
        }
        cur.isEnd = true;
    }

    class Trie {
        Trie[] next = new Trie[26];
        boolean isEnd;
    }

    public long maxStrength(int[] nums) {
        PriorityQueue<Integer> neg = new PriorityQueue<>();
        PriorityQueue<Integer> pos = new PriorityQueue<>((a, b) -> b - a);
        long max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > 0) {
                pos.add(num);
            } else if (num < 0) {
                neg.add(num);
            }
            max = Math.max(max, num);
        }
        if (pos.size() >= 1 || neg.size() >= 2) {
            long ans = 1;
            while (!pos.isEmpty()) {
                ans *= pos.poll();
            }
            while (neg.size() >= 2) {
                ans *= neg.poll();
                ans *= neg.poll();
            }
            max = Math.max(max, ans);
        }
        return max;
    }

    public boolean canTraverseAllPairs(int[] nums) {
        int[] p = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            p[i] = i;
        }
        Arrays.sort(nums);
        return false;
    }

    private int find(int x, int[] p) {
        if (p[x] != x) {
            p[x] = find(p[x], p);
        }
        return p[x];
    }

    public static void main(String[] args) {
        // "dwmodizxvvbosxxw"
        //["ox","lb","diz","gu","v","ksv","o","nuq","r","txhe","e","wmo","cehy","tskz","ds","kzbu"]
        String s = "dwmodizxvvbosxxw";
        String s2 = "d wmo diz x b sxxw";
        String[] words = {"ox","lb","diz","gu","v","ksv","o","nuq","r","txhe","e","wmo","cehy","tskz","ds","kzbu"};
        System.out.println(new LC0527().minExtraChar(s, words));
        int[] nums = {6,-3,-4,8,4,7,6,4,7,7,-3,-6,9};
        System.out.println(new LC0527().maxStrength(nums));
    }
}
