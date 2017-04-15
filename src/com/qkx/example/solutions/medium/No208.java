package com.qkx.example.solutions.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qkx on 17/4/15.
 */
public class No208 {
    public class Trie {
        private TrieNode root;

        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                int index = c - 'a';
                TrieNode next = p.children[index];
                if (next == null) {
                    next = new TrieNode();
                    p.children[index] = next;
                }

                p = next;
            }
            p.isEnd = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                int index = c - 'a';
                TrieNode next = p.children[index];
                if (next == null) return false;

                p = next;
            }

            return p.isEnd;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode p = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);

                int index = c - 'a';
                TrieNode next = p.children[index];
                if (next == null) return false;

                p = next;
            }

            return true;
        }

        class TrieNode {
            TrieNode[] children;
            boolean isEnd;

            public TrieNode() {
                children = new TrieNode[26];
                isEnd = false;
            }
        }
    }
}
