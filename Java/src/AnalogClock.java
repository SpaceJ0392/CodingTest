public class AnalogClock {
    public static void main(String[] args) {
        AnalogClock analogClock = new AnalogClock();
        analogClock.solution(0, 5, 30, 0, 7, 0);
    }

    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        int startTime = (h1 * 3600 * 1000) + (m1 * 60 * 1000) + (s1 * 1000);
        int endTime = (h2 * 3600 * 1000) + (m2 * 60 * 1000) + (s2 * 1000);

        float nowHourLoc = (float) (h1 / (3600 * 1000)) % 24;

        for(int time = startTime; time <= endTime; time++) {
        }
        return answer;
    }
}
