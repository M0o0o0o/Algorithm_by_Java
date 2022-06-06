package implementation.problem14890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14890
public class Main {

    public static int[][] graph = new int[100][100];
    public static int n, l;

    public static int[][] rotate90() {
        int[][] buf = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                buf[j][n - 1 - i] = graph[i][j];
            }
        }
        return buf;
    }

    public static boolean solve(int[] row) {
        boolean check = false;
        int level = row[0];
        int cnt = 1;
        int idx = 1;
        while (idx < n) {
            if (check) {
                check = false;
                idx++;
                continue;
            }
            // 차이가 2이상인 경우
            if(Math.abs(row[idx] - level) > 1) return false;
            // 같은 높이인 경우
            if (row[idx] == level) {
                cnt++;
                idx++;
                continue;
            }
            // 높이 차이가 직전에 level 보다 1이 큰 경우
            if (row[idx] - 1 == level) {
                if(cnt < l) return false;
                level = row[idx];
                cnt = 1;
                idx++;
                continue;
            }

            if (row[idx] + 1 == level) {
                int c = 0;
                int j = idx;
                while (j < n) {
                    if (row[j] == row[idx]) {
                        c++;
                        j++;
                    } else {
                        break;
                    }
                }
                if(c < l) return false;
                if(j == n) return true;
                idx = j - 1;
                cnt = c - l;
                level = row[idx];
                check = true;
            }
        }
        return true;
    }

    public static int[] reverse(int[] row) {
        int[] reversed_row = new int[n];
        for (int i = 0; i < n; i++) {
            reversed_row[i] = row[i];
        }
        return reversed_row;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        int ans = 0;


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] rotate_graph = rotate90();

        for (int i = 0; i < n; i++) {
            if (solve(graph[i])) {
                ans++;
            }
            if (solve(rotate_graph[i])) {
                ans++;
            }

        }
        System.out.println(ans);

    }
}
