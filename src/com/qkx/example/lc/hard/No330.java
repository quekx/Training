package com.qkx.example.lc.hard;

/**
 * @author kaixin
 * @since 2021-09-27 15:22
 */
//Given a sorted integer array nums and an integer n, add/patch elements to the
//array such that any number in the range [1, n] inclusive can be formed by the
//sum of some elements in the array.
//
// Return the minimum number of patches required.
//
//
// Example 1:
//
//
//Input: nums = [1,3], n = 6
//Output: 1
//Explanation:
//Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4
//.
//Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,
//3], [1,2,3].
//Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
//So we only need 1 patch.
//
//
// Example 2:
//
//
//Input: nums = [1,5,10], n = 20
//Output: 2
//Explanation: The two patches can be [2, 4].
//
//
// Example 3:
//
//
//Input: nums = [1,2,2], n = 5
//Output: 0
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 1000
// 1 <= nums[i] <= 10⁴
// nums is sorted in ascending order.
// 1 <= n <= 2³¹ - 1
//
// Related Topics Array Greedy 👍 989 👎 104


//leetcode submit region begin(Prohibit modification and deletion)
public class No330 {

    /**
     *16:06	info
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:38.9 MB,击败了52.23% 的Java用户
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {1,2,31,33};
        int n = 2147483647;
        int res = new No330().minPatches(nums, n);
        System.out.println(res);
    }

    /**
     * 设序列 num[0,i] 子序列符合 [1,n]
     * 新加入 k=num[i+1] 子序列的和 为【1,n】 + 【k, n+k】
     * 如果要满足子序列的和连续，【1,n】 + 【k, n+k】需要连续
     * 则 k <= n+1，即k<=n+1
     * 如果不满足，则需要手动补充一个最大的k=n+1
     *
     * @param nums
     * @param n
     * @return
     */
    public int minPatches(int[] nums, int n) {
        long curN = 0;
        int i = 0;
        int count = 0;
        while (curN < n) {
            if (i < nums.length) {
                if (nums[i] <= curN + 1) {
                    // 这里直接取数组的数
                    curN += nums[i];
                    i++;
                } else {
                    // 这里补充一个值 curN + 1;
                    curN = 2 * curN + 1;
                    count++;
                }
            } else {
                // 这里补充一个值 curN + 1;
                curN = 2 * curN + 1;
                count++;
            }
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
