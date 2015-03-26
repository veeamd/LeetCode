package com.weizhang;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by veeamd on 3/23/15.
 */
public class WordGraph {
    private LinkedList<Integer>[] adjacents; //
    private HashMap<Integer, String> wordMap;
    private int[] nodes;   // index of words;

    public WordGraph(String[] words) {
        int numWord = words.length;
        wordMap = new HashMap<Integer, String>();
        nodes = new int[numWord];
        adjacents = (LinkedList<Integer>[]) new LinkedList[numWord];

        for (int i = 0; i < numWord; i++) {
            nodes[i] = i;
            wordMap.put(i, words[i]);
            adjacents[i] = new LinkedList<Integer>();
        }

        for (int i = 0; i < numWord - 1; i++) {
            for (int j = i + 1; j < numWord; j++) {
                if (connected(words[i], words[j])) {
                    adjacents[i].add(j);
                    adjacents[j].add(i);
                }
            }
        }

    }

    private boolean connected(String v, String w) {
        // assume v.length = w.length
        int diff = 0;
        int index = 0;
        while (index < v.length()) {
            char vc = v.charAt(index);
            char wc = w.charAt(index);

            if (vc != wc) {
                diff++;
            }
            if (diff > 1)
                return false;
            index++;
        }

        return (diff == 1);
    }

    public int[] getNodes() {
        return nodes;
    }

    public LinkedList<Integer>[] getAdjacents() {
        return adjacents;
    }

    public String getWordAtNode(int node) {
        return wordMap.get(node);
    }

}
