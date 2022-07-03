package implementation.problem5893;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String bin = sc.next();
        int a = Integer.parseInt(bin, 2) * 17;
        System.out.println(Integer.toBinaryString(a));
    }
}
