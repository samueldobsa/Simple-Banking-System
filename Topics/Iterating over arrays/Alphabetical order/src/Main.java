import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        boolean alphabetical = true;
        while (scanner.hasNext()) {
            String nextInput = scanner.next();
            if (input.compareTo(nextInput) > 0) {
                alphabetical = false;
                break;
            }
            input = nextInput;
        }
        System.out.println(alphabetical);


    }


}