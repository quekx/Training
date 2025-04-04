package contest.lc2025;

import java.util.*;

public class LC0309 {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int ans = fruits.length;
        for (int i = 0; i < fruits.length; i++) {
            for (int j = 0; j < baskets.length; j++) {
                if (fruits[i] <= baskets[j]) {
                    baskets[j] = -1;
                    ans--;
                    break;
                }
            }
        }
        return ans;
    }

    public long[] findMaxSum(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        // 位置排序
        Integer[] p = new Integer[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        Arrays.sort(p, Comparator.comparingInt(a -> nums1[a]));

        long[] ans = new long[n];
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        List<Integer> tmp = new ArrayList<>();
        tmp.add(nums2[p[0]]);
        long curSum = 0;
        // 小于当前位置值的最大和
        for (int i = 1; i < n; i++) {
            if (nums1[p[i - 1]] == nums1[p[i]]) {
                ans[p[i]] = ans[p[i - 1]];
                tmp.add(nums2[p[i]]);
                continue;
            }
            for (Integer lastValue : tmp) {
                if (queue.isEmpty() || queue.size() < k) {
                    queue.add(lastValue);
                    curSum += lastValue;
                } else if (queue.peek() < lastValue) {
                    curSum -= queue.poll();
                    queue.add(lastValue);
                    curSum += lastValue;
                }
            }
            ans[p[i]] = curSum;
            tmp.clear();
            tmp.add(nums2[p[i]]);
        }
        return ans;
    }
}
