import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        int a = scanner.nextInt();
        int count = 0;

        for(int i = 1; i <= a; i++) {
            for (int j = 1; j <= i; j++) {
                if(count == a) {
                    break;
                }
                count++;
                System.out.print(i + " ");
            }
        }
    }
}