import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
//        int number = scanner.nextInt();
        System.out.println(gcd(4, 6));

//        switch (number){
//            case 1:
//                System.out.println("Yes!");
//                break;
//            case 2:
//                System.out.println("No!");
//                break;
//            case 3:
//                System.out.println("No!");
//                break;
//            case 4:
//                System.out.println("No!");
//                break;
//            default:
//                System.out.println("Unknown number");
//        }

    }
    public static int gcd(int a, int b) {
        while (b > 0) {
            int c = a % b;
            a = b;
            b = c;
        }
        return a;
    }
}