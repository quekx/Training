package contest.lc2022;

import java.io.*;
import java.util.*;

public class LC1204 {
    /**
     * 17:00
     * 句子 是由单个空格分隔的一组单词，且不含前导或尾随空格。
     * <p>
     * 例如，"Hello World"、"HELLO"、"hello world hello world" 都是符合要求的句子。
     * 单词 仅 由大写和小写英文字母组成。且大写和小写字母会视作不同字符。
     * <p>
     * 如果句子满足下述全部条件，则认为它是一个 回环句 ：
     * <p>
     * 单词的最后一个字符和下一个单词的第一个字符相等。
     * 最后一个单词的最后一个字符和第一个单词的第一个字符相等。
     * 例如，"leetcode exercises sound delightful"、"eetcode"、"leetcode eats soul" 都是回环句。然而，"Leetcode is cool"、"happy Leetcode"、"Leetcode" 和 "I like Leetcode" 都 不 是回环句。
     * <p>
     * 给你一个字符串 sentence ，请你判断它是不是一个回环句。如果是，返回 true ；否则，返回 false 。
     * <p>
     *  
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/circular-sentence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
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
     * 40.1 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 260 / 260
     */
    public boolean isCircularSentence(String sentence) {
        int len = sentence.length();
        for (int i = 0; i < len; i++) {
            char cur = sentence.charAt(i);
            if (cur == ' ') {
                if (i <= 0 || i >= len - 1) {
                    return false;
                }
                if (sentence.charAt(i - 1) != sentence.charAt(i + 1)) {
                    return false;
                }
            }
        }
        return sentence.charAt(0) == sentence.charAt(len - 1);
    }

    /**
     * 给你一个正整数数组 skill ，数组长度为 偶数 n ，其中 skill[i] 表示第 i 个玩家的技能点。将所有玩家分成 n / 2 个 2 人团队，使每一个团队的技能点之和 相等 。
     * <p>
     * 团队的 化学反应 等于团队中玩家的技能点 乘积 。
     * <p>
     * 返回所有团队的 化学反应 之和，如果无法使每个团队的技能点之和相等，则返回 -1 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/divide-players-into-teams-of-equal-skill
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 8 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 49.2 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 87 / 87
     */
    public long dividePlayers(int[] skill) {
        int n = skill.length;
        int gn = n / 2;
        long sum = 0;
        for (int sk : skill) {
            sum += sk;
        }
        if (sum % gn != 0) {
            return -1L;
        }

        Arrays.sort(skill);
        long gs = sum / gn;
        long ans = 0L;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            if (skill[l] + skill[r] != gs) {
                return -1L;
            }
            ans += (long) skill[l] * skill[r];
            l++;
            r--;
        }
        return ans;
    }

    /**
     * 给你一个正整数 n ，表示总共有 n 个城市，城市从 1 到 n 编号。给你一个二维数组 roads ，其中 roads[i] = [ai, bi, distancei] 表示城市 ai 和 bi 之间有一条 双向 道路，道路距离为 distancei 。城市构成的图不一定是连通的。
     * <p>
     * 两个城市之间一条路径的 分数 定义为这条路径中道路的 最小 距离。
     * <p>
     * 城市 1 和城市 n 之间的所有路径的 最小 分数。
     * <p>
     * 注意：
     * <p>
     * 一条路径指的是两个城市之间的道路序列。
     * 一条路径可以 多次 包含同一条道路，你也可以沿着路径多次到达城市 1 和城市 n 。
     * 测试数据保证城市 1 和城市n 之间 至少 有一条路径。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/minimum-score-of-a-path-between-two-cities
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 17:40 ~ 17:55 lashi
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 79 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 87.4 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 36 / 36
     * 炫耀一下:
     */
    public int minScore2(int n, int[][] roads) {
        Map<Integer, Integer>[] g = new HashMap[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = new HashMap<>();
        }
        for (int[] edge : roads) {
            g[edge[0]].put(edge[1], edge[2]);
            g[edge[1]].put(edge[0], edge[2]);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        boolean[] visit = new boolean[n + 1];
        visit[1] = true;
        int ans = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                Map<Integer, Integer> neighbor = g[cur];
                for (Map.Entry<Integer, Integer> entry : neighbor.entrySet()) {
                    ans = Math.min(ans, entry.getValue());
                    if (!visit[entry.getKey()]) {
                        queue.add(entry.getKey());
                        visit[entry.getKey()] = true;
                    }
                }
            }
        }
        return ans;
    }

    int[] p;

    private int find(int x) {
        int t = x;
        while (p[t] != t) {
            t = p[t];
        }
        p[x] = t;
        return p[x];
    }

    private int find(int[] p, int x) {
        if (p[x] == x) {
            return x;
        }
        int t = find(p, p[x]);
        // 这里直接连接到顶点，防止多次的路径查询
        p[x] = t;
        return t;
    }

    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 11 ms
     * , 在所有 Java 提交中击败了
     * 85.50%
     * 的用户
     * 内存消耗：
     * 106 MB
     * , 在所有 Java 提交中击败了
     * 48.07%
     * 的用户
     * 通过测试用例：
     * 41 / 41
     */
    public int minScore(int n, int[][] roads) {
        p = new int[n + 1];
        int[] w = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            p[i] = i;
            w[i] = Integer.MAX_VALUE;
        }

        for (int[] road : roads) {
            int a = find(road[0]);
            int b = find(road[1]);
            p[b] = a;
            w[a] = Math.min(Math.min(w[a], w[b]), road[2]);
        }
        return w[find(1)];
    }

    /**
     * 给你一个正整数 n ，表示一个 无向 图中的节点数目，节点编号从 1 到 n 。
     * <p>
     * 同时给你一个二维整数数组 edges ，其中 edges[i] = [ai, bi] 表示节点 ai 和 bi 之间有一条 双向 边。注意给定的图可能是不连通的。
     * <p>
     * 请你将图划分为 m 个组（编号从 1 开始），满足以下要求：
     * <p>
     * 图中每个节点都只属于一个组。
     * 图中每条边连接的两个点 [ai, bi] ，如果 ai 属于编号为 x 的组，bi 属于编号为 y 的组，那么 |y - x| = 1 。
     * 请你返回最多可以将节点分为多少个组（也就是最大的 m ）。如果没办法在给定条件下分组，请你返回 -1 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/divide-nodes-into-the-maximum-number-of-groups
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 726 ms
     * , 在所有 Java 提交中击败了
     * 24.12%
     * 的用户
     * 内存消耗：
     * 48.1 MB
     * , 在所有 Java 提交中击败了
     * 17.12%
     * 的用户
     * 通过测试用例：
     * 55 / 55
     */

    int[] p2;

    private int find2(int x) {
        while (p2[x] != x) {
            x = p2[x];
        }
        return p2[x];
    }

    /**
     * 1. DFS 染色，判断联通分量是否存在奇数环
     * 2. 并查集切割联通分量
     * 3. BFS 遍历每个顶点，记录每个联通分量的最大层数
     * 4. 每个分量的层数相加
     */
    public int magnificentSets(int n, int[][] edges) {
        List<Integer>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }
        int[] colors = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (colors[i] == 0 && !dfs(i, 1, colors, g)) {
                return -1;
            }
        }

        p2 = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            p2[i] = i;
        }
        for (int[] edge : edges) {
            int a = find2(edge[0]);
            int b = find2(edge[1]);
            p2[b] = a;
        }

        Map<Integer, Integer> maxLayer = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> tmp = new LinkedList<>();
        int[] visit = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int layer = bfs(i, g, queue, tmp, visit);
            int blk = find2(i);
            layer = Math.max(layer, maxLayer.getOrDefault(blk, 0));
            maxLayer.put(blk, layer);
        }

        int ans = 0;
        for (int layer : maxLayer.values()) {
            ans += layer;
        }
        return ans;
    }

    private int bfs(int start, List<Integer>[] g, Queue<Integer> queue, Queue<Integer> tmp, int[] visit) {
        int layer = 0;
        queue.add(start);
        visit[start] = start;
        while (!queue.isEmpty()) {
            layer++;
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int next : g[cur]) {
                    if (visit[next] != start) {
                        tmp.add(next);
                        visit[next] = start;
                    }
                }
            }
            Queue<Integer> x = tmp;
            tmp = queue;
            queue = x;
        }
        return layer;
    }

    private boolean dfs(int cur, int curColor, int[] colors, List<Integer>[] g) {
        if (colors[cur] != 0) {
            return curColor == colors[cur];
        }
        colors[cur] = curColor;
        for (int next : g[cur]) {
            if (!dfs(next, -curColor, colors, g)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        int n = 170;
        BufferedReader reader = new BufferedReader(new FileReader("xx"));
        List<int[]> data = new LinkedList<>();
        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] d = line.split(",");
            data.add(new int[]{Integer.parseInt(d[0]), Integer.parseInt(d[1])});
        }
        int[][] edges = new int[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            edges[i] = data.get(i);
        }
//        ArrayUtil.print(edges);
        System.out.println(new LC1204().magnificentSets(n, edges));
    }
}
