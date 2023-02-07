package contest;

import java.util.*;

public class LC0108 {
    /**
     * 给你一个按 非递减顺序 排列的数组 nums ，返回正整数数目和负整数数目中的最大值。
     *
     * 换句话讲，如果 nums 中正整数的数目是 pos ，而负整数的数目是 neg ，返回 pos 和 neg二者中的最大值。
     * 注意：0 既不是正整数也不是负整数。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/maximum-count-of-positive-integer-and-negative-integer
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.5 MB
     * , 在所有 Java 提交中击败了
     * 60.48%
     * 的用户
     * 通过测试用例：
     * 164 / 164
     */
    public int maximumCount(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = r - ((r - l) >> 1);
            if (nums[mid] < 0) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        int lp = nums[l] < 0 ? l : -1;
        l = 0;
        r = n - 1;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] > 0) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        int rp = nums[l] > 0 ? l : n;
        // [0 ~ lp] vs [rp, n - 1]
        return Math.max(lp + 1, n - rp);
    }

    /**
     * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。你的 起始分数 为 0 。
     *
     * 在一步 操作 中：
     *
     * 选出一个满足 0 <= i < nums.length 的下标 i ，
     * 将你的 分数 增加 nums[i] ，并且
     * 将 nums[i] 替换为 ceil(nums[i] / 3) 。
     * 返回在 恰好 执行 k 次操作后，你可能获得的最大分数。
     *
     * 向上取整函数 ceil(val) 的结果是大于或等于 val 的最小整数。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/maximal-score-after-applying-k-operations
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 118 ms
     * , 在所有 Java 提交中击败了
     * 72.77%
     * 的用户
     * 内存消耗：
     * 58.8 MB
     * , 在所有 Java 提交中击败了
     * 20.24%
     * 的用户
     * 通过测试用例：
     * 39 / 39
     */
    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int num : nums) {
            queue.add(num);
        }
        long ans = 0;
        for (int i = 0; i < k && !queue.isEmpty(); i++) {
            int max = queue.poll();
            ans += max;
            queue.add(max % 3 == 0 ? max / 3 : max / 3 + 1);
        }
        return ans;
    }

    /**
     * 给你两个下标从 0 开始的字符串 word1 和 word2 。
     *
     * 一次 移动 由以下两个步骤组成：
     *
     * 选中两个下标 i 和 j ，分别满足 0 <= i < word1.length 和 0 <= j < word2.length ，
     * 交换 word1[i] 和 word2[j] 。
     * 如果可以通过 恰好一次 移动，使 word1 和 word2 中不同字符的数目相等，则返回 true ；否则，返回 false 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/make-number-of-distinct-characters-equal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 51 ms
     * , 在所有 Java 提交中击败了
     * 17.85%
     * 的用户
     * 内存消耗：
     * 44.3 MB
     * , 在所有 Java 提交中击败了
     * 15.24%
     * 的用户
     * 通过测试用例：
     * 99 / 99
     */
    public boolean isItPossible(String word1, String word2) {
        Map<Character, Integer> counter1 = new HashMap<>();
        Map<Character, Integer> counter2 = new HashMap<>();
        for (char c : word1.toCharArray()) {
            counter1.put(c, counter1.getOrDefault(c, 0) + 1);
        }
        for (char c : word2.toCharArray()) {
            counter2.put(c, counter2.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry1 : counter1.entrySet()) {
            char x = entry1.getKey();
            int m = entry1.getValue();
            for (Map.Entry<Character, Integer> entry2 : counter2.entrySet()) {
                char y = entry2.getKey();
                int n = entry2.getValue();
                if (x == y) {
                    if (counter1.size() == counter2.size()) {
                        return true;
                    }
                } else {
                    int num1 = m == 1 ? counter1.size() - 1 : counter1.size();
                    num1 = counter1.containsKey(y) ? num1 : num1 + 1;
                    int num2 = n == 1 ? counter2.size() - 1 : counter2.size();
                    num2 = counter2.containsKey(x) ? num2 : num2 + 1;
                    if (num1 == num2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 共有 k 位工人计划将 n 个箱子从旧仓库移动到新仓库。给你两个整数 n 和 k，以及一个二维整数数组 time ，数组的大小为 k x 4 ，其中 time[i] = [leftToRighti, pickOldi, rightToLefti, putNewi] 。
     *
     * 一条河将两座仓库分隔，只能通过一座桥通行。旧仓库位于河的右岸，新仓库在河的左岸。开始时，所有 k 位工人都在桥的左侧等待。为了移动这些箱子，第 i 位工人（下标从 0 开始）可以：
     *
     * 从左岸（新仓库）跨过桥到右岸（旧仓库），用时 leftToRighti 分钟。
     * 从旧仓库选择一个箱子，并返回到桥边，用时 pickOldi 分钟。不同工人可以同时搬起所选的箱子。
     * 从右岸（旧仓库）跨过桥到左岸（新仓库），用时 rightToLefti 分钟。
     * 将箱子放入新仓库，并返回到桥边，用时 putNewi 分钟。不同工人可以同时放下所选的箱子。
     * 如果满足下面任一条件，则认为工人 i 的 效率低于 工人 j ：
     *
     * leftToRighti + rightToLefti > leftToRightj + rightToLeftj
     * leftToRighti + rightToLefti == leftToRightj + rightToLeftj 且 i > j
     * 工人通过桥时需要遵循以下规则：
     *
     * 如果工人 x 到达桥边时，工人 y 正在过桥，那么工人 x 需要在桥边等待。
     * 如果没有正在过桥的工人，那么在桥右边等待的工人可以先过桥。如果同时有多个工人在右边等待，那么 效率最低 的工人会先过桥。
     * 如果没有正在过桥的工人，且桥右边也没有在等待的工人，同时旧仓库还剩下至少一个箱子需要搬运，此时在桥左边的工人可以过桥。如果同时有多个工人在左边等待，那么 效率最低 的工人会先过桥。
     * 所有 n 个盒子都需要放入新仓库，请你返回最后一个搬运箱子的工人 到达河左岸 的时间。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/time-to-cross-a-bridge
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 1. 过桥排他
     * 2. 如果同时左右侧都有人等待，右侧先过桥
     * 3. 如果没有右侧的人等待，左侧才过桥
     * 
     * 1. 过桥
     * 2. 右侧仓库拿货
     * 3. 左侧仓库放货
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 205 ms
     * , 在所有 Java 提交中击败了
     * 11.37%
     * 的用户
     * 内存消耗：
     * 48.9 MB
     * , 在所有 Java 提交中击败了
     * 37.21%
     * 的用户
     * 通过测试用例：
     * 65 / 65
     */
    public int findCrossingTime(int n, int k, int[][] time) {
        PriorityQueue<Worker> leftWaiting = new PriorityQueue<>(k, new WorkerComparator());
        PriorityQueue<Worker> rightWaiting = new PriorityQueue<>(new WorkerComparator());
        List<Worker> leftWorking = new LinkedList<>();
        List<Worker> rightWorking = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            leftWaiting.add(new Worker(time[i][0], time[i][1], time[i][2], time[i][3], i));
        }
        int ans = 0;
        while (n > 0) {
            if (!rightWaiting.isEmpty()) {
                Worker worker = rightWaiting.poll();
                int cost = worker.rightToLeft;
                // 经过 cost 时间，处理左右侧 working 队列
                handleLeftWorking(leftWaiting, leftWorking, cost);
                handleRightWorking(rightWaiting, rightWorking, cost);
                leftWorking.add(worker);
                n--;
//                System.out.println("工人 " + worker.idx + " 返回时间: " + ans + "~" + (ans + cost));
                ans += cost;
                continue;
            }
            if (!leftWaiting.isEmpty() && rightWorking.size() < n) {
                Worker worker = leftWaiting.poll();
                int cost = worker.leftToRight;
                handleLeftWorking(leftWaiting, leftWorking, cost);
                handleRightWorking(rightWaiting, rightWorking, cost);
                rightWorking.add(worker);
//                System.out.println("工人 " + worker.idx + " 前往时间: " + ans + "~" + (ans + cost));
                ans += cost;
                continue;
            }
            // 从左右 working 找到一个最快完成工作的工人，进入等待队列
            int cost = getFastCostTime(leftWorking, rightWorking);
            handleLeftWorking(leftWaiting, leftWorking, cost);
            handleRightWorking(rightWaiting, rightWorking, cost);
//            System.out.println("工作时间: " + ans + "~" + (ans + cost));
            ans += cost;
        }
        return ans;
    }

    private int getFastCostTime(List<Worker> leftWorking, List<Worker> rightWorking) {
        int cost = Integer.MAX_VALUE;
        for (Worker w : leftWorking) {
            if (w.putNew - w.timeUsed < cost){
                cost = w.putNew - w.timeUsed;
            }
        }
        for (Worker w : rightWorking) {
            if (w.pickOld - w.timeUsed < cost){
                cost = w.pickOld - w.timeUsed;
            }
        }
        return cost;
    }

    private void handleRightWorking(PriorityQueue<Worker> rightWaiting, List<Worker> rightWorking, int cost) {
        Iterator<Worker> it = rightWorking.iterator();
        while (it.hasNext()) {
            Worker w = it.next();
            if (w.timeUsed + cost >= w.pickOld) {
                // 判断是否完成，完成则加入左侧等待队列
                w.timeUsed = 0;
                rightWaiting.add(w);
                it.remove();
            } else {
                w.timeUsed += cost;
            }
        }
    }

    private void handleLeftWorking(PriorityQueue<Worker> leftWaiting, List<Worker> leftWorking, int cost) {
        Iterator<Worker> it = leftWorking.iterator();
        while (it.hasNext()) {
            Worker w = it.next();
            if (w.timeUsed + cost >= w.putNew) {
                // 判断是否完成，完成则加入左侧等待队列
                leftWaiting.add(w);
                w.timeUsed = 0;
                it.remove();
            } else {
                w.timeUsed += cost;
            }
        }
    }

    class Worker {
        int leftToRight;
        int pickOld;
        int rightToLeft;
        int putNew;
        int idx;

        int timeUsed = 0;

        public Worker(int leftToRight, int pickOld, int rightToLeft, int putNew, int idx) {
            this.leftToRight = leftToRight;
            this.pickOld = pickOld;
            this.rightToLeft = rightToLeft;
            this.putNew = putNew;
            this.idx = idx;
        }
    }
    
    class WorkerComparator implements Comparator<Worker>{
        @Override
        public int compare(Worker o1, Worker o2) {
            int cost1 = o1.leftToRight + o1.rightToLeft;
            int cost2 = o2.leftToRight + o2.rightToLeft;
            if (cost1 != cost2) {
                return cost2 - cost1;
            }
            return o2.idx - o1.idx;
        }
    }

    public static void main(String[] args) {
        int n = 3;
        int k = 2;
        int[][] time = {{1,9,1,8},{10,10,10,10}};
        System.out.println(new LC0108().findCrossingTime(n, k, time));
    }
}
