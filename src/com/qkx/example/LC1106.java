package com.qkx.example;

import java.util.*;

public class LC1106 {
    public int[] applyOperations(int[] nums) {
        for (int i = 0; i <= nums.length - 2; i++) {
            if (nums[i] == nums[i + 1]) {
                nums[i] *= 2;
                nums[i + 1] = 0;
            }
        }

        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
        return nums;
    }

    public long maximumSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return 0L;
        }
        Map<Integer, Integer> countMap = new HashMap<>(k);
        long sum = 0;
        for (int i = 0; i <= k - 1; i++) {
            Integer count = countMap.getOrDefault(nums[i], 0);
            countMap.put(nums[i], count + 1);
            sum += nums[i];
        }
        long result = countMap.size() == k ? sum : 0L;

        for (int i = k; i < nums.length; i++) {
            int last = nums[i - k];
            sum -= last;
            Integer lastCount = countMap.get(last);
            if (lastCount <= 1) {
                countMap.remove(last);
            } else {
                countMap.put(last, lastCount - 1);
            }

            int next = nums[i];
            sum += next;
            Integer count = countMap.getOrDefault(next, 0);
            countMap.put(next, count + 1);

            result = countMap.size() == k ? Math.max(result, sum) : result;
        }
        return result;
    }


    class CostNode {
        public int cost;
        public int index;

        public CostNode(int cost, int index) {
            this.cost = cost;
            this.index = index;
        }
    }

    class NodeComparator implements Comparator<CostNode> {
        @Override
        public int compare(CostNode o1, CostNode o2) {
            if (o1.cost != o2.cost) {
                return o1.cost - o2.cost;
            }
            return o1.index - o2.index;
        }
    }

    /**
     * 通过
     * 76 ms	53.2 MB	Java	2022/11/09 20:28
     */
    public long totalCost(int[] costs, int k, int candidates) {
        int leftBound = candidates - 1;
        int rightBound = costs.length - candidates;
        // 重复入堆
        rightBound = leftBound < rightBound ? rightBound : leftBound + 1;
        PriorityQueue<CostNode> left = new PriorityQueue<>(candidates, new NodeComparator());
        PriorityQueue<CostNode> right = new PriorityQueue<>(candidates, new NodeComparator());
        for (int i = 0; i <= leftBound; i++) {
            left.add(new CostNode(costs[i], i));
        }
        for (int i = costs.length - 1; i >= rightBound; i--) {
            right.add(new CostNode(costs[i], i));
        }

        long cost = 0L;
        for (int i = 1; i <= k; i++) {
            CostNode leftMin = left.peek();
            CostNode rightMin = right.peek();
            CostNode min = null;
            if (leftMin != null && rightMin != null) {
                min = leftMin.cost <= rightMin.cost ? left.poll() : right.poll();
            } else if (leftMin != null) {
                min = left.poll();
            } else if (rightMin != null) {
                min = right.poll();
            }
            cost += min != null ? min.cost : 0L;

            if (leftBound + 1 < rightBound) {
                if (min.index <= leftBound) {
                    leftBound++;
                    left.add(new CostNode(costs[leftBound], leftBound));
                }
                if (min.index >= rightBound && leftBound + 1 <= rightBound) {
                    rightBound--;
                    right.add(new CostNode(costs[rightBound], rightBound));
                }
            }
        }
        return cost;
    }

    /**
     * 通过
     * 90 ms	49.3 MB	Java	2022/11/09 20:14
     */
    public long totalCost2(int[] costs, int k, int candidates) {
        int n = costs.length;
        int leftBound;
        int rightBound;
        if (candidates > n / 2) {
            leftBound = n - 2;
            rightBound = n - 1;
        } else {
            leftBound = candidates - 1;
            rightBound = costs.length - candidates;
        }
        PriorityQueue<CostNode> left = new PriorityQueue<>(candidates, new NodeComparator());
        PriorityQueue<CostNode> right = new PriorityQueue<>(candidates, new NodeComparator());
        for (int i = 0; i <= leftBound; i++) {
            left.add(new CostNode(costs[i], i));
        }
        for (int i = costs.length - 1; i >= rightBound; i--) {
            right.add(new CostNode(costs[i], i));
        }

        long cost = 0L;
        for (int i = 1; i <= k; i++) {
            CostNode leftMin = left.peek();
            CostNode rightMin = right.peek();
            if (leftBound + 1 < rightBound) {
                CostNode min = leftMin.cost <= rightMin.cost ? left.poll() : right.poll();
                if (min.index <= leftBound) {
                    leftBound++;
                    left.add(new CostNode(costs[leftBound], leftBound));
                }
                if (min.index >= rightBound) {
                    rightBound--;
                    right.add(new CostNode(costs[rightBound], rightBound));
                }
                cost += min.cost;
            } else {
                CostNode min = null;
                if (leftMin != null && rightMin != null) {
                    min = leftMin.cost <= rightMin.cost ? left.poll() : right.poll();
                } else if (leftMin != null) {
                    min = left.poll();
                } else if (rightMin != null) {
                    min = right.poll();
                }
                cost += min != null ? min.cost : 0L;
            }
        }
        return cost;
    }


    /**
     * [31,25,72,79,74,65,84,91,18,59,27,9,81,33,17,58]
     * 11
     * 2
     * <p>
     * 输入：
     * [28,35,21,13,21,72,35,52,74,92,25,65,77,1,73,32,43,68,8,100,84,80,14,88,42,53,98,69,64,40,60,23,99,83,5,21,76,34]
     * 32
     * 12
     * 输出：
     * 853
     * 预期：
     * 1407
     * <p>
     * 输入：
     * [18,64,12,21,21,78,36,58,88,58,99,26,92,91,53,10,24,25,20,92,73,63,51,65,87,6,17,32,14,42,46,65,43,9,75]
     * 13
     * 23
     * 输出：
     * 202
     * 预期结果：
     * 223
     *
     * @param args
     */
    public static void main(String[] args) {
//        int[] costs = {17,12,10,2,7,2,11,20,8};
//        int k = 3;
//        int candidates = 4;
        int[] costs = {18, 64, 12, 21, 21, 78, 36, 58, 88, 58, 99, 26, 92, 91, 53, 10, 24, 25, 20, 92, 73, 63, 51, 65, 87, 6, 17, 32, 14, 42, 46, 65, 43, 9, 75};
        System.out.println("length >> " + costs.length);
        int k = 13;
        int candidates = 23;
        System.out.println(new LC1106().totalCost(costs, k, candidates));
    }

    /**
     * 输入：robot = [0,4,6], factory = [[2,2],[6,2]]
     * 输出：4
     * 解释：如上图所示：
     * - 第一个机器人从位置 0 沿着正方向移动，在第一个工厂处维修。
     * - 第二个机器人从位置 4 沿着负方向移动，在第一个工厂处维修。
     * - 第三个机器人在位置 6 被第二个工厂维修，它不需要移动。
     * 第一个工厂的维修上限是 2 ，它维修了 2 个机器人。
     * 第二个工厂的维修上限是 2 ，它维修了 1 个机器人。
     * 总移动距离是 |2 - 0| + |2 - 4| + |6 - 6| = 4 。没有办法得到比 4 更少的总移动距离。
     */
    /**
     * 特性：
     * 工厂一定是负责相邻的机器人，比跨越工厂的机器人维修成本低
     * 如果机器人两边同时有工厂，一定是选离的近的工厂。
     * 1. 机器人、工厂按位置排序
     * 2. 从前到后遍历
     * 3. 每个工厂尽可能负责前面的机器人的维修，如果有盈余，将盈余传递给下一个工厂比较
     */
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Arrays.sort(factory, (o1, o2) -> o1[0] - o2[0]);
        robot.sort((o1, o2) -> o1 - o2);
        // 指向机器人位置
        int j = 0;
        long dis = 0L;
        Stack<FactoryNode> stack = new Stack<>();
        for (int k = 0; k <= factory.length - 1; k++) {
            int pos = factory[k][0];
            int limit = factory[k][1];
            // 从j开始遍历机器人，处理pos之前的机器人
            while (j < robot.size() && robot.get(j) <= pos) {
                // 处理j
                Integer rp = robot.get(j);
                FactoryNode prePos = stack.peek();
                if (prePos == null) {
                    // 只能使用当前工厂
                    limit--;
                } else {
                    // 存在之前工厂，比较距离
//                    if (Math.abs(prePos.current - rp) <= )
                }
                j++;
            }
        }
        return 0L;
    }

    class FactoryNode {
        int pos;
        int current;

        public FactoryNode(int pos, int current) {
            this.pos = pos;
            this.current = current;
        }
    }
}
