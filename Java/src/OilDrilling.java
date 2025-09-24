import java.util.*;

public class OilDrilling {
    public static void main(String[] args) {
        OilDrilling oilDrilling = new OilDrilling();
        int ans = oilDrilling.solution(new int[][] {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}});
        System.out.println(ans);
    }

    private int solution(int[][] land) {
        int answer = 0;

        Map<Integer, Integer> oilAmounts = new HashMap<>();
        int[][] oilMap = findOilAmounts(oilAmounts, land);

        for(int x = 0; x< oilMap[0].length; x++){
            Set<Integer> set = new HashSet<>();
            for(int y = 0; y < oilMap.length; y++){
                if(oilMap[y][x] != 0) set.add(oilMap[y][x]);
            }

            int sum = 0;
            for(Integer oilIdx : set){
                 sum += oilAmounts.get(oilIdx);
            }
            answer = Math.max(answer, sum);
        }
        return answer;
    }

    public int[][] findOilAmounts(Map<Integer, Integer> oilAmounts, int[][] land) {
        int[][] oilMap = new int[land.length][land[0].length];
        int[] dy = {0, -1, 1, 0 ,0}, dx = {0, 0, 0, -1, 1};
        int oilIdx = 1;
        Queue<int[]> queue = new ArrayDeque<>();
        for(int y = 0; y < land.length; y++){
            for(int x = 0; x < land[y].length; x++){
                if(land[y][x] == 1 && oilMap[y][x] == 0) queue.add(new int[]{y, x});

                while (!queue.isEmpty()) {
                    int[] now = queue.poll();
                    for(int i = 0; i < dy.length; i++){
                        int ny = now[0] + dy[i], nx = now[1] + dx[i];
                        if(ny < 0 || ny >= oilMap.length || nx < 0 || nx >= oilMap[now[0]].length || oilMap[ny][nx] != 0) continue;
                        if(land[ny][nx] != 0) {
                            oilMap[ny][nx] = oilIdx;
                            queue.add(new int[]{ny, nx});
                            oilAmounts.merge(oilIdx, 1, Integer::sum);
                        }
                    }
                    if(queue.isEmpty()) oilIdx++;
                }
            }
        }

        return oilMap;
    }
}
