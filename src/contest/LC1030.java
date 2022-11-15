package contest;

import com.qkx.example.model.TreeNode;

import java.util.*;

public class LC1030 {

    /**
     * 1:20 开始
     * 1:22 结束
     * <p>
     * 给你一个由正整数组成的整数数组 nums ，返回其中可被 3 整除的所有偶数的平均值。
     * <p>
     * 注意：n 个元素的平均值等于 n 个元素 求和 再除以 n ，结果 向下取整 到最接近的整数。
     */
    public int averageValue(int[] nums) {
        int sum = 0;
        int count = 0;
        for (int num : nums) {
            if (num % 3 == 0) {
                sum += num;
                count++;
            }
        }
        return count == 0 ? 0 : sum / count;
    }

    /**
     * 1:22 开始
     * 1:57 结束
     * <p>
     * 给你两个字符串数组 creators 和 ids ，和一个整数数组 views ，所有数组的长度都是 n 。平台上第 i 个视频者是 creator[i] ，视频分配的 id 是 ids[i] ，且播放量为 views[i] 。
     * <p>
     * 视频创作者的 流行度 是该创作者的 所有 视频的播放量的 总和 。请找出流行度 最高 创作者以及该创作者播放量 最大 的视频的 id 。
     * <p>
     * 如果存在多个创作者流行度都最高，则需要找出所有符合条件的创作者。
     * 如果某个创作者存在多个播放量最高的视频，则只需要找出字典序最小的 id 。
     * 返回一个二维字符串数组 answer ，其中 answer[i] = [creatori, idi] 表示 creatori 的流行度 最高 且其最流行的视频 id 是 idi ，可以按任何顺序返回该结果。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/most-popular-video-creator
     * <p>
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 29 ms
     * , 在所有 Java 提交中击败了
     * 98.63%
     * 的用户
     * 内存消耗：
     * 88.7 MB
     * , 在所有 Java 提交中击败了
     * 81.22%
     * 的用户
     * 通过测试用例：
     */
    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        Map<String, CreatorInfo> infoMap = new HashMap<>();
        for (int i = 0; i < creators.length; i++) {
            CreatorInfo creatorInfo = infoMap.get(creators[i]);
            if (creatorInfo == null) {
                creatorInfo = new CreatorInfo(creators[i], views[i], ids[i], views[i]);
                infoMap.put(creators[i], creatorInfo);
            } else {
                creatorInfo.totalView += views[i];
                if (views[i] > creatorInfo.mostPopularView
                        || (views[i] == creatorInfo.mostPopularView && ids[i].compareTo(creatorInfo.mostPopularId) < 0)) {
                    creatorInfo.mostPopularId = ids[i];
                    creatorInfo.mostPopularView = views[i];
                }
            }
        }
        List<List<String>> ans = new LinkedList<>();
        long maxView = 0;
        for (Map.Entry<String, CreatorInfo> item : infoMap.entrySet()) {
            CreatorInfo info = item.getValue();
            if (info.totalView > maxView) {
                ans.clear();
                ans.add(Arrays.asList(info.creator, info.mostPopularId));
                maxView = info.totalView;
            } else if (info.totalView == maxView) {
                ans.add(Arrays.asList(info.creator, info.mostPopularId));
            }
        }
        return ans;
    }

    class CreatorInfo {
        String creator;
        long totalView;
        String mostPopularId;
        int mostPopularView;

        public CreatorInfo(String creator, int totalView, String mostPopularId, int mostPopularView) {
            this.creator = creator;
            this.totalView = totalView;
            this.mostPopularId = mostPopularId;
            this.mostPopularView = mostPopularView;
        }
    }

    /**
     * 1:57 开始
     * 2:32 结束
     * <p>
     * 给你两个正整数 n 和 target 。
     * <p>
     * 如果某个整数每一位上的数字相加小于或等于 target ，则认为这个整数是一个 美丽整数 。
     * <p>
     * 找出并返回满足 n + x 是 美丽整数 的最小非负整数 x 。生成的输入保证总可以使 n 变成一个美丽整数。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/minimum-addition-to-make-integer-beautiful
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
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
     * 38.7 MB
     * , 在所有 Java 提交中击败了
     * 11.40%
     * 的用户
     * 通过测试用例：
     * 92 / 92
     */
    public long makeIntegerBeautiful(long n, int target) {
        int sum = 0;
        long k = n;
        while (k > 0) {
            sum += k % 10;
            k /= 10;
        }

        long ans = 0L;
        int delta = sum - target;
        int c = 0;
        long t = 1;
        while (delta + c > 0) {
            // 最低位补到10
            long lowest = n % 10;
            if (lowest + c == 10) {
                // 9 + 1 直接进位
                delta -= lowest - 1;
            } else {
                //
                ans += (10 - lowest - c) * t;
                delta -= lowest;
            }
            c = 1;
            n /= 10;
            t *= 10;
        }
        return ans;
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    /**
     * 给你一棵 二叉树 的根节点 root ，树中有 n 个节点。每个节点都可以被分配一个从 1 到 n 且互不相同的值。另给你一个长度为 m 的数组 queries 。
     * <p>
     * 你必须在树上执行 m 个 独立 的查询，其中第 i 个查询你需要执行以下操作：
     * <p>
     * 从树中 移除 以 queries[i] 的值作为根节点的子树。题目所用测试用例保证 queries[i] 不 等于根节点的值。
     * 返回一个长度为 m 的数组 answer ，其中 answer[i] 是执行第 i 个查询后树的高度。
     * <p>
     * 注意：
     * <p>
     * 查询之间是独立的，所以在每个查询执行后，树会回到其 初始 状态。
     * 树的高度是从根到树中某个节点的 最长简单路径中的边数 。
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/height-of-binary-tree-after-subtree-removal-queries
     *
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 50 ms
     * , 在所有 Java 提交中击败了
     * 78.07%
     * 的用户
     * 内存消耗：
     * 74 MB
     * , 在所有 Java 提交中击败了
     * 87.72%
     * 的用户
     * 通过测试用例：
     * 40 / 40
     */
    Map<Integer, Integer> queryMap = new HashMap<>();
    Map<Integer, Integer> heightMap = new HashMap<>();
    public int[] treeQueries(TreeNode root, int[] queries) {
        traverseAndMarkBrotherHeight(root);
        traverseQuery(root, 0, 0);
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            // 高度计算维度为路径的话，需要-1
            ans[i] = queryMap.get(queries[i]) - 1;
        }
        return ans;
    }

    /**
     * 遍历每个节点
     * 计算每个节点的子树高度
     */
    private int traverseAndMarkBrotherHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int lh = traverseAndMarkBrotherHeight(node.left);
        int rh = traverseAndMarkBrotherHeight(node.right);
        int h = Math.max(lh, rh) + 1;
        heightMap.put(node.val, h);
        return h;
    }

    /**
     * 遍历每个节点
     *
     */
    private void traverseQuery(TreeNode node, int level, int otherMaxHeight) {
        if (node == null) {
            return;
        }
        // otherMaxHeight为不包含当前节点的所有子树中，树高的最大值
        // 其实就是上一层递归时，砍掉节点的剩余子树的高
        // 以左子树为例：砍掉左子节点剩余子树的高 = 砍掉父节点剩余子树的高 + 父节点和右子树组成的子树高 决定
        if (node.left != null) {
            // 砍掉左子树
            int rh = node.right != null ? heightMap.get(node.right.val) + 1 : 1;
            int h = Math.max(rh + level, otherMaxHeight);
            queryMap.put(node.left.val, h);
            traverseQuery(node.left, level + 1, h);
        }
        if (node.right != null) {
            // 砍掉右子树
            int lh = node.left != null ? heightMap.get(node.left.val) + 1 : 1;
            int h = Math.max(lh + level, otherMaxHeight);
            queryMap.put(node.right.val, h);
            traverseQuery(node.right, level + 1, h);
        }
    }

    /**
     * ["alice","bob","alice","chris"]
     * ["one","two","three","four"]
     * [5,10,5,4]
     *
     * @param args
     */
    public static void main(String[] args) {
//        String[] creators = {};
//        String[] ids = {"one","two","three","four"};
//        int[] views = {5,10,5,4};
//        System.out.println(new LC1030().mostPopularCreator(creators, ids, views));

//        System.out.println(new LC1030().makeIntegerBeautiful(615024862295L, 7));

        // [5,8,9,2,1,3,7,4,6];
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(8);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(6);
        int[] queries = {3, 2, 4, 8};
        System.out.println(Arrays.toString(new LC1030().treeQueries(root, queries)));

    }
}
