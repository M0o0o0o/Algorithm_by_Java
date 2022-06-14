package simulation.problem10811;

import java.util.Scanner;

public class Main {
    private static int n, m;
    private static int[] list = new int[101];
    private static int[] buf = new int[101];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i < n+1; i++) {
            list[i] = i;
        }

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            for (int j = to; j >= from; j--) {
                buf[to - j] = list[j];
            }
            for (int j = from; j <= to; j++) {
                list[j] = buf[j - from];
            }
        }

        for (int i = 1; i < n + 1; i++) {
            System.out.print(list[i] + " ");
        }
    }
}
