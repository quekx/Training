package com.qkx.example.lc.achive.medium.x;

import java.util.*;

/**
 * Created by qkx on 17/4/15.
 */
public class No210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) return new int[0];

        List<Set<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new HashSet<>());
        }

        int[] degree = new int[numCourses];
        for (int[] pre : prerequisites) {
            graph.get(pre[0]).add(pre[1]);
            degree[pre[1]]++;
        }

        int[] result = new int[numCourses];
        int count = 0;
        Set<Integer> startPoints = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            if (degree[i] == 0) {
                startPoints.add(i);
                count++;

                result[numCourses - count] = i;
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

                    if (count <= numCourses) {
                        result[numCourses - count] = next;
                    } else {
                        return new int[0];
                    }
                }
            }
        }

        return count == numCourses ? result : new int[0];
    }
}
