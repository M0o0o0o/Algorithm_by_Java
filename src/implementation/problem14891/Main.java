package implementation.problem14891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14891
public class Main {
    public static ArrayList<LinkedList<Integer>> board = new ArrayList<>();
    public static boolean[] visited = new boolean[4];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            String[] buf = st.nextToken().split("");
            board.add(new LinkedList<>());
            for (int j = 0; j < buf.length; j++) {
                board.get(i).add(Integer.parseInt(buf[j]));
            }
        }
        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            init();
            solve(Integer.parseInt(st.nextToken()) -1 ,Integer.parseInt(st.nextToken()));
        }
        int ans = 0;
        for (int i = 0; i < 4; i++) {
            if(board.get(i).get(0) == 0 ) continue;
            ans += Math.pow(2, i);
        }

        System.out.println(ans);
    }

    private static void solve(int n, int d) {
        visited[n] = true;
        int left = board.get(n).get(6);
        int right = board.get(n).get(2);
        int newD = d == 1 ? -1 : 1;

        if (d == 1) {
            board.get(n).add(0, board.get(n).pollLast());

        } else {
            board.get(n).add(board.get(n).pollFirst());
        }
        if (n - 1 >= 0 && !visited[n - 1] && board.get(n - 1).get(2) != left) {
            solve(n - 1, newD);
        }
        if (n + 1 < 4 && !visited[n + 1] && board.get(n + 1).get(6) != right) {
            solve(n + 1, newD);
        }
    }

    private static void init() {
        for (int i = 0; i < 4; i++) {
            visited[i] = false;
        }
    }
}
