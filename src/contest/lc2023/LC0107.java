package contest.lc2023;

public class LC0107 {
    /**
     * 给你四个整数 length ，width ，height 和 mass ，分别表示一个箱子的三个维度和质量，请你返回一个表示箱子 类别 的字符串。
     *
     * 如果满足以下条件，那么箱子是 "Bulky" 的：
     * 箱子 至少有一个 维度大于等于 104 。
     * 或者箱子的 体积 大于等于 109 。
     * 如果箱子的质量大于等于 100 ，那么箱子是 "Heavy" 的。
     * 如果箱子同时是 "Bulky" 和 "Heavy" ，那么返回类别为 "Both" 。
     * 如果箱子既不是 "Bulky" ，也不是 "Heavy" ，那么返回类别为 "Neither" 。
     * 如果箱子是 "Bulky" 但不是 "Heavy" ，那么返回类别为 "Bulky" 。
     * 如果箱子是 "Heavy" 但不是 "Bulky" ，那么返回类别为 "Heavy" 。
     * 注意，箱子的体积等于箱子的长度、宽度和高度的乘积。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/categorize-box-according-to-criteria
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.5 MB
     * , 在所有 Java 提交中击败了
     * 74.80%
     * 的用户
     * 通过测试用例：
     * 48 / 48
     */
    public String categorizeBox(int length, int width, int height, int mass) {
        boolean isBulky = length >= 10000
                || width >= 10000
                || height >= 10000
                || (long) length * width * height >= 1000_000_000;
        boolean isHeavy = mass >= 100;
        if (isBulky && isHeavy) {
            return "Both";
        }
        if (!isBulky && !isHeavy) {
            return "Neither";
        }
        return isBulky ? "Bulky" : "Heavy";
    }

    /**
     * 给你一个整数数据流，请你实现一个数据结构，检查数据流中最后 k 个整数是否 等于 给定值 value 。
     *
     * 请你实现 DataStream 类：
     *
     * DataStream(int value, int k) 用两个整数 value 和 k 初始化一个空的整数数据流。
     * boolean consec(int num) 将 num 添加到整数数据流。如果后 k 个整数都等于 value ，返回 true ，否则返回 false 。如果少于 k 个整数，条件不满足，所以也返回 false 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/find-consecutive-integers-from-a-data-stream
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 23 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 78 MB
     * , 在所有 Java 提交中击败了
     * 50.73%
     * 的用户
     * 通过测试用例：
     * 38 / 38
     */
    class DataStream {
        int value;
        int k;
        int p = 0;

        public DataStream(int value, int k) {
            this.value = value;
            this.k = k;
        }

        public boolean consec(int num) {
            if (num == value) {
                p += 1;
            } else {
                p = 0;
            }
            return p >= k;
        }
    }

    /**
     * 给你一个下标从 0 开始的整数数组 nums 。
     *
     * 三个下标 i ，j 和 k 的 有效值 定义为 ((nums[i] | nums[j]) & nums[k]) 。
     *
     * 一个数组的 xor 美丽值 是数组中所有满足 0 <= i, j, k < n  的三元组 (i, j, k) 的 有效值 的异或结果。
     *
     * 请你返回 nums 的 xor 美丽值。
     *
     * 注意：
     *
     * val1 | val2 是 val1 和 val2 的按位或。
     * val1 & val2 是 val1 和 val2 的按位与。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/find-xor-beauty-of-array
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
     * 57.7 MB
     * , 在所有 Java 提交中击败了
     * 47.29%
     * 的用户
     * 通过测试用例：
     * 32 / 32
     */
    public int xorBeauty(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }

    /**
     * 给你一个下标从 0 开始长度为 n 的整数数组 stations ，其中 stations[i] 表示第 i 座城市的供电站数目。
     * <p>
     * 每个供电站可以在一定 范围 内给所有城市提供电力。换句话说，如果给定的范围是 r ，在城市 i 处的供电站可以给所有满足 |i - j| <= r 且 0 <= i, j <= n - 1 的城市 j 供电。
     * <p>
     * |x| 表示 x 的 绝对值 。比方说，|7 - 5| = 2 ，|3 - 10| = 7 。
     * 一座城市的 电量 是所有能给它供电的供电站数目。
     * <p>
     * 政府批准了可以额外建造 k 座供电站，你需要决定这些供电站分别应该建在哪里，这些供电站与已经存在的供电站有相同的供电范围。
     * <p>
     * 给你两个整数 r 和 k ，如果以最优策略建造额外的发电站，返回所有城市中，最小供电站数目的最大值是多少。
     * <p>
     * 这 k 座供电站可以建在多个城市。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/maximize-the-minimum-powered-city
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     *  1 2 3 4 -> 0 1 3 6 10 -> sum[i, j] = s[j + 1] - s[i]
     *
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 31 ms
     * , 在所有 Java 提交中击败了
     * 75.98%
     * 的用户
     * 内存消耗：
     * 56.5 MB
     * , 在所有 Java 提交中击败了
     * 52.85%
     * 的用户
     * 通过测试用例：
     * 30 / 30
     */
    public long maxPowerNew(int[] stations, int r, int k) {
        int n = stations.length;
        long[] sum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + stations[i - 1];
        }
        long[] score = new long[n];
        long min = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // s[i - r, i + r]
            score[i] = sum[Math.min(i + r + 1, n)] - sum[Math.max(i - r, 0)];
            min = Math.min(min, score[i]);
        }
        long left = min, right = min + k + 1;
        while (left < right) {
            long mid = right - ((right - left) >> 1);
            if (!check(score, r, mid, k)) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    /**
     * check 操作可以及时截断返回
     */
    private boolean check(long[] scores, int r, long t, int k) {
        int n = scores.length;
        long[] diff = new long[n];
        // 查分数据前缀和
        int diffSum = 0;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            diffSum += diff[i];
            long cur = scores[i] + diffSum;
            if (cur >= t) {
                continue;
            }
            long delta = t - cur;
            ans += delta;
            if (ans > k) {
                return false;
            }
            diffSum += delta;
            diff[i] += delta;
            if (i + r * 2 + 1 < n) {
                diff[i + r * 2 + 1] -= delta;
            }
        }
        return true;
    }

    /**
     * 查分数组
     *
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 27 ms
     * , 在所有 Java 提交中击败了
     * 89.08%
     * 的用户
     * 内存消耗：
     * 57.9 MB
     * , 在所有 Java 提交中击败了
     * 27.30%
     * 的用户
     * 通过测试用例：
     * 30 / 30
     */
    public long maxPower2(int[] stations, int r, int k) {
        int n = stations.length;
        long[] sum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + stations[i - 1];
        }
        long[] score = new long[n];
        long min = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // s[i - r, i + r]
            score[i] = sum[Math.min(i + r + 1, n)] - sum[Math.max(i - r, 0)];
            min = Math.min(min, score[i]);
        }

        long left = min, right = min + k;
        while (left < right) {
            long mid = right - ((right - left) >> 1);
            long num = getNum2(score, r, mid);
            if (num > k) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    /**
     * 计算每个城市要达到最小供电站数目为 t，需要建造的电站数
     * 查分数组
     * 单次操作：O(n)
     */
    private long getNum2(long[] scores, int r, long t) {
        int n = scores.length;
        int[] diff = new int[n];
        // 查分数据前缀和
        int diffSum = 0;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            diffSum += diff[i];
            long cur = scores[i] + diffSum;
            if (cur >= t) {
                continue;
            }
            long delta = t - cur;
            ans += delta;
            diffSum += delta;
            diff[i] += delta;
            if (i + r * 2 + 1 < n) {
                diff[i + r * 2 + 1] -= delta;
            }
        }
        return ans;
    }


    /**
     * 树状数组更新
     * 性能相较较慢
     *
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 203 ms
     * , 在所有 Java 提交中击败了
     * 8.95%
     * 的用户
     * 内存消耗：
     * 56 MB
     * , 在所有 Java 提交中击败了
     * 64.42%
     * 的用户
     * 通过测试用例：
     * 30 / 30
     */
    public long maxPower3(int[] stations, int r, int k) {
        int n = stations.length;
        long[] sum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + stations[i - 1];
        }
        long[] score = new long[n];
        long min = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // s[i - r, i + r]
            score[i] = sum[Math.min(i + r + 1, n)] - sum[Math.max(i - r, 0)];
            min = Math.min(min, score[i]);
        }

        long left = min, right = min + k;
        while (left < right) {
            long mid = right - ((right - left) >> 1);
            long num = getNum3(score, r, mid);
            if (num > k) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    /**
     * 计算每个城市要达到最小供电站数目为 t，需要建造的电站数
     * 树状数组
     * 单次操作：O(n*log(n))
     */
    private long getNum3(long[] scores, int r, long t) {
        int n = scores.length;
        long[] pos = new long[n + 1];
        long ans = 0;
        for (int i = 0; i < n; i++) {
            long score = scores[i];
            int min = Math.max(i - r, 0);
            int max = Math.min(i + r, n - 1);
            score += range(min, max, pos);
            if (score < t) {
                long v = t - score;
                add(max, v, pos);
                ans += v;
            }
        }
        return ans;
    }

    private int lowBit(int x) {
        return x & -x;
    }

    private void add(int i, long v, long[] pos) {
        i += 1;
        while (i < pos.length) {
            pos[i] += v;
            i += lowBit(i);
        }
    }

    private long query(int i, long[] pos) {
        i += 1;
        long ans = 0;
        while (i > 0) {
            ans += pos[i];
            i -= lowBit(i);
        }
        return ans;
    }

    private long range(int l, int r, long[] pos) {
        return query(r, pos) - query(l - 1, pos);
    }

    /**
     * 计算每个城市要达到最小供电站数目为 t，需要建造的电站数
     * 超出时间限制
     * 单次操作：O(n*r) = O(n*n)
     */
    private long getNum2(int[] scores, int r, int t) {
        int n = scores.length;
        int[] pos = new int[n];
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int score = scores[i];
            int min = Math.min(i + r, n - 1);
            for (int j = Math.max(i - r, 0); j <= min; j++) {
                score += pos[j];
            }
            if (score < t) {
                pos[min] += t - score;
                ans += t - score;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {15, 45, 20, 2, 34, 35, 5, 44, 32, 30};
        System.out.println(new LC0107().xorBeauty(nums));

        int[] stations = {1,2,4,5,0};
        int r = 1;
        int k = 2;
        System.out.println(new LC0107().maxPowerNew(stations, r, k));
    }
}
