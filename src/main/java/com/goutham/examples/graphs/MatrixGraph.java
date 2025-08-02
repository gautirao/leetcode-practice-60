package com.goutham.examples.graphs;

import java.util.ArrayList;
import java.util.List;

public class MatrixGraph {

  int matrix[][];
  List<GraphNode> nodes;

  public MatrixGraph(int size) {
    this.matrix = new int[size][size];
    nodes = new ArrayList<>();
  }

  public void addNode(GraphNode node) {
    nodes.add(node);
  }

  public void addEdge(int row, int col) {
    matrix[row][col] = 1;
  }

  public boolean checkEdge(int row, int col) {
    if (matrix[row][col] == 1) {
      return true;
    } else {
      return false;
    }
  }

  public void print() {
    System.out.print("  ");
    for (GraphNode node : nodes) {
      System.out.print(node.data + " ");
    }
    System.out.println();
    for (int row = 0; row < matrix.length; row++) {
      System.out.print(nodes.get(row).data + " ");
      for (int col = 0; col < matrix[row].length; col++) {
        System.out.print(matrix[row][col] + " ");
      }
      System.out.println();
    }
  }
}
