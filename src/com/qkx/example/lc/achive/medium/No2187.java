package com.qkx.example.lc.achive.medium;

//You are given an array time where time[i] denotes the time taken by the iᵗʰ
//bus to complete one trip.
//
// Each bus can make multiple trips successively; that is, the next trip can
//start immediately after completing the current trip. Also, each bus operates
//independently; that is, the trips of one bus do not influence the trips of any other
//bus.
//
// You are also given an integer totalTrips, which denotes the number of trips
//all buses should make in total. Return the minimum time required for all buses
//to complete at least totalTrips trips.
//
//
// Example 1:
//
//
//Input: time = [1,2,3], totalTrips = 5
//Output: 3
//Explanation:
//- At time t = 1, the number of trips completed by each bus are [1,0,0].
//  The total number of trips completed is 1 + 0 + 0 = 1.
//- At time t = 2, the number of trips completed by each bus are [2,1,0].
//  The total number of trips completed is 2 + 1 + 0 = 3.
//- At time t = 3, the number of trips completed by each bus are [3,1,1].
//  The total number of trips completed is 3 + 1 + 1 = 5.
//So the minimum time needed for all buses to complete at least 5 trips is 3.
//
//
// Example 2:
//
//
//Input: time = [2], totalTrips = 1
//Output: 2
//Explanation:
//There is only one bus, and it will complete its first trip at t = 2.
//So the minimum time needed to complete 1 trip is 2.
//
//
//
// Constraints:
//
//
// 1 <= time.length <= 10⁵
// 1 <= time[i], totalTrips <= 10⁷
//
//
// Related Topics Array Binary Search 👍 1400 👎 85


//leetcode submit region begin(Prohibit modification and deletion)
class No2187 {
    /**
     * O(time.length) * 0(log[min(time) * totalTrips])
     */
    /**
     * 解答成功:
     * 	执行耗时:183 ms,击败了91.94% 的Java用户
     * 	内存消耗:57.1 MB,击败了31.81% 的Java用户
     */
    public long minimumTime(int[] time, int totalTrips) {
        int n = time.length;
        long min = Integer.MAX_VALUE;
        for (int x : time) {
            min = Math.min(min, x);
        }
        long l = 0, r = min * totalTrips;
        while (l <= r) {
            long mid = l + ((r - l) >> 1);
            if (check(time, mid, totalTrips)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(int[] time, long t, int totalTrips) {
        long total = 0;
        for (int x : time) {
            total += t / x;
            if (total >= totalTrips) {
                return true;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

