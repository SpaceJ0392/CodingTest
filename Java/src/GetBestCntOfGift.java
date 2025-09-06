import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GetBestCntOfGift {
    public static void main(String[] args) {
        GetBestCntOfGift g = new GetBestCntOfGift();
        g.solution(new String[]{"muzi", "ryan", "frodo", "neo"},
                new String[]{"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"});

    }

    public int solution(String[] friends, String[] gifts) {
        int[][] giftMap = new int[friends.length][friends.length];
        int[] gp = new int[friends.length];
        int[] predictGift = new int[friends.length];

        initGiftMap(giftMap, friends, gifts);
        initGp(giftMap, gp);

        for (int y = 0; y < friends.length; y++) {
            for (int x = 0; x < friends.length; x++) {
                if (y == x) continue;

                if (giftMap[y][x] > giftMap[x][y]) predictGift[y]++;
                else if (giftMap[y][x] < giftMap[x][y]) {
                    continue;
                }
                else {
                    if (gp[y] > gp[x]) predictGift[y]++;
                }
            }
        }
        return Arrays.stream(predictGift).max().orElse(0);
    }

    public void initGiftMap(int[][] giftMap, String[] friends, String[] gifts) {

        Map<String, Integer> friendNumber = new HashMap<>();
        for (int i = 0; i < friends.length; i++) {
            friendNumber.put(friends[i], i);
        }

        for (String gift : gifts) {
            String[] target = gift.split(" ");
            giftMap[friendNumber.get(target[0])][friendNumber.get(target[1])]++;
        }
    }

    public void initGp(int[][] giftMap, int[] gp) {

        for (int i = 0; i < giftMap.length; i++) {
            int giveCnt = Arrays.stream(giftMap[i]).sum();
            int getCnt = 0;
            for (int[] ints : giftMap) {
                getCnt += ints[i];
            }
            gp[i] = giveCnt - getCnt;
        }
    }
}

