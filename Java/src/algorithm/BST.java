package algorithm;

import java.util.Random;

public class BST {
    private Node root;

    public void insert(int data) {
        this.root = insertRec(root, data);
    }

    public boolean search(int data) {
        return searchRec(root, data);
    }

    private Node insertRec(Node root, int data) {
        if(root == null) return new Node(data);
        if(data < root.data) root.left = insertRec(root.left, data);
        else if(data > root.data) root.right = insertRec(root.right, data);
        return root;
    }

    private boolean searchRec(Node root, int data) {
        if(root == null) return false;
        if(data == root.data) return true;
        if(data < root.data) return searchRec(root.left, data);
        else return searchRec(root.right, data);
    }

    public void traversal(){
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        if(root == null) return;
        inorderRec(root.left);
        System.out.print(root.data + " ");
        inorderRec(root.right);
    }

    static class Node {
        private final int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }
        public int getData() {
            return data;
        }
        public Node getRight() {
            return right;
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        Random rand = new Random();
        for(int i = 0; i < 10; i++){
            int randNum = rand.nextInt(100);
            bst.insert(randNum);
        }
        bst.traversal();
    }
}
