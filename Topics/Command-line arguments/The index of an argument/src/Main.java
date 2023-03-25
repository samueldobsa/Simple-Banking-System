class Problem {
    public static void main(String[] args) {
        int n = 3;
        int b = 0;
        String argument = "test";

        for (int i = 0; i < n; i++){
            if (args[i].equals("test")){
                b = i;
            }else {
                b = -1;
            }
        }
        System.out.println(b);
    }
}