package algorithm;

import java.util.Arrays;

/*
    Dijkstra 와 FloydWarsShall의 차이점?
    - Dijkstra : 한 노드에서의 다른 모든 간선에서의 최단 거리 확보.
    - FloydWarsShall : 모든 노드에서 다른 모든 노드간의 최단 거리 확보.
*/

public class FloydWarshall {
    private static final int INF = Integer.MAX_VALUE;


    public static void main(String[] args) {
        FloydWarshall fw = new FloydWarshall();
        int[][] ans = fw.solution(new int[][]{{0, 5, INF, 10}, {INF, 0, 2, INF}, {INF, INF, 0, 1}, {INF, INF, INF, 0}});
        System.out.println(Arrays.deepToString(ans));
    }

    public int[][] solution(int[][] graph) {
        int len = graph.length;

        for(int mid = 0; mid < len; mid++) {
            for (int start = 0; start < len; start++) {
                for (int end = 0; end < len; end++) {
                    if (graph[start][mid] != INF && graph[mid][end] != INF) {
                        graph[start][end] = Math.min(graph[start][end], graph[start][mid] + graph[mid][end]);
                    }
                }
            }
        }

        return graph;
    }
}
