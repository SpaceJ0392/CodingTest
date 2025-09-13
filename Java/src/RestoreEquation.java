import java.util.*;

public class RestoreEquation {

    public static void main(String[] args) {
        RestoreEquation restoreEquation = new RestoreEquation();
        System.out.println(
                Arrays.toString(
                        restoreEquation.solution(new String[]{"1 + 1 = 2", "1 + 3 = 4", "1 + 5 = X", "1 + 2 = X"}
                )
        ));
        System.out.println(
                Arrays.toString(
                        restoreEquation.solution(new String[]{"1 + 1 = 2", "1 + 3 = 4", "1 + 5 = X", "1 + 2 = X"}
                )
        ));
    }

    public String[] solution(String[] expressions) {

        List<String> target = new ArrayList<>();
        List<String> evidence = new ArrayList<>();
        int minBase = 2;

        for (String exp : expressions) {
            if (exp.charAt(exp.length() - 1) == 'X') target.add(exp);
            else evidence.add(exp);

            String[] splitExp = exp.split(" ");
            for (int i = 0; i < splitExp.length - 1; i += 2) {
                int intItem = Integer.parseInt(splitExp[i]);
                if (intItem < 10) minBase = Math.max(minBase, intItem + 1);
            }
        }

        int[] targetBase = guessBaseNInExpressions(minBase, evidence);
        return getResult(targetBase, target);
    }

    public String[] getResult(int[] targetBase, List<String> target) {
        List<String> answer = new ArrayList<>();

        for (String targetItem : target) {
            Set<Integer> checker = new HashSet<>();
            for (int base : targetBase) {
                String[] targetItemSplit = targetItem.split(" ");
                int[] NumOfBaseN = new int[2];
                int bias = 0;
                for (int i = 0; i < targetItemSplit.length - 1; i += 2) {
                    NumOfBaseN[i - bias] = getNumToBaseN(base, targetItemSplit[i]);
                    bias++;
                }
                int res = targetItemSplit[1].equals("+") ? NumOfBaseN[0] + NumOfBaseN[1] : NumOfBaseN[0] - NumOfBaseN[1];
                if (res >= base) res = getBase10(base, res);
                checker.add(res);
            }
            if (checker.size() == 1) {
                String goal = checker.iterator().next().toString();
                answer.add(targetItem.replace("X", goal));
            } else answer.add(targetItem.replace('X', '?'));
        }

        return answer.toArray(String[]::new);
    }

    public int[] guessBaseNInExpressions(int minBase, List<String> evidence) {
        Set<Integer> targetBase = new HashSet<>();

        for (int base = minBase; base <= 9; base++) {
            boolean flag = false;
            for (String evi : evidence) {
                String[] splitEvi = evi.split(" ");
                int[] NumOfbaseN = new int[3];
                int bias = 0;
                for (int i = 0; i < splitEvi.length; i += 2) {
                    NumOfbaseN[i - bias] = getNumToBaseN(base, splitEvi[i]);
                    bias++;
                }

                if (splitEvi[1].equals("+"))
                    flag = NumOfbaseN[0] + NumOfbaseN[1] == NumOfbaseN[2];
                else flag = NumOfbaseN[0] - NumOfbaseN[1] == NumOfbaseN[2];
            }
            if (flag && !targetBase.add(base)) return new int[]{base};
        }

        return targetBase.stream().mapToInt(Integer::intValue).toArray();
    }

    public int getNumToBaseN(int baseType, String target) {

        int res = 0;
        for (char item : target.toCharArray()) {
            int intItem = Character.getNumericValue(item);
            if (intItem <= -1) continue;
            res = res * baseType + intItem;
        }
        return res;
    }

    public int getBase10(int baseType, int target) {
        StringBuilder decodeTarget = new StringBuilder();
        while (target / baseType != 0) {
            decodeTarget.append(target % baseType);
            target /= baseType;
        }
        decodeTarget.append(target);
        return Integer.parseInt(decodeTarget.reverse().toString());
    }
}
