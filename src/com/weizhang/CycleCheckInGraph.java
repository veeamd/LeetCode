package com.weizhang;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei Zhang on 5/14/15.
 */
public class CycleCheckInGraph {
    private int[] edgeTo;
    private boolean[] marked;
    private boolean[] onStack;
    private boolean hasCycle;
    private Graph graph;
    private List<List<Integer>> cycles;
    public CycleCheckInGraph(Graph graph) {
        this.graph = graph;
        int num = graph.numberOfVertices();
        edgeTo = new int[num];
        onStack = new boolean[num];
        marked = new boolean[num];
        cycles = new ArrayList<List<Integer>>();
        for (int i = 0; i < num; i++) {
            if ( ! marked[i]) {
                dfs(i);
            }
        }
    }

    private void dfs(int v) {
        marked[v] = true;
        onStack[v] = true;
        List<Integer> adjList = graph.adjacencyListForVertex(v);
        for (int w : adjList) {
            if ( ! marked[w]) {
                edgeTo[w] = v;
                dfs(w);
            } else if (onStack[w]) {
                hasCycle = true;
                int n = v;
                List<Integer> cyclePath = new ArrayList<Integer>();
                do {
                    cyclePath.add(n);
                    n = edgeTo[n];
                } while (n != w);
                cyclePath.add(w);
                cycles.add(cyclePath);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public List<List<Integer>> getCycles() {
        return cycles;
    }

}
