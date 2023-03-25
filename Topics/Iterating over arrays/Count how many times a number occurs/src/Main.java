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
        int sum = 0;
        int number = scanner.nextInt();
        for (int a : array){
            if (a == number){
                sum++;
            }
        }
        System.out.println(sum);

    }
}