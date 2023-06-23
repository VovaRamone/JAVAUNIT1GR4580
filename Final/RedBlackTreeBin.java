package Final;

import java.util.LinkedList;
import java.util.Queue;

public class RedBlackTreeBin {
    private Node root;

    private enum Color {
        RED, BLACK
    }

    private static class Node {
        int value;
        Node left;
        Node right;
        Color color;

        Node(int value) {
            this.value = value;
            this.color = Color.RED; // New nodes are always inserted as red
        }
    }

    public Node find(int value) {
        return findNode(root, value);
    }

    private Node findNode(Node node, int value) {
        if (node == null || node.value == value) {
            return node;
        }
        if (node.value < value) {
            return findNode(node.right, value);
        } else {
            return findNode(node.left, value);
        }
    }

    public void add(int value) {
        root = insert(root, value);
        root.color = Color.BLACK; // Ensure the root is always black
        printTree();
    }

    private Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insert(node.left, value);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        } else {
            // Value already exists in the tree
            return node;
        }

        return rebalance(node);
    }

    private Node rebalance(Node node) {
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    private boolean isRed(Node node) {
        return node != null && node.color == Color.RED;
    }

    private Node rotateLeft(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        newRoot.color = node.color;
        node.color = Color.RED;
        return newRoot;
    }

    private Node rotateRight(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        newRoot.color = node.color;
        node.color = Color.RED;
        return newRoot;
    }

    private void flipColors(Node node) {
        node.left.color = Color.BLACK;
        node.right.color = Color.BLACK;
        node.color = Color.RED;
    }

    public void printTree() {
        if (root == null) {
            System.out.println("Tree is empty.");
        } else {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                for (int i = 0; i < levelSize; i++) {
                    Node node = queue.poll();
                    System.out.print(node.value + "(" + (node.color == Color.RED ? "R" : "B") + ") ");
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
                System.out.println();
            }
            System.out.println("---");
        }
    }
}
