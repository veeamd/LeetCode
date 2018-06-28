package com.weizhang;
import java.util.ArrayList;

/**
 * Google foo.bar
 *
 Don't Get Volunteered!
 ======================

 As a henchman on Commander Lambda's space station, you're expected to be resourceful, smart, and a quick thinker.
 It's not easy building a doomsday device and capturing bunnies at the same time, after all!
 In order to make sure that everyone working for her is sufficiently quick-witted,
 Commander Lambda has installed new flooring outside the henchman dormitories.
 It looks like a chessboard, and every morning and evening you have to solve a new movement puzzle in order to cross the floor.
 That would be fine if you got to be the rook or the queen, but instead, you have to be the knight.
 Worse, if you take too much time solving the puzzle, you get "volunteered" as a test subject for the LAMBCHOP doomsday device!

 To help yourself get to and from your bunk every day, write a function called answer(src, dest) which takes in two parameters:
 the source square, on which you start, and the destination square, which is where you need to land to solve the puzzle.
 The function should return an integer representing the smallest number of moves it will take for you to travel from the source
 square to the destination square using a chess knight's moves (that is, two squares in any direction immediately followed by
 one square perpendicular to that direction, or vice versa, in an "L" shape).  Both the source and destination squares will
 be an integer between 0 and 63, inclusive, and are numbered like the example chessboard below:

 -------------------------
 | 0| 1| 2| 3| 4| 5| 6| 7|
 -------------------------
 | 8| 9|10|11|12|13|14|15|
 -------------------------
 |16|17|18|19|20|21|22|23|
 -------------------------
 |24|25|26|27|28|29|30|31|
 -------------------------
 |32|33|34|35|36|37|38|39|
 -------------------------
 |40|41|42|43|44|45|46|47|
 -------------------------
 |48|49|50|51|52|53|54|55|
 -------------------------
 |56|57|58|59|60|61|62|63|
 -------------------------

 Languages
 =========

 To provide a Python solution, edit solution.py
 To provide a Java solution, edit solution.java

 Test cases
 ==========

 Inputs:
 (int) src = 19
 (int) dest = 36
 Output:
 (int) 1

 Inputs:
 (int) src = 0
 (int) dest = 1
 Output:
 (int) 3
 */


public class DontGetVolenteered {
    // breadth first
//    public static int answer(int src, int dest) {
//
//        ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
//
//        ArrayList<Integer> initialPath = new ArrayList<Integer>();
//        initialPath.add(src);
//        paths.add(initialPath);
//
//        while (paths.size() > 0) {
//            ArrayList<ArrayList<Integer>> pathsAux = new ArrayList<ArrayList<Integer>>();
//
//            for (ArrayList<Integer> path: paths) {
//                int lastNode = path.get(path.size() - 1);
//                for (int node: connections(lastNode)) {
//                    if (path.contains(node)) {
//                        continue;
//                    }
//                    ArrayList<Integer> newPath = (ArrayList<Integer>)path.clone();
//                    newPath.add(node);
//                    if (node == dest) {
//                        return newPath.size() - 1;  // size - 1 = moves
//                    }
//                    pathsAux.add(newPath);
//                }
//            }
//            paths = pathsAux;
//        }
//
//        // if programmed correctly, this line will never be called
//        return 0;
//    }

    // depth first
    public static int answer(int src, int dest) {
        if (src == dest) {
            return 0;
        }
        ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();

        ArrayList<Integer> initialPath = new ArrayList<Integer>();
        initialPath.add(src);

        findPath(dest, initialPath, paths);

        int max = Integer.MAX_VALUE;
        for (ArrayList<Integer> path: paths) {
            if (path.size() < max) {
                max = path.size();
            }
        }
        return max - 1;
    }

    public static void findPath(int dest, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> paths) {
        int lastNode = path.get(path.size() - 1);
        for (int node : connections(lastNode)) {
            if (path.contains(node)) {
                continue;
            }
            ArrayList<Integer> newPath = (ArrayList<Integer>) path.clone();
            newPath.add(node);
            if (node == dest) {
                paths.add(newPath);
                return;
            }
            if (newPath.size() > 8) {
                return;
            }
            findPath(dest, newPath, paths);
        }
    }

    public static int[] connections(int i) {
        ArrayList<Integer> connections = new ArrayList<Integer>();
        int m = i - 16;
        if (m >= 0) {
            if ((m % 8) - 1 >= 0) {
                connections.add(m-1);
            }
            if ((m % 8) + 1 < 8) {
                connections.add(m+1);
            }
        }
        m = i + 16;
        if (m < 64) {
            if ((m % 8) - 1 >= 0) {
                connections.add(m-1);
            }
            if ((m % 8) + 1 < 8) {
                connections.add(m+1);
            }
        }

        if ((i % 8) + 2 < 8) {
            int n = i + 2;
            if (n - 8 >= 0) {
                connections.add(n-8);
            }
            if (n + 8 < 64) {
                connections.add(n+8);
            }
        }
        if ((i % 8) - 2 >= 0) {
            int n = i - 2;
            if (n - 8 >= 0) {
                connections.add(n-8);
            }
            if (n + 8 < 64) {
                connections.add(n+8);
            }
        }
        int[] ret = new int[connections.size()];
        for (int j = 0; j < connections.size(); j++) {
            ret[j] = connections.get(j);
        }
        return ret;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 64; i++) {
            System.out.print(i);
            System.out.print(": ");
            int[] connections = connections(i);
            for (int j = 0; j < connections.length; j++) {
                System.out.print(connections[j]);
                System.out.print(", ");
            }
            System.out.println();
        }

//        System.out.println(answer(0, 1));
    }


}

/*
public class DontGetVolenteered {
    public static int answer(int src, int dest) {

        ArrayList<ArrayList<Node>> paths = new ArrayList<ArrayList<Node>>();

        Node[] allNodes = allNodes();
        Node start = allNodes[src];
        Node end = allNodes[dest];
        ArrayList<Node> initialPath = new ArrayList<Node>();
        initialPath.add(start);
        paths.add(initialPath);

        while (paths.size() > 0) {
            ArrayList<ArrayList<Node>> pathsAux = new ArrayList<ArrayList<Node>>();

            for (ArrayList<Node> path: paths) {
                Node lastNode = path.get(path.size() - 1);
                for (Node node: lastNode.connections) {
                    if (path.contains(node)) {
                        continue;
                    }
                    ArrayList<Node> newPath = (ArrayList<Node>)path.clone();
                    newPath.add(node);
                    if (node == end) {
                        return newPath.size() - 1;  // size - 1 = moves
                    }
                    pathsAux.add(newPath);
                }
            }
            paths = pathsAux;
        }

        // if programmed correctly, this line will never be called
        return 0;
    }

    public static Node[] allNodes() {
        int nodeCount = 64;
        Node[] allNodes = new Node[nodeCount];
        // initialize all nodes
        for (int i = 0; i < nodeCount; i++) {
            allNodes[i] = new Node(i);
        }
        // add all connections
        for (int i = 0; i < nodeCount; i++) {
            Node node = allNodes[i];
            ArrayList<Node> connections = new ArrayList<Node>();
            int m = i - 16;
            if (m >= 0) {
                if ((m % 8) - 1 >= 0) {
                    connections.add(allNodes[m-1]);
                }
                if ((m % 8) + 1 < 8) {
                    connections.add(allNodes[m+1]);
                }
            }
            m = i + 16;
            if (m < 64) {
                if ((m % 8) - 1 >= 0) {
                    connections.add(allNodes[m-1]);
                }
                if ((m % 8) + 1 < 8) {
                    connections.add(allNodes[m+1]);
                }
            }

            if ((i % 8) + 2 < 8) {
                int n = i + 2;
                if (n - 8 >= 0) {
                    connections.add(allNodes[n-8]);
                }
                if (n + 8 < 64) {
                    connections.add(allNodes[n+8]);
                }
            }
            if ((i % 8) - 2 >= 0) {
                int n = i - 2;
                if (n - 8 >= 0) {
                    connections.add(allNodes[n-8]);
                }
                if (n + 8 < 64) {
                    connections.add(allNodes[n+8]);
                }
            }
            node.connections = connections.toArray(new Node[connections.size()]);
        }

        return allNodes;
    }


    static class Node {
        public int value;
        public Node[] connections;

        public Node(int value) {
            this.value = value;
        }

        public String toString() {
            return "{" + getClass().getSimpleName() + "[" + value + "]" + "}";
        }
    }

    public static void main(String[] args) {
//        Node[] allNodes = allNodes();
//        for (Node n: allNodes) {
//            System.out.print(n.toString() + ": ");
//            for (Node c: n.connections) {
//                System.out.print(c.toString() + ", ");
//            }
//            System.out.println();
//        }
        System.out.println(answer(0, 1));
    }


}


*/