package contest.lc2022;

import java.util.*;

public class LC1029 {
    /**
     * 8:30 ~ 8:40
     * 给你一个字符串数组 words ，每一个字符串长度都相同，令所有字符串的长度都为 n 。
     * <p>
     * 每个字符串 words[i] 可以被转化为一个长度为 n - 1 的 差值整数数组 difference[i] ，其中对于 0 <= j <= n - 2 有 difference[i][j] = words[i][j+1] - words[i][j] 。注意两个字母的差值定义为它们在字母表中 位置 之差，也就是说 'a' 的位置是 0 ，'b' 的位置是 1 ，'z' 的位置是 25 。
     * <p>
     * 比方说，字符串 "acb" 的差值整数数组是 [2 - 0, 1 - 2] = [2, -1] 。
     * words 中所有字符串 除了一个字符串以外 ，其他字符串的差值整数数组都相同。你需要找到那个不同的字符串。
     * <p>
     * 请你返回 words中 差值整数数组 不同的字符串。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/odd-string-difference
     * <p>
     * 行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 69.74%
     * 的用户
     * 内存消耗：
     * 39.2 MB
     * , 在所有 Java 提交中击败了
     * 47.58%
     * 的用户
     * 通过测试用例：
     * 39 / 39
     */
    public String oddString(String[] words) {
        return words[getOddIndex(words)];
    }

    private int getOddIndex(String[] words) {
        Map<Integer, List<Integer>> counter = new HashMap<>(2);
        for (int k = 1; k < words[0].length(); k++) {
            counter.clear();
            // 计算当前所有差值
            for (int i = 0; i < words.length; i++) {
                int diff = words[i].charAt(k) - words[i].charAt(k - 1);
                List<Integer> list = counter.computeIfAbsent(diff, k1 -> new LinkedList<>());
                list.add(i);
            }
            if (counter.size() == 2) {
                for (List<Integer> l : counter.values()) {
                    if (l.size() == 1) {
                        return l.get(0);
                    }
                }
            }
        }
        return 0;
    }

    /**
     * 8:40 ~ 8:50
     * 给你两个字符串数组 queries 和 dictionary 。数组中所有单词都只包含小写英文字母，且长度都相同。
     * <p>
     * 一次 编辑 中，你可以从 queries 中选择一个单词，将任意一个字母修改成任何其他字母。从 queries 中找到所有满足以下条件的字符串：不超过 两次编辑内，字符串与 dictionary 中某个字符串相同。
     * <p>
     * 请你返回 queries 中的单词列表，这些单词距离 dictionary 中的单词 编辑次数 不超过 两次 。单词返回的顺序需要与 queries 中原本顺序相同。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/words-within-two-edits-of-dictionary
     *
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 13 ms
     * , 在所有 Java 提交中击败了
     * 44.80%
     * 的用户
     * 内存消耗：
     * 41.7 MB
     * , 在所有 Java 提交中击败了
     * 28.40%
     * 的用户
     * 通过测试用例：
     * 33 / 33
     */
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> ans = new LinkedList<>();
        for (String query : queries) {
            if (isValid(query, dictionary)) {
                ans.add(query);
            }
        }
        return ans;
    }

    private boolean isValid(String query, String[] dictionary) {
        for (String dic : dictionary) {
            if (getEditNum(query, dic) <= 2) {
                return true;
            }
        }
        return false;
    }

    private int getEditNum(String query, String dictionary) {
        int ans = 0;
        for (int i = 0; i < query.length(); i++) {
            if (query.charAt(i) != dictionary.charAt(i)) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 8:50 ~ 9:10
     * 给你一个下标从 0 开始的数组 nums ，它包含若干正整数，表示数轴上你需要摧毁的目标所在的位置。同时给你一个整数 space 。
     *
     * 你有一台机器可以摧毁目标。给机器 输入 nums[i] ，这台机器会摧毁所有位置在 nums[i] + c * space 的目标，其中 c 是任意非负整数。你想摧毁 nums 中 尽可能多 的目标。
     *
     * 请你返回在摧毁数目最多的前提下，nums[i] 的 最小值 。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/destroy-sequential-targets
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 42 ms
     * , 在所有 Java 提交中击败了
     * 38.93%
     * 的用户
     * 内存消耗：
     * 59.6 MB
     * , 在所有 Java 提交中击败了
     * 18.54%
     * 的用户
     * 通过测试用例：
     * 44 / 44
     */
    public int destroyTargets(int[] nums, int space) {
        Map<Integer, Integer> counter = new HashMap<>();
        Map<Integer, Integer> posMap = new HashMap<>();
        for (int num : nums) {
            int x = num % space;
            counter.put(x, counter.getOrDefault(x, 0) + 1);
            posMap.put(x, Math.min(num, posMap.getOrDefault(x, Integer.MAX_VALUE)));
        }
        System.out.println(counter);
        System.out.println(posMap);
        int maxCount = 0;
        List<Integer> maxCountPos = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            Integer pos = entry.getKey();
            Integer count = entry.getValue();
            if (count > maxCount) {
                maxCount = count;
                maxCountPos.clear();
            } else if (count == maxCount) {
                maxCountPos.add(pos);
            }
        }
        int ansIndex = Integer.MAX_VALUE;
        for (Integer pos : maxCountPos) {
            ansIndex = Math.min(ansIndex, posMap.get(pos));
        }
        return ansIndex;
    }

    /**
     * 给你一个下标从 0 开始的非负整数数组 nums 。对于 nums 中每一个整数，你必须找到对应元素的 第二大 整数。
     *
     * 如果 nums[j] 满足以下条件，那么我们称它为 nums[i] 的 第二大 整数：
     *
     * j > i
     * nums[j] > nums[i]
     * 恰好存在 一个 k 满足 i < k < j 且 nums[k] > nums[i] 。
     * 如果不存在 nums[j] ，那么第二大整数为 -1 。
     *
     * 比方说，数组 [1, 2, 4, 3] 中，1 的第二大整数是 4 ，2 的第二大整数是 3 ，3 和 4 的第二大整数是 -1 。
     * 请你返回一个整数数组 answer ，其中 answer[i]是 nums[i] 的第二大整数。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/next-greater-element-iv
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[] secondGreaterElement(int[] nums) {
        int[] ans = new int[nums.length];

        return ans;
    }
}
