package com.qkx.example.lc.achive.medium.No12x_13x;

import com.qkx.example.model.UndirectedGraphNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qkx on 16/11/26.
 */
public class No133 {
    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;

        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        return dfsClone(node, map);
    }

    private static UndirectedGraphNode dfsClone(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> map) {
        UndirectedGraphNode newNode = map.get(node.label);
        if (newNode != null) {
            return newNode;
        }

        newNode = new UndirectedGraphNode(node.label);
        map.put(node.label, newNode);

        for (UndirectedGraphNode nextNode : node.neighbors) {
            newNode.neighbors.add(dfsClone(nextNode, map));
        }

        return newNode;
    }

    /**
     *
     * Output:
     * {0,1#1,2#2,3#3,4#4,5#5}
     * Expected:
     * {0,1,5#1,2,5#2,3#3,4,4#4,5,5#5}
     */
}
