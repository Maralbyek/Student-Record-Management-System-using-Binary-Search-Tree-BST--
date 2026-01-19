package model;

public class Node {
    public Student student;
    public Node left;
    public Node right;

    public Node(Student student) {
        this.student = student;
        this.left = null;
        this.right = null;
    }
}
