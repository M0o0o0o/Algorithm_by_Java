package showmethecode.hap8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;

class Pair {
    int left;
    int right;

    public Pair(int left, int right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        Pair pair = (Pair) o;
        return left == pair.left && right == pair.right;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}

public class Main {
    static int[] alist, blist;
    static long[] asum, bsum;
    static int n;
    static HashSet<Pair> check = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        alist = new int[n];
        blist = new int[n];
        asum = new long[n];
        bsum = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            alist[i] = Integer.parseInt(st.nextToken());
            if (i > 0) {
                asum[i] = alist[i] + asum[i - 1];
            } else {
                asum[i] = alist[i];
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            blist[i] = Integer.parseInt(st.nextToken());
            if (i > 0) {
                bsum[i] = blist[i] + bsum[i - 1];
            } else {
                bsum[i] = blist[i];
            }
        }

        int middle = n / 2;
        int ans = 0;
        ans += count(0, middle);
        ans += count(middle, n - 1);
        ans += count(middle, middle);
        int l = middle;
        int r = middle;
        while (true) {
            boolean isLeft = false;
            boolean isRight = false;
            if (l - 1 >= 0) {
                l--;
                isLeft = true;
                if(r > middle) ans += count(l, r);
                ans += count(0, l);
                ans += count(l, l);
                ans += count(l, middle);
                ans += count(l, n - 1);
            }
            if (r + 1 != n) {
                r++;
                isRight = true;
                ans += count(0, r);
                ans += count(l, r);
                ans += count(r, r);
                ans += count(middle, r);
                ans += count(r, n - 1);
            }

            if(!isLeft && !isRight) break;
        }
        System.out.println(ans);
    }

    static int count(int start, int end) {
        Pair pair = new Pair(start, end);
        if (check.contains(pair)) {
            return 0;
        }
        check.add(pair);

        long aCompare = asum[end];
        long bCompare = bsum[end];
        if (start != 0) {
            aCompare -= asum[start - 1];
            bCompare -= bsum[start - 1];

        }

        if (aCompare == bCompare) {
            System.out.println((start + 1) + ", " + (end + 1));
            return 1;
        }
        return 0;
    }
}
