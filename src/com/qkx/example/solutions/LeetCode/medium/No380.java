package com.qkx.example.solutions.LeetCode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by qkx on 17/7/8.
 */
public class No380 {
    /**
     *
     * insert(val): Inserts an item val to the set if not already present.
     * remove(val): Removes an item val from the set if present.
     * getRandom: Returns a random element from current set of elements.
     * Each element must have the same probability of being returned.
     */
}

class RandomizedSet {

    private HashMap<Integer, Integer> map;
    private ArrayList<Integer> list;
    private Random random;
    private int size;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
        size = 0;
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        if (size == list.size()) {
            list.add(val);
        } else {
            list.set(size, val);
        }
        map.put(val, size);
        size++;
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int pos = map.remove(val);
        if (pos < size - 1) {
            // swap
            int last = list.get(size - 1);
            list.set(pos, last);
            map.put(last, pos);
        }
        size--;
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(random.nextInt(size));
    }
}

class RandomizedSet2 {

    private HashMap<Integer, Integer> map;
    private ArrayList<Integer> list;
    private Random random;

    /** Initialize your data structure here. */
    public RandomizedSet2() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int pos = map.remove(val);
        if (pos < list.size() - 1) {
            // swap
            int last = list.get(list.size() - 1);
            list.set(pos, last);
            map.put(last, pos);
        }
        list.remove(list.size() - 1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
