import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        int[] array = new int[size];


        for (int i = 0; i < size; i++){
            array[i] = scanner.nextInt();
        }

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        boolean check = true;


        for (int j = 0; j < array.length - 1; j++){
            if (n == array[j] && m == array[j + 1]
                    || m == array[j] && n == array[j + 1]) {
                check = false;
                break;
            }
        }
        System.out.println(check);

    }
}