package exercise;

public class Exercise1208 {
    // https://leetcode.cn/problems/sort-array-by-moving-items-to-empty-space/
    // https://www.bilibili.com/video/BV1aV4y1P7Ss/?spm_id_from=333.999.top_right_bar_window_history.content.click

    /**
     * 位置成环计数
     */
    public int getMinMoveTimes(int[] nums) {
        int n = nums.length;
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[nums[i]] = i;
        }

        // 顺序 + 倒序
        int[] isVisit = new int[n];
        int a = getMoveTimeInOrder(p, isVisit);
        int b = getMoveTimeInReverseOrder(p, isVisit);
        System.out.println("a >> " + a + ", b >> " + b);
        return Math.min(a, b);
    }

    private int getMoveTimeInOrder(int[] p, int[] isVisit) {
        int ans = visitInOrder(p, 0, isVisit, 1) - 1;
        for (int i = 1; i < p.length; i++) {
            int visitNum = visitInOrder(p, i, isVisit, 1);
            ans += visitNum == 0 || visitNum == 1 ? 0 : visitNum + 1;
        }
        return ans;
    }

    private int getMoveTimeInReverseOrder(int[] p, int[] isVisit) {
        int ans = visitInReverseOrder(p, 0, isVisit, -1) - 1;
        for (int i = 1; i < p.length; i++) {
            int visitNum = visitInReverseOrder(p, i, isVisit, -1);
            ans += visitNum == 0 || visitNum == 1 ? 0 : visitNum + 1;
        }
        return ans;
    }

    private int visitInOrder(int[] p, int i, int[] isVisit, int tag) {
        if (isVisit[i] == tag) {
            return 0;
        }
        isVisit[i] = tag;
        return visitInOrder(p, p[i], isVisit, tag) + 1;
    }

    private int visitInReverseOrder(int[] p, int i, int[] isVisit, int tag) {
        if (isVisit[i] == tag) {
            return 0;
        }
        isVisit[i] = tag;
        return visitInReverseOrder(p, p.length - 1 - p[i], isVisit, tag) + 1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 0};
        System.out.println(new Exercise1208().getMinMoveTimes(nums));
    }
}
