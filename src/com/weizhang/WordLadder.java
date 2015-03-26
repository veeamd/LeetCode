package com.weizhang;

import java.util.*;


/**
 * Created by Wei Zhang on 3/21/15.
 *
 * Given two words (start and end), and a dictionary,
 * find the length of shortest transformation sequence from start to end, such that:

 Only one letter can be changed at a time
 Each intermediate word must exist in the dictionary
 For example,

 Given:
 start = "hit"
 end = "cog"
 dict = ["hot","dot","dog","lot","log"]
 As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 return its length 5.

 Note:
 Return 0 if there is no such transformation sequence.
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 *
 *
 */
public class WordLadder {
    private String[] words;
    private boolean[] marked;
    private int[] nodeTo;

    private WordGraph wordGraph;

    public int ladderLength(String start, String end, Set<String> dict) {
        int numWords = dict.size();
        words = dict.toArray(new String[numWords]);

        wordGraph = new WordGraph(words);

        marked = new boolean[numWords];
        nodeTo = new int[numWords];
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < numWords; i++) {
            nodeTo[i] = -1;
            marked[i] = false;
            if (words[i].equals(start))
                startIndex = i;
            if (words[i].equals(end))
                endIndex = i;
        }


        bfs(startIndex, endIndex);

        if (nodeTo[endIndex] == -1) {
            return 0;
        } else {
            int jumps = 0;
            while (nodeTo[endIndex] != startIndex) {
                jumps++;
                endIndex = nodeTo[endIndex];
            }
            jumps++;
            int length = jumps + 1;
            return length;
        }
    }

    public void bfs(int start, int end) {
        Queue<Integer> nodeQueue = new LinkedList<Integer>();
        nodeQueue.add(start);
        marked[start] = true;
        LinkedList<Integer>[] adjacents = wordGraph.getAdjacents();
        while (nodeQueue.peek() != null) {
            int nodeIndex = nodeQueue.remove();
            LinkedList<Integer> neighbors = adjacents[nodeIndex];
            for (int neighbor : neighbors) {
                if ( ! marked[neighbor]) {
                    marked[neighbor] = true;
                    nodeQueue.add(neighbor);
                    nodeTo[neighbor] = nodeIndex;
                    if (neighbor == end)
                        return;
                }
            }
        }
    }



    public static void main(String[] args) {
        /*
        dict.add("hot");
        dict.add("dog");
        dict.add("cog");
        dict.add("pot");
        dict.add("dot");
        */
        WordLadder test = new WordLadder();
        Set<String> dict = new HashSet<String>();

        // "hot", "dog", ["hot","dog","cog","pot","dot"]
        dict.add("hot");
        dict.add("dog");
        dict.add("cog");
        dict.add("pot");
        dict.add("dot");
        System.out.println(test.ladderLength("hot", "dog", dict));
    }
}
