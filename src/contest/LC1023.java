package contest;

import java.util.*;

public class LC1023 {
    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 nums 的子数组中元素的最大公因数等于 k 的子数组数目。
     * <p>
     * 子数组 是数组中一个连续的非空序列。
     * <p>
     * 数组的最大公因数 是能整除数组中所有元素的最大整数。
     * 1 <= nums.length <= 1000
     * 1 <= nums[i], k <= 109
     *
     * 通过
     * 7 ms	41.3 MB	Java
     */
    public int subarrayGCD(int[] nums, int k) {
        int ans = 0;
        for (int l = 0; l < nums.length; l++) {
            if (nums[l] % k != 0) {
                continue;
            }
            int divisor = 0;
            for (int r = l; r < nums.length; r++) {
                if (nums[l] % k != 0) {
                    break;
                }
                if (r == l) {
                    divisor = nums[l];
                } else {
                    // 计算divisor和nums[r]的最大公约数
                    divisor = calMaxCommonDivisor(divisor, nums[r]);
                }
                if (divisor == k) {
                    ans++;
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

    /**
     * 给你两个下标从 0 开始的数组 nums 和 cost ，分别包含 n 个 正 整数。
     *
     * 你可以执行下面操作 任意 次：
     *
     * 将 nums 中 任意 元素增加或者减小 1 。
     * 对第 i 个元素执行一次操作的开销是 cost[i] 。
     *
     * 请你返回使 nums 中所有元素 相等 的 最少 总开销。
     *
     * n == nums.length == cost.length
     * 1 <= n <= 105
     * 1 <= nums[i], cost[i] <= 106
     */
    /**
     * 假设都移到某个位置 p
     * 位置 p 向左移动一位的代价是 右边的代价和 - 左边的代价和
     * 位置 p 向右移动一位的代价是 左边的代价和 - 右边的代价和
     * 先对数据排序
     * 定义数组 costLeft[p] 表示 p 位置左移的代价
     * 定义数组 costRight[p] 表示 p 位置右移的代价
     * 可以推导 costLeft[p] + costRight[p] = 2 * cost[p] > 0
     * 可以判定必定有一个方向代价增加的
     * 存在三种情况
     * 1. 一正一负：有一个方向代价是减小的，往这个方向移动
     * 2. 一正一零：有一个方向代价无差别，代表无需移动
     * 3. 两正：两个方向代价都是增加的，代表这个位置是最优的
     */
    /**
     *
     *
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 13 ms
     * , 在所有 Java 提交中击败了
     * 95.84%
     * 的用户
     * 内存消耗：
     * 54.7 MB
     * , 在所有 Java 提交中击败了
     * 63.22%
     * 的用户
     * 通过测试用例：
     * 48 / 48
     */
    public long minCost(int[] nums, int[] cost) {
        int n = nums.length;
        Node[] nodes = new Node[n];
        long totalCost = 0L;
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(nums[i], cost[i]);
            totalCost += cost[i];
        }
        Arrays.sort(nodes, (o1, o2) -> o1.position - o2.position);
        // 计算每个位置的代价 由于向左和向右代价数组每个位置相加为 2 * cost[p]，只算一个数据即可
        long[] leftCosts = new long[n];
        leftCosts[0] = totalCost;
        for (int i = 1; i <= n - 1; i++) {
            leftCosts[i] = leftCosts[i - 1] - 2L * nodes[i - 1].cost;
        }
        // 二分判断
        int l = 0;
        int r = n - 1;
        int mid = 0;
        while (l < r) {
            mid = l + ((r - l) >> 1);
            // 计算移动代价
            long leftCost = leftCosts[mid];
            long rightCost = 2L * nodes[mid].cost - leftCost;
//            System.out.println(l + " ~~~ " + r + ", mid: " + mid + ", lc: " + leftCost + ", rc: " + rightCost);
            if (leftCost == 0 || rightCost == 0) {
                l = mid;
                // 找到位置直接返回mid
                break;
            }
            if (leftCost > 0) {
                // 左侧位置可以丢弃
                l = mid;
            }
            if (rightCost < 0) {
                // 往右移动
                l++;
            }
            if (rightCost > 0) {
                // 右侧位置可以丢弃
                r = mid;
            }
            if (leftCost < 0) {
                // 往左移动
                r--;
            }
        }
        int target = nodes[l].position;
//        System.out.println(target);
        long ans = 0L;
        for (int i = 0; i < n; i++) {
            ans += (long) Math.abs(nums[i] - target) * cost[i];
        }
        return ans;
    }

    class Node {
        int position;
        int cost;
        public Node(int position, int cost) {
            this.position = position;
            this.cost = cost;
        }
    }

    /**
     * 给你两个正整数数组 nums 和 target ，两个数组长度相等。
     *
     * 在一次操作中，你可以选择两个 不同 的下标 i 和 j ，其中 0 <= i, j < nums.length ，并且：
     *
     * 令 nums[i] = nums[i] + 2 且
     * 令 nums[j] = nums[j] - 2 。
     * 如果两个数组中每个元素出现的频率相等，我们称两个数组是 相似 的。
     *
     * 请你返回将 nums 变得与 target 相似的最少操作次数。测试数据保证 nums 一定能变得与 target 相似。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [8,12,6], target = [2,14,10] // 6, 8, 12; 2, 10, 14 -> 4, 10, 12 -> 6, 8, 12
     * 输出：2
     * 解释：可以用两步操作将 nums 变得与 target 相似：
     * - 选择 i = 0 和 j = 2 ，nums = [10,12,4] 。
     * - 选择 i = 1 和 j = 2 ，nums = [10,14,2] 。
     * 2 次操作是最少需要的操作次数。
     *
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 45 ms
     * , 在所有 Java 提交中击败了
     * 54.90%
     * 的用户
     * 内存消耗：
     * 58.2 MB
     * , 在所有 Java 提交中击败了
     * 50.42%
     * 的用户
     * 通过测试用例：
     * 32 / 32
     */
    public long makeSimilar(int[] nums, int[] target) {
        Arrays.sort(nums);
        Arrays.sort(target);
        List<Integer> oddNums = new ArrayList<>();
        List<Integer> evenNums = new ArrayList<>();
        List<Integer> oddTargets = new ArrayList<>();
        List<Integer> evenTargets = new ArrayList<>();
        for (int num : nums) {
            if ((num & 1) != 0) {
                oddNums.add(num);
            } else {
                evenNums.add(num);
            }
        }
        for (int targetNum : target) {
            if ((targetNum & 1) != 0) {
                oddTargets.add(targetNum);
            } else {
                evenTargets.add(targetNum);
            }
        }

        long ans = 0L;
        int delta = 0;
        for (int i = 0; i < oddNums.size(); i++) {
            int move = (oddNums.get(i) - oddTargets.get(i)) >> 1;
            if (move > 0) {
                ans += move;
            }
            delta += move;
        }
        for (int i = 0; i < evenNums.size(); i++) {
            int move = (evenNums.get(i) - evenTargets.get(i)) >> 1;
            if (move > 0) {
                ans += move;
            }
            delta += move;
        }
        return ans + delta;
    }

    public static void main(String[] args) {
//        int[] nums = {3,12,9,6};
//        int k = 3;
//        System.out.println(new LC1023().subarrayGCD(nums, k));
//        System.out.println(new LC1023().calMaxCommonDivisor(12, 9));

//        int[] nums2 = {1, 2};
//        int[] cost = {1, 100};
//        System.out.println(new LC1023().minCost(nums2, cost));

        int[] nums = {8,12,6};
        int[] target = {2, 10, 14};
        System.out.println(new LC1023().makeSimilar(nums, target));
    }
}
