package binarysearch.problem2512;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int n;
    public static int[] list;
    public static long budget;
    public static long ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        list = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        budget = Long.parseLong(st.nextToken());

        Arrays.sort(list);

        int start = 0;
        int end = list[n - 1];

        while (true) {
            if(start > end) break;
            int mid = (start + end) / 2;
            long calc = sum(mid);
            if (calc <= budget) {
                start = mid + 1;
                ans = ans < mid ? mid : ans;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(ans);
    }
    public static long sum(int mid) {
        long res = 0;
        for (int i = 0; i < n; i++) {
            if(mid > list[i]) res += list[i];
            else res += mid;
        }
        return res;
    }
}
