import java.util.Arrays;
import java.util.Comparator;

public class MissileDefenseSystem {
    public static void main(String[] args) {
        MissileDefenseSystem obj = new MissileDefenseSystem();
        System.out.println(obj.solution(new int[][] {{4,5},{4,8},{10,14},{11,13},{5,12},{3,7},{1,4}}));
    }

    public int solution(int[][] targets) {
        int answer = 0;
        Arrays.sort(targets, Comparator.comparing((int[] item) -> item[1]));

        int temp = 0;
        for(int[] missile : targets) {
            int start = missile[0], end = missile[1];
            if(temp <= start){
                System.out.println(temp + " " + start + " " + end);
                answer++;
                temp = end;
            }
        }

        return answer;
    }
}
