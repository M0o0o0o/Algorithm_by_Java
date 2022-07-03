package showmethecode.drop7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    private static final int N = 7;
    private static int[][] init = new int[N][N+1];
    private static int[][] board = new int[N][N+1];
    private static int ball;
    private static LinkedList<int[]> remove = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        int ans = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                init[i][j] = Integer.parseInt(st.nextToken());
                if(init[i][j] > 0) ans++;
            }
        }
        ans += 1;
        ball = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            boardInit();
            if (board[0][i] != 0) continue;
            board[0][i] = ball;
            move();

            while (true) {
                remove.clear();
                horizon();
                verticle();

                if (remove.size() == 0) break;
                for (int[] rm : remove) {
                    board[rm[0]][rm[1]] = 0;
                }
                move();
            }
            ans = Math.min(ans, count());
        }
        System.out.println(ans);

        br.close();
    }

    public static void move() {
        for (int i = 0; i < N; i++) {
            while (true) {
                boolean isExit = true;
                for (int j = N - 1; j > 0; j--) {
                    if (board[j][i] == 0 && board[j - 1][i] > 0) {
                        board[j][i] = board[j - 1][i];
                        board[j - 1][i] = 0;
                        isExit = false;
                    }
                }
                if(isExit) break;
            }
        }
        return;
    }

    public static void horizon() {
        for (int i = 0; i < N; i++) {
            int cnt = 0;
            for (int j = 0; j < N + 1; j++) {
                if (board[i][j] > 0) {
                    cnt++;
                    continue;
                }
                for (int k = j - cnt; k < j; k++) {
                    if(cnt == board[i][k]) remove.add(new int[]{i, k});
                }
                cnt = 0;

            }
        }
    }

    public static void verticle() {
        for (int i = 0; i < N; i++) {
            int cnt = 0;
            for (int j = N - 1; j >= 0; j--) {
                if(board[j][i] > 0){cnt++;}
            }
            if(cnt == 0) continue;
            for (int j = N - 1; j >= 0; j--) {
                if (board[j][i] == cnt){
                    remove.add(new int[]{j, i});
                }
            }
        }
    }

    public static void boardInit() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = init[i][j];
            }
        }
    }

    public static int count() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(board[i][j] > 0) cnt++;
            }
        }
        return cnt;
    }


}
