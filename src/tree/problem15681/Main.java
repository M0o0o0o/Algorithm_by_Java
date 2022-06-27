package tree.problem15681;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int n, r, q;
    private static ArrayList<ArrayList<Integer>> tree;
    private static boolean[] visited;
    private static int[] child;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1];
        child = new int[n + 1];
        tree = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            tree.add(new ArrayList<>());
            child[i] = 1;
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            tree.get(from).add(to);
            tree.get(to).add(from);
        }

        visited[r] = true;
        dfs(r);

        for (int i = 0; i < q; i++) {
            System.out.println(child[Integer.parseInt(br.readLine())]);
        }

        br.close();
    }

    private static void dfs(int now) {
        if(tree.get(now).size() == 0){
            return;
        }
        for (int next : tree.get(now)) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next);
                child[now] += child[next];
            }
        }
        return;
    }
}
