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
        // 1. 답이 있는 친구들 고르기.
        // 2. 답이 있는 친구들을 통해 2 ~ 9진수 찾기.
        // 3. 1개가 있으면, 그대로 X에 답 채우기.
        // 3-1. 여러개가 있으면 X가 답이 될 수 있는지 없는지 확인?

        List<String> answer = new ArrayList<>();
        List<String> target = new ArrayList<>();
        int minBase = 2;
        for(String expression : expressions) {
            String[] splitExp =  expression.split(" ");
            int arg1 = Integer.parseInt(splitExp[0]),  arg2 = Integer.parseInt(splitExp[2]);
            String cmd = splitExp[1], res = splitExp[3];

            if(res.equals("X")) { answer.add(expression); }
            else target.add(expression);

            int candidateBase = (arg1 < 10 && arg2 < 10) ? Math.max(arg1, arg2) : Math.min(arg1, arg2);
            minBase = candidateBase >= 10 ? Math.min(minBase, candidateBase) : Math.max(minBase, candidateBase);
        }

        for(String exp : target) {
            findBase(exp, minBase);
        }
        return answer.toArray(String[]::new);
    }

    public void findBase(String exp, int minBase) {

    }

    public int numToBaseN(int baseType, String target) {
        int res = 0;
        for (char item : target.toCharArray()) {
            int intItem = Character.getNumericValue(item);
            if (intItem <= -1) continue;
            res = res * baseType + intItem;
        }
        return res;
    }

    public int numToBase10(int baseType, int target) {
        StringBuilder decodeTarget = new StringBuilder();
        while (target / baseType != 0) {
            decodeTarget.append(target % baseType);
            target /= baseType;
        }
        decodeTarget.append(target);
        return Integer.parseInt(decodeTarget.reverse().toString());
    }
}
