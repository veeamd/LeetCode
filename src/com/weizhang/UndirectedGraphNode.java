package com.weizhang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei Zhang on 3/25/15.
 */
public class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
