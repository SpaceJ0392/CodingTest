import java.util.*;

public class ChooseEnoughTangerine {
    public static void main(String[] args) {
        for (String arg : args) {
            int k = Integer.parseInt(arg.substring(0,1));
            int[] tangerine = Arrays.stream(arg.substring(2).split(" "))
                                    .mapToInt(Integer::parseInt)
                                    .toArray();

            int answer = 0;
            Map<Integer, Integer> map = new HashMap<>();
            for(int item : tangerine){
                map.merge(item, 1, Integer::sum);
            }

            List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
            list.sort(Map.Entry.comparingByValue());

            for(int i = list.size() - 1; i >= 0; i--){
                k -= list.get(i).getValue();
                answer++;
                if(k <= 0) break;
            }

            System.out.println(answer);
        }
    }
}
