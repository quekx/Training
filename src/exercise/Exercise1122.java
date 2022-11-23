package exercise;

import java.util.HashMap;
import java.util.Map;

public class Exercise1122 {
    /**
     * https://www.bilibili.com/video/BV1jD4y1s7oo/?spm_id_from=333.999.0.0&vd_source=377067b9ddfef24cbeb6f8862e6c1288
     */
    public int getLongestSumArr(int[] arr, int k) {
        if (k <= 0 || arr.length == 0) {
            return 0;
        }

        int n = arr.length;
        int l = 0;
        int r = 0;
        Map<Integer, Integer> counter = new HashMap<>();
        int ans = 0;
        counter.put(arr[0], 1);
        do {
            int max = 0;
            for (Integer num : counter.values()) {
                max = Math.max(max, num);
            }
            int rm = r - l + 1 - max;
            if (rm <= k) {
                ans = Math.max(ans, max);
                r++;
                if (r < n) {
                    counter.put(arr[r], counter.getOrDefault(arr[r], 0) + 1);
                } else {
                    break;
                }
            } else {
                counter.put(arr[l], counter.get(arr[l]) - 1);
                l++;
            }
        } while (l < arr.length);
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {3, -2, 3, 3, 5, 6, 3, 3, -2};
        int k = 3;
        System.out.println(new Exercise1122().getLongestSumArr(arr, k));
    }

}
