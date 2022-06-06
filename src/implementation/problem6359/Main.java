package implementation.problem6359;

import java.util.Scanner;

//https://www.acmicpc.net/problem/6359
public class Main {
    public static int tc;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        tc = sc.nextInt();
        boolean[] rooms;

        for (int t = 0; t < tc; t++) {
            int n = sc.nextInt();
            int ans = 0;
            rooms = new boolean[n + 1];

            for (int i = 1; i < n + 1; i++) {
                for (int j = i; j < n + 1; j += i) {
                    rooms[j] = !rooms[j];
                }
            }

            for (int i = 1; i < n + 1; i++) {
                if (rooms[i]) ans += 1;
            }
            System.out.println(ans);

        }
        sc.close();
    }
}
