import java.util.Arrays;

public class JadenCase {
    public static void main(String[] args) {
        for (String s : args) {
            String answer = "";
            char[] seq = s.toLowerCase().toCharArray();
            boolean flag = true;

            for (char item : seq) {
                answer += flag ? Character.toString(item).toUpperCase() : Character.toString(item);
                flag = item == ' ';
            }
            System.out.println(answer);
        }
    }
}
