package implementation.problem14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14499
public class Main {
    static int[] dice = new int[7];
    static int n,m,x, y, k;
    static int[][] board;
    static int[] DX = {1, -1, 0, 0};
    static int[] DY = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int d = Integer.parseInt(st.nextToken());
            move(d);
        }
    }

    static void move(int d) {
        int nx = x + DX[d - 1];
        int ny = y + DY[d - 1];
        if(nx < 0 || ny < 0 || nx > m-1 || ny > n-1 ) return;
        roll(d, nx, ny);
        x = nx;
        y = ny;
    }

    static void roll(int d, int x, int y) {
        int temp = dice[3];
        if (d == 1) {
            dice[3] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[2];
            dice[2] = temp;

        } else if (d == 2) {
            dice[3] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[4];
            dice[4] = temp;

        } else if (d  == 3) {
            dice[3] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[1];
            dice[1] = temp;
        } else {
            dice[3] = dice[1];
            dice[1] = dice[6];
            dice[6] = dice[5];
            dice[5] = temp;
        }

        if (board[y][x] == 0) {
            board[y][x] = dice[6];
        } else {
            dice[6] = board[y][x];
            board[y][x] = 0;
        }
        System.out.println(dice[3]);

    }
}

