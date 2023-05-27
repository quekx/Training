package contest;

import com.qkx.example.utils.ArrayUtil;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC0521 {
    public int minLength(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'B' && !deque.isEmpty() && deque.peek() == 'A') {
                deque.pop();
            } else if (ch == 'D' && !deque.isEmpty() && deque.peek() == 'C') {
                deque.pop();
            } else {
                deque.push(ch);
            }
        }
        return deque.size();
    }

    public String makeSmallestPalindrome(String s) {
        char[] arr = s.toCharArray();
        int i = 0, j = arr.length - 1;
        while (i < j) {
            if (arr[i] > arr[j]) {
                arr[i] = arr[j];
            } else if (arr[i] < arr[j]) {
                arr[j] = arr[i];
            }
            i++;
            j--;
        }
        return new String(arr);
    }

    public int punishmentNumber(int n) {
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            if (dfs(i, i * i, 1)) {
                ans += i * i;
            }
        }
        return ans;
    }

    private boolean dfs(int sum, int str, int sign) {
        if (sum == 0) {
            return str == 0;
        }
        if (str == 0) {
            return false;
        }

        int cur = str % 10;
        int next = str / 10;
        sum -= cur * sign;
        if (sum < 0) {
            return false;
        }
        return dfs(sum, next, sign * 10) || dfs(sum, next, 1);
    }

    /**
     * 799 / 872 个通过测试用例
     * 状态：超出时间限制
     * 提交时间：几秒前
     */
    public int[][] modifiedGraphEdges2(int n, int[][] edges, int source, int destination, int target) {
        long[][] dest = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                dest[i][j] = Integer.MAX_VALUE;
                dest[j][i] = Integer.MAX_VALUE;
            }
        }
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1], w = edge[2];
            if (w > 0) {
                dest[a][b] = Math.min(dest[a][b], w);
                dest[b][a] = Math.min(dest[b][a], w);
                for (int s = 0; s < n; s++) {
                    for (int e = 0; e < n; e++) {
                        dest[s][e] = Math.min(dest[s][e], dest[s][a] + dest[a][b] + dest[b][e]);
                        dest[s][e] = Math.min(dest[s][e], dest[s][b] + dest[b][a] + dest[a][e]);
                    }
                }
            }
        }
        if (dest[source][destination] < target) {
            return new int[][]{};
        }

        for (int[] edge : edges) {
            int a = edge[0], b = edge[1], w = edge[2];
            if (w < 0) {
                edge[2] = 1;
                dest[a][b] = Math.min(dest[a][b], 1);
                dest[b][a] = Math.min(dest[b][a], 1);
                for (int s = 0; s < n; s++) {
                    for (int e = 0; e < n; e++) {
                        if (dest[s][a] + dest[a][b] + dest[b][e] <= dest[s][e]) {
                            dest[s][e] = dest[s][a] + dest[a][b] + dest[b][e];
                        }
                        if (dest[s][b] + dest[b][a] + dest[a][e] <= dest[s][e]) {
                            dest[s][e] = dest[s][b] + dest[b][a] + dest[a][e];
                        }
                    }
                }
            }
            if (dest[source][destination] <= target) {
                edge[2] += target - dest[source][destination];
                dest[source][destination] = target;
                dest[destination][source] = target;
            }
        }
//        ArrayUtil.print(dest);
        if (dest[source][destination] == target) {
            return edges;
        } else {
            return new int[][]{};
        }
    }

    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        int[] dest = new int[n];
        for (int[] edge : edges) {

        }

        return null;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] e = {{0, 1, -1}, {1, 2, -1}, {3, 1, -1}, {3, 0, 2}, {0, 2, 5}};
        int source = 2;
        int dest = 3;
        int target = 8;
        ArrayUtil.print(new LC0521().modifiedGraphEdges2(n, e, source, dest, target));
        ;
    }
}
