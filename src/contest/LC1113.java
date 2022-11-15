package contest;

import com.qkx.example.model.TreeNode;

import java.util.*;

public class LC1113 {
    public double[] convertTemperature(double celsius) {

        return new double[]{celsius + 273.15, celsius * 1.80 + 32.00};
    }

    public int subarrayLCM(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        for (int l = 0; l <= n - 1; l++) {
            int common = 1;
            for (int r = l; r <= n - 1; r++) {
                if (k % nums[r] != 0) {
                    break;
                }
                // 计算最小公倍数
                int commonDivisor = calMaxCommonDivisor(common, nums[r]);
                common = common * nums[r] / commonDivisor;
                if (common == k) {
                    ans++;
                } else if (common > 1 && k % common != 0) {
                    break;
                }
            }
        }
        return ans;
    }

    private int calMaxCommonDivisor(int a, int b) {
        int ans = 1;
        for (int i = 2; i <= a && i <= b; i++) {
            if (a % i == 0 && b % i == 0) {
                ans = i;
            }
        }
        return ans;
    }

    private int calMaxCommonDivisor2(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    class NumberNode {
        int val;
        int index;

        public NumberNode(int val, int index) {
            this.val = val;
            this.index = index;
        }

        @Override
        public String toString() {
            return val + ":" + index + "";
        }
    }

    public int minimumOperations(TreeNode root) {
        // 层序遍历
        Queue<TreeNode> cur = new LinkedList<>();
        cur.add(root);

        int ans = 0;
        while (!cur.isEmpty()) {
            Queue<TreeNode> next = new LinkedList<>();
            List<NumberNode> list = new ArrayList<>();
            PriorityQueue<NumberNode> nums = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
            int index = 0;
            int size = cur.size();
            for (int k = 0; k < size; k++) {
                TreeNode node = cur.poll();
                NumberNode curNum = new NumberNode(node.val, index++);
                list.add(curNum);
                nums.add(curNum);
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            cur = next;

            System.out.println(list);
            System.out.println();
            NumberNode[] arr = list.toArray(new NumberNode[0]);
            for (int i = 0; i < list.size(); i++) {
                NumberNode min = nums.poll();
                if (min.index != i) {
                    // 当前最小序号不匹配
                    // 将当前最小的元素和 i 位置（榜首）的元素交换
                    NumberNode top = arr[i];
                    arr[i] = min;
                    arr[min.index] = top;
                    top.index = min.index;
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 给你一个字符串 s 和一个 正 整数 k 。
     * <p>
     * 从字符串 s 中选出一组满足下述条件且 不重叠 的子字符串：
     * <p>
     * 每个子字符串的长度 至少 为 k 。
     * 每个子字符串是一个 回文串 。
     * 返回最优方案中能选择的子字符串的 最大 数目。
     * <p>
     * 子字符串 是字符串中一个连续的字符序列。
     */
    /**
     * 40 / 40 个通过测试用例
     * 状态：通过
     * 执行用时: 89 ms
     * 内存消耗: 50.2 MB
     */
    public int maxPalindromes(String s, int k) {
        char[] arr = s.toCharArray();

        int n = s.length();
        boolean dp[][] = new boolean[n][n];
        for (int i = 0; i <= n - 1; i++) {
            dp[i][i] = true;
        }
        for (int i = 0; i <= n - 2; i++) {
            dp[i][i + 1] = arr[i] == arr[i + 1];
        }
        for (int len = 3; len <= n; len++) {
            // [i][i + len - 1]
            for (int i = 0; i + len - 1 <= n - 1; i++) {
                dp[i][i + len - 1] = arr[i] == arr[i + len - 1] && dp[i + 1][i + len - 2];
            }
        }

        // ans[i] 代表以 i 为结尾的数组，符合题目的不重叠回文子串数目
        // ans[i] = max{max(ans[j - 1] + 1, if s[j][i]为回文串), ans[i - 1]}
        int[] ans = new int[n];
        for (int i = k - 1; i <= n - 1; i++) {
            // 初始化区间长度为 k
            ans[i] = dp[i - k + 1][i] ? 1 : 0;
        }

        // 计算区间长度为 k + 1 及以上
        // 区间切分为[0, j - 1] + [j, i]
        for (int i = k; i <= n - 1; i++) {
            ans[i] = ans[i - 1];
            ans[i] = Math.max(ans[i], dp[0][i] ? 1 : 0);
            for (int j = i - k + 1; j >= 1; j--) {
                if (dp[j][i]) {
                    ans[i] = Math.max(ans[i], ans[j - 1] + 1);
                }
            }
        }
        return ans[n - 1];
    }

    // [49,45,1,20,46,15,39,27,null,null,null,25]
    public static void main(String[] args) {
//        int[] nums = {3, 6, 2, 7, 1};
//        int k = 6;
//        System.out.println(new LC1113().subarrayLCM(nums, k));
//        TreeNode root = new TreeNode(49);
//
//        TreeNode l = new TreeNode(45);
//        root.left = l;
//        l.left = new TreeNode(20);
//        l.right = new TreeNode(46);
//        l.left.left = new TreeNode(27);
//
//        TreeNode r = new TreeNode(1);
//        root.right = r;
//        r.left = new TreeNode(15);
//        ;
//        r.right = new TreeNode(39);
//        r.left.left = new TreeNode(25);
//
//
//        System.out.println(new LC1113().minimumOperations(root));
        String s = "abaccdbbd";
        int k = 3;
        System.out.println(new LC1113().maxPalindromes(s, k));
    }
}
