import java.util.*;

public class SheepAndWolf {
    public static void main(String[] args) {
        SheepAndWolf sheep = new SheepAndWolf();
        int ans = sheep.solution(new int[]{0,0,1,1,1,0,1,0,1,0,1,1}, new int[][]{{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}});
        System.out.println(ans);
    }

    public int solution(int[] info, int[][] edges) {
        List<Integer>[] map = initMap(info.length, edges);
        return getMaxSheep(info, map);
    }

    private int getMaxSheep(int[] info, List<Integer>[] map){
        int maxSheep = 0;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 1, 0, new HashSet<>()));

        while(!queue.isEmpty()){
            Node now = queue.poll();
            maxSheep = Math.max(maxSheep, now.sheepCnt);
            now.nearNodes.addAll(map[now.id]);

            for(int next : now.nearNodes){
                Set<Integer> nextSet = new HashSet<>(now.nearNodes);
                nextSet.remove(next);

                if(info[next] == 1){
                    if(now.sheepCnt != now.wolfCnt + 1) {
                        queue.add(new Node(next, now.sheepCnt, now.wolfCnt + 1, nextSet));
                    }
                }
                else {
                    queue.add(new Node(next, now.sheepCnt + 1, now.wolfCnt, nextSet));
                }
            }
        }

        return maxSheep;
    }

    private List<Integer>[] initMap(int n, int[][] edges){
        List<Integer>[] map = new ArrayList[n];
        for(int i = 0; i < n; i++){ map[i] = new ArrayList<>(); }

        for(int[] edge : edges){
            int parent = edge[0], child = edge[1];
            map[parent].add(child);
        }
        return map;
    }

    private static class Node {
        private final int id;
        private final int sheepCnt;
        private final int wolfCnt;
        private final Set<Integer> nearNodes;

        public Node(int id, int sCnt, int wCnt, Set<Integer> nodes){
            this.id = id;
            sheepCnt = sCnt;
            wolfCnt = wCnt;
            nearNodes = nodes;
        }
    }
}
