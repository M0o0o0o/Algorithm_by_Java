package implementation.problem17822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17822
public class Main {

    public static int[] DX = new int[]{0, 0, -1, 1};
    public static int[] DY = new int[]{-1, 1, 0, 0};
    public static int n, m, t;
    public static ArrayList<ArrayList<Integer>> board = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        board.add(new ArrayList<>());

        for (int i = 1; i < n + 1; i++) {
            board.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < t; i++) {
            int x, d, k;
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            for (int j = 1; j < n + 1; j++) {
                if (j % x == 0) {
                    rotate(d, k, j);
                }
            }
            boolean isRemove = remove();
            if (!isRemove) plusOrMinus();
        }

        int ans = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < m; j++) {
                ans += board.get(i).get(j);
            }
        }

        System.out.println(ans);
        br.close();

    }


        private static void plusOrMinus () {

            int sum = 0;
            int cnt = 0;
            for (int i = 1; i < n + 1; i++) {
                for (int j = 0; j < m; j++) {
                    sum += board.get(i).get(j);
                    if (board.get(i).get(j) > 0) cnt++;
                }
            }

            float avg =  ((float)sum / cnt);
            for (int i = 1; i < n + 1; i++) {
                for (int j = 0; j < m; j++) {
                    if(board.get(i).get(j) == 0 ) continue;
                    if (board.get(i).get(j) > avg) board.get(i).set(j, board.get(i).get(j) - 1);
                    else if (board.get(i).get(j) < avg) board.get(i).set(j, board.get(i).get(j) + 1);
                }
            }

            return;
        }


        private static boolean remove () {
            boolean flag = false;
            int[][] buf = new int[n + 1][m];
            for (int row = 1; row < n + 1; row++) {
                for (int col = 0; col < m; col++) {

                    for (int i = 0; i < DX.length; i++) {
                        if ((row == 1 && i == 2) || (row == n && i == 3)) continue;
                        int num = board.get(row).get(col);
                        if (num == 0) continue;
                        int nx, ny;
                        nx = DX[i] + row;
                        ny = DY[i] + col;
                        if (ny == -1) ny = m - 1;
                        if (ny == m) ny = 0;
                        if (num == board.get(nx).get(ny)) {
                            buf[row][col] = -1;
                            buf[nx][ny] = -1;
                            flag = true;
                        }
                    }

                }

            }
            for (int i = 1; i < n + 1; i++) {
                for (int j = 0; j < m; j++) {
                    if(buf[i][j] == -1) board.get(i).set(j, 0);
                }
            }
            return flag;
        }

        private static void rotate( int d, int k, int j){

            int[] buf = new int[m];
            k %= m;
            d = d == 1 ? -k : k;
            for (int i = 0; i < m; i++) {
                int loc = i + d;
                if (loc >= m) loc -= m;
                if (loc < 0) loc += m;
                buf[loc] = board.get(j).get(i);
            }
            for (int i = 0; i < m; i++) {
                board.get(j).set(i, buf[i]);
            }
            return;
        }
}


