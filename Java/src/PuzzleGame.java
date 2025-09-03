import java.util.Arrays;

public class PuzzleGame {
    private int[] diffs;
    private int[] times;
    private long limit;

    public static void main(String[] args) {
        PuzzleGame puzzleGame = new PuzzleGame();
        System.out.println(puzzleGame.solution(new int[]{1, 328, 467, 209, 54}, new int[]{2, 7, 1, 4, 3}, 1723));
        System.out.println(puzzleGame.solution(new int[]{1, 5, 3}, new int[]{2, 4, 7}, 30));
        System.out.println(puzzleGame.solution(new int[]{1, 99999, 100000, 99995}, new int[]{9999, 9001, 9999, 9001}, 3456789012L));
    }

    private int solution(int[] diffs, int[] times, long limit) {

        int answer = Integer.MAX_VALUE;
        int left = 1, right = Arrays.stream(diffs).max().orElse(1);
        while(left <= right){

            int pivot = (left + right) / 2;
            long nowTime = 0;

            for(int i = 0; i < diffs.length; i++){

                if(nowTime > limit) break;
                if(diffs[i] <= pivot) nowTime += times[i];
                else nowTime += (long) (times[i] + times[i - 1]) * (diffs[i] - pivot)
                        + times[i];
            }

            if(nowTime <= limit) {
                answer = Math.min(pivot, answer);
                right = pivot - 1;
            }
            else {
                left = pivot + 1;
            }
        }
        return answer;
    }
}
