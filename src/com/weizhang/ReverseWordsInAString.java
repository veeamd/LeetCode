package com.weizhang;

/**
 * Created by Wei Zhang on 4/16/15.
 *
 * Given an input string, reverse the string word by word.

 For example,
 Given s = "the sky is blue",
 return "blue is sky the".

 Update (2015-02-12):
 For C programmers: Try to solve it in-place in O(1) space.

 click to show clarification.

 Clarification:
 What constitutes a word?
 A sequence of non-space characters constitutes a word.
 Could the input string contain leading or trailing spaces?
 Yes. However, your reversed string should not contain leading or trailing spaces.
 How about multiple spaces between two words?
 Reduce them to a single space in the reversed string.
 *
 */
public class ReverseWordsInAString {
    public String reverseWords(String s) {
        char[] cs = new char[s.length()];
        int p = s.length() - 1;
        boolean didJustSeeSpace = true;
        int length = 0;
        while (p >= 0) {
            char c = s.charAt(p);
            if (didJustSeeSpace) {
                if (c != ' ') {
                    didJustSeeSpace = false;
                    cs[length] = c;
                    length++;
                }
            } else {
                cs[length] = c;
                length++;
                if (c == ' ') {
                    didJustSeeSpace = true;
                }
            }
            p--;
        }
        if (length == 0)
            return "";
        length--;
        if (cs[length] == ' ')
            length--;
        // length is the end of the string
        int endIndex = length;
        int swapStart = 0;
        int swapEnd = 0;
        while (swapEnd <= endIndex){
            if (cs[swapEnd] != ' ') swapEnd++;
            else if (cs[swapEnd] == ' ' || swapEnd == endIndex){
                reverseString(cs, swapStart, swapEnd - 1);
                swapStart = swapEnd + 1;
                swapEnd = swapStart;
            }
        }
        return new String(cs, 0, endIndex + 1);
    }

    public void reverseString(char[] s, int lo, int hi) {
        while (lo < hi) {
            char t = s[lo];
            s[lo] = s[hi];
            s[hi] = t;
            lo++;
            hi--;
        }
    }

    public static void main(String[] args) {
        ReverseWordsInAString test = new ReverseWordsInAString();
        System.out.println(test.reverseWords(" wo menfgfe shi    ge  sha bi "));
        System.out.println(test.reverseWords("  "));
        System.out.println(test.reverseWords("aaa  aa b"));
        System.out.println(test.reverseWords("b"));
        System.out.println(test.reverseWords(" b"));
        System.out.println(test.reverseWords("b "));
    }
}
