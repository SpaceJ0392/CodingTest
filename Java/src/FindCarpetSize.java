public class FindCarpetSize {
    public static void main(String[] args) {
        for (String arg : args) {
            String[] target = arg.split(" ");
            int brown = Integer.parseInt(target[0]), yellow = Integer.parseInt(target[1]);

            for (int y = 1; y <= (yellow >= 2 ? yellow / 2 : 1); y++) {
                if (yellow % y != 0) continue;

                int x = yellow / y;
                if ((x + y) * 2 + 4 == brown){
                    System.out.println((x + 2) + " " + (y + 2));
                    break;
                }
            }
        }
    }
}
