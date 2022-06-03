package dijkstra.problem10776;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    private int index;
    private int distance;
    private int weight;

    public Node(int index, int distance, int weight) {
        this.index = index;
        this.distance = distance;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public int getIndex() {
        return index;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Node o) {
        if (this.distance < o.distance) {
            return -1;
        }
        return 1;
    }

}

public class Main {
    public static final int INF = (int) 1e9;
    public static int k,n, m, start, end;
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    public static int[][] d = new int[2001][201];

    private static void dijkstra(int start) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(start, 0, 0));
        d[start][0] = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();
            int dist = node.getDistance();
            int now = node.getIndex();
            int weight = node.getWeight();

            if(d[now][weight] < dist) continue;

            for (int i = 0; i < graph.get(now).size(); i++) {
                int next = graph.get(now).get(i).getIndex();
                int nextW = graph.get(now).get(i).getWeight() + weight;
                int cost = graph.get(now).get(i).getDistance() + dist;

                if (nextW >= k) continue;

                if (d[next][nextW] > cost) {
                    d[next][nextW] = cost;
                    q.offer(new Node(next, cost, nextW));
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Node>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, t, h));
            graph.get(b).add(new Node(a, t, h));
        }
        st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n+1; i++) {
            Arrays.fill(d[i], INF);
        }

        dijkstra(start);

        int ans = INF;

        for (int i = 0; i < k; i++) {
            ans = Math.min(ans, d[end][i]);
        }
        System.out.println(ans == INF ? -1 : ans);
        br.close();
    }
}
