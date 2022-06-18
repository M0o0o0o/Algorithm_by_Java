package string.problem3062;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < sc.nextInt(); i++) {
            String input = sc.nextLine();
            input = String.valueOf(Integer.parseInt(input) + Integer.parseInt(new StringBuffer(input).reverse().toString()));
            boolean res = true;
            char[] arr = input.toCharArray();
            for (int j = 0; j < arr.length; j++) {
                System.out.println("hi");
                if (arr[j] != arr[arr.length - j - 1]) {
                    res = false;
                    break;
                }
            }
            if(res) System.out.println("YES");
            else System.out.println("NO");

        }

    }
}
