package com.qkx.example.lc;
//Design a data structure that supports adding new words and finding if a
//string matches any previously added string.
//
// Implement the WordDictionary class:
//
//
// WordDictionary() Initializes the object.
// void addWord(word) Adds word to the data structure, it can be matched later.
//
// bool search(word) Returns true if there is any string in the data structure
//that matches word or false otherwise. word may contain dots '.' where dots can
//be matched with any letter.
//
//
//
// Example:
//
//
//Input
//["WordDictionary","addWord","addWord","addWord","search","search","search",
//"search"]
//[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
//Output
//[null,null,null,null,false,true,true,true]
//
//Explanation
//WordDictionary wordDictionary = new WordDictionary();
//wordDictionary.addWord("bad");
//wordDictionary.addWord("dad");
//wordDictionary.addWord("mad");
//wordDictionary.search("pad"); // return False
//wordDictionary.search("bad"); // return True
//wordDictionary.search(".ad"); // return True
//wordDictionary.search("b.."); // return True
//
//
//
// Constraints:
//
//
// 1 <= word.length <= 25
// word in addWord consists of lowercase English letters.
// word in search consist of '.' or lowercase English letters.
// There will be at most 3 dots in word for search queries.
// At most 10⁴ calls will be made to addWord and search.
//
//
// Related Topics String Depth-First Search Design Trie 👍 5853 👎 339


import java.util.ArrayList;
import java.util.List;

/**
 *
 * BFS
 * 解答成功:
 * 执行耗时:999 ms,击败了30.91% 的Java用户
 * 内存消耗:85 MB,击败了90.38% 的Java用户
 *
 * DFS
 * 解答成功:
 * 	执行耗时:442 ms,击败了79.55% 的Java用户
 * 	内存消耗:83.7 MB,击败了93.39% 的Java用户
 */
//leetcode submit region begin(Prohibit modification and deletion)
class WordDictionary {

    private WordNode root;

    public WordDictionary() {
        root = new WordNode();
    }

    public void addWord(String word) {
        addWord(word.toCharArray(), 0, root);
    }

    public boolean search(String word) {
        return searchByDFS(word.toCharArray(), 0, root);
    }

    private boolean searchByDFS(char[] arr, int i, WordNode curNode) {
        if (curNode == null) {
            return false;
        }
        if (i == arr.length) {
            return curNode.isLeaf;
        }
        char w = arr[i];
        if (w != '.') {
            return searchByDFS(arr, i + 1, curNode.nodeArr[w - 'a']);
        }
        for (WordNode nextNode : curNode.nodeArr) {
            if (searchByDFS(arr, i + 1, nextNode)) {
                return true;
            }
        }
        return false;
    }

    private boolean searchByBFS(char[] arr, int i, List<WordNode> curNode) {
        if (i == arr.length) {
            for (WordNode leaf : curNode) {
                if (leaf.isLeaf) {
                    return true;
                }
            }
            return false;
        }
        char w = arr[i];
        List<WordNode> next = new ArrayList<>(26);
        for (WordNode node : curNode) {
            if (w == '.') {
                for (WordNode child : node.nodeArr) {
                    if (child != null) {
                        next.add(child);
                    }
                }
            } else {
                if (node.nodeArr[w - 'a'] != null) {
                    next.add(node.nodeArr[w - 'a']);
                }
            }
        }
        if (next.isEmpty()) {
            return false;
        }
        return searchByBFS(arr, i + 1, next);
    }

    private void addWord(char[] arr, int i, WordNode curNode) {
        if (i == arr.length) {
            curNode.isLeaf = true;
            return;
        }
        char w = arr[i];
        // add w
        if (curNode.nodeArr[w - 'a'] == null) {
            curNode.nodeArr[w - 'a'] = new WordNode();
        }
        addWord(arr, i + 1, curNode.nodeArr[w - 'a']);
    }

    class WordNode {
        private WordNode[] nodeArr = new WordNode[26];
        private boolean isLeaf = false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
//leetcode submit region end(Prohibit modification and deletion)

public class No211 {

}
