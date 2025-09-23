public class PointsBetweenTwoCircles {
    public static void main(String[] args) {
        PointsBetweenTwoCircles p = new PointsBetweenTwoCircles();
        System.out.println(p.solution(2, 3));
    }

    public long solution(int r1, int r2) {
        long answer = 0;
        for (int i = r1; i < r2; i++) {
            if ((i * i * 2) > r2) answer += (i + (i + 1)) * 4L - 4L;
            else answer += (i + (i + 1)) * 4L - 8L;
        }
        return answer + 4;
    }
}
