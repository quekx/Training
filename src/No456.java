import java.util.Stack;

/**
 * @author kaixin
 * @since 2021-11-18 14:57
 */
//Given an array of n integers nums, a 132 pattern is a subsequence of three
//integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] <
//nums[j].
//
// Return true if there is a 132 pattern in nums, otherwise, return false.
//
//
// Example 1:
//
//
//Input: nums = [1,2,3,4]
//Output: false
//Explanation: There is no 132 pattern in the sequence.
//
//
// Example 2:
//
//
//Input: nums = [3,1,4,2]
//Output: true
//Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
//
//
// Example 3:
//
//
//Input: nums = [-1,3,2,0]
//Output: true
//Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3,
// 0] and [-1, 2, 0].
//
//
//
// Constraints:
//
//
// n == nums.length
// 1 <= n <= 2 * 10⁵
// -10⁹ <= nums[i] <= 10⁹
//
// Related Topics Array Binary Search Stack Monotonic Stack Ordered Set 👍 2809
//👎 157


//leetcode submit region begin(Prohibit modification and deletion)
public class No456 {
    /**
     * /**
     * [3,5,0,3,4]
     *
     * min[i] 代表位置 i 左边的最小值
     * 从右往左遍历，用栈记录
     * 1. 如果 min[i] < num[i]，说明 i 左边存在比 num[i] 小的值; 开始比较 min[i] 和 栈顶元素 x，
     * 1.1 如果 min[i] < x ,返回 true
     * 1.2 如果 min[i] >= x ,将 num[i] 入栈
     * 2. 如果 min[i] >= num[i]，说明 i 左边的最小值都比 num[i] 大，num[i] 可以忽略；开始对比 min[i] 和 栈顶元素 x
     * 2.1 如果 min[i] < x
     *
     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return false;
        }



        Stack<Integer> stack = new Stack<>();

        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
