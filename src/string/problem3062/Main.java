package string.problem3062;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    private static int tc;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        tc = Integer.parseInt(st.nextToken());
        String num = null;
        for (int i = 0; i < tc; i++) {
            st = new StringTokenizer(br.readLine());
            num = st.nextToken();
            num = String.valueOf(Integer.parseInt(num) + Integer.parseInt(new StringBuffer(num).reverse().toString()));
            char[] arr = num.toCharArray();
            boolean ans = true;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] != arr[arr.length- j - 1]) {
                    ans = false;
                    break;
                }
            }
            if(ans) System.out.println("YES");
            else System.out.println("NO");

        }

    }
}
