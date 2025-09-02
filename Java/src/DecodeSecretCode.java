public class DecodeSecretCode {
    private int answer = 0;

    public static void main(String[] args) {
        DecodeSecretCode decodeSecretCode = new DecodeSecretCode();
        System.out.println(
                decodeSecretCode.solution(10,
                                             new int[][]{{1, 2, 3, 4, 5},
                                                     {6, 7, 8, 9, 10},
                                                     {3, 7, 8, 9, 10},
                                                     {2, 5, 7, 9, 10},
                                                     {3, 4, 5, 6, 7}},
                                             new int[] {2,3,4,3,3}
        ));
    }

    private int solution(int n, int[][] q, int[] ans) {
        getRandomCode(1, 0, n, q, ans, new int[5]);
        return answer;
    }

    private void getRandomCode(int start, int lev, int limNum, int[][] query,
                               int[] ans, int[] code){
        if(lev == 5){
            if(isCorrectCode(query, ans, code, limNum)) answer++;
            return;
        }

        for(int i = start; i <= limNum; i++){
            code[lev] = i;
            getRandomCode(i + 1, lev + 1, limNum, query, ans, code);
            code[lev] = 0;
        }
    }

    private boolean isCorrectCode(int[][] query, int[] ans, int[] code, int limNum){


        for(int i = 0; i < query.length; i++){
            int res = 0;
            int[] checker = new int[limNum + 1];
            for(int j = 0; j < query[i].length; j++){
                checker[query[i][j]]++;
                checker[code[j]]++;
            }

            for (int j : checker) {
                if (j == 2) res++;
            }
            if(res != ans[i]) return false;

        }
        return true;
    }
}
