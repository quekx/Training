package com.qkx.example.lc.achive.hard;

import java.util.HashMap;
import java.util.Map;

public class No432 {
    //Design a data structure to store the strings' count with the ability to
//return the strings with minimum and maximum counts.
//
// Implement the AllOne class:
//
//
// AllOne() Initializes the object of the data structure.
// inc(String key) Increments the count of the string key by 1. If key does not
//exist in the data structure, insert it with count 1.
// dec(String key) Decrements the count of the string key by 1. If the count of
//key is 0 after the decrement, remove it from the data structure. It is
//guaranteed that key exists in the data structure before the decrement.
// getMaxKey() Returns one of the keys with the maximal count. If no element
//exists, return an empty string "".
// getMinKey() Returns one of the keys with the minimum count. If no element
//exists, return an empty string "".
//
//
// Note that each function must run in O(1) average time complexity.
//
//
// Example 1:
//
//
//Input
//["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey",
//"getMinKey"]
//[[], ["hello"], ["hello"], [], [], ["leet"], [], []]
//Output
//[null, null, null, "hello", "hello", null, "hello", "leet"]
//
//Explanation
//AllOne allOne = new AllOne();
//allOne.inc("hello");
//allOne.inc("hello");
//allOne.getMaxKey(); // return "hello"
//allOne.getMinKey(); // return "hello"
//allOne.inc("leet");
//allOne.getMaxKey(); // return "hello"
//allOne.getMinKey(); // return "leet"
//
//
//
// Constraints:
//
//
// 1 <= key.length <= 10
// key consists of lowercase English letters.
// It is guaranteed that for each call to dec, key is existing in the data
//structure.
// At most 5 * 10⁴ calls will be made to inc, dec, getMaxKey, and getMinKey.
//
//
// Related Topics Hash Table Linked List Design Doubly-Linked List 👍 1343 👎 15
//4


    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 解答成功:
     * 	执行耗时:53 ms,击败了76.39% 的Java用户
     * 	内存消耗:58.6 MB,击败了67.71% 的Java用户
     * 	有序散列表
     */
    class AllOne {

        Entry head = new Entry();
        Entry tail = head;

        Map<String, Node> counter = new HashMap<>();

        Map<Integer, Entry> entryMap = new HashMap<>();


        private void addEntry(Entry prev, Entry cur) {
            cur.prev = prev;
            cur.next = prev.next;
            prev.next = cur;
            if (cur.next != null) {
                cur.next.prev = cur;
            }
            if (cur.next == null) {
                tail = cur;
            }
            entryMap.put(cur.count, cur);
        }

        private void removeEntry(Entry cur) {
            cur.prev.next = cur.next;
            if (cur.next != null) {
                cur.next.prev = cur.prev;
            }
            if (cur.next == null) {
                tail = cur.prev;
            }
            entryMap.remove(cur.count);
        }

        private void addNode(Entry entry, Node node) {
            Node root = entry.root;
            node.prev = root;
            node.next = root.next;
            root.next = node;
            if (node.next != null) {
                node.next.prev = node;
            }
        }

        private void removeNode(Node node) {
            node.prev.next = node.next;
            if (node.next != null) {
                node.next.prev = node.prev;
            }
        }

        private boolean isEmpty(Entry entry) {
            return entry.root.next == null;
        }

        public AllOne() {
        }

        public void inc(String key) {
            Node cur = counter.get(key);
            if (cur == null) {
                cur = new Node();
                cur.key = key;
                cur.count = 1;
                counter.put(key, cur);
                Entry entry = entryMap.get(1);
                if (entry == null) {
                    entry = new Entry();
                    entry.count = 1;
                    addEntry(head, entry);
                }
                addNode(entry, cur);
            } else {
                removeNode(cur);
                Entry curEntry = entryMap.get(cur.count);
                cur.count++;
                Entry nextEntry = entryMap.get(cur.count);
                if (nextEntry == null) {
                    nextEntry = new Entry();
                    nextEntry.count = cur.count;
                    addEntry(curEntry, nextEntry);
                }
                addNode(nextEntry, cur);
                if (isEmpty(curEntry)) {
                    removeEntry(curEntry);
                }
            }
        }

        public void dec(String key) {
            Node cur = counter.get(key);
            removeNode(cur);
            Entry curEntry = entryMap.get(cur.count);
            cur.count--;
            if (cur.count == 0) {
                counter.remove(cur.key);
            } else {
                Entry prevEntry = entryMap.get(cur.count);
                if (prevEntry == null) {
                    prevEntry = new Entry();
                    prevEntry.count = cur.count;
                    addEntry(curEntry.prev, prevEntry);
                }
                addNode(prevEntry, cur);
            }
            if (isEmpty(curEntry)) {
                removeEntry(curEntry);
            }
        }

        public String getMaxKey() {
            if (head == tail) {
                return "";
            }
            return tail.root.next.key;
        }

        public String getMinKey() {
            if (head == tail) {
                return "";
            }
            return head.next.root.next.key;
        }

        class Entry {
            Entry prev;
            Entry next;
            int count;
            Node root = new Node();
        }

        class Node {
            Node prev;
            Node next;
            String key;
            int count;
        }
    }

    /**
     * Your AllOne object will be instantiated and called as such:
     * AllOne obj = new AllOne();
     * obj.inc(key);
     * obj.dec(key);
     * String param_3 = obj.getMaxKey();
     * String param_4 = obj.getMinKey();
     */
    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        AllOne allOne = new No432().new AllOne();
        allOne.inc("a");
        allOne.inc("b");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
        allOne.inc("a");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
        allOne.dec("a");
    }
}
