package com.goutham.examples.graphs;

public class AdhacencyGraphDemo {
  /*
   *  Adjacency list = an arraylist of linked lists
   *  Each linked list has an unique node at the head
   * all adjacent neighbours are added to the node's linked list
   * runtime complexity to check an edge is O(v)
   * Space complexity : O(v + e)
   * */

  public static void main(String[] args) {

    AdjacencyGraph graph = new AdjacencyGraph();
    graph.addNode(new GraphNode('A')); // 0
    graph.addNode(new GraphNode('B')); // 1
    graph.addNode(new GraphNode('C')); // 2
    graph.addNode(new GraphNode('D')); // 3
    graph.addNode(new GraphNode('E')); // 4

    graph.addEdge(0, 1);
    graph.addEdge(1, 2);
    graph.addEdge(1, 4);
    graph.addEdge(2, 3);
    graph.addEdge(2, 4);
    graph.addEdge(4, 0);
    graph.addEdge(4, 2);

    graph.print();
  }
}
