package com.qkx.example.lc.achive.hard;

import java.util.Objects;
import java.util.PriorityQueue;

/**
 * @author kaixin
 * @since 2021-09-23 16:27
 */
public class No295 {

}

//The median is the middle value in an ordered integer list. If the size of the
//list is even, there is no middle value and the median is the mean of the two
//middle values.
//
//
// For example, for arr = [2,3,4], the median is 3.
// For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
//
//
// Implement the MedianFinder class:
//
//
// MedianFinder() initializes the MedianFinder object.
// void addNum(int num) adds the integer num from the data stream to the data
//structure.
// double findMedian() returns the median of all elements so far. Answers
//within 10⁻⁵ of the actual answer will be accepted.
//
//
//
// Example 1:
//
//
//Input
//["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
//[[], [1], [2], [], [3], []]
//Output
//[null, null, null, 1.5, null, 2.0]
//
//Explanation
//MedianFinder medianFinder = new MedianFinder();
//medianFinder.addNum(1);    // arr = [1]
//medianFinder.addNum(2);    // arr = [1, 2]
//medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
//medianFinder.addNum(3);    // arr[1, 2, 3]
//medianFinder.findMedian(); // return 2.0
//
//
//
// Constraints:
//
//
// -10⁵ <= num <= 10⁵
// There will be at least one element in the data structure before calling
//findMedian.
// At most 5 * 10⁴ calls will be made to addNum and findMedian.
//
//
//
// Follow up:
//
//
// If all integer numbers from the stream are in the range [0, 100], how would
//you optimize your solution?
// If 99% of all integer numbers from the stream are in the range [0, 100], how
//would you optimize your solution?
//
// Related Topics Two Pointers Design Sorting Heap (Priority Queue) Data Stream
//👍 5351 👎 96


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 维护两个堆
 * 左边为大根堆，右边为小根堆
 * 两个堆的堆顶分别是中间两个数
 * 每次加入时，对比堆顶元素
 */
class MedianFinder {

    private PriorityQueue<Integer> preQueue;
    private PriorityQueue<Integer> postQueue;

    public MedianFinder() {
        preQueue = new PriorityQueue<>((o1, o2) -> {
            if (Objects.equals(o1, o2)) {
                return 0;
            }
            return o1 < o2 ? 1 : -1;
        });
        postQueue = new PriorityQueue<>((o1, o2) -> {
            if (Objects.equals(o1, o2)) {
                return 0;
            }
            return o1 < o2 ? -1 : 1;
        });
    }

    public void addNum(int num) {
        int preSize = preQueue.size();
        Integer preNum = preQueue.peek();
        int postSize = postQueue.size();
        Integer postNum = postQueue.peek();
        if (preNum == null) {
            preQueue.add(num);
        } else if (postNum == null) {
            if (num < preNum) {
                preQueue.poll();
                preQueue.add(num);
                postQueue.add(preNum);
            } else {
                postQueue.add(num);
            }
        } else {
            if (preSize == postSize) {
                if (num > postNum) {
                    postQueue.poll();
                    postQueue.add(num);
                    preQueue.add(postNum);
                } else {
                    preQueue.add(num);
                }
            } else {
                if (num < preNum) {
                    preQueue.poll();
                    preQueue.add(num);
                    postQueue.add(preNum);
                } else {
                    postQueue.add(num);
                }
            }
        }
    }

    public double findMedian() {
        if (preQueue.isEmpty()) {
            return 0D;
        }

        int preSize = preQueue.size();
        int postSize = postQueue.size();
        if (preSize == postSize) {
            return (double) (preQueue.peek() + postQueue.peek()) / 2;
        } else {
            return preQueue.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)
