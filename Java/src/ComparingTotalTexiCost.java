import java.util.*;

public class ComparingTotalTexiCost {
    public static void main(String[] args) {
        ComparingTotalTexiCost obj = new ComparingTotalTexiCost();
        int ans = obj.solution(6,4,6,2, new int[][] {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}});
        System.out.println(ans);
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        int[][] graph = new int[n + 1][n + 1];
        initGraph(graph, fares);

        int[] costToStart = dijkstra(s, graph);
        int[] costToA = dijkstra(a, graph);
        int[] costToB = dijkstra(b, graph);

        //가장 작은 totCost 값 찾기.
        answer = costToStart[a] + costToStart[b];
        for(int node = 1; node < graph.length; node++){
            if(node == s) continue;
            answer = Math.min(answer,
                    costToA[node] + costToB[node] + costToStart[node]);
        }
        return answer;
    }

    public void initGraph(int[][] graph, int[][] fares){
        for(int[] fare : fares){
            graph[fare[0]][fare[1]] = fare[2];
            graph[fare[1]][fare[0]] = fare[2];
        }
    }

    public int[] dijkstra(int start, int[][] graph){
        int[] totCost = new int[graph.length];
        Arrays.fill(totCost, Integer.MAX_VALUE);
        totCost[start] = 0;

        Queue<Map<Integer, Integer>> queue = new PriorityQueue<>(
                Comparator.comparingInt(node -> node.values().iterator().next()));
        queue.add(Map.of(start, 0));

        while(!queue.isEmpty()){
            Map.Entry<Integer, Integer> now = queue.poll().entrySet()
                    .iterator().next();

            int nowNode = now.getKey();
            int nowCost = now.getValue();

            if(nowCost > totCost[nowNode]) continue;
            for(int node = 1; node < graph[nowNode].length; node++){
                if(graph[nowNode][node] == 0) continue;
                int nextCost = nowCost + graph[nowNode][node];
                if(nextCost > totCost[node]) continue;
                totCost[node] = nextCost;
                queue.add(Map.of(node, nextCost));
            }
        }

        return totCost;
    }
}
