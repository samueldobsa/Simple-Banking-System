import java.lang.reflect.Array;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        int x = scanner.nextInt();
        int count = 1;
        int max = 0;

        while (count <= x) {
            int y = scanner.nextInt();
            if (y % 4 == 0 && y > max) {
                max = y;
            }
            count++;
        }
        System.out.println(max);
    }
}