import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlackUser {

    private Set<Set<String>> visited = new HashSet<>();

    public static void main(String[] args) {
        BlackUser blackUser = new BlackUser();
        int ans = blackUser.solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "abc1**"});
        System.out.println(ans);
    }

    public int solution(String[] userId, String[] bannedId) {

        for(int i = 0 ; i < bannedId.length; i++){
            bannedId[i] = bannedId[i].replaceAll("\\*", ".");
        }

        List<String>[] matched = new ArrayList[bannedId.length];
        for(int i = 0; i < matched.length; i++){
            matched[i] = new ArrayList<>();
        }

        for(String id : userId){
            for(int i = 0; i < bannedId.length; i++){
                if(id.matches(bannedId[i])) matched[i].add(id);
            }
        }

        return getCombination(0, bannedId.length, matched, new HashSet<>());
    }

    public int getCombination(int now, int lim, List<String>[] matched,
                              Set<String> combination){

        if(now == lim){
            Set<String> nowCombination = new HashSet<>(combination);
            if(!visited.add(nowCombination)) return 0;
            return 1;
        }

        int ans = 0;
        for(String target : matched[now]){
            if(!combination.add(target)) continue;
            ans += getCombination(now + 1, lim, matched, combination);
            combination.remove(target);
        }

        return ans;
    }

}
