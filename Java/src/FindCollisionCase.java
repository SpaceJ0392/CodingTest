import java.util.*;

public class FindCollisionCase {

    private final List<Queue<int[]>> robotsRoutes = new ArrayList<>();

    public static void main(String[] args) {
        FindCollisionCase obj = new FindCollisionCase();
//        System.out.println(obj.solution(new int[][]{{3, 2}, {6, 4}, {4, 7}, {1, 4}},
//                new int[][]{{4, 2}, {1, 3}, {2, 4}}));
//        System.out.println(obj.solution(new int[][]{{3, 2}, {6, 4}, {4, 7}, {1, 4}},
//                new int[][]{{4, 2}, {1, 3}, {4, 2}, {4, 3}}));
        System.out.println(obj.solution(new int[][]{{2, 2}, {2, 3}, {2, 7}, {6, 6}, {5, 2}},
                new int[][]{{2, 3, 4, 5}, {1, 3, 4, 5}}));

    }

    public int solution(int[][] points, int[][] routes) {

        for (int[] route : routes) {
            int[] start = points[route[0] - 1];
            Queue<int[]> routeQueue = new ArrayDeque<>();
            routeQueue.add(start);

            for (int endIdx = 1; endIdx < route.length; endIdx++) {
                int[] end = points[route[endIdx] - 1];
                moveRobots(start, end, routeQueue);
                start = end;
            }
            robotsRoutes.add(routeQueue);
        }
        return getCollision();
    }

    public int getCollision() {
        int answer = 0;
        while (checkEnd()) {
            int[][] nowRobots = new int[robotsRoutes.size()][2];
            for (int i = 0; i < robotsRoutes.size(); i++) {
                Queue<int[]> queue = robotsRoutes.get(i);
                if (queue.isEmpty()) continue;
                nowRobots[i] = queue.poll();
            }

            Map<String, Integer> collisionMap = new HashMap<>();
            for (int[] point : nowRobots) {
                if (Arrays.equals(point, new int[]{0, 0})) continue;
                collisionMap.merge(point[0] + "," + point[1], 1, Integer::sum);
            }
            answer += (int) collisionMap.values().stream().filter(val -> val > 1).count();
        }
        return answer;
    }

    public boolean checkEnd() {
        for (Queue<int[]> route : robotsRoutes) {
            if (!route.isEmpty()) return true;
        }
        return false;
    }

    public void moveRobots(int[] start, int[] end, Queue<int[]> routeQueue) {
        int[] sp = start, ep = end;
        while (!Arrays.equals(sp, ep)) {
            int spY = sp[0], spX = sp[1], epY = ep[0], epX = ep[1];
            if (spY > epY) spY--;
            else if (spY < epY) spY++;
            else {
                if (spX > epX) spX--;
                else if (spX < epX) spX++;
            }
            sp = new int[]{spY, spX};
            routeQueue.add(sp);
        }
    }
}
