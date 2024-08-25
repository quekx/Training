package contest;

import java.util.ArrayList;

public class LC0519 {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int len = nums.length;
        ArrayList<Integer> posList = new ArrayList<>(len);
        for (int i = 1; i < len; i++) {
            if (!isValid(nums[i - 1], nums[i])) {
                posList.add(i);
            }
        }

        return null;
    }

    private boolean isValid(int a, int b) {
        return ((a ^ b) & 1) == 0;
    }

//    private boolean match(ArrayList<Integer> posList, int start, int end) {
//
//    }
//
//    private int findLeft(ArrayList<Integer> posList, int target) {
//        // 找第一个比target小的数 或者等于
//        int l = 0, r = posList.size() - 1;
//        while (l < r) {
//            int mid = l + ((r - l) >> 1);
//            if (posList.get(mid) == target) {
//                return mid;
//            } else if (posList.get(mid) < target) {
//                l = mid + 1;
//            } else {
//                r = mid - 1;
//            }
//        }
//    }
}
