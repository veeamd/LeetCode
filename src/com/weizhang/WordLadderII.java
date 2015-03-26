package com.weizhang;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Wei Zhang on 3/23/15.
 *
 *
 * Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

 Only one letter can be changed at a time
 Each intermediate word must exist in the dictionary
 For example,

 Given:
 start = "hit"
 end = "cog"
 dict = ["hot","dot","dog","lot","log"]
 Return
 [
 ["hit","hot","dot","dog","cog"],
 ["hit","hot","lot","log","cog"]
 ]
 Note:
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 *
 */
public class WordLadderII {
    private String[] words;
    private int[] marked;   // shortest path steps

    private WordGraph wordGraph;

    private ArrayList<Stack<Integer>> nodePaths;  // to save temporary results;

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        int numWords = dict.size();
        words = dict.toArray(new String[numWords]);

        wordGraph = new WordGraph(words);

        marked = new int[numWords];
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < numWords; i++) {
            marked[i] = -1;
            if (words[i].equals(start))
                startIndex = i;
            if (words[i].equals(end))
                endIndex = i;
        }

        // use BFS to mark every node with steps from start node, when hit end, stop
        bfs(startIndex, endIndex);

        nodePaths = new ArrayList<Stack<Integer>>();
        Stack<Integer> firstPath = new Stack<Integer>();
        firstPath.push(endIndex);
        reverseDFS(startIndex, endIndex, firstPath);


        // replace node index with string
        List<List<String>> wordLadders = new LinkedList<List<String >>();
        for (Stack<Integer> path : nodePaths) {
            List<String> wordLadder = new LinkedList<String>();
            while ( ! path.empty()) {
                wordLadder.add(wordGraph.getWordAtNode(path.pop()));
            }
            wordLadders.add(wordLadder);
        }
        return wordLadders;

    }

    // use BFS to mark every node with steps from start node, when hit end, stop
    public void bfs(int start, int end) {
        Queue<Integer> nodeQueue = new LinkedList<Integer>();
        nodeQueue.add(start);
        marked[start] = 0;
        int step = 0;
        LinkedList<Integer>[] adjacents = wordGraph.getAdjacents();
        while (nodeQueue.peek() != null) {
            int nodeIndex = nodeQueue.remove();
            step = marked[nodeIndex] + 1;
            LinkedList<Integer> neighbors = adjacents[nodeIndex];
            for (int neighbor : neighbors) {
                if (marked[neighbor] == -1) {
                    marked[neighbor] = step;
                    nodeQueue.add(neighbor);
                    if (neighbor == end) {
                        return;
                    }
                }
            }
        }
    }
/*
    public Queue<List<Integer>> reverseBFS(int end) {
        // use BFS to generate all the path from end to start, wherever branches, clone the current path
        // use DFS could be easier?
        int step = marked[end];
        int steps = step + 1;  // total steps
        Queue<List<Integer>> pathQueue = new LinkedList<List<Integer>>();
        if (step > 0) {

            LinkedList<Integer>[] adjacents = wordGraph.getAdjacents();


            List<Integer> firstPath = new ArrayList<Integer>(steps);
            for (int i = 0; i < steps; i++) {
                firstPath.add(0);
            }

            firstPath.set(step, end);
            pathQueue.add(firstPath);
            int totalBranches = 1;
            int branchDequeueNum = 0;
            while (pathQueue.peek() != null && step > 0) {
                List<Integer> path = pathQueue.remove();

                int currentNode = path.get(step);
                List<Integer> nodeNeighbors = adjacents[currentNode];

                int subbranchCount = 0;
                for (int neighbor : nodeNeighbors) {
                    if (marked[neighbor] < step && marked[neighbor] >= 0) {
                        if (subbranchCount == 0) {
                            path.set(step - 1, neighbor);
                            pathQueue.add(path);
                        } else {
                            // clone the path
                            List<Integer> branchedPath = clonedPath(path);
                            branchedPath.set(step - 1, neighbor);
                            pathQueue.add(branchedPath);
                        }
                        subbranchCount++;
                    }
                }

                branchDequeueNum++;
                if (branchDequeueNum == totalBranches) {
                    step--;
                    branchDequeueNum = 0;
                    if (subbranchCount > 0)
                        totalBranches += subbranchCount - 1;
                }
            }


        }

        return pathQueue;
    }
*/

    // from branchNode to startNode
    public void reverseDFS(int startNode, int branchNode, Stack<Integer> currentPath) {
        List<Integer>[] adjacents = wordGraph.getAdjacents();
        List<Integer> nodeNeighbors = adjacents[branchNode];

        for (int nodeIndex : nodeNeighbors) {
            if (nodeIndex == startNode) {
                Stack<Integer> newPath = (Stack<Integer>) currentPath.clone();
                newPath.push(nodeIndex);
                nodePaths.add(newPath);
            }
            else if (marked[nodeIndex] > 0 && marked[branchNode] > marked[nodeIndex]) {
                Stack<Integer> newPath = (Stack<Integer>) currentPath.clone();
                newPath.push(nodeIndex);
                reverseDFS(startNode, nodeIndex, newPath);
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
        WordLadderII test = new WordLadderII();
        Set<String> dict = new HashSet<String>();

        // "hot", "dog", ["hot","dog","cog","pot","dot"]
        dict.add("hot");
        dict.add("dog");
        dict.add("cog");
        dict.add("lot");
        dict.add("log");
        dict.add("pot");
        dict.add("dot");
        System.out.print(test.findLadders("hot", "cog", dict));
    }
}
