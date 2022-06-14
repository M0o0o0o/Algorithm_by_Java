package simulation.problem14920;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int cnt = 1;
        while (num != 1) {
            if (num % 2 == 0) {
                num = num / 2;
            } else {
                num = (3 * num) + 1;
            }
            cnt += 1;
        }
        System.out.println(cnt);

    }
}
