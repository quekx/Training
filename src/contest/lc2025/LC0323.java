package contest.lc2025;

import com.qkx.example.utils.ArrayUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LC0323 {
    public int numberOfComponents(int[][] properties, int k) {
        int n = properties.length;
        HashSet<Integer>[] sets = new HashSet[n];
        for (int i = 0; i < n; i++) {
            sets[i] = new HashSet<>();
            for (int j = 0; j < properties[i].length; j++) {
                sets[i].add(properties[i][j]);
            }
        }
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                HashSet<Integer> temp = new HashSet<>(sets[i]);
                temp.retainAll(sets[j]);
                if (temp.size() >= k) {
                    int pi = find(p, i);
                    int pj = find(p, j);
                    p[pj] = pi;
                }
            }
        }
        HashSet<Integer> ans = new HashSet<>();
        for (int i = 0; i < n; i++) {
            ans.add(find(p, i));
        }
        return ans.size();
    }

    private int find(int[] p, int x) {
        if (p[x] != x) {
            p[x] = find(p, p[x]);
        }
        return p[x];
    }

    public long minTime(int[] skill, int[] mana) {
        long[] lastTime = new long[skill.length];
        long[] curTime = new long[skill.length];
        for (int i = 0; i < mana.length; i++) {
            long delta = 0;
            for (int j = 0; j < skill.length; j++) {
                int cost = mana[i] * skill[j];
                if (j == 0) {
                    curTime[j] = lastTime[0] + cost;
                } else {
                    curTime[j] = curTime[j - 1] + cost;
                }
                if (j <= skill.length - 2) {
                    delta = Math.max(delta, lastTime[j + 1] - curTime[j]);
                }
            }
            for (int j = 0; j < skill.length; j++) {
                lastTime[j] = curTime[j] + delta;
            }
            ArrayUtil.print(lastTime);
        }
        return lastTime[skill.length - 1];
    }


    /**
     * [3,5,3,9]
     * [1,10,7,3]
     * @param args
     */
    public static void main(String[] args) {
        int[] skill = {3,5,3,9}, mana = {1,10,7,3};
        new LC0323().minTime(skill, mana);
    }
}
