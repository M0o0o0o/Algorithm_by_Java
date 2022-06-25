package binarysearch.problem3079;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[] lists;
    private static long max = 0;
    private static long min = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        lists = new int[n];

        for (int i = 0; i < n; i++) {
            lists[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, lists[i]);
        }
        solve();
        System.out.println(min);
    }

    private static void solve() {
        long left = 0;
        long right = max * m;

        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0;

            for (int list : lists) {
                long people = mid / list;

                if (sum >= m) break;
                sum += people;
            }

            if (sum >= m) {
                min = Math.min(min, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    }
}
