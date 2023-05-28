package contest.lc2023;

import com.qkx.example.utils.ArrayUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC0312 {
    /**
     * 53
     * 给你一个下标从 0 开始的字符串数组 words 和两个整数：left 和 right 。
     *
     * 如果字符串以元音字母开头并以元音字母结尾，那么该字符串就是一个 元音字符串 ，其中元音字母是 'a'、'e'、'i'、'o'、'u' 。
     *
     * 返回 words[i] 是元音字符串的数目，其中 i 在闭区间 [left, right] 内。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/count-the-number-of-vowel-strings-in-range
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.1 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 1053 / 1053
     */
    public int vowelStrings(String[] words, int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; i++) {
            String word = words[i];
            if (isVowel(word.charAt(0)) && isVowel(word.charAt(word.length() - 1))) {
                ans++;
            }
        }
        return ans;
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    /**
     * 给你一个下标从 0 开始的整数数组 nums 。你可以将 nums 中的元素按 任意顺序 重排（包括给定顺序）。
     *
     * 令 prefix 为一个数组，它包含了 nums 重新排列后的前缀和。换句话说，prefix[i] 是 nums 重新排列后下标从 0 到 i 的元素之和。nums 的 分数 是 prefix 数组中正整数的个数。
     *
     * 返回可以得到的最大分数。
     *
     *  
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/rearrange-array-to-maximize-prefix-score
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 30 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 49.9 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 1071 / 1071
     */
    public int maxScore(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        long sum = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            sum += nums[i];
            if (sum > 0) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 给你一个下标从 0 开始的整数数组nums 。每次操作中，你可以：
     *
     * 选择两个满足 0 <= i, j < nums.length 的不同下标 i 和 j 。
     * 选择一个非负整数 k ，满足 nums[i] 和 nums[j] 在二进制下的第 k 位（下标编号从 0 开始）是 1 。
     * 将 nums[i] 和 nums[j] 都减去 2k 。
     * 如果一个子数组内执行上述操作若干次后，该子数组可以变成一个全为 0 的数组，那么我们称它是一个 美丽 的子数组。
     *
     * 请你返回数组 nums 中 美丽子数组 的数目。
     *
     * 子数组是一个数组中一段连续 非空 的元素序列。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/count-the-number-of-beautiful-subarrays
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 超出时间限制
     */
    public long beautifulSubarrays2(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        long ans = 0;
        for (int num : nums) {
            int count = counter.getOrDefault(num, 0);
            ans += count;
            if (num == 0) {
                ans++;
            }
            Map<Integer, Integer> tmp = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
                tmp.put(entry.getKey() ^ num, entry.getValue());
            }
            tmp.put(num, tmp.getOrDefault(num, 0) + 1);
            counter = tmp;
        }
        return ans;
    }

    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 43 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 54.5 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 114 / 114
     */
    public long beautifulSubarrays(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        long ans = 0;
        int prefix = 0;
        for (int num : nums) {
            prefix ^= num;
            int count = counter.getOrDefault(prefix, 0);
            ans += count;
            if (prefix == 0) {
                ans++;
            }
            counter.put(prefix, count + 1);
        }
        return ans;
    }

    /**
     * 你有一台电脑，它可以 同时 运行无数个任务。给你一个二维整数数组 tasks ，其中 tasks[i] = [starti, endi, durationi] 表示第 i 个任务需要在 闭区间 时间段 [starti, endi] 内运行 durationi 个整数时间点（但不需要连续）。
     * <p>
     * 当电脑需要运行任务时，你可以打开电脑，如果空闲时，你可以将电脑关闭。
     * <p>
     * 请你返回完成所有任务的情况下，电脑最少需要运行多少秒。
     * <p>
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/minimum-time-to-complete-all-tasks
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int findMinimumTime(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });
        int ans = 0;
        int p = 0;
        int x = 0;
        for (int[] task : tasks) {
            int s = task[0], e = task[1], d = task[2];
            if (s > p) {
                ans += d;
                p = e;
                x = d;
            } else {
                // 重合 p 位置之前还能放 x 个
                // 优先在 [s, min(p, e)] 之间放, 还能放 x 个， 需要 d 个
                int num = Math.min(e, p) - s + 1;
                num = Math.min(num, x);
                num = Math.min(num, d);
                p = Math.max(p, e);
                ans += d - num;
                x -= num;
                x += d - num;
            }
        }
        ArrayUtil.print(tasks);
        return ans;
    }

    public static void main(String[] args) {
//        Hashtable<String, String> ta = new Hashtable<>();
//        ta.put(null, null);
//        ta.get(null);
//
//        HashMap<String, String> tb = new HashMap<>();
//        tb.put(null, null);
//        tb.get(null);
//
//        ConcurrentHashMap<String, String> tc = new ConcurrentHashMap<>();
//        tc.put(null, null);
//        tc.get(null);

        // [[2,13,2],[6,18,5],[2,13,3]]
        int[][] t = {{2,13,2}, {6,18,5}, {2,13,3}};
        System.out.println(new LC0312().findMinimumTime(t));
    }
}
