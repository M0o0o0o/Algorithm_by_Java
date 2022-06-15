package mst.problem4386;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static int n;
    public static ArrayList<Edge> edges = new ArrayList<>();
    public static ArrayList<double[]> stars = new ArrayList<>();
    public static int[] parents;
    public static double ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < n; i++) {
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            stars.add(new double[]{x, y});
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double yd = Math.pow((stars.get(i)[1] - stars.get(j)[1]),2);
                double xd = Math.pow((stars.get(i)[0] - stars.get(j)[0]),2);
                edges.add(new Edge(i, j, Math.abs(Math.sqrt(yd+xd))));
            }
        }

        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if (o1.cost > o2.cost) {
                    return 1;
                } else if (o1.cost == o2.cost) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if (find_parent(edge.getFrom()) != find_parent(edge.getTo())) {
                union_parent(edge.getFrom(), edge.getTo());
                ans += edge.getCost();
            }
        }

        System.out.printf("%.2f", ans);

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
}

class Edge {
    int from;
    int to;
    double cost;

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", cost=" + cost +
                '}';
    }

    public Edge(int from, int to, double cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public double getCost() {
        return cost;
    }
}
