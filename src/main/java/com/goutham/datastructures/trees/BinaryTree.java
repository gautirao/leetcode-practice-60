package com.goutham.datastructures.trees;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

  TreeNode root;

  public BinaryTree() {
    this.root = null;
  }

  public static void main(String[] ar) {
    BinaryTree tree = new BinaryTree();
    tree.insert(50);
    tree.insert(30);
    tree.insert(70);
    tree.insert(20);
    tree.insert(40);
    tree.insert(60);
    tree.insert(80);

    // builds a tree with below structure
    /*
                    50
                    /  \
                  30    70
                 / \    / \
               20 40  60 80
    */

    tree.inOrderTraversal();
    tree.preorderTraversal();
    tree.postOrderTraversal();
    tree.levelOrderTraversal();
  }

  public void insert(int value) {
    root = insertRec(root, value);
  }

  private TreeNode insertRec(TreeNode root, int value) {
    if (root == null) {
      return new TreeNode(value);
    }
    if (value < root.val) {
      root.left = insertRec(root.left, value);
    } else if (value > root.val) {
      root.right = insertRec(root.right, value);
    }
    return root;
  }

  // Visit nodes level by level from top to bottom, left to right.
  // Implemented using a queue; useful for finding shortest paths or printing tree by levels.
  public void levelOrderTraversal() {
    System.out.println("Level Order Traversal ");
    levelOrderTraversal(root);
  }

  private void levelOrderTraversal(TreeNode root) {
    if (root == null) return;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
      TreeNode current = queue.poll();
      System.out.println(current.val + "");
      if (current.left != null) queue.add(current.left);
      if (current.right != null) queue.add(current.right);
    }
  }

  // Visit the left subtree, then the root node, then the right subtree.
  // For binary search trees, this produces nodes in sorted order.
  public void inOrderTraversal() {
    System.out.println("InOrder traversal ");

    inOrderRec(root);
  }

  private void inOrderRec(TreeNode root) {
    if (root != null) {
      inOrderRec(root.left);
      System.out.println(root.val + "");
      inOrderRec(root.right);
    }
  }

  // Description:
  // Visit the root node, then the left subtree, then the right subtree.
  // Useful for copying the tree or generating prefix expressions.
  public void preorderTraversal() {
    System.out.println("Preorder traversal ");

    preorderRec(root);
  }

  private void preorderRec(TreeNode node) {
    if (node == null) return;
    System.out.println(node.val + " ");
    preorderRec(node.left);
    preorderRec(node.right);
  }

  /*
      * Description:
  Visit the left subtree, then the right subtree, then the root node.
  Useful for deleting the tree or evaluating postfix expressions.
  * */
  public void postOrderTraversal() {
    System.out.println("Post Order traversal ");

    postOrderTraversalRecursive(root);
  }

  private void postOrderTraversalRecursive(TreeNode node) {
    if (node == null) return;
    postOrderTraversalRecursive(node.left);
    postOrderTraversalRecursive(node.right);
    System.out.println(node.val + "");
  }

  public void depthFirstTraversal(){

  }
}
