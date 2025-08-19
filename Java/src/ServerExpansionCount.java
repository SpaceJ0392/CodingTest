public class ServerExpansionCount {
    public int solution(int[] players, int m, int k) {
        int[] timeTable = new int[players.length];
        int n = 0, answer = 0;
        for(int i = 0; i < players.length; i++){

            if(timeTable[i] != 0) n -= timeTable[i];

            if(players[i] >= (n + 1) * m){
                int cnt = (players[i] / m) - n;
                answer += cnt;
                n = players[i] / m;
                if(i + k < players.length) timeTable[i + k] = cnt;
            }
        }
        return answer;
    }
}
