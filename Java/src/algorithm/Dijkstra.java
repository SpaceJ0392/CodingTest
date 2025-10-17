package algorithm;

import java.util.*;

public class Dijkstra {

    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        graph.put("A", Map.of("B", 8, "C", 1,  "D", 2));
        graph.put("B", new HashMap<>());
        graph.put("C", Map.of("B", 5,  "D", 2));
        graph.put("D", Map.of("E", 3,  "F", 5));
        graph.put("E", Map.of("F", 1));
        graph.put("F", Map.of("A", 5));

        String startNode = "A";
        Map<String, Integer> res = dijkstra.algorithm(graph, startNode);
        System.out.println(res);
    }

    public Map<String, Integer> algorithm(Map<String, Map<String, Integer>> graph, String startNode){
        Map<String, Integer> cost = new HashMap<>();
        for (String key : graph.keySet()) {
            if(key.equals(startNode)) {
                cost.put(key, 0);
                continue;
            }
            cost.put(key, Integer.MAX_VALUE);
        }

        Queue<Map<String, Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.values().iterator().next()));
        queue.add(Map.of(startNode, 0));
        while(!queue.isEmpty()){
            Map.Entry<String, Integer> now = queue.poll().entrySet().iterator().next();
            String nowNode = now.getKey();
            int nowCost = now.getValue();

            if (cost.get(nowNode) < nowCost) continue;

            for(Map.Entry<String, Integer> item : graph.get(nowNode).entrySet()) {
                int totCost = nowCost + item.getValue();
                if(totCost < cost.get(item.getKey())) {
                    cost.put(item.getKey(), totCost);
                    queue.add(Map.of(item.getKey(), totCost));
                }
            }
        }
        return cost;
    }
}
