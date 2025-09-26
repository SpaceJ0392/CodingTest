import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RestoreEquation {

    public static void main(String[] args) {
        RestoreEquation restoreEquation = new RestoreEquation();
        System.out.println(
                Arrays.toString(
                        restoreEquation.solution(new String[]{"1 + 1 = 2", "1 + 3 = 4", "1 + 5 = X", "1 + 2 = X"}
                        )
                ));System.out.println(
                Arrays.toString(
                        restoreEquation.solution(new String[]{"14 + 3 = 17", "13 - 6 = X", "51 - 5 = 44"}
                        )
                ));
        System.out.println(
                Arrays.toString(
                        restoreEquation.solution(new String[]{"2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "8 + 4 = X"}
                        )
                ));
    }

    public String[] solution(String[] expressions) {

        List<String> answer = new ArrayList<>();
        List<String> target = new  ArrayList<>();
        List<String> evidence = new ArrayList<>();
        int minBase = getMinBase(expressions);
        splitExpressions(target, evidence, expressions);

        Set<Integer> candidateBase = IntStream.rangeClosed(minBase, 9).boxed().collect(Collectors.toSet());
        for (String exp : evidence) {
            findBase(exp, minBase, candidateBase);
        }


        for (String exp : target) {
            Set<String> candidateResult = new HashSet<>();
            for (int base : candidateBase) {
                candidateResult.add(getAnswer(base, exp));
            }

            answer.add(
                    candidateResult.size() == 1 ?
                    exp.replaceAll("X", candidateResult.iterator().next()) :
                    exp.replaceAll("X", "?")
            );
        }


        return answer.toArray(String[]::new);
    }

    public int getMinBase(String[] expressions) {
        int maxDigit = 0;
        for (String exp : expressions) {
            for(char item : exp.toCharArray()) {
                //if(item == '=') break;
                if(!Character.isDigit(item)) continue;
                maxDigit = Math.max(maxDigit, Character.getNumericValue(item));
            }
        }

        return Math.max((maxDigit + 1), 2);
    }

    public String getAnswer(int base, String exp) {
        String cmd = "";
        List<Long> encodingNum = new ArrayList<>();
        for (String arg : exp.split(" ")) {
            switch (arg) {
                case "+", "-":
                    cmd = arg;
                    continue;
                case "=", "X":
                    continue;
            }
            encodingNum.add(numToBase10(base, arg));
        }

        return switch (cmd) {
            case "+" -> numToBaseN(base, encodingNum.get(0) + encodingNum.get(1));
            case "-" -> numToBaseN(base, encodingNum.get(0) - encodingNum.get(1));
            default -> "0";
        };
    }

    public void findBase(String exp, int minBase, Set<Integer> candidateBase) {

        Set<Integer> removedBase = new HashSet<>();
        for (int base : candidateBase) {
            String cmd = "";
            List<Long> encodingNum = new ArrayList<>();
            for (String arg : exp.split(" ")) {
                switch (arg) {
                    case "+", "-":
                        cmd = arg;
                        continue;
                    case "=":
                        continue;
                }
                encodingNum.add(numToBase10(base, arg));
            }

            long result = switch (cmd) {
                case "+" -> encodingNum.get(0) + encodingNum.get(1);
                case "-" -> encodingNum.get(0) - encodingNum.get(1);
                default -> -1;
            };

            if (result != encodingNum.get(2)) removedBase.add(base);
        }

        candidateBase.removeAll(removedBase);
    }

    public void splitExpressions(List<String> target, List<String> evidence, String[] expressions) {

        for (String expression : expressions) {
            String res = expression.substring(expression.length() - 1);

            if (res.equals("X")) {
                target.add(expression);
            } else evidence.add(expression);
        }
    }

    public long numToBase10(int baseType, String target) {
        long res = 0;
        for (char item : target.toCharArray()) {
            long intItem = Character.getNumericValue(item);
            if (intItem <= -1) continue;
            res = res * baseType + intItem;
        }
        return res;
    }

    public String numToBaseN(int baseType, long target) {
        StringBuilder decodeTarget = new StringBuilder();
        while (target / baseType != 0) {
            decodeTarget.append(target % baseType);
            target /= baseType;
        }
        decodeTarget.append(target);
        return decodeTarget.reverse().toString();
    }
}
