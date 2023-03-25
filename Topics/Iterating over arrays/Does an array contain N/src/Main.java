import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] array = new int[size];
        boolean output = false;

        for (int i = 0; i < array.length; i++){
            array[i] = scanner.nextInt();
        }
        int n = scanner.nextInt();
        for(int ch : array){
            if (n == ch){
                output = true;
            }
        }

        System.out.println(output);

    }
}