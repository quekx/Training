package com.qkx.example.solutions.LeetCode.medium;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author kaixin
 * @since 2021-11-11 17:41
 */
//Given an integer array nums, return the maximum result of nums[i] XOR nums[j],
// where 0 <= i <= j < n.
//
//
// Example 1:
//
//
//Input: nums = [3,10,5,25,2,8]
//Output: 28
//Explanation: The maximum result is 5 XOR 25 = 28.
//
// Example 2:
//
//
//Input: nums = [0]
//Output: 0
//
//
// Example 3:
//
//
//Input: nums = [2,4]
//Output: 6
//
//
// Example 4:
//
//
//Input: nums = [8,10,2]
//Output: 10
//
//
// Example 5:
//
//
//Input: nums = [14,70,53,83,49,91,36,80,92,51,66,70]
//Output: 127
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 2 * 10⁵
// 0 <= nums[i] <= 2³¹ - 1
//
// Related Topics Array Hash Table Bit Manipulation Trie 👍 2596 👎 239


//leetcode submit region begin(Prohibit modification and deletion)
public class No421 {

    /**
     *
     * 解答成功:
     * 执行耗时:35 ms,击败了98.69% 的Java用户
     * 内存消耗:59 MB,击败了76.52% 的Java用户
     * @param args
     */
    public static void main(String[] args) {
//        int[] nums = {2,4};
        int[] nums = {1, 2 ,3 ,4};
        System.out.println(new No421().findMaximumXOR2(nums));
    }

    public int findMaximumXOR(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        int tag = 0;
        while (max > 0) {
            max = max >>> 1;
            tag++;
        }

        Node root = buildTree(nums, tag);
        return find(root.left, root.right, 0, tag);
    }

    private int find(Node i, Node j, int preSum, int tag) {
        if (i != null && j != null) {
            int curSum = preSum + ((i.val ^ j.val) << (tag - 1));
            if (tag == 1) {
                return curSum;
            }

            if ((i.left != null && j.right != null) && (i.right != null && j.left != null)) {
                int r1 = find(i.left, j.right, curSum, tag - 1);
                int r2 = find(i.right, j.left, curSum, tag - 1);
                return Math.max(r1, r2);
            } else if (i.left != null && j.right != null) {
                return find(i.left, j.right, curSum, tag - 1);
            } else if (i.right != null && j.left != null) {
                return find(i.right, j.left, curSum, tag - 1);
            } else if (i.left != null) {
                return find(i.left, j.left, curSum, tag - 1);
            } else {
                return find(i.right, j.right, curSum, tag - 1);
            }
        } else if (i != null) {
            if (tag == 1) {
                return preSum;
            }
            if (i.left != null && i.right != null) {
                return find(i.left, i.right, preSum, tag - 1);
            } else if (i.left != null) {
                return find(i.left, null, preSum, tag - 1);
            } else {
                return find(null, i.right, preSum, tag - 1);
            }
        } else if (j != null) {
            if (tag == 1) {
                return preSum;
            }
            if (j.left != null && j.right != null) {
                return find(j.left, j.right, preSum, tag - 1);
            } else if (j.left != null) {
                return find(j.left, null, preSum, tag - 1);
            } else {
                return find(null, j.right, preSum, tag - 1);
            }
        }
        return 0;
    }

    private Node buildTree(int[] nums, int tag) {
        Node root = new Node();
        for (int num : nums) {
            Node cur = root;
            for (int i = tag - 1; i >= 0; i--) {
                if ((num & (1 << i)) == 0) {
                    if (cur.left == null) {
                        cur.left = new Node();
                        cur.left.val = 0;
                    }
                    cur = cur.left;
                } else {
                    if (cur.right == null) {
                        cur.right = new Node();
                        cur.right.val = 1;
                    }
                    cur = cur.right;
                }
            }
        }
        return root;
    }

    private static class Node {
        private Node left;
        private Node right;
        private int val;
    }

    /**
     * 这个按理是构造树的时候，一遍构造一遍判断计算
     * @param nums
     * @return
     */
    public int findMaximumXOR2(int[] nums) {
        int maxNumber= Arrays.stream(nums).max().getAsInt();
        int max = Integer.MIN_VALUE;

        int L = (Integer.toBinaryString(maxNumber)).length();
        TrieNode root = new TrieNode('*');
        // zero left-padding to ensure L bits for each number
        int n = nums.length, bitmask = 1 << L;
        String[] strNums = new String[n];
        for (int i = 0; i < n; ++i) {
            strNums[i] = Integer.toBinaryString(bitmask | nums[i]).substring(1);
        }


        for (String binaryString : strNums) {
            TrieNode node = root;
            TrieNode xorNode = root;
            StringBuilder xorString = new StringBuilder();
            for (Character bit : binaryString.toCharArray()) {
                if (node.map.containsKey(bit)) {
                    node = node.map.get(bit);
                } else {
                    node.map.put(bit, new TrieNode(bit));
                    node = node.map.get(bit);
                }
                Character toggledBit = bit == '1' ? '0' : '1';

                if(xorNode.map.containsKey(toggledBit)){
                    xorNode=xorNode.map.get(toggledBit);
                    xorString.append('1');
                }else{
                    xorNode=xorNode.map.get(bit);
                    xorString.append('0');
                }
            }
            int res = Integer.parseInt(xorString.toString(), 2);
            max = Math.max(res, max);
            //System.out.println("Result: " + res);
        }
        return max;
    }

    private static class TrieNode {
        char val;
        HashMap<Character, TrieNode> map;

        public TrieNode(char val) {
            this.val = val;
            this.map = new HashMap<>();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

