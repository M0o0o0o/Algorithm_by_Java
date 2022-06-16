package mst.problem4792;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/4792

/**
 * type(0) : blue, tye(1) : red
 */
class Edge {
    public int from, to, cost;
    public String type;

    public Edge(int from, int to, int cost, String type) {
        this.from = from;
        this.to = to;
        this.cost = cost;
        this.type = type;
    }

    public static void changeCost(ArrayList<Edge> edges, String type) {
        for (Edge edge: edges) {
            if (edge.type.equals(type)) {
                edge.cost = 2;
                continue;
            }
            edge.cost = 1;
        }
    }

}
public class Main {
    public static int[] parents;
    public static ArrayList<Edge> edges;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            if(n==0 && m == 0 && k == 0) break;



            edges = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                String type = st.nextToken();
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                edges.add(new Edge(from, to, 1, type));
            }

            int bMin = 0;
            int bMax = 0;
            // 파란색 간선의 min 값을 책정, change Cost 2 로
            parents = initialize_parents(parents, n);
            Edge.changeCost(edges, "B");
            edges_sort(edges);

            for (int i = 0; i < edges.size(); i++) {
                Edge edge = edges.get(i);
                if (find_parent(edge.from) != find_parent(edge.to)) {
                    union_parent(edge.from, edge.to);
                    if(edge.type.equals("B")) bMin++;
                }
            }

            parents = initialize_parents(parents, n);
            // 파란색 간선의 max 값으 책성, 빨간색 Cost를 1로
            Edge.changeCost(edges, "R");
            edges_sort(edges);

            for (int i = 0; i < edges.size(); i++) {
                Edge edge = edges.get(i);
                if (find_parent(edge.from) != find_parent(edge.to)) {
                    union_parent(edge.from, edge.to);
                    if(edge.type.equals("B")) bMax++;
                }
            }

            if(bMin <= k && bMax >= k) System.out.println(1);
            else System.out.println(0);

        }

        br.close();
    }
    static private int find_parent(int node) {
        if (node != parents[node]) {
            parents[node] = find_parent(parents[node]);
        }
        return parents[node];
    }

    static private void union_parent(int a, int b) {
        a = find_parent(a);
        b = find_parent(b);
        if (a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

    static private void edges_sort(ArrayList<Edge> edges) {
        Collections.sort(edges, (o1, o2) -> {
            if (o1.cost > o2.cost) {
                return 1;
            } else if (o1.cost == o2.cost) {
                return 0;
            } else {
                return -1;
            }
        });
    }

    static private int[] initialize_parents(int[] parents, int n) {
        parents = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parents[i] = i;
        }
        return parents;

    }

}
