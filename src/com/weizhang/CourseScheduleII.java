package com.weizhang;

/**
 * Created by Wei Zhang on 5/18/15.
 *
 * There are a total of n courses you have to take, labeled from 0 to n - 1.

 Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

 Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

 There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

 For example:

 2, [[1,0]]
 There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

 4, [[1,0],[2,0],[3,1],[3,2]]
 There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2.
 Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3].
 Another correct ordering is[0,2,1,3].
 *
 *
 * This is actually different from what we learn from algorithm class,
 * Algorithm class will have [1, 0] is 1 is the prerequisite of 0;
 * So this is a reversed graph of what we learned from Algorithm class,
 * Which makes it easier.
 *
 * The plan is to use DFS to traverse the graph, add to the result list when a node is done (all adjacent nodes are visited)
 *
 */
public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Graph graph = new Graph(numCourses, prerequisites);
        ScheduledOrderInGraph scheduledOrder = new ScheduledOrderInGraph(graph);
        if (scheduledOrder.hasScheduledOrder()) {
            return scheduledOrder.getOrder();
        } else {
            return new int[0];
        }
    }

    public static void main(String[] args) {
        CourseScheduleII test = new CourseScheduleII();
        int[][] prerequisites = {
                {0, 1},
                {1, 2},
                {2, 3},
                {0, 4},
                {4, 5},
        };
        int[] order = test.findOrder(6, prerequisites);
        for (int node : order) {
            System.out.println(node);
        }

    }
}
