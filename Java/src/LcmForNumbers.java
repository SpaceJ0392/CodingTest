import java.util.Arrays;

public class LcmForNumbers {
    //최대공약수
    public int gcd(int a, int b){
        int r = a % b;
        if(a % b == 0) return b;
        return gcd(b, r);
    }

    //최소공배수
    public int lcm(int a, int b){
        return Math.abs(a * b) / gcd(a, b);
    }

    public static void main(String[] args) {
        int[] arr = Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
        LcmForNumbers lcmForNumbers = new LcmForNumbers();

        int answer = lcmForNumbers.lcm(arr[0], arr[1]);
        for(int i = 2; i < arr.length; i++){
            answer = lcmForNumbers.lcm(answer, arr[i]);
        }

        System.out.println(answer);
    }
}
