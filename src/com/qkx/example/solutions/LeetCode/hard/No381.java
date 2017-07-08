package com.qkx.example.solutions.LeetCode.hard;

import java.util.*;

/**
 * Created by qkx on 17/7/9.
 */
public class No381 {
/**
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * Note: Duplicate elements are allowed.
 * insert(val): Inserts an item val to the collection.
 * remove(val): Removes an item val from the collection if present.
 * getRandom: Returns a random element from current collection of elements.
 * The probability of each element being returned is linearly related to the number of same value the collection contains.
 */
}
class RandomizedCollection {

    private HashMap<Integer, Queue<Integer>> map;
    private ArrayList<Integer> list;
    private Random random;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        Queue<Integer> queue = map.get(val);
        if (queue == null) {
            queue = new LinkedList<>();
            map.put(val, queue);
        }
        queue.add(list.size());
        list.add(val);
        return true;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        Queue<Integer> queue = map.get(val);
        int pos = queue.poll();
        if (queue.size() == 0) {
            map.remove(val);
        }
        if (pos < list.size() - 1) {
            // swap
            int last = list.get(list.size() - 1);
            list.set(pos, last);
            Queue<Integer> lastPos = map.get(last);
            lastPos.remove(list.size() - 1);
            lastPos.add(pos);
        }
        list.remove(list.size() - 1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
