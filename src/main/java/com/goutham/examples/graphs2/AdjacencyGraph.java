package com.goutham.examples.graphs2;

import java.util.*;
//undirected graph implemented using an adjacency list

public class AdjacencyGraph {

  private Map<String, List<String>> adjList = new HashMap<>();

  public static void main(String[] args) {

    /*
     *               A
     *             /   \
     *            B     C
     *           /       \
     *          D         E
     *
     *       BFS : A,B,C,D,E
     *       DFS:  A,B,D,C,E
     * */

    AdjacencyGraph g = new AdjacencyGraph();
    g.addVertex("A");
    g.addVertex("B");
    g.addVertex("C");
    g.addVertex("D");
    g.addVertex("E");

    g.addEdge("A", "B");
    g.addEdge("A", "C");
    g.addEdge("B", "D");
    g.addEdge("C", "E");

    g.printGraph();
    g.bfs("A");
    g.dfs("A");

  }

  public void addVertex(String label) {
    adjList.putIfAbsent(label, new ArrayList<>());
  }

  public void removeVertex(String label) {
    adjList.values().forEach(e -> e.remove(label));
    adjList.remove(label);
  }

  public void addEdge(String from, String to) {
    adjList.get(from).add(to);
    adjList.get(to).add(from); // omit this line for directed graph
  }

  public void removeEdge(String from, String to) {
    adjList.get(from).remove(to);
    adjList.get(to).remove(from); // omit this line for directed graph
  }

  // has connection between edges
  public boolean hasEdge(String from, String to) {
    return adjList.containsKey(from) && adjList.get(from).contains(to);
  }

  public void printGraph() {
    for (String vertex : adjList.keySet()) {
      System.out.println(vertex + ": " + adjList.get(vertex));
    }
  }

  public void bfs(String start) {
    Set<String> visited = new HashSet<>();
    Queue<String> queue = new LinkedList<>();
    visited.add(start);
    queue.add(start);

    System.out.println("BFS traversal");
    while (!queue.isEmpty()) {
      String vertex = queue.poll();
      System.out.println(vertex + " ");

      for (String neighbour : adjList.get(vertex)) {
        if (!visited.contains(neighbour)) {
          visited.add(neighbour);
          queue.add(neighbour);
        }
      }
    }
  }

  private void dfs(String start) {
    Set<String> visited = new HashSet<>();
    System.out.println("DFS traversal: ");
    dfsRecursive(start, visited);
    System.out.println("done");
  }

  private void dfsRecursive(String vertex, Set<String> visited) {
    visited.add(vertex);
    System.out.println(vertex + " ");
    for (String neighbor : adjList.get(vertex)) {
      if (!visited.contains(neighbor)) {
        dfsRecursive(neighbor, visited);
      }
    }
  }
}
