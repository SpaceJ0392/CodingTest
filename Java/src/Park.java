import java.util.Arrays;
import java.util.OptionalInt;

public class Park {
    public static void main(String[] args) {
        Park park = new Park();
        System.out.println(
                park.solution(new int[]{5, 3, 2},
                              new String[][]{{"A", "A", "-1", "B", "B", "B", "B", "-1"},
                                             {"A", "A", "-1", "B", "B", "B", "B", "-1"},
                                             {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"},
                                             {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"},
                                             {"D", "D", "-1", "-1", "-1", "-1", "-1", "F"},
                                             {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"}})
        );
    }

    private int solution(int[] mats, String[][] park) {
        int result = 0;
        int[][] paddingPark = padding(park);
        int[][] dpPark = new int[paddingPark.length][paddingPark[0].length];

        for(int y = 1; y < paddingPark.length - 1; y++){
            for(int x = 1; x < paddingPark[y].length - 1; x++){
                if(paddingPark[y][x] == -1){
                    OptionalInt min = Arrays.stream(new int[] {dpPark[y - 1][x], dpPark[y - 1][x - 1], dpPark[y][x - 1]}).min();
                    dpPark[y][x] =  min.getAsInt() + 1;

                    if(dpPark[y][x] >= result){ result = dpPark[y][x]; }
                }
            }
        }

        Arrays.sort(mats);
        for(int i = mats.length - 1; i >= 0; i--){
            if(result >= mats[i]){
                return mats[i];
            }
        }
        return -1;
    }

    private int[][] padding(String[][] park) {
        int rowLen = park.length + 2, colLen = park[0].length + 2;
        int[][] paddingPark = new int[rowLen][colLen];
        for(int y = 0; y < paddingPark.length; y++){
            for(int x = 0; x < paddingPark[y].length; x++){
                if(y == 0 || y == rowLen - 1 || x == 0 || x == colLen - 1){
                    paddingPark[y][x] = 1;
                }
                else{
                    paddingPark[y][x] = park[y - 1][x - 1].equals("-1") ? -1 : 1;
                }
            }
        }

        return paddingPark;
    }
}
