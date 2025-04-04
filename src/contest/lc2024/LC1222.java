package contest.lc2024;

import com.qkx.example.utils.ArrayUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LC1222 {
    public int minimumOperations(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int i = nums.length - 1;
        while (i >= 0 && !set.contains(nums[i])) {
            set.add(nums[i]);
            i--;
        }
        // 0 ~ i
        int remove = i + 1;
        return remove % 3 == 0 ? remove / 3 : remove / 3 + 1;
    }

    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 1;
        nums[0] -= k;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] >= nums[i] + k) {
                nums[i] = nums[i - 1];
                continue;
            }
            nums[i] = Math.max(nums[i - 1] + 1, nums[i] - k);
            ans++;
        }
        ArrayUtil.print(nums);
        return ans;
    }

    public int minLength(String s, int numOps) {
        char[] chs = s.toCharArray();
        int l = 1;
        int r = s.length();
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int ops = getNumsOps(chs, mid);
            if (ops > numOps) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    /**
     * 最长相同子串长度 maxSameNum
     * 所需要的操作数
     */
    private int getNumsOps(char[] chs, int maxSameNum) {
        int ans = Integer.MAX_VALUE;
        char pre = chs[0];
        int preNum = 1;
        int ops = 0;
        for (int i = 1; i < chs.length; i++) {
            if (pre == chs[i]) {
                if (preNum < maxSameNum) {
                    preNum++;
                } else {
                    // reverse
                    pre = chs[i] == '1' ? '0' : '1';
                    preNum = 1;
                    ops++;
                }
            } else {
                pre = chs[i];
                preNum = 1;
            }
        }
        ans = ops;

        pre = chs[0] == '1' ? '0' : '1';
        preNum = 1;
        ops = 1;
        for (int i = 1; i < chs.length; i++) {
            if (pre == chs[i]) {
                if (preNum < maxSameNum) {
                    preNum++;
                } else {
                    // reverse
                    pre = chs[i] == '1' ? '0' : '1';
                    preNum = 1;
                    ops++;
                }
            } else {
                pre = chs[i];
                preNum = 1;
            }
        }
        ans = Math.min(ans, ops);

        pre = chs[chs.length - 1];
        preNum = 1;
        ops = 0;
        for (int i = chs.length - 2; i >= 0; i--) {
            if (pre == chs[i]) {
                if (preNum < maxSameNum) {
                    preNum++;
                } else {
                    // reverse
                    pre = chs[i] == '1' ? '0' : '1';
                    preNum = 1;
                    ops++;
                }
            } else {
                pre = chs[i];
                preNum = 1;
            }
        }
        ans = Math.min(ans, ops);

        pre = chs[chs.length - 1] == '1' ? '0' : '1';
        preNum = 1;
        ops = 1;
        for (int i = chs.length - 2; i >= 0; i--) {
            if (pre == chs[i]) {
                if (preNum < maxSameNum) {
                    preNum++;
                } else {
                    // reverse
                    pre = chs[i] == '1' ? '0' : '1';
                    preNum = 1;
                    ops++;
                }
            } else {
                pre = chs[i];
                preNum = 1;
            }
        }
        ans = Math.min(ans, ops);
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {9,9,9,10,10,9,10};
        int k = 1;
        System.out.println(new LC1222().maxDistinctElements(nums, k));

        String s = "110001";
        int numOps = 1;
        System.out.println(new LC1222().minLength(s, numOps));

    }
}
