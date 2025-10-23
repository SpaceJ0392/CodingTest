public class Rank {

    public static void main(String[] args) {
        Rank rank = new Rank();
        int ans = rank.solution(5, new int[][] {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}});
        System.out.println(ans);
    }

    public int solution(int n, int[][] results) {
        int answer = 0;

        boolean[][] graph = new boolean[n][n];
        for(int[] res : results){
            graph[res[0] - 1][res[1] - 1] = true;
        }

        for(int mid = 0; mid < n; mid++){
            for(int start = 0; start < n; start++){
                for(int end = 0; end < n; end++){
                    if(graph[start][mid] && graph[mid][end]) graph[start][end] = true;
                }
            }
        }

        for(int i = 0; i < n; i++){
            int winCnt = 0, loseCnt = 0;
            for(int j = 0; j < n ; j++){
                if(graph[i][j]) winCnt++;
                if(graph[j][i]) loseCnt++;
            }
            if(winCnt + loseCnt == n - 1) answer++;
        }
        return answer;
    }
}
