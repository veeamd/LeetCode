package com.weizhang;

/**
 * Created by Wei Zhang on 4/11/15.
 *
 * Given a string only has space and characters, test if the string has all the 26 characters
 *
 */
public class Pangram {
    static int isPangram(String n) {
        int[] alphabets = new int[26];

        int count = 0;

        for (int i = 0; i < 26; i++) {
            alphabets[i] = 0;
        }

        for (int i = 0; i < n.length(); i++) {
            char c = n.charAt(i);
            if (c != ' ') {
                if (c > 'Z' && alphabets[c - 'a'] == 0) {
                    alphabets[c - 'a'] = 1;
                    count++;
                } else if (c <= 'Z' && alphabets[c - 'A'] == 0)  {
                    alphabets[c - 'A'] = 1;
                    count++;
                }
                if (count == 26) {
                    break;
                }
            }
        }

        if (count == 26) {
            return 1;
        } else {
            return 0;
        }

    }

    public static void main(String[] args) {
        Pangram test = new Pangram();
        System.out.println(test.isPangram("a b cd fghGTghijklmnopqrst Uvwxyz"));
    }
}
