package programmers.lessons17679;

import java.util.ArrayList;

class Solution {
    private int ans = 0;
    private char[][] graph;
    private int[] DX = {0, 0, 1, 1};
    private int[] DY = {0, 1, 1, 0};
    private ArrayList<int[]> targets = new ArrayList<>();

    public int solution(int m, int n, String[] board) {
        graph = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = board[i].charAt(j);
            }
        }

        while (true) {
            targets.clear();
            for (int i = 0; i < m-1; i++) {
                for (int j = 0; j < n-1; j++) {
                    if(graph[i][j] == Character.MIN_VALUE) continue;
                    if (check(i, j, m, n)) {
                        targets.add(new int[]{i, j});
                    }
                }
            }
            if (targets.isEmpty()) {
                break;
            }
            remove();
            move(m, n);
        }

        return ans;
    }

    private void move(int m, int n) {

        for (int i = 0; i < n; i++) {
            while (true) {
                boolean isMove = false;
                for (int j = m-1; j > 0; j--) {
                    if (graph[j][i] == Character.MIN_VALUE && graph[j-1][i] != Character.MIN_VALUE) {
                        graph[j][i] = graph[j-1][i];
                        graph[j-1][i] = Character.MIN_VALUE;
                        isMove = true;
                    }
                }
                if(!isMove) break;
            }
        }
    }

    private void remove() {
        for (int i = 0; i < targets.size(); i++) {
            int x = targets.get(i)[0];
            int y = targets.get(i)[1];

            for (int j = 0; j < 4; j++) {
                int nx = x + DX[j];
                int ny = y + DY[j];
                if (graph[nx][ny] != Character.MIN_VALUE) {
                    graph[nx][ny] = Character.MIN_VALUE;
                    ans++;
                }
            }
        }
    }
    private boolean check(int x, int y, int m, int n) {
        for (int i = 0; i < 4; i++) {
            int nx = x + DX[i];
            int ny = y + DY[i];
            if(nx < 0 || nx >= m || ny < 0 || ny >= n) return false;
            if(graph[x][y] != graph[nx][ny]) return false;
        }
        return true;
    }

}