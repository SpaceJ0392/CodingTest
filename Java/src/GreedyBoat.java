import java.util.Arrays;

public class GreedyBoat {
    public static void main(String[] args) {
        for (String arg : args) {
            int[] items = Arrays.stream(arg.split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] people = Arrays.stream(items).limit(items.length - 1).toArray();
            int limit = items[items.length - 1];

            Arrays.sort(people);
            int answer = 0, j = 0;
            for (int i = people.length - 1; i >= 0; i--) {
                if (j > i) break;
                if (people[j] + people[i] <= limit) j++;
                answer++;
            }

            System.out.println(answer);
        }
    }
}