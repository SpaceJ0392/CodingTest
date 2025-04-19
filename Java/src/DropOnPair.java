import java.util.Stack;

public class DropOnPair {
    public static void main(String[] args) {
        for (String arg : args) {
            Stack<Character> stack = new Stack<>();
            for (char c : arg.toCharArray()) {
                if (stack.isEmpty() || stack.peek() != c) stack.push(c);
                else if (stack.peek() == c) stack.pop();
            }

            if (stack.isEmpty()) System.out.println("1");
            else System.out.println("0");
        }
    }
}
