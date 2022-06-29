package dp.lessons42897;

import java.util.Arrays;

class Solution {
    public  int solution(int[] money) {
        int len = money.length;
        if (len == 3) {
            return Math.max(money[0], Math.max(money[1], money[2]));
        }
        int[] dpFirstO = new int[len];
        int[] dpFirstX = new int[len];

        dpFirstO[0] = money[0];
        dpFirstO[1] = Math.max(money[0], money[1]);
        dpFirstX[0] = 0;
        dpFirstX[1] = money[1];

        int ans = 0;
        for (int i = 2; i < len; i++) {
            dpFirstX[i] = Math.max(dpFirstX[i - 1], money[i] + dpFirstX[i - 2]);
            ans = Math.max(ans, dpFirstX[i]);
            if(i == len-1) break;
            dpFirstO[i] = Math.max(dpFirstO[i - 1], money[i] + dpFirstO[i - 2]);
            ans = Math.max(ans, dpFirstO[i]);
        }

        return ans;
    }

}