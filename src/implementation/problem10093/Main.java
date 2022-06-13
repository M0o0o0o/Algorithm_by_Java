package implementation.problem10093;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        if (a > b) {
            long buf = a;
            a = b;
            b = buf;
        }
        if (a == b) {
            System.out.println(0);
            return;
        }
        System.out.println(b - a - 1);

        for (long i = a + 1; i < b; i++) {
            System.out.print(i + " ");
        }
    }
}
