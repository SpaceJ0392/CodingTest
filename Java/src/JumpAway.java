public class JumpAway {
    public static void main(String[] args) {
        long answer = 1;
        long pre = 1;

        /*
        DP (O) --> 2칸의 경우의 수.
        - 4
          2칸 1번이면, 3 = (n - 2) + 1
          2칸 2번이면, 1 = (n - 4) + 1

        - 3
          2칸 1번이면, 2 = (n - 2) + 1

        핵심은 2칸의 개수가 k개일 때의 경우의 수 구하기?
        - 5
          2칸 1 -> 4 = (n - 2) + 1
          2칸 2 -> 3  = 5 - 2?

        -6
         2칸 1 ->

        ??피보나치??
        0 1 1 2 3 5 8 ...
          0 1 2 3 4 ...
        */

        //즉, n + 1 번째의 피보나치 수를 구하면 된다...
        for(int i = 1; i < Integer.parseInt(args[0]); i++){
            long temp = answer;
            answer = (answer + pre) % 1234567;
            pre = temp;
        }

        System.out.println(answer);
    }
}
