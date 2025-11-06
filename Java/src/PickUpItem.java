import java.util.*;

public class PickUpItem {

    public static void main(String[] args) {
        PickUpItem p = new PickUpItem();
        int ans = p.solution(new int[][]{{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}}, 1, 3, 7, 8);
        System.out.println(ans);
    }

    private static class Point {
        private final int x;
        private final int y;
        private int step;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.step = 0;
        }

        public void setStep(int step) {
            this.step = step;
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return this.x == point.x && this.y == point.y;
        }

        public int hashCode() {
            return Objects.hash(x, y);
        }

        public String toString() {
            return "Point : " + x + " " + y;
        }
    }

    public int solution(int[][] rectangle, int characterX, int characterY,
                        int itemX, int itemY) {

        //일단, 사각형들을 이용 가장 외부 경계값들만 구해야 함.
        Set<Point> rectBorderPos = makeMap(rectangle);
        //BFS로 최단 이동 거리 구하기.
        return moveToGoal(characterX * 2, characterY * 2,
                itemX * 2, itemY * 2, rectBorderPos);
    }

    private int moveToGoal(int startX, int startY, int goalX, int goalY,
                           Set<Point> rectBorderPos) {

        int answer = 0;
        Queue<Point> queue = new ArrayDeque<>();
        Set<Point> visited = new HashSet<>();
        queue.add(new Point(startX, startY));

        int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            int y = now.y, x = now.x, step = now.step;
            visited.add(now);

            if (y == goalY && x == goalX) {
                answer = step;
                break;
            }

            for (int i = 0; i < dy.length; i++) {
                int ny = y + dy[i], nx = x + dx[i];
                Point nextPoint = new Point(nx, ny);
                if (ny <= 0 || nx <= 0 || visited.contains(nextPoint)
                        || !rectBorderPos.contains(nextPoint)) continue;

                nextPoint.setStep(step + 1);
                queue.add(nextPoint);
            }
        }

        return answer / 2;
    }

    private Set<Point> makeMap(int[][] rectangle) {

        Set<Point> rectBorderPos = new HashSet<>();
        Set<Point> rectFullPos = new HashSet<>();

        for (int[] box : rectangle) {
            int minX = box[0] * 2, minY = box[1] * 2,
                    maxX = box[2] * 2, maxY = box[3] * 2;
            for (int x = minX; x <= maxX; x++) {
                rectBorderPos.add(new Point(x, minY));
                rectBorderPos.add(new Point(x, maxY));
            }

            for (int y = minY; y <= maxY; y++) {
                rectBorderPos.add(new Point(minX, y));
                rectBorderPos.add(new Point(maxX, y));
            }

            for (int y = minY + 1; y < maxY; y++) {
                for (int x = minX + 1; x < maxX; x++) {
                    rectFullPos.add(new Point(x, y));
                }
            }
        }

        rectBorderPos.removeAll(rectFullPos);
        return rectBorderPos;
    }
}
