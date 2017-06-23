package com.qkx.example.solutions.LeetCode.medium.x;

import java.util.*;

/**
 * Created by qkx on 17/4/8.
 */
public class No207 {

    /**
     * There are a total of n courses you have to take, labeled from 0 to n - 1.
     * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
     * which is expressed as a pair: [0,1]
     * Given the total number of courses and a list of prerequisite pairs,
     * is it possible for you to finish all courses?
     */

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 1) return true;

        List<Set<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new HashSet<>());
        }

        int[] degree = new int[numCourses]; // No2 BFS

        for (int[] pre : prerequisites) {
            graph.get(pre[0]).add(pre[1]);

            degree[pre[1]]++; // No2
        }

        // No2
        int count = 0;
        Set<Integer> startPoints = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            if (degree[i] == 0) {
                startPoints.add(i);
                count++;
            }
        }

        while (!startPoints.isEmpty()) {
            Iterator<Integer> iterator = startPoints.iterator();
            int cur = iterator.next();
            iterator.remove();
            Set<Integer> neighbors = graph.get(cur);
            for (int next : neighbors) {
                degree[next]--;
                if (degree[next] == 0) {
                    startPoints.add(next);
                    count++;
                }
            }
        }

        return count == numCourses;

        // No1
//        boolean[] isVisit = new boolean[numCourses];
//        for (int i = 0; i < numCourses; i++) {
//            if (!dfs(graph, i, isVisit)) return false;
//        }
//
//        return true;

    }

    private boolean dfs(List<Set<Integer>> graph, int i, boolean[] isVisit) {
        if (isVisit[i]) return false;

        isVisit[i] = true;

        Set<Integer> neighbors = graph.get(i);
        while (!neighbors.isEmpty()) {
            Iterator<Integer> iterator = neighbors.iterator();
            int next = iterator.next();
            if (!dfs(graph, next, isVisit)) return false;

            iterator.remove();
        }

        isVisit[i] = false;

        return true;
    }
}
