package com.weizhang;

/**
 * Created by Wei Zhang on 5/7/15.
 */
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            TrieNode child = p.getChildAt(index);
            if (child == null) {
                child = p.addChildAt(index);
            }
            p = child;
        }
        p.setVal(1);
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            TrieNode child = p.getChildAt(index);
            if (child == null) {
                return false;
            }
            p = child;
        }
        return p.getVal() == 1;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode p = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            TrieNode child = p.getChildAt(index);
            if (child == null) {
                return false;
            }
            p = child;
        }
        return true;
    }

    public static void main(String[] args) {
        Trie test = new Trie();
        test.insert("abc");
        test.insert("abd");
        test.insert("abe");
        test.insert("abf");
        test.insert("abgh");
        test.insert("abc");
        System.out.println(test.startsWith("abc"));

        System.out.println(test.startsWith("abz"));
        System.out.println(test.search("abz"));
        System.out.println(test.search("abd"));
    }
}
