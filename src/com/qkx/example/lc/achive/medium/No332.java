package com.qkx.example.lc.achive.medium;

import java.util.*;

/**
 * Created by qkx on 17/6/23.
 */
public class No332 {
    public List<String> findItinerary(String[][] tickets) {
        if (tickets == null || tickets.length == 0) return null;

        Map<String, PriorityQueue<String>> map = new HashMap<>();
        List<String> result = new LinkedList<>();

        for (String[] ticket : tickets) {
            map.putIfAbsent(ticket[0], new PriorityQueue<>());
            map.get(ticket[0]).add(ticket[1]);
        }

        dfs(map, result, "JFK");
        return result;
    }

    private void dfs(Map<String, PriorityQueue<String>> map, List<String> result, String from) {
        PriorityQueue<String> to = map.get(from);
        while (to != null && !to.isEmpty()) {
            dfs(map, result, to.poll());
        }
        result.add(0, from);
    }
}
