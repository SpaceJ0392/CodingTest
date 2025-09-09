import java.util.*;

public class PCCE10DataAnalysis {
    public static void main(String[] args) {
        PCCE10DataAnalysis dataAnalysis = new PCCE10DataAnalysis();
        System.out.println(Arrays.deepToString(
                dataAnalysis.solution(
                        new int[][]{{1, 20300104, 100, 80}, {2, 20300804, 847, 37}, {3, 20300401, 10, 8}},
                        "date", 20300501, "remain")
        ));
    }

    private int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        List<int[]> answer = new ArrayList<>();
        Map<String, Integer> typeMap = Map.of("code", 0, "date", 1, "maximum", 2, "remain", 3);

        for(int[] item : data){
            if(item[typeMap.get(ext)] > val_ext) continue;
            answer.add(item);
        }

        answer.sort(Comparator.comparing(item -> item[typeMap.get(sort_by)]));
        return answer.toArray(int[][]::new);
    }
}
