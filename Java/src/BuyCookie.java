public class BuyCookie {

    public static void main(String[] args) {
        BuyCookie b = new BuyCookie();
        int ans = b.solution(new int[]{1, 1, 2, 3});
        System.out.println(ans);
    }

    public int solution(int[] cookie) {
        int answer = 0;

        for (int m = 0; m < cookie.length - 1; m++) {
            int l = m, r = m + 1;
            int sum1 = cookie[m], sum2 = cookie[m + 1];

            while (l >= 0 && r < cookie.length) {
                if (sum1 == sum2) {
                    answer = Math.max(answer, sum1);
                }
                if (sum1 > sum2) {
                    r++;
                    sum2 += (r < cookie.length) ? cookie[r] : 0;
                } else {
                    l--;
                    sum1 += (l >= 0) ? cookie[l] : 0;
                }
            }

        }

        return answer;
    }

}
