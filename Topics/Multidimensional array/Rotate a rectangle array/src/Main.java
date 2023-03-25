import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int m = scanner.nextInt();
//        int[][] array = new int[n][m];
//        int[][] array1 = new int[n + 1][m];
//
//        for (int i = 0; i < n; i++){
//            for (int j = 0; j < m; j++){
//                array[i][j] = scanner.nextInt();
//            }
//            for (int cal = 0; cal < m; cal++){
//                for (int row = n - 1; row >= 0; row--){
//                    array1[cal][row] = array[n][m];
//                }
//            }
//        }
//
//        System.out.println(Arrays.toString(array1));
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] array = new int[n][m];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = scanner.nextInt();
            }
        }
        for (int j = 0; j < m; j++) {
            for (int i = n -1 ; i >= 0; i--) {
                System.out.print(array[i][j] + " ");
            }

            System.out.println(" ");
        }
    }
}