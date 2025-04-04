package contest.lc2024;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LC0430 {
    public int isWinner(int[] player1, int[] player2) {
        int score1 = 0, score2 = 0;
        for (int i = 0; i < player1.length; i++) {
            int sign1 = 1, sign2 = 1;
            if (i - 2 >= 0) {
                if (player1[i - 2] == 10 || player1[i - 1] == 10) {
                    sign1 = 2;
                }
                if (player2[i - 2] == 10 || player2[i - 1] == 10) {
                    sign2 = 2;
                }
            } else if (i - 1 >= 0) {
                if (player1[i - 1] == 10) {
                    sign1 = 2;
                }
                if (player2[i - 1] == 10) {
                    sign2 = 2;
                }
            }
            score1 += sign1 * player1[i];
            score2 += sign2 * player2[i];
        }
        if (score1 == score2) {
            return 0;
        }
        return score1 > score2 ? 1 : 2;
    }

    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length, n = mat[0].length;

        return 0;
    }

    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        int startX = start[0], startY = start[1];
        int targetX = start[0], targetY = start[1];
        Map<Long, Integer> map = new HashMap<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>(specialRoads.length, (a, b) -> a[4] - b[4]);
        queue.addAll(Arrays.asList(specialRoads));
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

        }
        return 0;
    }
}
