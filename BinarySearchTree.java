package bst;

import model.Node;
import model.Student;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    // Insert
    public void insert(Student student) {
        this.root = insertRec(this.root, student);
    }

    private Node insertRec(Node root, Student student) {
        if (root == null) {
            root = new Node(student);
            return root;
        }

        if (student.getMatricNumber().compareTo(root.student.getMatricNumber()) < 0) {
            root.left = insertRec(root.left, student);
        } else if (student.getMatricNumber().compareTo(root.student.getMatricNumber()) > 0) {
            root.right = insertRec(root.right, student);
        }

        return root;
    }

    // Search
    public Student search(String matricNumber) {
        Node result = searchRec(this.root, matricNumber);
        return (result != null) ? result.student : null;
    }

    private Node searchRec(Node root, String matricNumber) {
        if (root == null || root.student.getMatricNumber().equals(matricNumber)) {
            return root;
        }

        if (matricNumber.compareTo(root.student.getMatricNumber()) < 0) {
            return searchRec(root.left, matricNumber);
        }

        return searchRec(root.right, matricNumber);
    }

    // Delete
    public void delete(String matricNumber) {
        this.root = deleteRec(this.root, matricNumber);
    }

    private Node deleteRec(Node root, String matricNumber) {
        if (root == null) {
            return root;
        }

        if (matricNumber.compareTo(root.student.getMatricNumber()) < 0) {
            root.left = deleteRec(root.left, matricNumber);
        } else if (matricNumber.compareTo(root.student.getMatricNumber()) > 0) {
            root.right = deleteRec(root.right, matricNumber);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            root.student = minValue(root.right);
            root.right = deleteRec(root.right, root.student.getMatricNumber());
        }

        return root;
    }

    private Student minValue(Node root) {
        Student minv = root.student;
        while (root.left != null) {
            minv = root.left.student;
            root = root.left;
        }
        return minv;
    }

    // Traversals
    public List<Student> inOrderTraversal() {
        List<Student> result = new ArrayList<>();
        inOrderRec(this.root, result);
        return result;
    }

    private void inOrderRec(Node root, List<Student> result) {
        if (root != null) {
            inOrderRec(root.left, result);
            result.add(root.student);
            inOrderRec(root.right, result);
        }
    }

    public List<Student> preOrderTraversal() {
        List<Student> result = new ArrayList<>();
        preOrderRec(this.root, result);
        return result;
    }

    private void preOrderRec(Node root, List<Student> result) {
        if (root != null) {
            result.add(root.student);
            preOrderRec(root.left, result);
            preOrderRec(root.right, result);
        }
    }

    public List<Student> postOrderTraversal() {
        List<Student> result = new ArrayList<>();
        postOrderRec(this.root, result);
        return result;
    }

    private void postOrderRec(Node root, List<Student> result) {
        if (root != null) {
            postOrderRec(root.left, result);
            postOrderRec(root.right, result);
            result.add(root.student);
        }
    }

     // Utility Methods
    public int height() {
        return heightRec(this.root);
    }

    private int heightRec(Node root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = heightRec(root.left);
            int rightHeight = heightRec(root.right);

            if (leftHeight > rightHeight) {
                return (leftHeight + 1);
            } else {
                return (rightHeight + 1);
            }
        }
    }

    public int countNodes() {
        return countNodesRec(this.root);
    }

    private int countNodesRec(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodesRec(root.left) + countNodesRec(root.right);
    }

    public Student minNode() {
        if (root == null) return null;
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.student;
    }

    public Student maxNode() {
        if (root == null) return null;
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.student;
    }

    public void levelOrderTraversal() {
        if (root == null) {
            return;
        }
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            System.out.print(node.student.getMatricNumber() + " ");
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
        System.out.println();
    }
    
    public void printTree() {
        printTree(this.root, "", true);
    }

    private void printTree(Node node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.student.getMatricNumber());
            printTree(node.left, prefix + (isLeft ? "│   " : "    "), true);
            printTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }

    public Node getRoot() {
        return root;
    }
}
