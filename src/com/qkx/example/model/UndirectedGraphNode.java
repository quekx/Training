package com.qkx.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qkx on 16/11/26.
 */
public class UndirectedGraphNode {
    public int label;
    public List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
