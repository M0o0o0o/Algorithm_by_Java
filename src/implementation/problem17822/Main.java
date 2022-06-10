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
                    System.out.println(j);
                }
            }

            System.out.println("==== after rotate ====== ");

            for (int X = 1; X < n+1; X++) {
                for (int y = 0; y < m; y++) {
                    System.out.print(board.get(X).get(y) + " ");
                }
                System.out.println();
            }

            System.out.println();
            boolean isRemove = remove();
            if (!isRemove) plusOrMinus();

            System.out.println("==== after calc ====");
            for (int X = 1; X < n+1; X++) {
                for (int y = 0; y < m; y++) {
                    System.out.print(board.get(X).get(y) + " ");
                }
                System.out.println();
            }

            System.out.println();
            System.out.println();

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

            System.out.println(sum + " " + cnt);
            float avg =  ((float)sum / cnt);
            System.out.println(avg);
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
            for (int row = 1; row < n + 1; row++) {
                for (int col = 0; col < m; col++) {
//                    System.out.println();
//                    System.out.println("start::  row : " + row + " col : " + col + " num : " + board.get(row).get(col));

                    for (int i = 0; i < DX.length; i++) {
                        //    public static int[] DX = new int[]{0, 0, -1, 1};
                        //    public static int[] DY = new int[]{-1, 1, 0, 0};
                        // row == 1 and dx - 1 continue
                        if ((row == 1 && i == 2) || (row == n && i == 3)) continue;
                        int num = board.get(row).get(col);
                        if (num == 0) continue;
                        int nx, ny;
                        nx = DX[i] + row;
                        ny = DY[i] + col;
                        if (ny == -1) ny = m - 1;
                        if (ny == m) ny = 0;
                        if (num == board.get(nx).get(ny)) {
//                            System.out.println("    delete::  nx : " + nx + " ny : " + ny + " num : " + board.get(nx).get(ny));
                            board.get(row).set(col, 0);
                            board.get(nx).set(ny, 0);
                            flag = true;
                        }
                    }
                }

            }
            return flag;
        }


        // 번호가 xi의 배수인 원판을 di방향으로 ki칸 회전시킨다. di가 0인 경우는 시계 방향, 1인 경우는 반시계 방향이다.
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


