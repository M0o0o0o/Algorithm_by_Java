package dijkstra.problem16118;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node> {

    private int index;
    private int distance;
    private int mode;
    public Node(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }

    public Node(int index, int distance, int mode) {
        this.index = index;
        this.distance = distance;
        this.mode = mode;
    }

    public int getIndex() {
        return index;
    }

    public int getDistance() {
        return distance;
    }

    public int getMode() {
        return mode;
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
    public static int n, m, start;
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    public static int[] d = new int[4001];
    public static int[][] wd = new int[4001][2];


    private static void dijkstra(int start) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(start, 0));
        d[start] = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();
            int dist = node.getDistance();
            int now = node.getIndex();

            if(d[now] < dist) continue;

            for (int i = 0; i < graph.get(now).size(); i++) {
                int next = graph.get(now).get(i).getIndex();
                int cost = (graph.get(now).get(i).getDistance() * 2) + d[now];
                if (cost < d[next]) {
                    d[next] = cost;
                    q.offer(new Node(next, cost));
                }
            }
        }
    }

    private static void wDijkstra(int start) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(start, 0, 0));
        wd[start][0] = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();
            int dist = node.getDistance();
            int now = node.getIndex();
            int mode = node.getMode();
            int nextMode = mode == 0 ? 1 : 0;
            if(wd[now][mode] < dist) continue;

            for (int i = 0; i < graph.get(now).size(); i++) {
                int next = graph.get(now).get(i).getIndex();
                int cost = 0;
                if (nextMode == 1) {
                    cost = (graph.get(now).get(i).getDistance()) + wd[now][mode];
                } else {
                    cost = (graph.get(now).get(i).getDistance() * 4) + wd[now][mode];
                }
                if (cost < wd[next][nextMode]) {
                    wd[next][nextMode] = cost;
                    q.offer(new Node(next, cost, nextMode));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        start = 1;

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Node>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        Arrays.fill(d, INF);
        for (int i = 0; i < 4001; i++) {
            Arrays.fill(wd[i], INF);
        }
        dijkstra(start);
        wDijkstra(start);

        int ans = 0;

        for (int i = 1; i <= n; i++) {
            if (d[i] < wd[i][0] && d[i] < wd[i][1]) {
                ans += 1;
            }
        }
        System.out.println(ans);
        br.close();

    }
}
