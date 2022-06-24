package tree.problem11437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    private static int[] parent, depth;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        depth = new int[n + 1];
        parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            tree.get(from).add(to);
            tree.get(to).add(from);
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        dfs(1, 1);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            System.out.println(lca(from, to));
        }

        br.close();
    }

    private static int lca(int a, int b) {
        int ad = depth[a];
        int bd = depth[b];
        if (ad > bd) {
            while (ad != bd) {
                ad--;
                a = parent[a];
            }
        } else if (ad < bd) {
            while (ad != bd) {
                bd--;
                b = parent[b];
            }

        }

        while (a != b) {
            a = parent[a];
            b = parent[b];

        }
        return a;
    }

    private static void dfs(int x, int cnt) {
        depth[x] = cnt++;
        for (Integer child : tree.get(x)) {
            if (depth[child] == 0) {
                parent[child] = x;
                dfs(child, cnt);
            }
        }
    }
}
