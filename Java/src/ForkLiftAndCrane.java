import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class ForkLiftAndCrane {

    public static void main(String[] args) {
        ForkLiftAndCrane sol = new ForkLiftAndCrane();
        System.out.println(sol.solution(new String[]{"AZWQY", "CAABX", "BBDDA", "ACACA" }, new String[]{"A", "BB", "A" }));
    }

    private int solution(String[] storage, String[] requests) {

        int answer = 0;
        int rowLen = storage.length + 2, colLen = storage[0].length() + 2;
        char[][] paddingStorage = new char[rowLen][colLen];

        for (int y = 0; y < paddingStorage.length; y++) {
            if (y == 0 || y == rowLen - 1) {
                Arrays.fill(paddingStorage[y], '0');
                continue;
            }

            char[] targetLayer = storage[y - 1].toCharArray();
            for (int x = 0; x < paddingStorage[y].length; x++) {
                if (x == 0 || x == colLen - 1) paddingStorage[y][x] = '0';
                else paddingStorage[y][x] = targetLayer[x - 1];
            }
        }

        for (String req : requests) {
            if (req.length() == 1) clearBoundaryTarget(req, paddingStorage);
            else clearAllTarget(req, paddingStorage);
        }

        for (int y = 0; y < rowLen; y++) {
            for (int x = 0; x < colLen; x++) {
                if (paddingStorage[y][x] != '0') answer++;
            }
        }

        return answer;
    }

    private void clearBoundaryTarget(String req, char[][] paddingStorage){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {0, 0});

        int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
        int yLim = paddingStorage.length, xLim = paddingStorage[0].length;
        boolean[][] tracker = new boolean[yLim][xLim];
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            tracker[now[0]][now[1]] = true;

            for(int i = 0; i < dy.length; i++){
                int ny = now[0] + dy[i], nx = now[1] + dx[i];
                if(ny < 0 || ny >= yLim || nx < 0 || nx >= xLim
                        || tracker[ny][nx]) continue;

                tracker[ny][nx] = true;
                if(paddingStorage[ny][nx] == '0') {
                    queue.add(new int[] {ny, nx});
                }
                else if(paddingStorage[ny][nx] == req.charAt(0)) {
                    paddingStorage[ny][nx] = '0';
                }
            }
        }

    }

    private void clearAllTarget(String req, char[][] paddingStorage){

        for(int y = 0; y < paddingStorage.length; y++){
            for(int x = 0; x < paddingStorage[y].length; x++){
                if(req.charAt(0) == paddingStorage[y][x]) paddingStorage[y][x] = '0';
            }
        }

    }
}
