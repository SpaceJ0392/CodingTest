public class Change {
    public static void main(String[] args) {
        Change change = new Change();
        int ans = change.solution(5, new int[]{1, 2, 5});
        System.out.println(ans);
    }

    public int solution(int n, int[] money) {
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for(int coin : money){
            for(int target = 1; target <= n; target++){
                if(target >= coin) dp[target] += (dp[target - coin]);
            }
        } //coin 종류가 밖으로 가면 조합. 안으로 들어오면 순열.
        return dp[n];
    }
}
