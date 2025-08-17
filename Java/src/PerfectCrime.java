import java.util.*;

public class PerfectCrime {
    public int[] dpArr = new int[121];

    public static void main(String[] args) {

        PerfectCrime pc = new PerfectCrime();
        int answer = pc.solution(new int[][]{{3, 3}, {3, 3}}, 6, 1);
        System.out.println(answer);
    };

    public int solution(int[][] info, int n, int m) {
        Arrays.fill(dpArr, 121);
        dpArr[0] = 0;

        int answer = -1;

        for(int[] item : info){
            for(int i = 120; i >= 0; i--){
                if(i - item[0] >= 0)
                    dpArr[i] = Math.min(dpArr[i - item[0]], dpArr[i] + item[1]);
                else
                    dpArr[i] = dpArr[i] + item[1];
            }
        }

        for(int i = 0; i < dpArr.length; i++){
            if(dpArr[i] >= m) continue;
            if(i >= n) break;
            answer = i;
            break;
        }

        //System.out.println(Arrays.toString(dpArr));

        return answer;
    }
}
