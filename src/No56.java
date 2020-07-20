//Given a collection of intervals, merge all overlapping intervals.
//
// Example 1:
//
//
//Input: [[1,3],[2,6],[8,10],[15,18]]
//Output: [[1,6],[8,10],[15,18]]
//Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
//
//
// Example 2:
//
//
//Input: [[1,4],[4,5]]
//Output: [[1,5]]
//Explanation: Intervals [1,4] and [4,5] are considered overlapping.
//
// NOTE: input types have been changed on April 15, 2019. Please reset to defaul
//t code definition to get new method signature.
// Related Topics Array Sort
// 👍 4325 👎 295


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class No56 {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }

        List<int[]> res = new ArrayList<>();
        List<int[]> inputs = Arrays.asList(intervals);
        Collections.sort(inputs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int start = inputs.get(0)[0];
        int end = inputs.get(0)[1];
        for (int[] input : inputs) {
            if (end >= input[0]) {
                end = Math.max(end, input[1]);
            } else {
                res.add(new int[]{start, end});
                start = input[0];
                end = input[1];
            }
        }
        res.add(new int[]{start, end});

        int[][] result = new int[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
