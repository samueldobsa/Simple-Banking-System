import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int number = scanner.nextInt();
        boolean isTrue;

        if (number < 10 && number > 0){
            isTrue = true;
            System.out.println(isTrue);
        }else {
            isTrue = false;
            System.out.println(isTrue);
        }
    }
}