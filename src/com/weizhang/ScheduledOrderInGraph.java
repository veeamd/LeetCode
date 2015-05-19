package com.weizhang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei Zhang on 5/14/15.
 */
public class ScheduledOrderInGraph {
    private int[] edgeTo;
    private boolean[] marked;
    private boolean[] onStack;
    private boolean hasCycle;
    private Graph graph;
    private int[] order;
    private int numberOfNodesPutInOrder;
    public ScheduledOrderInGraph(Graph graph) {
        this.graph = graph;
        int num = graph.numberOfVertices();
        edgeTo = new int[num];
        onStack = new boolean[num];
        marked = new boolean[num];
        order = new int[num];
        numberOfNodesPutInOrder = 0;
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
            }
        }
        order[numberOfNodesPutInOrder] = v;
        numberOfNodesPutInOrder++;
        onStack[v] = false;
    }

    public boolean hasScheduledOrder() {
        return !hasCycle;
    }

    public int[] getOrder() {
        return order;
    }
}
