import java.util.HashMap;
import java.util.Map;

public class OilDrilling {
    public static void main(String[] args) {
        OilDrilling oilDrilling = new OilDrilling();
        int ans = oilDrilling.solution(new int[][] {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}});
        System.out.println(ans);
    }

    private int solution(int[][] land) {
        int answer = 0;

        Map<Integer, Integer> oilArea = new HashMap<>();
        findOilArea(oilArea, land);

        return answer;
    }

    private void findOilArea(Map<Integer, Integer> oilArea, int[][] land) {
        int areaId = 1;
        int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
        boolean[][] checker = new boolean[land.length][land[0].length];
        for(int y = 0; y < land.length; y++){
            for(int x = 0; x < land[0].length; x++){
                for(int i = 0; i < dy.length; i++){
                    int ny = y + dy[i], nx = x + dx[i];
                    if(ny < 0 || ny >= land.length || nx < 0 || nx >= land[0].length) continue;
                    if()
                }
            }
        }
    }
}
