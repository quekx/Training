package com.qkx.example.lc.achive;

//Given an integer array nums, handle multiple queries of the following types:
//
//
// Update the value of an element in nums.
// Calculate the sum of the elements of nums between indices left and right
//inclusive where left <= right.
//
//
// Implement the NumArray class:
//
//
// NumArray(int[] nums) Initializes the object with the integer array nums.
// void update(int index, int val) Updates the value of nums[index] to be val.
// int sumRange(int left, int right) Returns the sum of the elements of nums
//between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... +
//nums[right]).
//
//
//
// Example 1:
//
//
//Input
//["NumArray", "sumRange", "update", "sumRange"]
//[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
//Output
//[null, 9, null, 8]
//
//Explanation
//NumArray numArray = new NumArray([1, 3, 5]);
//numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
//numArray.update(1, 2);   // nums = [1, 2, 5]
//numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 3 * 10⁴
// -100 <= nums[i] <= 100
// 0 <= index < nums.length
// -100 <= val <= 100
// 0 <= left <= right < nums.length
// At most 3 * 10⁴ calls will be made to update and sumRange.
//
//
// Related Topics Array Design Binary Indexed Tree Segment Tree 👍 4266 👎 232

/**
 * 解答成功:
 * 	执行耗时:117 ms,击败了69.05% 的Java用户
 * 	内存消耗:72.7 MB,击败了56.16% 的Java用户
 */
//leetcode submit region begin(Prohibit modification and deletion)
class NumArray {

    private int[] nums;
    private int[] tree;

    public NumArray(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        this.tree = new int[n * 4];
        buildTree(1, 0, n - 1);
    }

    private void buildTree(int cur, int left, int right) {
        if (left == right) {
            tree[cur] = nums[left];
            return;
        }
        int mid = left + ((right - left) >> 1);
        buildTree(cur * 2, left, mid);
        buildTree(cur * 2 + 1, mid + 1, right);
        tree[cur] = tree[cur * 2] + tree[cur * 2 + 1];
    }

    public void update(int index, int val) {
        int add = val - nums[index];
        nums[index] = val;
        add(1, 0, nums.length - 1, index, add);
    }

    private void add(int cur, int left, int right, int index, int add) {
        tree[cur] += add;
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        if (index <= mid) {
            add(cur * 2, left, mid, index, add);
        } else {
            add(cur * 2 + 1, mid + 1, right, index, add);
        }
    }

    public int sumRange(int left, int right) {
        return sum(1, 0, nums.length - 1, left, right);
    }

    private int sum(int cur, int left, int right, int queryLeft, int queryRight) {
        if (queryLeft <= left && queryRight >= right) {
            return tree[cur];
        }
        int sum = 0;
        // [left, mid] + [mid + 1, right]
        int mid = left + ((right - left) >> 1);
        if (mid >= queryLeft) {
            sum += sum(cur * 2, left, mid, queryLeft, queryRight);
        }
        if (mid + 1 <= queryRight) {
            sum += sum(cur * 2 + 1, mid + 1, right, queryLeft, queryRight);
        }
        return sum;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
//leetcode submit region end(Prohibit modification and deletion)


public class No307 {
    public static void main(String[] args) {
        int[] nums = {1,3,5};
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(0, 2));
        numArray.update(1, 2);
        System.out.println(numArray.sumRange(0, 2));
    }
}
