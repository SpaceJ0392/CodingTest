import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class AnalogClock {
    public static void main(String[] args) {
        AnalogClock analogClock = new AnalogClock();
        analogClock.solution(0, 5, 30, 0, 7, 0);
    }

    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        int startTime = (h1 * 3600) + (m1 * 60) + (s1);
        int endTime = (h2 * 3600) + (m2 * 60) + (s2);
        double[] now = getStartLoc(h1, m1, s1);

        for(int i = startTime; i <= endTime; i++) {
            answer += checkDistinct(now);
            moveLoc(now);
        }
        return answer;
    }

    private void moveLoc(double[] now) {
        now[0] += 0.008;
        now[1] += 0.1;
        now[2] += 6;
    }

    public int checkDistinct(double[] now) {
        Set<Double> check = Arrays.stream(now).boxed().collect(Collectors.toSet());
        return check.size() < 3 ? 1 : 0;
    }

    public double[] getStartLoc (int hour, int min, int sec){
        double h = ((hour % 12) * 30) + (min * 0.5) + (sec * 0.008);
        double m = ((min * 6) + (sec * 0.1));
        double s = ((sec * 6));
        return  new double[] {h,m,s};
    }
}
