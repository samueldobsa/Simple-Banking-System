import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] array = new int[size];

        for (int i = 0; i < array.length; i++){
            array[i] = scanner.nextInt();
        }
        int result = IntStream.of(array).min().orElse(Integer.MAX_VALUE);

        System.out.println(result);

    }
}