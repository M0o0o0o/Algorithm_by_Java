package implementation.problem17135;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

// https://www.acmicpc.net/problem/17135
public class Main {
    public static int n, m, d;
    public static int[][] board;
    public static int ans;

    public static int gameStart(int[][] board, int[] archers) {
        int cnt = 0;
        ArrayList<int[]> targets = new ArrayList<>();
        while (checkGameEnd(board)) {
            targets.clear();
            for (int archer : archers) {
                int[] t = hit(board, archer);
                if(t[0] == -1 && t[1] == -1) continue;
                targets.add(t);
            }
            for (int[] t : targets) {
                if (board[t[0]][t[1]] == 1) {
                    board[t[0]][t[1]] = 0;
                    cnt++;
                }
            }
            move(board);
        }
        return cnt;
    }

    public static int[] hit(int[][] board, int archer) {
        int minDistance = 11;
        int[] res = new int[]{-1, -1};
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                int now = Math.abs(n - i) + Math.abs(archer - j);
                if (now <= d  && board[i][j] == 1) {
                    if (now < minDistance || (now == minDistance && res[1] != -1 && res[1] > j)) {
                        minDistance = now;
                        res[0] = i;
                        res[1] = j;
                    }
                }
            }
        }
        return res;
    }

    public static void move(int[][] board) {
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                board[i + 1][j] = board[i][j];
                if (i == 0) {
                    board[i][j] = 0;
                }
            }
        }
        return;
    }

    public static boolean checkGameEnd(int[][] board) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(board[i][j] == 1) return true;
            }
        }
        return false;
    }
    public static int[][] copy(int[][] board) {
        int[][] buf = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                buf[i][j] = board[i][j];
            }
        }

        return buf;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        d = sc.nextInt();
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = i+1; j < m; j++) {
                for (int k = j+1; k < m; k++) {
                    int cnt = gameStart(copy(board), new int[]{i, j, k});
                    ans = Math.max(ans, cnt);
                }
            }
        }

        System.out.println(ans);

    }
}
