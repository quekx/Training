package contest.lc2023;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LC0422 {
    /**
     * 远征队即将开启未知的冒险之旅，不过在此之前，将对补给车队进行最后的检查。supplies[i] 表示编号为 i 的补给马车装载的物资数量。
     * 考虑到车队过长容易被野兽偷袭，他们决定将车队的长度变为原来的一半（向下取整），计划为：
     *
     * 找出车队中 物资之和最小 两辆 相邻 马车，将它们车辆的物资整合为一辆。若存在多组物资之和相同的马车，则取编号最小的两辆马车进行整合；
     * 重复上述操作直到车队长度符合要求。
     * 请返回车队长度符合要求后，物资的分布情况。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/hqCnmP
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 38 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 42.5 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 72 / 72
     */
    public int[] supplyWagon(int[] supplies) {
        int n = supplies.length;
        int[] p = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            p[i] = i;
        }
        int re = n / 2;
        for (int i = 0; i < n - re; i++) {
            int cur = find(0, p);
            int next = find(cur + 1, p);
            int x = cur, y = next;
            while (cur < n && next < n) {
                if (supplies[cur] + supplies[next] < supplies[x] + supplies[y]) {
                    x = cur;
                    y = next;
                }
                cur = next;
                next = find(next + 1, p);
            }
            supplies[y] += supplies[x];
            p[x] = y;
        }

        int[] ans = new int[re];
        for (int i = 0, cur = find(0, p); i < re; i++, cur = find(cur + 1, p)) {
            ans[i] = supplies[cur];
        }
        return ans;
    }

    private int find(int x, int[] p) {
        if (p[x] != x) {
            p[x] = find(p[x], p);
        }
        return p[x];
    }

    /**
     * 探险家小扣的行动轨迹，都将保存在记录仪中。expeditions[i] 表示小扣第 i 次探险记录，用一个字符串数组表示。其中的每个「营地」由大小写字母组成，通过子串 -> 连接。
     *
     * 例："Leet->code->Campsite"，表示到访了 "Leet"、"code"、"Campsite" 三个营地。
     *
     * expeditions[0] 包含了初始小扣已知的所有营地；对于之后的第 i 次探险(即 expeditions[i] 且 i > 0)，如果记录中包含了之前均没出现的营地，则表示小扣 新发现 的营地。
     *
     * 请你找出小扣发现新营地最多且索引最小的那次探险，并返回对应的记录索引。如果所有探险记录都没有发现新的营地，返回 -1
     *
     * 注意：
     *
     * 大小写不同的营地视为不同的营地；
     * 营地的名称长度均大于 0。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/0Zeoeg
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int adventureCamp(String[] expeditions) {
        Set<String> set = new HashSet<>();
        String init = expeditions[0];
        if (!init.isEmpty()) {
            String[] list = expeditions[0].split("->");
            set.addAll(Arrays.asList(list));
        }
        int ans = -1;
        int count = 0;
        for (int i = 1; i < expeditions.length; i++) {
            String cur = expeditions[i];
            if (cur.isEmpty()) {
                continue;
            }
            int find = 0;
            String[] list = cur.split("->");
            for (String e : list) {
                if (!set.contains(e)) {
                    find++;
                    set.add(e);
                }
            }
            if (find > count) {
                ans = i;
                count = find;
            }
        }
        return ans;
    }

    public int fieldOfGreatestBlessing(int[][] forceField) {

        return 0;
    }

    public static void main(String[] args) {
        String[] e = {"Alice->Dex","","Dex"};
        System.out.println(new LC0422().adventureCamp(e));
    }
}
