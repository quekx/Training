package com.qkx.example.lc;

//Design and implement a data structure for a Least Frequently Used (LFU) cache.
//
//
// Implement the LFUCache class:
//
//
// LFUCache(int capacity) Initializes the object with the capacity of the data
//structure.
// int get(int key) Gets the value of the key if the key exists in the cache.
//Otherwise, returns -1.
// void put(int key, int value) Update the value of the key if present, or
//inserts the key if not already present. When the cache reaches its capacity, it
//should invalidate and remove the least frequently used key before inserting a new
//item. For this problem, when there is a tie (i.e., two or more keys with the same
//frequency), the least recently used key would be invalidated.
//
//
// To determine the least frequently used key, a use counter is maintained for
//each key in the cache. The key with the smallest use counter is the least
//frequently used key.
//
// When a key is first inserted into the cache, its use counter is set to 1 (
//due to the put operation). The use counter for a key in the cache is incremented
//either a get or put operation is called on it.
//
// The functions get and put must each run in O(1) average time complexity.
//
//
// Example 1:
//
//
//Input
//["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get",
//"get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
//Output
//[null, null, null, 1, null, -1, 3, null, -1, 3, 4]
//
//Explanation
//// cnt(x) = the use counter for key x
//// cache=[] will show the last used order for tiebreakers (leftmost element
//is  most recent)
//LFUCache lfu = new LFUCache(2);
//lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
//lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
//lfu.get(1);      // return 1
//                 // cache=[1,2], cnt(2)=1, cnt(1)=2
//lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest,
//invalidate 2.
//                 // cache=[3,1], cnt(3)=1, cnt(1)=2
//lfu.get(2);      // return -1 (not found)
//lfu.get(3);      // return 3
//                 // cache=[3,1], cnt(3)=2, cnt(1)=2
//lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1
//.
//                 // cache=[4,3], cnt(4)=1, cnt(3)=2
//lfu.get(1);      // return -1 (not found)
//lfu.get(3);      // return 3
//                 // cache=[3,4], cnt(4)=1, cnt(3)=3
//lfu.get(4);      // return 4
//                 // cache=[4,3], cnt(4)=2, cnt(3)=3
//
//
//
// Constraints:
//
//
// 1 <= capacity <= 10⁴
// 0 <= key <= 10⁵
// 0 <= value <= 10⁹
// At most 2 * 10⁵ calls will be made to get and put.
//
//
//
//
//
// Related Topics Hash Table Linked List Design Doubly-Linked List 👍 4918 👎 30
//5


import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * 解答成功:
 * 	执行耗时:71 ms,击败了67.35% 的Java用户
 * 	内存消耗:121.2 MB,击败了77.01% 的Java用户
 */
//leetcode submit region begin(Prohibit modification and deletion)
class LFUCache {

    private Map<Integer, LinkList> freqMap = new HashMap<>();
    private Map<Integer, KeyNode> keyMap = new HashMap<>();

    private int capacity;
    private int size = 0;
    private int minFreq = 0;
    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        KeyNode node = keyMap.get(key);
        if (node == null) {
            return -1;
        }
        freqMap.get(node.frequency).removeNode(node);
        node.frequency++;
        LinkList nextList = freqMap.computeIfAbsent(node.frequency, k -> new LinkList());
        nextList.addNode(node);
        if (freqMap.get(minFreq).isEmpty()) {
            minFreq++;
        }
        return node.val;
    }

    public void put(int key, int value) {
        KeyNode node = keyMap.get(key);
        if (node == null) {
            node = new KeyNode(key, value);
            node.frequency = 1;
            if (size == capacity) {
                LinkList minList = freqMap.get(minFreq);
                minList.removeTail();
                size--;
            }
            LinkList list = freqMap.computeIfAbsent(node.frequency, k -> new LinkList());
            list.addNode(node);
            minFreq = 1;
            size++;
        } else {
            node.val = value;
            freqMap.get(node.frequency).removeNode(node);
            node.frequency++;
            LinkList nextList = freqMap.computeIfAbsent(node.frequency, k -> new LinkList());
            nextList.addNode(node);
            if (freqMap.get(minFreq).isEmpty()) {
                minFreq++;
            }
        }
    }

    class LinkList {
        KeyNode head;
        KeyNode tail;

        public LinkList() {
            this.head = new KeyNode(-1, -1);
            tail = head;
        }

        public void addNode(KeyNode node) {
            keyMap.put(node.key, node);
            KeyNode next = head.next;
            head.next = node;
            node.prev = head;
            node.next = next;
            if (next != null) {
                next.prev = node;
            }
            if (node.next == null) {
                tail = node;
            }
        }

        public void removeNode(KeyNode node) {
            KeyNode prev = node.prev;
            KeyNode next = node.next;
            prev.next = next;
            if (next != null) {
                next.prev = prev;
            }
            if (node == tail) {
                tail = prev;
            }
        }

        public void removeTail() {
            if (tail == head) {
                return;
            }
            keyMap.remove(tail.key);
            KeyNode prev = tail.prev;
            prev.next = null;
            tail = prev;
        }

        public boolean isEmpty() {
            return tail == head;
        }
    }

    class KeyNode {
        KeyNode prev;
        KeyNode next;
        int val;
        int key;
        int frequency = 0;

        public KeyNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)


public class No460 {
    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1, 1); // (1,1)
        lfuCache.put(2, 2); // (1,1) + (2,1)
        lfuCache.get(1);   // (1,2) + (2,1)
        lfuCache.put(3, 3); // (1,2) + (3,1)
        lfuCache.get(2);   // (1,2) + (3,1)
        lfuCache.get(3);   // (1,2) + (3,2)
        lfuCache.put(4, 4);
        System.out.println(123);
    }
}
