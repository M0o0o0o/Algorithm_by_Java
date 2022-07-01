package programmers.lessons81303;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        Stack<Integer> rmList = new Stack<>();
        int size = n;
        for (int i = 0; i < cmd.length; i++) {
            char c = cmd[i].charAt(0);
            if (c == 'D') {
                k += Integer.parseInt(cmd[i].substring(2));

            } else if (c == 'U') {
                k -= Integer.parseInt(cmd[i].substring(2));
            } else if (c == 'C') {
                rmList.add(k);
                size--;
                if (k == size) {
                    k--;
                }
            } else {
                if (rmList.pop() <= k) k++;
                size++;
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append("O");
        }
        while (!rmList.isEmpty()) {
            builder.insert(rmList.pop().intValue(), "X");
        }
        return builder.toString();
    }
}
// 0 1 2 3 4 5 6 7
// 0 1 2 4 5 6 7