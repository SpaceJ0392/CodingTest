import java.util.*;

public class ChooseDice {

    private final List<int[]> combination = new ArrayList<>();
    private final List<List<Integer>> sumOfDiceList = new ArrayList<>();

    public static void main(String[] args) {
        ChooseDice cd = new ChooseDice();
        System.out.println(Arrays.toString(cd.solution(
                new int[][]{
                        {1, 2, 3, 4, 5, 6},
                        {3, 3, 3, 3, 4, 4},
                        {1, 3, 3, 4, 4, 4},
                        {1, 1, 4, 4, 5, 5}
                }))
        );
    }

    public int[] solution(int[][] dice) {

        getCombination(0, 0, dice.length - 1, new int[dice.length / 2]);
        for(int i = 0 ; i < combination.size(); i++){
            sumOfDiceList.add(new ArrayList<>());
            getDiceTotal(0, 0, dice, combination.get(i), i);
        }

        int[] resultsCombination = combination.get(getWinCombination());
        for(int i = 0 ; i < resultsCombination.length; i++){
            resultsCombination[i]++;
        }
        return resultsCombination;
    }

    public int getWinCombination() {
        int maxWinCnt = 0, maxCombinationIdx = -1;
        for(int i = 0 ; i < combination.size() / 2; i++){

            Map<Integer, Integer> diceATotMap = new HashMap<>();
            Map<Integer, Integer> diceBTotMap = new HashMap<>();
            for(int tot : sumOfDiceList.get(i)) diceATotMap.merge(tot, 1, Integer::sum);
            for(int tot : sumOfDiceList.get(combination.size() - 1 - i)) diceBTotMap.merge(tot, 1, Integer::sum);

            int wincnt = 0, loseCnt = 0, combinationIdx = i;
            for(int totA :  diceATotMap.keySet()){
                for(int totB :  diceBTotMap.keySet()){
                    if (totA < totB) loseCnt += diceATotMap.get(totA) * diceBTotMap.get(totB);
                    else if (totA > totB) wincnt += diceATotMap.get(totA) * diceBTotMap.get(totB);
                }
            }

            if(wincnt < loseCnt) {
                wincnt = loseCnt;
                combinationIdx = combination.size() - 1 - i;
            }

            if(wincnt > maxWinCnt){
                maxWinCnt = wincnt;
                maxCombinationIdx = combinationIdx;
            }

        }

        return maxCombinationIdx;
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
