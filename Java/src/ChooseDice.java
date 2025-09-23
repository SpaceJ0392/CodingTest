import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChooseDice {

    private final List<int[]> combination = new ArrayList<>();
    private final List<List<Integer>> sumOfDiceList = new ArrayList<>();

    public static void main(String[] args) {
        ChooseDice cd = new ChooseDice();
        cd.solution(new int[][] {{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}});
    }

    public int[] solution(int[][] dice) {
        int[] answer = {};

        int winCnt = 0;
        getCombination(0, 0, dice.length - 1, new int[dice.length / 2]);
        for(int i = 0 ; i < combination.size(); i++){
            sumOfDiceList.add(new ArrayList<>());
            getDiceTotal(0, 0, dice, combination.get(i), i);
            int[] res = getWinCnt();
            if(winCnt < res[1]) {
                winCnt = res[1];
                answer = combination.get(res[0]);
            }
        }

        return answer;
    }

    public int[] getWinCnt(){
        Map<Integer, Integer> diceA = new HashMap<>();
        Map<Integer, Integer> diceB = new HashMap<>();
        int caseSize = sumOfDiceList.size();
        for(int i = 0; i <= caseSize / 2; i++){
            for(int item : sumOfDiceList.get(i))
                diceA.merge(item, 1, Integer::sum);
            for(int item : sumOfDiceList.get(caseSize - 1 - i))
                diceB.merge(item, 1, Integer::sum);
        }

        System.out.println(diceA);
        System.out.println(diceB);
        return new int[2];
    }

    public void getDiceTotal(int lev, int res, int[][] dice, int[] myDice, int idx){
        if(lev == myDice.length){
            sumOfDiceList.get(idx).add(res);
            return;
        }

        for(int i = 0; i < dice[myDice[lev]].length; i++){
            getDiceTotal(lev + 1, res + dice[myDice[lev]][i], dice, myDice, idx);
        }
    }

    public void getCombination(int lev, int start, int end, int[] dice){
        if(lev == dice.length){
            combination.add(dice.clone());
            return;
        }

        for(int i = start; i <= end; i++){
            dice[lev] = i;
            getCombination(lev + 1, i + 1, end, dice);
        }
    }
}
