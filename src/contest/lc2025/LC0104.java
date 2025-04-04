package contest.lc2025;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class LC0104 {
    public boolean hasMatch(String s, String p) {
        for (int i = 0; i < s.length(); i++) {
            if (match(s, p, i, 0)) {
                return true;
            }
        }
        return false;
    }

    private boolean match(String s, String p, int i, int j) {
        if (j == p.length()) {
            return true;
        }
        if (i == s.length()) {
            if (p.charAt(j) == '*') {
                return match(s, p, i, j + 1);
            } else {
                return false;
            }
        }
        if (s.charAt(i) == p.charAt(j)) {
            return match(s, p, i + 1, j + 1);
        }
        if (p.charAt(j) == '*') {
            return match(s, p, i + 1, j) || match(s, p, i + 1, j + 1) || match(s, p, i, j + 1);
        }
        return false;
    }

    static class TaskManager {

        private PriorityQueue<Task> priorityQueue;
        private Map<Integer, Task> taskMap;

        public TaskManager(List<List<Integer>> tasks) {
            taskMap = new HashMap<>();
            priorityQueue = new PriorityQueue<>((a, b) -> {
                if (a.priority != b.priority) {
                    return b.priority - a.priority;
                }
                return b.taskId - a.taskId;
            });
            for (List<Integer> task : tasks) {
                Task t = new Task(task.get(0), task.get(1), task.get(2));
                priorityQueue.add(t);
                taskMap.put(task.get(1), t);
            }
        }

        public void add(int userId, int taskId, int priority) {
            Task t = new Task(userId, taskId, priority);
            priorityQueue.add(t);
            taskMap.put(taskId, t);
        }

        public void edit(int taskId, int newPriority) {
            Task t = taskMap.get(taskId);
            t.status = 1;
            Task newT = new Task(t.userId, taskId, newPriority);
            priorityQueue.add(newT);
            taskMap.put(taskId, newT);
        }

        public void rmv(int taskId) {
            Task t = taskMap.get(taskId);
            t.status = 1;
            taskMap.remove(taskId);
        }

        public int execTop() {
            while (!priorityQueue.isEmpty()) {
                Task top = priorityQueue.poll();
                if (top.status != 1) {
                    return top.userId;
                }
            }
            return -1;
        }

        private class Task {
            private int userId;
            private int taskId;
            private int priority;
            private int status = 0;

            public Task(int userId, int taskId, int priority) {
                this.userId = userId;
                this.taskId = taskId;
                this.priority = priority;
            }
        }
    }

    /**
     * dp[i][j] 表示以 num[i] 为结尾元素, 且结尾元素与倒数第二个元素差 >= j 的最长子序列长度
     *
     * dp[i + 1][k] =
     * 前一个元素 x 遍历 [0, i], 结尾为 num[x], num[i + 1], 差值 d = |num[x] - num[i + 1]|
     *
     */
    public int longestSubsequence(int[] nums) {

        return 0;
    }
}

