package bfs;


import java.util.LinkedList;
import java.util.Queue;

public class Lessons1829 {
    public static int[] solution(int m, int n, int[][] picture) {
        boolean visited[][] = new boolean[m][n];
        int[] ans = new int[2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == false && picture[i][j] > 0) {
                    int cnt = bfs(i, j, visited, picture, m, n);
                    ans[0] += 1;
                    ans[1] = cnt > ans[1] ? cnt : ans[1];
                }
            }
        }
        return ans;
    }

    private static int bfs(int i, int j, boolean[][] visited, int[][] picture, int m, int n) {
        int dx[] = new int[]{0, 1, 0, -1};
        int dy[] = new int[]{1, 0, -1, 0};
        int cnt = 1;
        int value = picture[i][j];
        visited[i][j] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        while(!q.isEmpty()){
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (0 <= nx && nx < m && 0 <= ny && ny < n && !visited[nx][ny] && picture[nx][ny] == value) {
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    cnt += 1;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] ans = solution(5, 4, new int[][]{{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}});
        System.out.println(ans[0] + " " + ans[1]);

    }
}