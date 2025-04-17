import java.util.Arrays;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class FIndMaxMinOnString {
    public static void main(String[] args) {
        int[] array = Arrays.stream(args[0].split(" ")).mapToInt(Integer::parseInt).toArray();

        int max = array[0], min = array[0];
        for (int item : array) {
            if (item > max) max = item;
            if (item < min) min = item;
        }

        System.out.println(max + " " + min);
    }
}