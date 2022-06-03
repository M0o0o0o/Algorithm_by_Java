package dijkstra.problem23807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    private int index;
    private long distance;

    public Node(int index, long distance) {
        this.index = index;
        this.distance = distance;
    }

    public int getIndex() {
        return index;
    }

    public long getDistance() {
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

    public static final long INF = Long.MAX_VALUE - 10000000;
    public static int n, m, start, end, p;
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    public static long[][] d = new long[101][100001];
    public static int[] pList = new int[101];

    public static void dijkstra(int start, long[] d) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(start, 0));
        d[start] = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();
            long dist = node.getDistance();
            int now = node.getIndex();

            if(d[now] < dist) continue;

            for (int i = 0; i < graph.get(now).size(); i++) {
                int next = graph.get(now).get(i).getIndex();
                long cost = graph.get(now).get(i).getDistance() + dist;

                if (d[next] > cost) {
                    d[next] = cost;
                    q.offer(new Node(next, cost));
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long ans = INF;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n + 1; i++) {
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
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n+1; i++) {
            Arrays.fill(d[i], INF);
        }

        dijkstra(start, d[0]);
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < p+1; i++) {
            pList[i] = Integer.parseInt(st.nextToken());
            dijkstra(pList[i], d[i]);
//            System.out.println(pList[i] + " 번째 차례");
//            for (int j = 1; j < n + 1; j++) {
//                System.out.print(d[pList[i]][j] + ", ");
//            }
//            System.out.println();
        }
        for (int i = 1; i < p+1; i++) {
            for (int j = 1; j < p+1; j++) {
                for (int k = 1; k < p+1; k++) {
                    int a, b, c;
                    a = pList[i];
                    b = pList[j];
                    c = pList[k];
                    if(d[0][a] == INF ) continue;
                    if(a == b || d[i][b] == INF) continue;
                    if(a==c || b==c || d[j][c] == INF) continue;
                    ans = Math.min(ans, d[0][a] + d[i][b] + d[j][c] + d[k][end]);

                }
            }
        }
        System.out.println(ans == INF ? -1 : ans);

        br.close();
    }
}
