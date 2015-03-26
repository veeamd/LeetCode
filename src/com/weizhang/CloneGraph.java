package com.weizhang;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Wei Zhang on 3/25/15.
 *
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


 OJ's undirected graph serialization:
 Nodes are labeled uniquely.

 We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 As an example, consider the serialized graph {0,1,2#1,2#2,2}.

 The graph has a total of three nodes, and therefore contains three parts as separated by #.

 First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 Second node is labeled as 1. Connect node 1 to node 2.
 Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 Visually, the graph looks like the following:

    1
   / \
  /   \
 0 --- 2
      / \
      \_/

 *
 */
public class CloneGraph {
    private HashMap<Integer, UndirectedGraphNode> marked;
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        // Use DFS/BFS to clone down the path
        // use a marked[] array to mark the visited nodes to prevent infinite loop
        if (node == null) return null;

        marked = new HashMap<Integer, UndirectedGraphNode>();

        return dfsClone(node);
    }

    private UndirectedGraphNode dfsClone(UndirectedGraphNode node) {

        if (marked.containsKey(node.label)) {
            return marked.get(node.label);
        }

        UndirectedGraphNode clonedNode = new UndirectedGraphNode(node.label);
        marked.put(clonedNode.label, clonedNode);

        for (UndirectedGraphNode neighbor : node.neighbors) {
            clonedNode.neighbors.add(dfsClone(neighbor));
        }

        return clonedNode;

    }

    public static void main(String[] args) {
        UndirectedGraphNode testGraph = new UndirectedGraphNode(-1);
        UndirectedGraphNode node = new UndirectedGraphNode(1);
        testGraph.neighbors.add(node);
        CloneGraph cg = new CloneGraph();
        cg.cloneGraph(testGraph);
    }
}
