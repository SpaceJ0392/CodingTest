public class CarryGoldAndSliver {

    public static void main(String[] args) {
        CarryGoldAndSliver obj = new CarryGoldAndSliver();
        long ans = obj.solution(90, 500, new int[]{70, 70, 0}, new int[]{0, 0, 500}, new int[]{100, 100, 2}, new int[]{4, 8, 1});
        System.out.println(ans);
    }

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = -1;
        long start = 0, end = 400_000_000_000_000L;

        while(start <= end){
            long mid = (start + end) / 2;
            if(isPossible(mid, a, b, g, s, w, t)) {
                answer = mid;
                end = mid - 1l;
            }
            else start = mid + 1l;
        }

        return answer;
    }

    public boolean isPossible(long now, int goalG, int goalS,
                              int[] cityG, int[] cityS, int[] weight, int[] time){


        long nowG = 0, nowS = 0, nowTot = 0;
        for(int i = 0; i < weight.length; i++){
            int round = time[i] * 2;
            long cnt = now / round;
            if((now % round) >= time[i]) cnt++;

            long totW = weight[i] * cnt;
            nowG += Math.min(totW, cityG[i]);
            nowS += Math.min(totW, cityS[i]);
            nowTot += Math.min(totW, cityG[i] + cityS[i]);
        }
        return goalG <= nowG && goalS <= nowS && goalG + goalS <= nowTot;
    }
}
