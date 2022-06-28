package tree.problem4803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/4803
public class Main {
    private static int n, m;
    private static int[] parents = new int[501];
    private static ArrayList<int[]> edges = new ArrayList<>();
    private static HashSet<Integer> graph = new HashSet<>();
    private static HashSet<Integer> ans = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int cnt = 0;
        while (true) {
            cnt++;
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) {
                br.close();
                return;
            }
            for (int i = 0; i < n + 1; i++) parents[i] = i;

            edges.clear();
            graph.clear();
            ans.clear();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                edges.add(new int[]{from, to});
            }

            sort();

            for (int[] edge : edges) {
                int a = edge[0];
                int b = edge[1];
                if (find(a) == find(b) || graph.contains(a) || graph.contains(b)) {
                    graph.add(find(a));
                    graph.add(find(b));
                    continue;
                }
                union(a, b);
            }
            for (int i = 1; i < n + 1; i++) {
                if(!graph.contains(parents[i])) ans.add(parents[i]);
            }

            if (ans.size() == 0) {
                System.out.println("Case " + cnt + ": No trees.");
            } else if (ans.size() == 1) {
                System.out.println("Case " + cnt + ": There is one tree.");
            } else {
                System.out.println("Case " + cnt + ": A forest of " + ans.size() + " trees.");
            }
        }
    }

    private static int find(int x) {
        if (parents[x] != x) {
            parents[x] = find(parents[x]);
        }
        return parents[x];
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

    private static void sort() {
        edges.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] > o2[0]) {
                    return 1;
                } else if (o1[0] < o2[0]) {
                    return -1;
                } else {
                    if(o1[1] > o2[1]) return 1;
                    else if (o1[1] < o2[1]) return -1;
                    else return 0;
                }
            }
        });
    }
}

