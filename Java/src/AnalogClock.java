import java.util.Arrays;

public class AnalogClock {
    public static void main(String[] args) {
        AnalogClock analogClock = new AnalogClock();
        System.out.println(analogClock.solution(0, 5, 30, 0, 7, 0));
    }

    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        int startTime = nowTime(h1, m1, s1);
        int endTime = nowTime(h2, m2, s2);
        double[] pastAngle = getAngle(startTime);
        if(pastAngle[0] == pastAngle[2] || pastAngle[1] == pastAngle[2]) answer++;

        for(int t = startTime + 1; t <= endTime; t++){
            double[] nowAngle = getAngle(t);
            answer += checkAlarm(t, pastAngle, nowAngle);
            pastAngle = nowAngle;
        }

        return answer;
    }

    public int checkAlarm(int t, double[] past, double[] now){
        double pastH = past[0], pastM = past[1], pastS = past[2];
        double nowH = now[0], nowM = now[1], nowS = now[2];
        //초당 변화하는 시침과 분침, 초침이 만약 초침이 두 시,분침을 지나면 알람이 울림.
        //고민되는 지점.
        //만약 한번의 초침 이동에서 시침과 분침이 겹쳐지지 않았는데, 통과하는 경우가 있는가?
        int alarm = 0;
        if(pastS < nowS) {
            if(pastS < pastH && nowH <= nowS) alarm++;
            if(pastS < pastM && nowM <= nowS) alarm++;
        }
        else {
            if(pastS < pastH && nowS <= nowH) alarm++;
            if(pastS < pastM && nowS <= nowM) alarm++;
            if(nowM == nowH) alarm--;

        }
        return alarm;
    }

    public double[] getAngle(int time){
        int h = (time / 3600) % 12, m = time % 3600 / 60, s = time % 3600 % 60;
        return new double[] {
                ((h * 30)+ (m * 0.5) + (s / 120.)),
                ((m * 6) + (s * 0.1)),
                (s * 6)
        };
    }

    public int nowTime(int h, int m, int s){
        return h * 3600 + m * 60 + s;
    }
}
