package implementation.problem19236;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static int[][][] board = new int[4][4][2];
    public static final int[] DX = new int[]{0, -1, -1, 0, 1, 1, 1, 0, -1};
    public static final int[] DY = new int[]{0, 0, -1, -1, -1, 0, 1, 1, 1};
    public static final int X = 4;
    public static final int Y = 4;
    public static final int Z = 2;
    public static int ans;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                for (int k = 0; k < Z; k++) {
                    board[i][j][k] = sc.nextInt();
                }
            }
        }
        solve(0, 0, board);
        System.out.println(ans);
    }

    private static void solve(int x, int y, int[][][] board) {
        int[][][] buf = new int[4][4][2];
        int s = board[x][y][0];
        ans = ans < s ? s : ans;
        int d = board[x][y][1];
        System.out.println(s + " " + d);
        board[x][y][0] = board[x][y][1] = 0;

        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                buf[i][j][0] = board[i][j][0];
                buf[i][j][1] = board[i][j][1];
            }
        }


//        System.out.println("==================");
        buf = fishMove(x, y, buf);
//        System.out.println();
//        for (int i = 0; i < X; i++) {
//            for (int j = 0; j < Y; j++) {
//                System.out.print(buf[i][j][0] + ":" +buf[i][j][1] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
        boolean isMove = false;
        int nx = x;
        int ny = y;

        while (true) {
//            for (int i = 0; i < X; i++) {
//                for (int j = 0; j < Y; j++) {
//                    System.out.print(buf[i][j][0] + ":" +buf[i][j][1] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println(nx + ", " + ny + "  " + (nx + DX[d]) + ", " + (ny + DY[d]) +" ,,, "+ d);


            nx += DX[d];
            ny += DY[d];

            if (nx >= 0 && nx < X && ny >= 0 && ny < Y) {
                if(buf[nx][ny][1]  == 0 && buf[nx][ny][0] == 0) continue;
                isMove = true;
                buf[nx][ny][0] += s;
                solve(nx, ny, board);
                buf[nx][ny][0] -= s;
            } else break;
        }
        return;
    }

    private static int[][][] fishMove(int x, int y, int[][][] buf) {
        HashMap<Integer, int[]> fishes = new HashMap<>();
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if(x == i && y == j ) continue;
                if(buf[i][j][0] == 0 && buf[i][j][1] == 0) continue;
                int size = buf[i][j][0];
                int direction = buf[i][j][1];
                fishes.put(size, new int[]{i, j, direction});
            }
        }

        for (int i = 1; i <= 16; i++) {
            int[] value = fishes.getOrDefault(i, new int[]{-1, -1, -1});
            if(value[0] == -1) continue;
            int n = value[0];
            int m = value[1];
            int d = value[2];
            int cnt = 0;
            while (true) {
                if( cnt == 8) break;
                int nx = n + DX[d];
                int ny = m + DY[d];
                int[] fb = null;
                if (nx >= 0 && nx < X && ny >= 0 && ny < Y && (nx != x && ny != y)) {
                    if (buf[nx][ny][0] > 0 && buf[nx][ny][1] > 0)
                        fb = new int[]{nx, ny, buf[nx][ny][0], buf[nx][ny][1]};

                    buf[nx][ny][0] = buf[n][m][0];
                    buf[nx][ny][1] = buf[n][m][1];
                    buf[n][m][0] = buf[n][m][1] = 0;

                    if (fb != null) {
                        buf[n][m][0] = fb[2];
                        buf[n][m][1] = fb[3];
                        fishes.put(fb[1], new int[]{fb[0], fb[1], fb[3]});
                    }
                    break;
                }
                d--;
                cnt++;
                if(d == 0) d = 8;
            }
        }
        return buf;
    }
}
