import org.w3c.dom.Node;

import java.util.*;

public class TheFarthestNode {
    public static void main(String[] args) {
        TheFarthestNode farthestNode = new TheFarthestNode();
        int ans = farthestNode.solution(6, new int[][] {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}});
        System.out.println(ans);
    }

    private int solution(int n, int[][] edge) {
        List<Integer>[] graph = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++){
            graph[i] = new ArrayList<>();
        }
        initGraph(graph, edge);

        int[] nodeCost = bfs(1, graph);
        int maxEdge = Arrays.stream(nodeCost).max().orElse(-1);
        return (int) Arrays.stream(nodeCost).filter(item -> item == maxEdge).count();
    }

    public void initGraph(List<Integer>[] graph, int[][] edges){

        for(int[] edge : edges){
            int node1 = edge[0], node2 = edge[1];
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
    }

    public int[] bfs(int start, List<Integer>[] graph){
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(start, 0));

        int[] costTable = new int[graph.length];
        costTable[1] = -1;

        while(!queue.isEmpty()){
            Node now = queue.poll();
            int nowNode = now.getNode(), nowCost = now.getCost();

            for(int next : graph[nowNode]){
                if(costTable[next] != 0) continue;

                int nextCost = nowCost + 1;
                queue.add(new Node(next, nextCost));
                costTable[next] = nextCost;
            }
        }

        return costTable;
    }

    static class Node {
        private final int node;
        private final int cost;

        public Node(int node, int cost){
            this.node = node;
            this.cost = cost;
        }

        public int getNode() { return this.node; }
        public int getCost() { return this.cost; }

        @Override
        public String toString() {
            return getNode() + " " + getCost();
        }
    }
}
