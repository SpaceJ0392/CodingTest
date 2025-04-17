public class FindRightBrackets {
    public static void main(String[] args) {
        for (String arg : args) {

            boolean found = true;
            int count = 0;
            for (char c : arg.toCharArray()) {
                if (c == '(') count++;
                else if (count != 0 && c == ')') count--;
                else {
                    found = false;
                    break;
                }
            }
            if (count != 0) found = false;
            System.out.println(found);
        }
    }
}
