package com.qkx.example.lc;

import java.util.HashMap;
import java.util.Map;


//Design a data structure that follows the constraints of a Least Recently Used
//(LRU) cache.
//
// Implement the LRUCache class:
//
//
// LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
//
// int get(int key) Return the value of the key if the key exists, otherwise ret
//urn -1.
// void put(int key, int value) Update the value of the key if the key exists. O
//therwise, add the key-value pair to the cache. If the number of keys exceeds the
// capacity from this operation, evict the least recently used key.
//
//
// Follow up:
//Could you do get and put in O(1) time complexity?
//
//
// Example 1:
//
//
//Input
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//Output
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//Explanation
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // cache is {1=1}
//lRUCache.put(2, 2); // cache is {1=1, 2=2}
//lRUCache.get(1);    // return 1
//lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
//lRUCache.get(2);    // returns -1 (not found)
//lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
//lRUCache.get(1);    // return -1 (not found)
//lRUCache.get(3);    // return 3
//lRUCache.get(4);    // return 4
//
//
//
// Constraints:
//
//
// 1 <= capacity <= 3000
// 0 <= key <= 3000
// 0 <= value <= 104
// At most 3 * 104 calls will be made to get and put.
//
// Related Topics Design
// 👍 7294 👎 300
/**
 * @author kaixin
 * @since 2020-12-15 15:09
 */
public class No146 {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
    }
}

/**
 * 解答成功:
 * 	执行耗时:74 ms,击败了29.16% 的Java用户
 * 	内存消耗:117.7 MB,击败了38.61% 的Java用户
 */
//leetcode submit region begin(Prohibit modification and deletion)
class LRUCache {

    private Map<Integer, KeyNode> map;
    private KeyNode head = new KeyNode(-1, -1);
    private KeyNode tail = head;
    private int size = 0;
    private int capacity;
    public LRUCache(int capacity) {
        this.map = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        KeyNode node = map.get(key);
        if (node == null) {
            return -1;
        }
        removeNode(node);
        addNode(node);
        return node.val;
    }

    public void put(int key, int value) {
        KeyNode node = map.get(key);
        if (node == null) {
            node = new KeyNode(key, value);
        } else {
            node.val = value;
            removeNode(node);
        }
        addNode(node);
        if (size > capacity) {
            removeNode(tail);
        }
    }

    private void addNode(KeyNode node) {
        map.put(node.key, node);
        node.next = head.next;
        node.prev = head;
        head.next = node;
        if (node.next == null) {
            tail = node;
        } else {
            node.next.prev = node;
        }
        size++;
    }

    private void removeNode(KeyNode node) {
        map.remove(node.key);
        node.prev.next = node.next;
        if (node.next == null) {
            tail = node.prev;
        } else {
            node.next.prev = node.prev;
        }
        size--;
    }

    class KeyNode {
        private KeyNode prev;
        private KeyNode next;
        int key;
        int val;

        public KeyNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

