package com.qkx.example.lc.achive.medium;

//You are given n tasks labeled from 0 to n - 1 represented by a 2D integer
//array tasks, where tasks[i] = [enqueueTimei, processingTimei] means that the ith
//task will be available to process at enqueueTimei and will take processingTimei to
//finish processing.
//
// You have a single-threaded CPU that can process at most one task at a time
//and will act in the following way:
//
//
// If the CPU is idle and there are no available tasks to process, the CPU
//remains idle.
// If the CPU is idle and there are available tasks, the CPU will choose the
//one with the shortest processing time. If multiple tasks have the same shortest
//processing time, it will choose the task with the smallest index.
// Once a task is started, the CPU will process the entire task without
//stopping.
// The CPU can finish a task then start a new one instantly.
//
//
// Return the order in which the CPU will process the tasks.
//
//
// Example 1:
//
//
//Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
//Output: [0,2,3,1]
//Explanation: The events go as follows:
//- At time = 1, task 0 is available to process. Available tasks = {0}.
//- Also at time = 1, the idle CPU starts processing task 0. Available tasks = {
//}.
//- At time = 2, task 1 is available to process. Available tasks = {1}.
//- At time = 3, task 2 is available to process. Available tasks = {1, 2}.
//- Also at time = 3, the CPU finishes task 0 and starts processing task 2 as
//it is the shortest. Available tasks = {1}.
//- At time = 4, task 3 is available to process. Available tasks = {1, 3}.
//- At time = 5, the CPU finishes task 2 and starts processing task 3 as it is
//the shortest. Available tasks = {1}.
//- At time = 6, the CPU finishes task 3 and starts processing task 1.
//Available tasks = {}.
//- At time = 10, the CPU finishes task 1 and becomes idle.
//
//
// Example 2:
//
//
//Input: tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
//Output: [4,3,2,0,1]
//Explanation: The events go as follows:
//- At time = 7, all the tasks become available. Available tasks = {0,1,2,3,4}.
//- Also at time = 7, the idle CPU starts processing task 4. Available tasks = {
//0,1,2,3}.
//- At time = 9, the CPU finishes task 4 and starts processing task 3.
//Available tasks = {0,1,2}.
//- At time = 13, the CPU finishes task 3 and starts processing task 2.
//Available tasks = {0,1}.
//- At time = 18, the CPU finishes task 2 and starts processing task 0.
//Available tasks = {1}.
//- At time = 28, the CPU finishes task 0 and starts processing task 1.
//Available tasks = {}.
//- At time = 40, the CPU finishes task 1 and becomes idle.
//
//
//
// Constraints:
//
//
// tasks.length == n
// 1 <= n <= 10⁵
// 1 <= enqueueTimei, processingTimei <= 10⁹
//
//
// Related Topics Array Sorting Heap (Priority Queue) 👍 1212 👎 137


import java.util.Arrays;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class No1834 {
    /**
     * 解答成功:
     * 	执行耗时:126 ms,击败了95.16% 的Java用户
     * 	内存消耗:77.6 MB,击败了86.87% 的Java用户
     */
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        Task[] taskList = new Task[n];
        for (int i = 0; i < n; i++) {
            taskList[i] = new Task(tasks[i][0], tasks[i][1], i);
        }
        Arrays.sort(taskList, (a, b) -> {
            if (a.enqueueTime != b.enqueueTime) {
                return a.enqueueTime - b.enqueueTime;
            }
            if (a.processingTime != b.processingTime) {
                return a.processingTime - b.processingTime;
            }
            return a.id - b.id;
        });
        PriorityQueue<Task> queue = new PriorityQueue<>((a, b) -> {
            if (a.processingTime != b.processingTime) {
                return a.processingTime - b.processingTime;
            }
            return a.id - b.id;
        });

        int[] ans = new int[n];
        queue.add(taskList[0]);
        int start = taskList[0].enqueueTime;
        int j = 0;
        for (int i = 0; i < n; i++) {
            Task cur = queue.poll();
            ans[i] = cur.id;
            start += cur.processingTime;
            while (j < n && taskList[j].enqueueTime <= start) {
                queue.add(taskList[j]);
                j++;
            }
            if (j < n && queue.isEmpty()) {
                queue.add(taskList[j]);
                start = taskList[j].enqueueTime;
                j++;
            }
        }
        return ans;
    }

    class Task {
        int enqueueTime;
        int processingTime;
        int id;
        public Task(int enqueueTime, int processingTime, int id) {
            this.enqueueTime = enqueueTime;
            this.processingTime = processingTime;
            this.id = id;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

