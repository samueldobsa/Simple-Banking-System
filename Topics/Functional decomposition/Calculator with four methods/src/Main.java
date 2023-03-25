class SimpleCalculator {

    // Implement your methods here
    public static void subtractTwoNumbers(long n, long p){
        long number1 = n;
        long number2 = p;
        System.out.println(number1 - number2);
    }


    public static void sumTwoNumbers(long n, long p){
        long number = n;
        long number2 = p;
        System.out.println(number + number2);
    }


    public static void divideTwoNumbers(long n, long p){
        long number1 = n;
        long number2 = p;
        if (number1 == 0 || number2 == 0){
            System.out.println("Division by 0!");
        }else {
            System.out.println(number1 / number2);
        }
    }

    public static void multiplyTwoNumbers(long n, long p){
        long number1 = n;
        long number2 = p;
        System.out.println(number1 * number2);
    }

    // Implemented method
    public static void power(long n, long p) {
        long number = n;
        long power = p;
        long result = 1;
        while (power > 0) {
            if (power % 2 != 0) {
                result *= number;
            }
            power /= 2;
            number *= number;
        }
        System.out.println(result);
    }
}

public class Main {

    public static void main(String[] args) {

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        long num1 = scanner.nextLong();
        String operator = scanner.next();
        long num2 = scanner.nextLong();

        switch (operator) {
            case "^":
                SimpleCalculator.power(num1, num2);
                break;
            case "+":
                SimpleCalculator.sumTwoNumbers(num1, num2);
                break;
            case "-":
                SimpleCalculator.subtractTwoNumbers(num1, num2);
                break;
            case "/":
                SimpleCalculator.divideTwoNumbers(num1, num2);
                break;
            case "*":
                SimpleCalculator.multiplyTwoNumbers(num1, num2);
                break;
            default:
                break;
        }
    }
}