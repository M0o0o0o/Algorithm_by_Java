package dp.problem11066;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11066
public class Main {
    private static int t;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());
        for (int i = 0; i < t; i++) {
            int k = Integer.parseInt(br.readLine());
            int[] list = new int[k + 1];
            int[] sum = new int[k + 1];
            int[][] dp = new int[k + 1][k + 1];

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= k; j++) {
                list[j] = Integer.parseInt(st.nextToken());
                sum[j] = sum[j - 1] + list[j];
            }

            for (int n = 1; n <= k; n++) {
                for (int start = 1; start + n <= k; start++) {
                    int end = start + n;
                    dp[start][end] = Integer.MAX_VALUE;
                    for (int w = start; w < end; w++) {
                        // dp[from][to] = Math.min(dp[from][to], dp[from][divide] + dp[divide + 1][to] + sum[to] - sum[from - 1]);
                        dp[start][end] = Math.min(dp[start][end], dp[start][w] + dp[w + 1][end] + sum[end] - sum[start - 1]);
                    }
                }
            }

        System.out.println(dp[1][k]);
        }


    }
}
