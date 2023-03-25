import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        int input = 0;

        do {
            input = scanner.nextInt();
            if (input % 2 == 0 && input != 0) {
                System.out.println("even");
            } else if (input % 2 != 0) {
                System.out.println("odd");
            }

        } while (input != 0) ;

    }
}
