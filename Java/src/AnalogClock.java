public class AnalogClock {
    public static void main(String[] args) {
        AnalogClock analogClock = new AnalogClock();
        analogClock.solution(0, 5, 30, 0, 7, 0);
    }

    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        int startTime = (h1 * 3600) + (m1 * 60) + (s1);
        int endTime = (h2 * 3600) + (m2 * 60) + (s2);


        getStartLoc(h1, m1, s1);
        return answer;
    }

    public double[] getStartLoc (int h, int m, int s){
       return  new double[]{h,m,s};
    }
}
