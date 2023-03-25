import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int a = scanner.nextInt();
        int[] array = new int[a];
        int[] newArray = new int[a];


        for (int i= 0; i < array.length; i++){
            array[i] = scanner.nextInt();
            for (int j = 1; j < newArray.length; j++){

                newArray[j] = array[j-1];

            }
        }newArray[0] = array[array.length -1];
        String formattedString = Arrays.toString(newArray).replace("[","").replace("]", "").replace(",", "");
        System.out.println(formattedString);


    }
}