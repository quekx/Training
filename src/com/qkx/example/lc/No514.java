package com.qkx.example.lc;

//In the video game Fallout 4, the quest "Road to Freedom" requires players to
//reach a metal dial called the "Freedom Trail Ring" and use the dial to spell a
//specific keyword to open the door.
//
// Given a string ring that represents the code engraved on the outer ring and
//another string key that represents the keyword that needs to be spelled, return
//the minimum number of steps to spell all the characters in the keyword.
//
// Initially, the first character of the ring is aligned at the "12:00"
//direction. You should spell all the characters in key one by one by rotating ring
//clockwise or anticlockwise to make each character of the string key aligned at the "12
//:00" direction and then by pressing the center button.
//
// At the stage of rotating the ring to spell the key character key[i]:
//
//
// You can rotate the ring clockwise or anticlockwise by one place, which
//counts as one step. The final purpose of the rotation is to align one of ring's
//characters at the "12:00" direction, where this character must equal key[i].
// If the character key[i] has been aligned at the "12:00" direction, press the
//center button to spell, which also counts as one step. After the pressing, you
//could begin to spell the next character in the key (next stage). Otherwise, you
//have finished all the spelling.
//
//
//
// Example 1:
//
//
//Input: ring = "godding", key = "gd"
//Output: 4
//Explanation:
//For the first key character 'g', since it is already in place, we just need 1
//step to spell this character.
//For the second key character 'd', we need to rotate the ring "godding"
//anticlockwise by two steps to make it become "ddinggo".
//Also, we need 1 more step for spelling.
//So the final output is 4.
//
//
// Example 2:
//
//
//Input: ring = "godding", key = "godding"
//Output: 13
//
//
//
// Constraints:
//
//
// 1 <= ring.length, key.length <= 100
// ring and key consist of only lower case English letters.
// It is guaranteed that key could always be spelled by rotating ring.
//
//
// Related Topics String Dynamic Programming Depth-First Search Breadth-First
//Search 👍 814 👎 36


import com.qkx.example.utils.ArrayUtil;

//leetcode submit region begin(Prohibit modification and deletion)
class No514 {
    /**
     * dp[i][j] 表示匹配到 key[i]，在 ring[j] 位置的最小步数
     * key[i] == ring[j]
     * dp[i][j] = dp[i - 1][k] + min(j - k, n + k - j), 其中 key[i - 1] == ring[k]
     *
     * 解答成功:
     * 	执行耗时:30 ms,击败了34.59% 的Java用户
     * 	内存消耗:43 MB,击败了30.83% 的Java用户
     */
    public int findRotateSteps(String ring, String key) {
        int ringLength = ring.length(), keyLength = key.length();
        int[][] dp = new int[keyLength][ringLength];
        for (int j = 0; j < ring.length(); j++) {
            if (key.charAt(0) == ring.charAt(j)) {
                dp[0][j] = Math.min(j, ringLength - j) + 1;
            }
        }
        for (int i = 1; i < keyLength; i++) {
            for (int j = 0; j < ringLength; j++) {
                if (key.charAt(i) != ring.charAt(j)) {
                    continue;
                }
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < ringLength; k++) {
                    // k -> j
                    if (key.charAt(i - 1) == ring.charAt(k)) {
                        int diff = Math.abs(j - k);
                        int move = Math.min(diff, ringLength - diff);
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + move + 1);
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < ringLength; j++) {
            if (dp[keyLength - 1][j] != 0) {
                ans = Math.min(ans, dp[keyLength - 1][j]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // k -> j
        // k = 0, j = 3, len = 5
        System.out.println(new No514().findRotateSteps("abcde", "ade"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

