package mst.problem16950;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/16950
class Edge implements Comparable<Edge>{
    int from, to, type;

    public Edge(int from, int to, int type) {
        this.from = from;
        this.to = to;
        this.type = type;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.type > o.type) {
            return 1;
        } else if (this.type == o.type) {
            return 0;
        } else {
            return -1;
        }
    }
}

public class Main {

    private static int n, m, k, cnt;
    private static boolean isPossible;
    private static ArrayList<Edge> edges = new ArrayList<>();
    private static int[][] board = new int[1001][1001];
    private static int[] parents;
    private static boolean[] visited;
    private static boolean isFind = false;

    public static void main(String[] args) throws IOException {
        InputAndInit();
        Collections.sort(edges);
        kruskal();

        for (int i = 0; i < edges.size(); i++) {
            if(cnt == k) break;
            Edge edge = edges.get(i);
            if (edge.type == 1 || (edge.type == 2 && board[edge.from][edge.to] == edge.type)) continue;
            int from = edge.from;
            int to = edge.to;
            clearVisited();
            visited[from] = true;
            isFind = false;
            dfs(from, to, new ArrayList<>());
            if (isFind) {
                board[from][to] = edge.type;
                board[to][from] = edge.type;
                cnt++;
                continue;
            }
        }
        if (cnt == k) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = i + 1; j < n + 1; j++) {
                    if (board[i][j] > 0) System.out.println(i + " " + j);
                }
            }
        } else {
            System.out.println(0);
        }

    }

    private static void clearVisited() {
        for (int i = 0; i < n + 1; i++) {
            visited[i] = false;
        }
    }

    private static void dfs(int now, int target, ArrayList<int[]> store){
        if(isFind) return;
        if (now == target) {
            if(store.isEmpty()) return;
            isFind = true;
            int[] fromTo = store.get(0);
            int from = fromTo[0];
            int to = fromTo[1];
            board[from][to] = 0;
            board[to][from] = 0;
            return;
        }

        for (int i = 1; i < n + 1; i++) {
            if (board[now][i] != 0 && !visited[i]) {
                visited[i] = true;
                if (board[now][i] == 1) store.add(new int[]{now, i});
                dfs(i, target, store);
                if (board[now][i] == 1) store.remove(store.size() - 1);
                visited[i] = false;
            }

        }
    }

    private static void InputAndInit() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        parents = new int[n + 1];
        visited = new boolean[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parents[i] = i;
            visited[i] = false;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            // from, to type(1:red, 2:blue
            edges.add(new Edge(from, to, type.equals("B") ? 2 : 1));
        }
        br.close();
    }

    private static int findParents(int node) {
        if (node != parents[node]) {
            parents[node] = findParents(parents[node]);
        }
        return parents[node];
    }

    private static void union(int a, int b) {
        a = findParents(a);
        b = findParents(b);
        if (a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

    private static void kruskal() {
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if (findParents(edge.from) != findParents(edge.to)) {
                union(edge.from, edge.to);
                board[edge.from][edge.to] = edge.type;
                board[edge.to][edge.from] = edge.type;
                if(edge.type == 2) cnt++;
            }
        }

    }
}
