package com.weizhang;

import java.util.List;

/**
 * Created by Wei Zhang on 5/14/15.
 *
 * There are a total of n courses you have to take, labeled from 0 to n - 1.

 Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

 Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 For example:

 2, [[1,0]]
 There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

 2, [[1,0],[0,1]]
 There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

 Note:
 The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 *
 */
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Graph graph = new Graph(numCourses, prerequisites);
        CycleCheckInGraph cc = new CycleCheckInGraph(graph);
        boolean hasCycle = cc.hasCycle();
        if (hasCycle) {
            List<List<Integer>> cycles = cc.getCycles();
            for (List<Integer> cycle : cycles) {
                for (int node : cycle) {
                    System.out.print(node);
                }
                System.out.println();
            }
        }
        return !hasCycle;
    }

    public static void main(String[] args) {
        int[][] prerequisites = {
                {0, 1},
                {1, 2},
                {2, 0},
                {3, 4},
                {4, 5},
                {5, 3}
        };
        CourseSchedule test = new CourseSchedule();
        System.out.println(test.canFinish(6, prerequisites));

    }
}
