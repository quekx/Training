package contest.lc2024;

import com.qkx.example.utils.ArrayUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC1124 {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int[] preSum = new int[n + 1];
        preSum[0] = nums.get(0);
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums.get(i - 1);
        }
        int ans = Integer.MAX_VALUE;
        for (int left = 0; left < n; left++) {
            for (int right = left + l - 1; right <= left + r - 1 && right < n; right++) {
                // [left, right]
                int sum = preSum[right + 1] - preSum[left];
                if (sum > 0) {
                    ans = Math.min(ans, sum);
                }
            }
        }
        return ans != Integer.MAX_VALUE ? ans : -1;
    }

    public boolean isPossibleToRearrange(String s, String t, int k) {
        int len = s.length();
        if (len % k != 0 || s.length() != t.length()) {
            return false;
        }
        int x = len / k;
        Map<String, Integer> counter1 = new HashMap<>(k);
        Map<String, Integer> counter2 = new HashMap<>(k);
        for (int i = 0; i < k; i++) {
            String word1 = s.substring(i * x, i * x + x);
            counter1.put(word1, counter1.getOrDefault(word1, 0) + 1);

            String word2 = t.substring(i * x, i * x + x);
            counter2.put(word2, counter2.getOrDefault(word2, 0) + 1);
        }
        if (counter1.size() != counter2.size()) {
            return false;
        }
        for (Map.Entry<String, Integer> kv : counter1.entrySet()) {
            if (!kv.getValue().equals(counter2.getOrDefault(kv.getKey(), 0))) {
                return false;
            }
        }
        return true;
    }

    /**
     * >= 2k的数 优先减半
     * < 2k && >=k的数 优先减k
     */
    public int minArraySum(int[] nums, int k, int op1, int op2) {
        int n = nums.length;
        Arrays.sort(nums);
        ArrayUtil.print(nums);
        int i = n - 1;
        while (i >= 0 && nums[i] >= 2 * k - 1 && op1 > 0) {
            nums[i] = nums[i] % 2 == 0 ? nums[i] / 2 : nums[i] / 2 + 1;
            op1--;
            if (op2 > 0) {
                nums[i] -= k;
                op2--;
            }
            i--;
        }
        ArrayUtil.print(nums);
        System.out.println(i);
        int j = 0;
        while (j <= i && op2 > 0) {
            if (nums[j] >= k) {
                nums[j] -= k;
                op2--;
            }
            j++;
        }
        ArrayUtil.print(nums);
        if (op1 > 0) {
            Arrays.sort(nums, 0, i + 1);
            ArrayUtil.print(nums);
            j = i;
            while (j >= 0 && op1 > 0) {
                nums[j] = nums[j] % 2 == 0 ? nums[j] / 2 : nums[j] / 2 + 1;
                j--;
                op1--;
            }
            ArrayUtil.print(nums);
        }
        int ans = 0;
        for (int num : nums) {
            ans += num;
        }
        return ans;
    }

    /**
     * [882,307,624,469,329,684,851,608,317,205]
     * 431
     * 9
     * 4
     * @param args
     */
    public static void main(String[] args) {
        new LC1124().isPossibleToRearrange("aabbcc", "aabbcc", 3);

        int[] nums = {882,307,624,469,329,684,851,608,317,205};
//        Arrays.sort(nums);
//        ArrayUtil.print(nums);
        int k = 431, op1 = 9, op2 = 4;
        new LC1124().minArraySum(nums, k, op1, op2);
//        ArrayUtil.print(nums);

    }
}
