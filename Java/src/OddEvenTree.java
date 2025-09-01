import java.util.*;

public class OddEvenTree {
    private int[] answer = new int[2];

    public static void main(String[] args) {
        OddEvenTree oddEvenTree = new OddEvenTree();
//        System.out.println(
//                Arrays.toString(oddEvenTree.solution(new int[]{11, 9, 3, 2, 4, 6},
//                        new int[][]{{9, 11}, {2, 3}, {6, 3}, {3, 4}}))
//        );

        System.out.println(
                Arrays.toString(oddEvenTree.solution(new int[]{9, 15, 14, 7, 6, 1, 2, 4, 5, 11, 8, 10},
                        new int[][]{{5, 14}, {1, 4}, {9, 11}, {2, 15}, {2, 5}, {9, 7}, {8, 1}, {6, 4}}))
        );
    }

    public int[] solution(int[] nodes, int[][] edges) {
        Map<Integer, ArrayList<Integer>> nodeLeaf = new HashMap<>();
        Map<Integer, Integer> nodeLeafCnt = new HashMap<>();

        for (int node : nodes) nodeLeaf.put(node, new ArrayList<>());

        for (int[] edge : edges) {
            nodeLeaf.get(edge[0]).add(edge[1]);
            nodeLeaf.get(edge[1]).add(edge[0]);

            nodeLeafCnt.merge(edge[0], 1, Integer::sum);
            nodeLeafCnt.merge(edge[1], 1, Integer::sum);
        }
        nodeLeafCnt.replaceAll((k, v) -> v - 1);


        for (int root : nodes) {
            getTreeType(root, nodeLeaf, nodeLeafCnt);
        }

        return answer;
    }

    public void getTreeType(int root, Map<Integer, ArrayList<Integer>> nodeLeaf,
                            Map<Integer, Integer> nodeLeafCnt) {

        Queue<Integer> rootQueue = new ArrayDeque<>();
        rootQueue.add(root);

        Set<Integer> tracker = new HashSet<>();
        boolean typeFlag = true;
        while (!rootQueue.isEmpty()) {
            int now = rootQueue.poll();

            for (int leaf : nodeLeaf.get(now)) {
                if (tracker.contains(leaf)) continue;

                boolean nodeType = getNodeType(root, now, nodeLeafCnt);
                boolean leafNodeType = getNodeType(root, leaf, nodeLeafCnt);

                if (leafNodeType != nodeType) return;
                typeFlag = nodeType;

                tracker.add(now);
                rootQueue.add(leaf);
            }
        }

        if (typeFlag) answer[0]++;
        else answer[1]++;
    }

    public boolean getNodeType(int root, int target, Map<Integer, Integer> nodeLeafCnt) {
        int leafCnt = root == target ? nodeLeafCnt.get(target) + 1 : nodeLeafCnt.get(target);
        return (target % 2 == 0) == (leafCnt % 2 == 0);
    }
}
