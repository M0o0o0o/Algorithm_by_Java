package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

class Edge {
    int a,b, cost;

    public Edge(int a, int b, int cost) {
        this.a = a;
        this.b = b;
        this.cost = cost;
    }
}

public class problem1197  {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        ArrayList<Edge> edges = new ArrayList<>();
        int[] parents = new int[v + 1];
        int ans = 0;

        for (int i = 0; i < v+1; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b=  Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, cost));
        }
        edges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.cost, o2.cost);
            }
        });

        for(Edge edge : edges) {
            if (findParent(parents, edge.a) != findParent(parents, edge.b)) {
                unionParent(parents, edge.a, edge.b);
                ans += edge.cost;
            }
        }

        System.out.println(ans);
        br.close();

    }

    private static void unionParent(int[] parents, int a, int b) {
        a = findParent(parents, a);
        b = findParent(parents, b);
        if (a < b) {
            parents[b] = a;
            return;
        }
        parents[a] = b;
    }

    private static int findParent(int[] parents, int a) {
        if (parents[a] != a) {
            parents[a] = findParent(parents, parents[a]);
        }
        return parents[a];
    }
}
