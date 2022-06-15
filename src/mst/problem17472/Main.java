package mst.problem17472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge{
    public int from, to, cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", cost=" + cost +
                '}';
    }
}

public class Main {
    public static int n, m;
    public static int[][] record;
    public static int[] parents;
    public static int[][] board;
    public static int landCnt = 0;
    public static int bridgeCnt = 0;
    public static int ans = 0;

    public static boolean[][] visited;
    public static final int[] DX = new int[]{-1, 0, 1, 0};
    public static final int[] DY = new int[]{0, 1, 0, -1};

    public static ArrayList<Edge> edges = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1 && !visited[i][j]) {
                    landCnt++;
                    bfs2(i, j);
                }


            }
        }


        parents = new int[landCnt+1];
        record = new int[landCnt+1][landCnt+1];
        for (int i = 0; i < landCnt+1; i++) {
            parents[i] = i;
            for (int j = 0; j < landCnt + 1; j++) {
                record[i][j] = (int) 1e9;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != 0 ) {
                    bfs(i, j, board[i][j]);
                }
            }
        }

//        //sadfsdafsdfs
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }

        for (int i = 1; i < landCnt + 1; i++) {
            for (int j = i+1; j < landCnt + 1; j++) {
                if(record[i][j] == (int)1e9) continue;
                edges.add(new Edge(i, j, record[i][j]));
            }
        }


        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if (o1.cost > o2.cost) {
                    return 1;
                } else if (o1.cost == o2.cost) {
                    return 0;
                }
                return -1;
            }
        });

        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if(edge.cost < 2 ) continue;
            if (find_parent(edge.from) != find_parent(edge.to)) {
                union_parent(edge.from, edge.to);
//                System.out.println("from : " + edge.from + " to : " + edge.to + " cost : " + edge.cost);
                ans += edge.cost;
                bridgeCnt++;
            }
        }

//        ////dsfsadfadf
//        System.out.println("land : " + landCnt + " bridge : " + bridgeCnt);

        if (ans == 0 || bridgeCnt + 1 != landCnt) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }



    }

    public static void bfs(int X, int Y, int now) {
        for (int i = 0; i < 4; i++) {
            int x = X;
            int y = Y;
            int cnt = 0;
            while (true) {
                x += DX[i];
                y += DY[i];
                if (x >= 0 && x < n && y >= 0 && y < m) {
                    if (board[x][y] == now) break;
                    if (board[x][y] != now && board[x][y] > 0) {
                        if(cnt < 2) break;
                        if (record[now][board[x][y]] > cnt){
                            record[now][board[x][y]] = cnt;
                            record[board[x][y]][now] = cnt;
                        }
                        break;
                    }
                    cnt++;
                } else break;
            }

        }
    }

    public static void bfs2(int X, int Y) {

        Queue<int[]> q = new LinkedList<>();
        visited[X][Y] = true;
        q.add(new int[]{X, Y});
        board[X][Y] = landCnt;

        while (!q.isEmpty()) {
            int[] xy = q.poll();
            for (int i = 0; i < 4; i++) {
                int x = xy[0] + DX[i];
                int y = xy[1] + DY[i];
                if (x >= 0 && x < n && y >= 0 && y < m && board[x][y] == 1 && !visited[x][y]) {
                    visited[x][y] = true;
                    board[x][y] = landCnt;
                    q.add(new int[]{x, y});
                }
            }
        }
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
