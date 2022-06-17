package binarysearch.problem1072;

import java.util.Scanner;

// https://www.acmicpc.net/problem/1072
public class Main {

    private static long total, win, ans = -1;
    private static double percent;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = calc(x, y);

        int ans = -1;
        int left = 0;
        int right = (int) 1e9;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (calc(x + mid, y + mid) != z) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(ans);
    }
    static int calc(int x, int y) {
        return (int) ((long) y * 100 / x);
    }
}
