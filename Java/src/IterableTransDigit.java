import java.util.Arrays;

public class IterableTransDigit {
    public static void main(String[] args) {
        for(String s : args) {
            int[] answer = new int[2];

            while(!s.equals("1")){
                answer[1] += (int) s.chars().filter(ch -> ch == '0').count();
                s = s.replace("0", "");
                s = transposeDigit(s);
                answer[0]++;
            }

            System.out.println(Arrays.toString(answer));
        }
    }

    public static String transposeDigit(String target) {
        int targetInt = target.length();
        StringBuilder result = new StringBuilder();

        while(targetInt > 0){
            result.append(targetInt % 2);
            targetInt /= 2;
        }
        return result.reverse().toString();
    }
}
