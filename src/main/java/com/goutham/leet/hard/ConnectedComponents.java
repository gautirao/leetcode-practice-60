package com.goutham.leet.hard;
/*
Number of Connected Components in an Undirected Graph
Question

You are given:

n nodes numbered from 0 to n - 1
an array edges, where edges[i] = [a, b] means node a is connected to node b
the graph is undirected, so if a connects to b, then b also connects to a

Return the number of separate connected components in the graph.

Example
n = 5

edges = [
    [0,1],
    [1,2],
    [3,4]
]

Visually:

0 ----- 1 ----- 2

3 ----- 4

There are two separate groups and no connections between them:

Component 1 = {0, 1, 2}
Component 2 = {3, 4}

Therefore:

Answer = 2

* */

import java.util.ArrayList;
import java.util.List;

public class ConnectedComponents {
/*
Key idea This is almost the same pattern as Number of Islands.

NUMBER OF ISLANDS

find unvisited land
       ↓
new island
       ↓
explore all connected land
       ↓
mark visited

Here CONNECTED COMPONENTS:

Find unvisited node
        ↓
new connected component
        ↓
explore every node connected to it
        ↓
mark everything visited

The important observation is:
Every time we encounter a node that has not been visited yet, it must belong to a new connected component.
Then we use DFS to visit its entire group.
* */

    public int countComponents(int n, int[][] edges) {

        //Step 1 : build Adjacency List
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        //Step 2 : build graph by adding edges in both directions
        for(int[] edge : edges){
            int nodeA = edge[0];
            int nodeB = edge[1];

            graph.get(nodeA).add(nodeB);
            graph.get(nodeB).add(nodeA);

        }

        boolean[] visited = new boolean[n];

        int components = 0;

        for (int node=0; node<n; node++){
            if(!visited[node]){
                components++;

                dfs(graph, visited, node);
            }
        }

    return components;
    }

    private void dfs(List<List<Integer>> graph, boolean[] visited, int node) {

        visited[node] = true;

        for (int neighbour : graph.get(node)){
            if(!visited[neighbour]){
                dfs(graph, visited, neighbour);
            }
        }
    }


    public static void main(String[] args) {
        ConnectedComponents solution = new ConnectedComponents();


        // TEST 1
        // 0 ---- 1 ---- 2
        //
        // 3 ---- 4
        //
        // Two separate groups

        int[][] edges1 = {
                {0, 1},
                {1, 2},
                {3, 4}
        };

        System.out.println("Test 1");
        System.out.println("Expected: 2");
        System.out.println("Actual:   " + solution.countComponents(5, edges1));
        System.out.println();


        // TEST 2
        //
        // 0 ---- 1 ---- 2 ---- 3 ---- 4
        //
        // Everything is connected

        int[][] edges2 = {
                {0, 1},
                {1, 2},
                {2, 3},
                {3, 4}
        };

        System.out.println("Test 2");
        System.out.println("Expected: 1");
        System.out.println("Actual:   " + solution.countComponents(5, edges2));
        System.out.println();


        // TEST 3
        //
        // 0     1     2
        //
        // No connections at all.
        // Every node is its own component.

        int[][] edges3 = {};

        System.out.println("Test 3");
        System.out.println("Expected: 3");
        System.out.println("Actual:   " + solution.countComponents(3, edges3));
        System.out.println();


        // TEST 4
        //
        // 0 ---- 1
        //
        // 2
        //
        // 3 ---- 4
        //
        // Three separate groups

        int[][] edges4 = {
                {0, 1},
                {3, 4}
        };

        System.out.println("Test 4");
        System.out.println("Expected: 3");
        System.out.println("Actual:   " + solution.countComponents(5, edges4));
        System.out.println();


        // TEST 5
        //
        //     1
        //    / \
        //   0   2
        //       |
        //       3
        //
        // All connected

        int[][] edges5 = {
                {0, 1},
                {1, 2},
                {2, 3},
                {0, 2}
        };

        System.out.println("Test 5");
        System.out.println("Expected: 1");
        System.out.println("Actual:   " + solution.countComponents(4, edges5));
    }

}
