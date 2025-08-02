package com.goutham.examples.graphs;

public class MatrixGraphDemo {
  /*
   *   unidrected graph        A->B->C->D->A
   *  the graph can represented by 4X4 matrix , 1 indicates an edge between two nodes , else 0
   *           A  B  C  D
   *           0  1  2  3
   *      A 0  0  1  0  0
   *      B 1  0  0  1  0
   *      C 2  0  0  0  1
   *      D 3  1  0  0  0
   *
   *  time complexity is O(1)  same for every node , we just need the index
   *  space complexity is O(n^2)   if we have 10 nodes , we need create a matrix of 10 X 10 = 10^2 array items
   * */
  public static void main(String[] args) {
    MatrixGraph graph = new MatrixGraph(4);
    graph.addNode(new GraphNode('A'));
    graph.addNode(new GraphNode('B'));
    graph.addNode(new GraphNode('C'));
    graph.addNode(new GraphNode('D'));

    graph.addEdge(0, 1);
    graph.addEdge(1, 2);
    graph.addEdge(2, 3);
    graph.addEdge(3, 0);

    graph.print();
  }
}
