package com.weizhang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei Zhang on 5/14/15.
 *
 * Implementing my own Graph class
 *
 */
public class Graph {
    private List<Integer>[] adj;
    private int num;
    public Graph(int numOfVertices, int[][] edges) {
        num = numOfVertices;
        adj = (ArrayList<Integer>[]) new ArrayList[numOfVertices];
        for (int i = 0; i < numOfVertices; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            int first = edge[0];
            int second = edge[1];
            adj[first].add(second);
        }
    }

    public List<Integer> adjacencyListForVertex(int vet) {
        return adj[vet];
    }

    public int numberOfVertices() {
        return num;
    }

}
