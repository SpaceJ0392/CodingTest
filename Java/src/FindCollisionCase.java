import java.util.*;

public class FindCollisionCase {
    public static void main(String[] args) {
        FindCollisionCase obj = new FindCollisionCase();
        System.out.println(obj.solution(new int[][]{{3, 2}, {6, 4}, {4, 7}, {1, 4}},
                new int[][]{{4, 2}, {1, 3}, {2, 4}}));
        System.out.println(obj.solution(new int[][]{{3, 2}, {6, 4}, {4, 7}, {1, 4}},
                new int[][]{{4, 2}, {1, 3}, {4, 2}, {4, 3}}));
        //1,4 | 3,2 | 1,4 | 1,4
        //6,4 | 4,7 | 6,4 | 4,7
    }

    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        Map<Integer, int[]> pointsMap = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            pointsMap.put(i + 1, points[i]);
        }

        int[][] robots = new int[routes.length][2];
        int[][] goals = new int[routes.length][2];
        int[] targetIdxArr = new int[routes.length];
        Arrays.fill(targetIdxArr, 1);
        init(robots, goals, routes, pointsMap);
        answer += isCollision(robots);

        while (!isEnd(robots)) {

            for (int i = 0; i < robots.length; i++) {
                if(Arrays.equals(robots[i], new int[] {0, 0})) continue;
                moveRobots(robots[i], goals[i]);
            }

            answer += isCollision(robots);

            for(int i = 0; i < robots.length; i++){
                if (Arrays.equals(robots[i], goals[i])) {
                    if (Arrays.equals(goals[i],
                            pointsMap.get(routes[i][routes[i].length - 1])))
                    {
                        robots[i] = new int[]{0, 0};
                        continue;
                    }
                    goals[i] = pointsMap.get(routes[i][++targetIdxArr[i]]).clone();
                }
            }
        }
        return answer;
    }

    public void init(int[][] robots, int[][] goals, int[][] routes,
                     Map<Integer, int[]> pointsMap){

        for(int i = 0; i < robots.length; i++){
            robots[i] = pointsMap.get(routes[i][0]).clone();
            goals[i] = pointsMap.get(routes[i][1]).clone();
        }
    }

    public void moveRobots(int[] robot, int[] goal) {
        if(Arrays.equals(robot, new int[] {0, 0})) return;

        if (robot[0] - goal[0] > 0) robot[0]--;
        else if (robot[0] - goal[0] < 0) robot[0]++;
        else {
            if (robot[1] - goal[1] > 0) robot[1]--;
            else if (robot[1] - goal[1] < 0) robot[1]++;
        }
    }

    public int isCollision(int[][] robots) {

        int result = 0;
        Map<String, Integer> collisionMap = new HashMap<>();
        for (int[] robot : robots) {
            collisionMap.merge(robot[0] + " " + robot[1], 1, Integer::sum);
        }
        int i = 0;
        for (Map.Entry<String, Integer> item : collisionMap.entrySet()) {
            if (item.getKey().equals("0 0")) continue;
            if (item.getValue() != 1) result++;
            i++;
        }
        return result;
    }

    public boolean isEnd(int[][] robots) {
        int cnt = 0;
        int[] target = {0, 0};
        for (int[] item : robots) {
            if (Arrays.equals(item, target)) cnt++;
        }
        return cnt == robots.length;
    }
}
