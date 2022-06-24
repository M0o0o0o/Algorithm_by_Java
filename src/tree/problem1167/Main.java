package tree.problem1167;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {

    public static class Node {
        int e;
        int cost;

        public Node(int e, int cost) {
            this.e = e;
            this.cost = cost;
        }
    }

    static ArrayList<Node>[] tree;
    static boolean[] visited;
    static int ans = 0;
    static int node;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        tree = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if(to == -1) break;
                int cost = Integer.parseInt(st.nextToken());
                tree[from].add(new Node(to, cost));
            }
        }

        visited = new boolean[n + 1];
        dfs(1, 0);

        visited = new boolean[n + 1];
        dfs(node, 0);

        System.out.println(ans);
        br.close();
    }

    static private void dfs(int x, int len) {
        if (len > ans) {
            ans = len;
            node = x;
        }

        visited[x] = true;

        for (int i = 0; i < tree[x].size(); i++) {
            Node next = tree[x].get(i);
            if (!visited[next.e]) {
                dfs(next.e, next.cost + len);
                visited[next.e] = true;
            }
        }
    }


}
