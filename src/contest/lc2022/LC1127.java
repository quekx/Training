package contest.lc2022;

import com.qkx.example.model.ListNode;

import java.util.HashMap;
import java.util.Stack;

/**
 * 10:40
 */
public class LC1127 {
    /**
     * 10:40
     * 给你一个正整数 n ，找出满足下述条件的 中枢整数 x ：
     * <p>
     * 1 和 x 之间的所有元素之和等于 x 和 n 之间所有元素之和。
     * 返回中枢整数 x 。如果不存在中枢整数，则返回 -1 。题目保证对于给定的输入，至多存在一个中枢整数。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/find-the-pivot-integer
     * <p>
     * (1 + x) * x - x = (1 + n) * n / 2
     * x ^ 2 = (1 + n) * n / 2
     */
    public int pivotInteger(int n) {
        int sum = (1 + n) * n / 2;
        int x = (int) Math.sqrt(sum);
        return x * x == sum ? sum : -1;
    }

    /**
     * 10:49
     * 给你两个仅由小写英文字母组成的字符串 s 和 t 。
     *
     * 现在需要通过向 s 末尾追加字符的方式使 t 变成 s 的一个 子序列 ，返回需要追加的最少字符数。
     *
     * 子序列是一个可以由其他字符串删除部分（或不删除）字符但不改变剩下字符顺序得到的字符串。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/append-characters-to-string-to-make-subsequence
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 6 ms
     * , 在所有 Java 提交中击败了
     * 39.39%
     * 的用户
     * 内存消耗：
     * 42.3 MB
     * , 在所有 Java 提交中击败了
     * 51.49%
     * 的用户
     * 通过测试用例：
     * 85 / 85
     */
    public int appendCharacters(String s, String t) {
        int j = 0;
        int k = 0;
        while (j < s.length() && k < t.length()) {
            if (s.charAt(j) == t.charAt(k)) {
                k++;
            }
            j++;
        }
        return t.length() - k;
    }

    /**
     * 10:55
     * 给你一个链表的头节点 head 。
     *
     * 对于列表中的每个节点 node ，如果其右侧存在一个具有 严格更大 值的节点，则移除 node 。
     *
     * 返回修改后链表的头节点 head 。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/remove-nodes-from-linked-list
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 243 ms
     * , 在所有 Java 提交中击败了
     * 5.05%
     * 的用户
     * 内存消耗：
     * 58.1 MB
     * , 在所有 Java 提交中击败了
     * 92.24%
     * 的用户
     * 通过测试用例：
     * 32 / 32
     * todo: 可以优化
     */
    public ListNode removeNodes(ListNode head) {
        Stack<ListNode> s = new Stack<>();
        s.push(head);
        ListNode ans = head;
        ListNode it = head.next;
        while (it != null) {
            while (!s.isEmpty() && s.peek().val < it.val) {
                s.pop();
            }
            if (s.isEmpty()) {
                ans = it;
            } else {
                s.peek().next = it;
            }
            s.push(it);
            it = it.next;
        }
        return ans;
    }

    /**
     * 给你一个长度为 n 的数组 nums ，该数组由从 1 到 n 的 不同 整数组成。另给你一个正整数 k 。
     *
     * 统计并返回 num 中的 中位数 等于 k 的非空子数组的数目。
     *
     * 注意：
     *
     * 数组的中位数是按 递增 顺序排列后位于 中间 的那个元素，如果数组长度为偶数，则中位数是位于中间靠 左 的那个元素。
     * 例如，[2,3,1,4] 的中位数是 2 ，[8,4,3,5,1] 的中位数是 4 。
     * 子数组是数组中的一个连续部分。
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/count-subarrays-with-median-k
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 94.98%
     * 的用户
     * 内存消耗：
     * 49.7 MB
     * , 在所有 Java 提交中击败了
     * 93.92%
     * 的用户
     * 通过测试用例：
     * 45 / 45
     */
    public int countSubarrays(int[] nums, int k) {
        if (nums.length == 1) {
            return 1;
        }

        int n = nums.length;
        int t = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == k) {
                t = i;
                break;
            }
        }

        int[] left = new int[n * 2];
        int[] right = new int[n * 2];
        int d = 0;
        for (int l = t - 1; l >= 0; l--) {
            if (nums[l] < k) {
                d--;
            } else {
                d++;
            }
            left[d + n]++;
        }
        d = 0;
        for (int r = t + 1; r <= n - 1; r++) {
            if (nums[r] < k) {
                d--;
            } else {
                d++;
            }
            right[d + n]++;
        }

        // 1. k 自身
        int ans = 1;
        // 2. k + 左侧大小数目差值=1
        ans += left[n + 1];
        // 3. k + 左侧大小数目相同
        ans += left[n];
        // 4. k + 右侧大小数目差值=1
        ans += right[n + 1];
        // 5. k + 右侧大小数目相同
        ans += right[n];
        // 6. k + 左侧 + 右侧
        ans += left[1] * right[2 * n - 1];
        for (int l = 2; l <= 2 * n - 1; l++) {
            ans += left[l] * right[2 * n - l];
            ans += left[l] * right[2 * n + 1 - l];
        }
        return ans;
    }

    public int countSubarrays2(int[] nums, int k) {
//        HashMap<Integer, Integer> map = new HashMap<>(Map.of(0, 1));
        HashMap<Integer, Integer> map = new HashMap<>();
        int i = 0, curr = 0, count;
        for (; nums[i] != k; i++) {
            map.put(curr += nums[i] < k ? -1 : 1, map.getOrDefault(curr, 0) + 1);
        }
        for (count = map.getOrDefault(curr, 0) + map.getOrDefault(curr - 1, 0); ++i < nums.length; ) {
            count += map.getOrDefault(curr += nums[i] < k ? -1 : 1, 0) + map.getOrDefault(curr - 1, 0);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new LC1127().pivotInteger(8));

        int[] nums = {2, 5, 1, 4, 3, 6};
        int k = 1;
        System.out.println(new LC1127().countSubarrays(nums, k));
    }
}
