package tree.problem4803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/4803
public class Main {
    private static int n, m;
    private static ArrayList<Integer>[] tree;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int cnt = 1;
        while (true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if(n == 0 && m == 0) return;

            int ans = 0;
            tree = new ArrayList[n];
            visited = new boolean[n];
            for (int i = 0; i < n; i++) {
                tree[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                tree[from].add(to);
                tree[to].add(from);
            }

            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    if(dfs(-1, i)) ans++;
                }
            }

            if (ans == 0) {
                System.out.println("Case " + cnt + ": No trees.");
            } else if (ans == 1) {
                System.out.println("Case " + cnt + ": There is one tree.");
            } else {
                System.out.println("Case " + cnt + ": A forest of " + ans + " trees.");
            }
            cnt++;
        }


    }

    static private boolean dfs(int root, int now) {
        for (int next : tree[now]) {
            if(next == root) continue; // 자기 참조를 위한 확인 ex) 1->1
            if(visited[next]) return false;
            visited[next] = true;
            if(!dfs(now, next)) return false;
        }
        return true;

    }
}
