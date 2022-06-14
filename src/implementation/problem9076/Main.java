package implementation.problem9076;

import java.util.Arrays;
import java.util.Scanner;

public class Main {



    public static int t;
    public static int[] scores;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();
        scores = new int[5];

        for (int i = 0; i < t; i++) {
            for (int j = 0; j < 5; j++) {
                scores[j] = sc.nextInt();
            }
            Arrays.sort(scores);
            int sum = scores[1] + scores[2] + scores[3];
            System.out.println(scores[3] - scores[1] >= 4 ? "KIN" : "" + sum);
        }
        sc.close();
    }

}

