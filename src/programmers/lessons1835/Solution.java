package programmers.lessons1835;



class Solution {
    private int ans = 0;
    private int N = 8;
    private String[] kids = {"A", "C", "F", "J", "M", "N", "R", "T"};
    public int solution(int n, String[] data) {
        boolean[] visited = new boolean[N];
        dfs(visited, data, "");
        return ans;
    }

    private void dfs(boolean[] visited, String[] data, String make) {
        if (make.length() == 8) {
            if (isTrue(data, make)) {
                ans++;
                return;
            }
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                String buf = make + kids[i];
                dfs(visited, data, buf);
                visited[i] = false;
            }
        }
    }

    private boolean isTrue(String[] data, String make) {
        for (String d : data) {
            int from = make.indexOf(d.charAt(0));
            int to = make.indexOf(d.charAt(2));
            char op = d.charAt(3);
            int distance = d.charAt(4) - '0';
            if (op == '=') {
                if(Math.abs(from - to) != (distance + 1)) return false;
            } else if (op == '>') {
                if(Math.abs(from -to) <= (distance+1)) return false;
            } else {
                if(Math.abs(from - to) >= (distance +1)) return false;
            }
        }
        return true;
    }

}
/**
 * {A, C, F, J, M, N, R, T}
 * 첫 번째 글자는 조건을 제시한 친구
 * 세 번째 글자는 상대방이다.
 *
 * 즉, 총 8 명의 친구들을 모두 줄 세워야 하는데
 * 각 조건에 맞게 친구들을 줄 세울 수 있는 모든 경우의 수를 구해야 한다.
 * {=, <, >}
 *
 * 2	["N~F=0", "R~T>2"]	3648
 * 2	["M~C<2", "C~M>1"]	0
 *
 * 친구는 총 8명이기 때문에 dfs로 풀 수 있다는 생각이 든다 .
 */
