import java.util.Arrays;

public class ShuttleBus {
    public static void main(String[] args) {
        ShuttleBus shuttleBus = new ShuttleBus();
        String ans = shuttleBus.solution(1, 1, 5, new String[]{"08:00", "08:01", "08:02", "08:03"});
        System.out.println("ans = " + ans);
    }

    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        int[] crew = initCrew(timetable);

        int startTime = stringToTime("09:00"), endTime = startTime + ((n - 1) * t);
        int startCrew = -1;
        for(int busTime = startTime; busTime <= endTime; busTime += t){
            int crewCnt = 0;
            for(int c = startCrew + 1; c < crew.length; c++){
                if(crew[c] > busTime || crewCnt >= m) break;
                startCrew++;
                crewCnt++;

                if(busTime == endTime && crewCnt == m){
                    answer = timeToString(crew[c] - 1);
                    break;
                }
            }
        }

        return answer == "" ? timeToString(endTime) : answer;
    }

    private int[] initCrew(String[] timetable){
        int[] crew = new int[timetable.length];

        for(int i = 0; i < timetable.length; i++){
            crew[i] = stringToTime(timetable[i]);
        }
        Arrays.sort(crew);
        return crew;
    }

    private int stringToTime(String time){
        String[] timeSplit = time.split(":");
        return Integer.parseInt(timeSplit[0]) * 60 + Integer.parseInt(timeSplit[1]);
    }

    private String timeToString(int time){
        int h = time / 60, m = time % 60;
        String hourStr = (h / 10 == 0) ? "0" + h : Integer.toString(h);
        String minStr = (m / 10 == 0) ? "0" + m : Integer.toString(m);
        return hourStr + ":" + minStr;
    }
}
