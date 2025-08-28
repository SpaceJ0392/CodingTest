import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DecodeSecretCode {
    public static void main(String[] args) {
        int[] item = {1, 1};
        Map<Integer, Set<int[]>> caseMap = new HashMap<>();
        caseMap.merge(1, Set.of(item), caseMap.get(1).add(item));
    }
}
