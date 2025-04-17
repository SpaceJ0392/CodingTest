import java.util.Arrays;
//두 배열의 곱의 최솟값은 두 배열간 최소값과 최대값의 곱들로 이루어진다?
//
public class MakeMin {
    public static void main(String[] args) {
        int[] A = {1, 4, 2}, B = {5, 4, 4};
        Arrays.sort(A);
        Arrays.sort(B);

        int sum = 0, size = A.length;
        for (int i = 0; i < size; i++) {
            sum += A[i] * B[size - i - 1];
        }

        System.out.println(sum);
    }
}
