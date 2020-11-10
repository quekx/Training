package com.qkx.example.solutions.LeetCode.medium;

//Suppose you have a random list of people standing in a queue. Each person is d
//escribed by a pair of integers (h, k), where h is the height of the person and k
// is the number of people in front of this person who have a height greater than
//or equal to h. Write an algorithm to reconstruct the queue.
//
// Note:
//The number of people is less than 1,100.
//
//
// Example
//
//
//Input:
//[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
//
//Output:
//[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
//
//
//
// Related Topics Greedy
// 👍 3576 👎 403

import java.util.Arrays;

/**
 * 策略：先按元素p[0]从高到低，位数p[1]从低到高进行排序
 * 原因：先从最高开始排，后面低的排列调整位置对高的不会产生影响
 */

//leetcode submit region begin(Prohibit modification and deletion)
public class No406 {
    public static int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o2[0] - o1[0];
            }
            return o1[1] - o2[1];
        });

        // 把p[i] 加入前面的集合中
        // 前面集合中的元素全部>=p[i]元素，比当前数大的数个数为i，因此只需要比较位置i与个数pi[1]
        // 1. 个数pi[1]小于i，则与pi-1交换
        // 2. 个数p[i] 大于i，不可能bad case
        for (int i = 1; i < people.length; i++) {
            for (int k = i; k >= 1; k--) {
                if (people[k][1] < k) {
                    // 交换
                    int[] temp = people[k];
                    people[k] = people[k - 1];
                    people[k - 1] = temp;
                } else {
                    break;
                }
            }
        }
        return people;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
