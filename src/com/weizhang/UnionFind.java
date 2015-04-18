package com.weizhang;

/**
 * Created by Wei Zhang on 4/18/15.
 *
 * Implementing my own Union Find
 * a Weighted version with path compression
 */
public class UnionFind {
    int[] a;
    int[] size;
    public UnionFind(int n) {
        a = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
            size[i] = 1;
        }
    }

    public void union(int i, int j) {
        int rooti = root(i);
        int rootj = root(j);
        if (rooti == rootj) return;
        if (size[rooti] >= size[rootj]) {
            a[rootj] = rooti;
            size[rooti] = size[rootj] + size[rooti];
        } else {
            a[rooti] = rootj;
            size[rootj] = size[rooti] + size[rootj];
        }
    }

    public boolean connected(int i, int j) {
        return root(i) == root(j);
    }

    public int root(int i) {
        while (a[i] != i) {
            a[i] = a[a[i]];
            i = a[i];
        }
        return i;
    }

    public static void main(String[] args) {
        UnionFind test = new UnionFind(6);
        test.union(1, 2);
        test.union(2, 3);
        test.union(3, 4);

        System.out.println(test.connected(0, 4));
        System.out.println(test.connected(0, 5));
        System.out.println(test.connected(1, 4));
    }
}
