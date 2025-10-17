import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class TransposeWord {
    public static void main(String[] args) {
        TransposeWord obj = new TransposeWord();
         int ans = obj.solution("hit", "cog",new String[] {"hot", "dot", "dog", "lot", "log", "cog"});
        System.out.println(ans);
    }

    private int solution(String begin, String target, String[] words) {
        int answer = 0;

        Queue<Word> queue = new ArrayDeque<>();
        queue.add(new Word(0, begin));

        Set<String> checker = new HashSet<>();
        while(!queue.isEmpty()){
            Word now = queue.poll();
            String nowWord = now.word();
            int nowLev = now.lev();
            checker.add(nowWord);

            if(nowWord.equals(target)) {
                answer = nowLev;
                break;
            }

            for(String item : words){
                if(checker.contains(item)) continue;
                if(!canTransform(nowWord, item)) continue;
                queue.add(new Word (nowLev + 1, item));
            }
        }
        return answer;
    }

    public boolean canTransform(String base, String target){
        int cnt = 0;
        for(int i = 0 ; i< base.length(); i++){
            if(base.charAt(i) != target.charAt(i)) cnt++;
        }
        return cnt <= 1;
    }
}

record Word(int lev, String word) {

    @Override
    public String toString() {
        return word() + " " + lev();
    }
}