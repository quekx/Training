package contest.lc2024;

import java.util.Arrays;

// 1725
public class LC0513 {
    public int countSeniors(String[] details) {
        int ans = 0;
        for (String d : details) {
            int age = (d.charAt(11) - '0') * 10 + (d.charAt(12) - '0');
            if (age > 60) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * https://leetcode.cn/problems/power-of-heroes/
     * 执行结果：
     * 通过
     * 显示详情
     * 查看示例代码
     * 添加备注
     *
     * 执行用时：
     * 15 ms
     * , 在所有 Java 提交中击败了
     * 58.82%
     * 的用户
     * 内存消耗：
     * 53.9 MB
     * , 在所有 Java 提交中击败了
     * 23.53%
     * 的用户
     * 通过测试用例：
     * 2582 / 2582
     */
    public int sumOfPower(int[] nums) {
        int n = nums.length, mod = 1000_000_007;
        Arrays.sort(nums);
        long[] f = new long[n + 1];
        for (int i = 0; i < n; i++) {
            f[i + 1] = (f[i] * 2 + nums[i]) % mod;
        }

        long ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            ans += ((f[i] + nums[i]) * nums[i] % mod) * nums[i];
            ans %= mod;
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        long mod = 1000_000_007;
        System.out.println(mod * mod);
        System.out.println(mod * mod * mod);

        System.out.println(Long.MAX_VALUE);
    }
}
