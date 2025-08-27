import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OddEvenTree {

    public static void main(String[] args) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= 100; i++) {
            map.merge(i, new ArrayList<>(List.of(i)), List::add);
        }

    }
}
