import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordChain {
    public static void main(String[] args) {
        for (String arg : args){
            int n = arg.charAt(0) - '0';
            String[] words = arg.substring(2).split(" ");
            int[] answer = {0, 0};

            char preLastChar = words[0].charAt(0);
            Set<String> set = new HashSet<>();
            for(int i = 0; i < words.length; i++){
                boolean flag = set.add(words[i]);
                if(preLastChar != words[i].charAt(0)) flag = false;

                preLastChar = words[i].charAt(words[i].length() - 1);
                if(i % n == 0) answer[1]++;
                answer[0] = i % n + 1;
                if(!flag) break;
            }

            System.out.println(Arrays.toString(answer));
        }
    }
}
