package contest;

import com.qkx.example.utils.ArrayUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LC0811 {
    public int countGoodNodes(int[][] edges) {
        int n = edges.length + 1;
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(0, -1, graph);
        return ans;
    }

    int ans = 0;

    private int dfs(Integer cur, Integer parent, ArrayList<Integer>[] graph) {
        ArrayList<Integer> children = graph[cur];
        children.remove(parent);
        if (children.isEmpty()) {
            ans++;
            return 1;
        }

        boolean isSame = true;
        int cn0 = dfs(children.get(0), cur, graph);
        int cnt = cn0;
        for (int i = 1; i < children.size(); i++) {
            int cni = dfs(children.get(i), cur, graph);
            cnt += cni;
            if (cni != cn0) {
                isSame = false;
            }
        }
        if (isSame) {
            ans++;
        }
        return cnt + 1;
    }


    /**
     * 0 <= k <= 50
     * dp[i][k] 表示长度为 i + 1 ，arr1 以 k 结尾时，数组对个数
     * dp[i + 1][k] = dp[i][x] (x <= k && num[i - 1] - x >= num[i] - k) 的求和
     */
    public int countOfPairs(int[] nums) {
        int mod = 1000_000_000 + 7;
        int n = nums.length;
        int m = 51;
        long[][] dp = new long[n][m];
        for (int x = 0; x <= nums[0]; x++) {
            dp[0][x] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int k = 0; k <= nums[i]; k++) {
                long cnt = 0;
                for (int x = 0; x <= k; x++) {
                    if (nums[i - 1] - x >= nums[i] - k) {
                        cnt += dp[i - 1][k];
                    }
                }
                dp[i][k] = cnt % mod;
            }
        }
        int ans = 0;
        for (int k = 0; k < m; k++) {
            ans += dp[n - 1][k];
        }
        ArrayUtil.print(dp);
        return ans % mod;
    }


    /**
     * l = 1  -  2
     * [0] [1] [2]
     * l = 2  -  3
     * [0,0] [0,1]
     */
    public static void main(String[] args) {
//        int[][] edges = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6}};
//        System.out.println(new LC0811().countGoodNodes(edges));

        int[] nums = {2,3,2};
        System.out.println(new LC0811().countOfPairs(nums));
    }
}
