import java.util.Arrays;

public class FoldBills {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        while (true){
            Arrays.sort(wallet);
            Arrays.sort(bill);

            if(wallet[0] >= bill[0] && wallet[1] >= bill[1]) break;
            bill[1] /= 2;
            answer++;
        }
        return answer;
    }
}
