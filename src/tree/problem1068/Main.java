package tree.problem1068;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1068
public class Main {
    private static int n;
    private static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    private static int start;
    private static int remove;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                start = i;
                continue;
            }
            tree.get(parent).add(i);
        }

        st = new StringTokenizer(br.readLine());
        remove = Integer.parseInt(st.nextToken());
        visited[start] = true;
        System.out.println(dfs(start, -1));
    }

    private static int dfs(int now, int parent) {
        if (now == remove) {
            if (parent != -1 &&tree.get(parent).size() == 1) {
                return 1;
            }
            return 0;
        }
        if (tree.get(now).size() == 0) {
            return 1;
        }
        int cnt = 0;

        for (Integer next : tree.get(now)) {
            if (!visited[next]) {
                visited[next] = true;
                cnt += dfs(next, now);
            }
        }
        return cnt;
    }
}

