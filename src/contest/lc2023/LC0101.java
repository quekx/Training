package contest.lc2023;

import java.util.HashSet;
import java.util.Set;

public class LC0101 {
    /**
     * 15
     * 给你一个整数 num ，返回 num 中能整除 num 的数位的数目。
     *
     * 如果满足 nums % val == 0 ，则认为整数 val 可以整除 nums 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/count-the-digits-that-divide-a-number
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
     * 38.1 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 56 / 56
     */
    public int countDigits(int num) {
        int ans = 0;
        int t = num;
        while (t > 0) {
            int x = t % 10;
            if (num % x == 0) {
                ans++;
            }
            t /= 10;
        }
        return ans;
    }

    /**
     * 给你一个正整数数组 nums ，对 nums 所有元素求积之后，找出并返回乘积中 不同质因数 的数目。
     *
     * 注意：
     *
     * 质数 是指大于 1 且仅能被 1 及自身整除的数字。
     * 如果 val2 / val1 是一个整数，则整数 val1 是另一个整数 val2 的一个因数。
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/distinct-prime-factors-of-product-of-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 51 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 42.1 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 81 / 81
     */
    public int distinctPrimeFactors(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            while (num > 1) {
                int x = 2;
                while (num % x != 0) {
                    x++;
                }
                set.add(x);
                num /= x;
            }
        }
        return set.size();
    }

    /**
     * 给你一个字符串 s ，它每一位都是 1 到 9 之间的数字组成，同时给你一个整数 k 。
     * <p>
     * 如果一个字符串 s 的分割满足以下条件，我们称它是一个 好 分割：
     * <p>
     * s 中每个数位 恰好 属于一个子字符串。
     * 每个子字符串的值都小于等于 k 。
     * 请你返回 s 所有的 好 分割中，子字符串的 最少 数目。如果不存在 s 的 好 分割，返回 -1 。
     * <p>
     * 注意：
     * <p>
     * 一个字符串的 值 是这个字符串对应的整数。比方说，"123" 的值为 123 ，"1" 的值是 1 。
     * 子字符串 是字符串中一段连续的字符序列。
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/partition-string-into-substrings-with-values-at-most-k
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 42 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 50 / 50
     */
    public int minimumPartition(String s, int k) {
        char[] arr = s.toCharArray();
        for (char c : arr) {
            if (c - '0' > k) {
                return -1;
            }
        }
        int ans = 0;
        long x = 0;
        int j = 0;
        while (j < arr.length) {
            int cur = arr[j] - '0';
            long tmp = x * 10 + cur;
            if (tmp > k) {
                x = cur;
                ans++;
            } else {
                x = tmp;
            }
            j++;
        }
        ans++;
        return ans;
    }

    /**
     * 给你两个正整数 left 和 right ，请你找到两个整数 num1 和 num2 ，它们满足：
     * <p>
     * left <= nums1 < nums2 <= right  。
     * nums1 和 nums2 都是 质数 。
     * nums2 - nums1 是满足上述条件的质数对中的 最小值 。
     * 请你返回正整数数组 ans = [nums1, nums2] 。如果有多个整数对满足上述条件，请你返回 nums1 最小的质数对。如果不存在符合题意的质数对，请你返回 [-1, -1] 。
     * <p>
     * 如果一个整数大于 1 ，且只能被 1 和它自己整除，那么它是一个质数。
     * <p>
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/closest-prime-numbers-in-range
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 825 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 47.6 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 62 / 62
     * O(n*sqrt(n))
     */
    public int[] closestPrimes(int left, int right) {
        int[] primes = new int[1000_000];
        primes[0] = 2;
        int cur = 3;
        int count = 1;
        int delta = Integer.MAX_VALUE;
        int[] ans = new int[]{-1, -1};
        while (cur <= right) {
            for (int prime : primes) {
                if (prime == 0 || prime * prime > cur) {
                    primes[count] = cur;
                    int last = primes[count - 1];
                    if (last >= left && cur - last < delta) {
                        delta = cur - last;
                        ans = new int[]{last, cur};
                    }
                    count++;
                    cur++;
                    break;
                }
                if (cur % prime == 0) {
                    cur++;
                    break;
                }
            }
        }
        return ans;
    }
}
