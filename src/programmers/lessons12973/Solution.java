package programmers.lessons12973;


import java.util.ArrayList;
import java.util.LinkedList;

class Solution {
    public int solution(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) {
                stack.add(s.charAt(i));
                continue;
            }
            if (stack.peekLast() == s.charAt(i)) stack.pollLast();
            else stack.add(s.charAt(i));
        }
        if(stack.size() == 0) return 1;
        else return 0;
    }


}