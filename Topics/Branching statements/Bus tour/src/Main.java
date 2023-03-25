import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        int busHeight = scanner.nextInt();
        int numOfBridges = scanner.nextInt();
        int bridgeHeight;
        boolean crash = false;

        for (int i = 1; i <= numOfBridges; i++) {
            bridgeHeight = scanner.nextInt();
            if (busHeight >= bridgeHeight) {
                System.out.printf("Will crash on bridge %d\n", i);
                crash = true;
                break;
            }
        }
        if (!crash) {
            System.out.println("Will not crash");
        }
    }
}