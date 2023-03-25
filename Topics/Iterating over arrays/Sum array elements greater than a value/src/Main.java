import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int arraySize = scanner.nextInt();
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = scanner.nextInt();
        }
        int number = scanner.nextInt();
        int sum = 0;
        for (int num:array) {
            if (num > number) {
                sum += num;
            }
        }
        System.out.println(sum);
    }



}