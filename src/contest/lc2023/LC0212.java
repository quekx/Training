package contest.lc2023;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC0212 {
    /**
     * 给你一个下标从 0 开始的整数数组 nums 。
     *
     * 现定义两个数字的 串联 是由这两个数值串联起来形成的新数字。
     *
     * 例如，15 和 49 的串联是 1549 。
     * nums 的 串联值 最初等于 0 。执行下述操作直到 nums 变为空：
     *
     * 如果 nums 中存在不止一个数字，分别选中 nums 中的第一个元素和最后一个元素，将二者串联得到的值加到 nums 的 串联值 上，然后从 nums 中删除第一个和最后一个元素。
     * 如果仅存在一个元素，则将该元素的值加到 nums 的串联值上，然后删除这个元素。
     * 返回执行完所有操作后 nums 的串联值。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/find-the-array-concatenation-value
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 查看示例代码
     * 添加备注
     * <p>
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.4 MB
     * , 在所有 Java 提交中击败了
     * 26.55%
     * 的用户
     * 通过测试用例：
     * 53 / 53
     */
    public long findTheArrayConcVal(int[] nums) {
        long ans = 0;
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            if (i == j) {
                ans += nums[i];
            } else {
                ans += concat(nums[i], nums[j]);
            }
            i++;
            j--;
        }
        return ans;
    }

    private long concat(int a, int b) {
        long ans = 0;
        long s = 1L;
        while (b > 0) {
            ans += (b % 10) * s;
            b /= 10;
            s *= 10;
        }
        return ans + a * s;
    }

    /**
     * 给你一个下标从 0 开始、长度为 n 的整数数组 nums ，和两个整数 lower 和 upper ，返回 公平数对的数目 。
     *
     * 如果 (i, j) 数对满足以下情况，则认为它是一个 公平数对 ：
     *
     * 0 <= i < j < n，且
     * lower <= nums[i] + nums[j] <= upper
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/count-the-number-of-fair-pairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 查看示例代码
     * 添加备注
     * <p>
     * 执行用时：
     * 43 ms
     * , 在所有 Java 提交中击败了
     * 70.74%
     * 的用户
     * 内存消耗：
     * 59 MB
     * , 在所有 Java 提交中击败了
     * 39.39%
     * 的用户
     * 通过测试用例：
     * 53 / 53
     */
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        int n = nums.length;
        long ans = 0;
        for (int i = 0; i < n - 1; i++) {
            int lb = getLeftBound(nums, i + 1, n - 1, lower - nums[i]);
            int rb = getRightBound(nums, i + 1, n - 1, upper - nums[i]);
            if (lb <= rb) {
                ans += rb - lb + 1;
            }
        }
        return ans;
    }

    private int getLeftBound(int[] nums, int l, int r, long target) {
        if (nums[r] < target) {
            return r + 1;
        }
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private int getRightBound(int[] nums, int l, int r, long target) {
        if (nums[l] > target) {
            return l - 1;
        }
        while (l < r) {
            int mid = r - ((r - l) >> 1);
            if (nums[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    /**
     * 给你一个 二进制字符串 s 和一个整数数组 queries ，其中 queries[i] = [firsti, secondi] 。
     * <p>
     * 对于第 i 个查询，找到 s 的 最短子字符串 ，它对应的 十进制值 val 与 firsti 按位异或 得到 secondi ，换言之，val ^ firsti == secondi 。
     * <p>
     * 第 i 个查询的答案是子字符串 [lefti, righti] 的两个端点（下标从 0 开始），如果不存在这样的子字符串，则答案为 [-1, -1] 。如果有多个答案，请你选择 lefti 最小的一个。
     * <p>
     * 请你返回一个数组 ans ，其中 ans[i] = [lefti, righti] 是第 i 个查询的答案。
     * <p>
     * 子字符串 是一个字符串中一段连续非空的字符序列。
     * <p>
     *  
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/substring-xor-queries
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 查看示例代码
     * 添加备注
     *
     * 执行用时：
     * 37 ms
     * , 在所有 Java 提交中击败了
     * 76.13%
     * 的用户
     * 内存消耗：
     * 98.2 MB
     * , 在所有 Java 提交中击败了
     * 62.35%
     * 的用户
     * 通过测试用例：
     * 59 / 59
     */
    public int[][] substringXorQueries(String s, int[][] queries) {
        int n = queries.length;
        // 二分
        int[] nums = new int[n];
        int[] q = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = queries[i][0] ^ queries[i][1];
            q[i] = queries[i][0] ^ queries[i][1];
        }
        Arrays.sort(nums);
        Map<Integer, int[]> map = new HashMap<>(queries.length);
        for (int k = 0; k < s.length(); k++) {
            query(s, nums, map, k);
        }
        int[][] ans = new int[n][2];
        for (int i = 0; i < n; i++) {
            ans[i] = map.getOrDefault(q[i], new int[]{-1, -1});
        }
        return ans;
    }

    private void query(String s, int[] nums, Map<Integer, int[]> map, int k) {
        int num = 0;
        for (int j = 0; j < 31 && k + j < s.length(); j++) {
            num = (num << 1) + s.charAt(k + j) - '0';
            if (!map.containsKey(num)) {
                if (exist(nums, num)) {
                    map.put(num, new int[]{k, k + j});
                }
            }
            if (num == 0) {
                return;
            }
        }
    }

    private boolean exist(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }

    /**
     * 超时
     */
    public int[][] substringXorQueries2(String s, int[][] queries) {
        int n = queries.length;
        int[][] ans = new int[n][2];
        for (int i = 0; i < n; i++) {
            int target = queries[i][0] ^ queries[i][1];
            ans[i] = doQuery(s, target);
        }
        return ans;
    }

    private int[] doQuery(String s, int target) {
        for (int left = 0; left < s.length(); left++) {
            int right = calRight(s, target, left);
            if (right != -1) {
                return new int[]{left, right};
            }
        }
        return new int[]{-1, -1};
    }

    private int calRight(String s, int target, int left) {
        if (target == 0) {
            return s.charAt(left) == '0' ? left : -1;
        }
        if (target == 1) {
            return s.charAt(left) == '1' ? left : -1;
        }
        if (s.charAt(left) == '0') {
            return -1;
        }
        long num = 1;
        for (int j = left + 1; j < s.length(); j++) {
            if (s.charAt(j) == '0') {
                num <<= 1;
            } else {
                num = (num << 1) + 1;
            }
            if (num == target) {
                return j;
            } else if (num > target) {
                return -1;
            }
        }
        return -1;
    }

    /**
     * 给你两个字符串 s 和 t 。
     *
     * 你可以从字符串 t 中删除任意数目的字符。
     *
     * 如果没有从字符串 t 中删除字符，那么得分为 0 ，否则：
     *
     * 令 left 为删除字符中的最小下标。
     * 令 right 为删除字符中的最大下标。
     * 字符串的得分为 right - left + 1 。
     *
     * 请你返回使 t 成为 s 子序列的最小得分。
     *
     * 一个字符串的 子序列 是从原字符串中删除一些字符后（也可以一个也不删除），剩余字符不改变顺序得到的字符串。（比方说 "ace" 是 "abcde" 的子序列，但是 "aec" 不是）。
     *
     *  
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/subsequence-with-the-minimum-score
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 查看示例代码
     * 添加备注
     *
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 32.61%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 76.30%
     * 的用户
     * 通过测试用例：
     * 54 / 54
     */
    public int minimumScore(String s, String t) {
        int n = s.length(), m = t.length();
        int[] pre = new int[n + 1];
        pre[0] = -1;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (j < m && s.charAt(i) == t.charAt(j)) {
                pre[i + 1] = j;
                j++;
            } else {
                pre[i + 1] = pre[i];
            }
        }
        int[] suf = new int[n + 1];
        suf[n] = m;
        j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && s.charAt(i) == t.charAt(j)) {
                suf[i] = j;
                j--;
            } else {
                suf[i] = suf[i + 1];
            }
        }
        int ans = suf[0];
        for (int i = 0; i < n; i++) {
            // pre[i + 1] + 1 ~ s[i + 1] - 1
            int cur = Math.max(suf[i + 1] - pre[i + 1] - 1, 0);
            ans = Math.min(ans, cur);
        }
        return ans;
    }

    private boolean check(String s, String t, int score) {
        int i = 0, j = 0;
        int p = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
                p = i;
            } else {
                i++;
            }
        }
        // 删除 t[i] ~ t[i + score -1]

        return false;
    }

    public static void main(String[] args) {
//        String s = "0000001111101001010";
//        int[][] q = {{0,3914},{2,4},{2,2},{3,8009},{4,3918},{7,26}};
//        ArrayUtil.print(new LC0212().substringXorQueries(s, q));
//        "dcadebdecbeaedd"
//        "dcdadeb""acdedcdbabecdbebda""adebddaccdcabaade"
//"adbae"
//"bbecddb""gbjbacdiiiecgceeafdcdhjhhcjfchjbejibhejgjhhhjhifahfbbcfcajehjgcjgffjdejbhjahgffgdaifhhgaadjiabfdf"
//"hidefgbgjghceagdaaib"
        String s = "gbjbacdiiiecgceeafdcdhjhhcjfchjbejibhejgjhhhjhifahfbbcfcajehjgcjgffjdejbhjahgffgdaifhhgaadjiabfdf";
        String t = "hidefgbgjghceagdaaib";
        System.out.println(new LC0212().minimumScore(s, t));
    }

}
