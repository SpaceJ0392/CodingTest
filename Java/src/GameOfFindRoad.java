import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class GameOfFindRoad {
    public static void main(String[] args) {
        GameOfFindRoad game = new GameOfFindRoad();
        int[][] ans = game.solution(new int[][]{{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}});
        System.out.println(Arrays.deepToString(ans));
    }

    private int[][] solution(int[][] nodeinfo) {
        List<List<Integer>> answer = new ArrayList<>();
        Node[] nodeInfo = initNode(nodeinfo);
        Arrays.sort(nodeInfo, Comparator.comparingInt(Node::getLev).reversed());

        BST tree = new BST();
        for(Node node : nodeInfo){
            tree.insert(node);
        }

        for(int i = 0; i <= 1; i++){
            answer.add(tree.orderAll(i));
        }
        return answer.stream()
                .map(rows -> rows.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);
    }

    private Node[] initNode(int[][] nodeinfo){
        Node[] nodeInfo = new Node[nodeinfo.length];
        for(int i = 0; i < nodeinfo.length; i++){
            nodeInfo[i] = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
        }
        return nodeInfo;
    }

    static class Node {
        private final int id;
        private final int data;
        private final int lev;
        private Node left;
        private Node right;

        public Node (int id, int data, int lev){
            this.id = id;
            this.data = data;
            this.lev = lev;
        }

        public int getLev() { return this.lev; }
        public int getData() { return this.data; }
        public int getId() { return this.id; }

        public void setLeft(Node left) { this.left = left; }
        public Node getLeft() { return this.left; }

        public void setRight(Node right) { this.right = right; }
        public Node getRight() { return this.right; }

        public String toString() { return this.id + " " + this.data + " " + this.lev; }
    }

    static class BST {
        private Node root;

        public void insert(Node node){
            this.root = insertNode(root, node);
        }

        private Node insertNode(Node root, Node node){
            if(root == null) return node;

            if(root.getData() < node.getData()){
                root.setRight(insertNode(root.getRight(), node));
            }
            else if(root.getData() > node.getData()) {
                root.setLeft(insertNode(root.getLeft(), node));
            }
            return root;
        }

        public List<Integer> orderAll(int type){
            List<Integer> path = new ArrayList<>();
            if (type <= 0) preOrder(root, path);
            else postOrder(root, path);
            return path;
        }

        private void preOrder(Node now, List<Integer> path){
            if(now == null) return;

            path.add(now.getId());
            preOrder(now.getLeft(), path);
            preOrder(now.getRight(), path);
        }

        private void postOrder(Node now, List<Integer> path){
            if(now == null) return;

            postOrder(now.getLeft(), path);
            postOrder(now.getRight(), path);
            path.add(now.getId());
        }
    }
}
