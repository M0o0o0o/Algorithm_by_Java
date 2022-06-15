package dfs.problem16964;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main{
    static int n;
    static ArrayList<Integer>[] graph;
    static int[] num;
    static int[] order;
    static boolean[] visited;
    static ArrayList<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n+1];
        for(int i=1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st = null;
        for(int i=1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }


        num = new int[n+1];
        order = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i <= n; i++){
            num[i] = Integer.parseInt(st.nextToken());
            order[num[i]] = i;
        }

        for(int i=1; i <= n; i++){
            Collections.sort(graph[i], (u, v) -> {
                if(order[u] < order[v]){
                    return -1;
                } else if(order[u] == order[v]){
                    return 0;
                } else {
                    return 1;
                }
            });
        }

        visited = new boolean[n+1];
        dfs(1);
        boolean ans = true;
        for(int i=1; i <= n; i++){
            if(result.get(i-1) != num[i]){
                ans = false;
            }
        }

        if(ans){
            System.out.println(1);
        } else {
            System.out.println(0);
        }
        br.close();
    }

    private static void dfs(int x){
        if(visited[x]) return;
        result.add(x);
        visited[x] = true;

        for(int y : graph[x]){
            dfs(y);
        }
    }
}