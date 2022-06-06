package tree.problem6416;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

//https://www.acmicpc.net/problem/6416
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, Integer> v;
        int cnt = 1;

        while (true) {
            v = new HashMap<>();
            int edges = 0;
            while (true) {
                int a = sc.nextInt();
                int b = sc.nextInt();

                if (a == -1 && b == -1) return;
                if (a == 0 && b == 0) break;

                v.put(a, v.getOrDefault(a, 0));
                v.put(b, v.getOrDefault(b, 0) + 1);
                edges++;
            }
            int root = 0;
            boolean isTrue = true;
            Iterator<Integer> k = v.keySet().iterator();
            while (k.hasNext()) {
                int num = k.next();
                if(v.get(num) == 0) root++;
                else if (v.get(num) > 1) {
                    isTrue = false;
                    break;
                }
            }

            if(v.size() == 0) {
                System.out.println("Case " + cnt + " is a tree.");
            }
            else if(isTrue && root == 1 && edges == v.size() - 1) {
                System.out.println("Case " + cnt + " is a tree.");
            } else {
                System.out.println("Case " + cnt + " is not a tree.");
            }
            cnt++;
        }
    }
}
