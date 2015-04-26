package com.weizhang;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei Zhang on 4/24/15.
 *
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

 For example, given
 s = "leetcode",
 dict = ["leet", "code"].

 Return true because "leetcode" can be segmented as "leet code".
 *
 */
public class WordBreak {
    private String stringToMatch;
    private Set<String> dict;
    public boolean wordBreak(String s, Set<String> wordDict) {
        stringToMatch = s;
        dict = wordDict;
//        return dfs(0);
        return dynamic();

    }

    public boolean dfs(int wordStart) {
        if (wordStart == stringToMatch.length()) { // end case
            return true;
        }
        for (int i = wordStart + 1; i <= stringToMatch.length(); i++) {
            String word = stringToMatch.substring(wordStart, i);
            if (dict.contains(word) && dfs(i)) {
                return true;
            }
        }
        return false;
    }

    // DFS not gona work if there's a lot repeated characters or words. probably exponential
    public boolean dynamic() {
        int length = stringToMatch.length();
        boolean[] f = new boolean[length + 1];
        // when f[x] is marked to be true, means there's a word matching in the dict for characters from x-n to x-1
        // to make sure wordbreakable, we also need to check if f[x-n] is true.
        // start with first character f[0] mark that as true, which means "" (empty string) is matched.
        // so if f[x-n] is true and [x-n, ..., x-1] can be found in the dictionary then mark f[x] is true.
        // in the end, we check f[length] is true or not
        f[0] = true;
        for (int i = 1; i < length + 1; i++) {
            for (int j = i - 1; j >= 0; j--) {
                String word = stringToMatch.substring(j, i);
                if (dict.contains(word) && f[j]) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[length];
    }


    public static void main(String[] args) {
        WordBreak test = new WordBreak();
        Set<String> dict = new HashSet<String>();
        dict.add("a");
        dict.add("aa");
        dict.add("aaa");
        dict.add("aaab");
        System.out.println(test.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", dict));
    }
}
