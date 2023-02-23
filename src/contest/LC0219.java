package contest;

import com.qkx.example.utils.ArrayUtil;

import java.util.HashMap;
import java.util.Map;

public class LC0219 {
    /**
     * 给你两个 二维 整数数组 nums1 和 nums2.
     *
     * nums1[i] = [idi, vali] 表示编号为 idi 的数字对应的值等于 vali 。
     * nums2[i] = [idi, vali] 表示编号为 idi 的数字对应的值等于 vali 。
     * 每个数组都包含 互不相同 的 id ，并按 id 以 递增 顺序排列。
     *
     * 请你将两个数组合并为一个按 id 以递增顺序排列的数组，并符合下述条件：
     *
     * 只有在两个数组中至少出现过一次的 id 才能包含在结果数组内。
     * 每个 id 在结果数组中 只能出现一次 ，并且其对应的值等于两个数组中该 id 所对应的值求和。如果某个数组中不存在该 id ，则认为其对应的值等于 0 。
     * 返回结果数组。返回的数组需要按 id 以递增顺序排列。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/merge-two-2d-arrays-by-summing-values
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
     * 42.1 MB
     * , 在所有 Java 提交中击败了
     * 49.93%
     * 的用户
     * 通过测试用例：
     * 39 / 39
     */
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        int ii = 0, jj = 0, len = 0;
        while (ii < nums1.length && jj < nums2.length) {
            if (nums1[ii][0] == nums2[jj][0]) {
                ii++;
                jj++;
            } else if (nums1[ii][0] < nums2[jj][0]) {
                ii++;
            } else {
                jj++;
            }
            len++;
        }
        len += nums1.length - ii + nums2.length - jj;
        int[][] ans = new int[len][2];
        int i = 0, j = 0, p = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i][0] == nums2[j][0]) {
                ans[p][0] = nums1[i][0];
                ans[p][1] = nums1[i][1] + nums2[j][1];
                i++;
                j++;
            } else if (nums1[i][0] < nums2[j][0]) {
                ans[p][0] = nums1[i][0];
                ans[p][1] = nums1[i][1];
                i++;
            } else {
                ans[p][0] = nums2[j][0];
                ans[p][1] = nums2[j][1];
                j++;
            }
            p++;
        }
        while (i < nums1.length) {
            ans[p][0] = nums1[i][0];
            ans[p][1] = nums1[i][1];
            i++;
            p++;
        }
        while (j < nums2.length) {
            ans[p][0] = nums2[j][0];
            ans[p][1] = nums2[j][1];
            j++;
            p++;
        }
        return ans;
    }

    /**
     * 给你一个正整数 n ，你可以执行下述操作 任意 次：
     *
     * n 加上或减去 2 的某个 幂
     * 返回使 n 等于 0 需要执行的 最少 操作数。
     *
     * 如果 x == 2i 且其中 i >= 0 ，则数字 x 是 2 的幂。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/minimum-operations-to-reduce-an-integer-to-0
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
     * 38.1 MB
     * , 在所有 Java 提交中击败了
     * 88.35%
     * 的用户
     * 通过测试用例：
     * 41 / 41
     */
    public int minOperations(int n) {
        int ans = 0;
        while (n > 1) {
            if ((n & 0x3) == 0x3) {
                n += 1;
                ans++;
            } else if ((n & 0x1) == 0x1) {
                n -= 1;
                ans++;
            }
            n >>= 1;
        }
        return ans + 1;
    }

    /**
     * 给你一个正整数数组 nums 。
     * <p>
     * 如果数组 nums 的子集中的元素乘积是一个 无平方因子数 ，则认为该子集是一个 无平方 子集。
     * <p>
     * 无平方因子数 是无法被除 1 之外任何平方数整除的数字。
     * <p>
     * 返回数组 nums 中 无平方 且 非空 的子集数目。因为答案可能很大，返回对 109 + 7 取余的结果。
     * <p>
     * nums 的 非空子集 是可以由删除 nums 中一些元素（可以不删除，但不能全部删除）得到的一个数组。如果构成两个子集时选择删除的下标不同，则认为这两个子集不同。
     * <p>
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/count-the-number-of-square-free-subsets
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int squareFreeSubsets(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            if (check(num)) {
                ans = (ans * 2 + 1) % 1000_000_007;
            }
        }
        return ans;
    }

    private boolean check(int num) {
        if (num == 1) {
            return true;
        }
        for (int x = 2; x * x <= num; x++) {
            if (num % (x * x) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 对任一由 n 个小写英文字母组成的字符串 word ，我们可以定义一个 n x n 的矩阵，并满足：
     * <p>
     * lcp[i][j] 等于子字符串 word[i,...,n-1] 和 word[j,...,n-1] 之间的最长公共前缀的长度。
     * 给你一个 n x n 的矩阵 lcp 。返回与 lcp 对应的、按字典序最小的字符串 word 。如果不存在这样的字符串，则返回空字符串。
     * <p>
     * 对于长度相同的两个字符串 a 和 b ，如果在 a 和 b 不同的第一个位置，字符串 a 的字母在字母表中出现的顺序先于 b 中的对应字母，则认为字符串 a 按字典序比字符串 b 小。例如，"aabd" 在字典上小于 "aaca" ，因为二者不同的第一位置是第三个字母，而 'b' 先于 'c' 出现。
     * <p>
     *  
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/find-the-string-with-lcp
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
     * 11 ms
     * , 在所有 Java 提交中击败了
     * 35.95%
     * 的用户
     * 内存消耗：
     * 128.6 MB
     * , 在所有 Java 提交中击败了
     * 15.03%
     * 的用户
     * 通过测试用例：
     * 55 / 55
     */
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        int[] p = new int[n];
        int cur = 1;
        p[n - 1] = cur++;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 1; j >= i + 1; j--) {
                if (lcp[i][j] != 0) {
                    // s[i] = s[j]
                    p[i] = p[j];
                }
            }
            if (p[i] == 0) {
                p[i] = cur++;
            }
        }
        if (cur > 27) {
            return "";
        }
        if (!check(lcp, p)) {
            return "";
        }
        Map<Integer, Character> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int d = 0;
        for (int i = 0; i < n; i++) {
            Character c = map.get(p[i]);
            if (c == null) {
                c = (char) ((int) 'a' + d++);
                map.put(p[i], c);
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private boolean check(int[][] lcp, int[] p) {
        int n = lcp.length;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (lcp[i][j] != lcp[j][i]) {
                    return false;
                }
                if (p[i] == p[j]) {
                    if (lcp[i][j] != (i + 1 < n && j + 1 < n ? lcp[i + 1][j + 1] + 1 : 1)) {
                        return false;
                    }
                } else {
                    if (lcp[i][j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public String findTheString2(int[][] lcp) {
        int n = lcp.length;
        for (int i = 0; i < n; i++) {
            if (lcp[i][i] != n - i) {
                return "";
            }
            for (int j = i + 1; j < n; j++) {
                if (lcp[i][j] != lcp[j][i]) {
                    return "";
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (lcp[i][n - 1] > 1) {
                return "";
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 2; j >= i + 1; j--) {
                if (lcp[i][j] != 0 && lcp[i][j] != lcp[i + 1][j + 1] + 1) {
                    return "";
                }
            }
        }

        int[] s = new int[n];
        int cur = 1;
        s[n - 1] = cur++;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 1; j >= i + 1; j--) {
                if (i == 0) {
                    System.out.println(123);
                }
                if (lcp[i][j] != 0) {
                    // s[i] = s[j]
                    if (s[i] == 0) {
                        s[i] = s[j];
                    } else if (s[i] != s[j]) {
                        return "";
                    }
                } else {
                    // s[i] != s[j]
                    // todo: 这里还需要标记 s[i] != s[j]
                    //  不然后面赋值的时候有没判断的边界情况
                    if (s[i] != 0 && s[i] == s[j]) {
                        return "";
                    }
                }
            }
            if (s[i] == 0) {
                s[i] = cur++;
            }
        }
        if (cur > 27) {
            return "";
        }
        Map<Integer, Character> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int d = 0;
        for (int i = 0; i < n; i++) {
            Character c = map.get(s[i]);
            if (c == null) {
                c = (char) ((int) 'a' + d++);
                map.put(s[i], c);
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        int[][] lcp = {{4,0,2,0},{0,3,0,1},{2,0,2,0},{0,1,0,1}};
        int[][] lcp = {{3,2,0},{2,2,1},{0,1,1}};
        ArrayUtil.print(lcp);
        System.out.println("------");
        System.out.println(new LC0219().findTheString2(lcp));
    }
}
