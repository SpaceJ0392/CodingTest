import java.util.Arrays;

public class MatrixMultiplication {
    public static void main(String[] args) {
        int[][] arr1 = {{1, 4}, {3, 2}, {4 ,1}};
        int[][] arr2 = {{3, 3}, {3, 3}};
        int[][] answer = new int[arr1.length][arr2[0].length];

        for(int y = 0; y < arr1.length; y++){
            for(int x = 0; x < arr2[0].length; x++) {
                for(int p = 0; p < arr2.length; p++) {
                    answer[y][x] += arr1[y][p] * arr2[p][x];
                }
            }
        }

        for(int[] item : answer){
            System.out.println(Arrays.toString(item));
        }
    }
}
