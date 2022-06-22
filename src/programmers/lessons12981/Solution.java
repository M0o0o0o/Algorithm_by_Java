package programmers.lessons12981;

import com.sun.source.tree.ClassTree;

import java.util.HashSet;

class Solution {
    public int[] solution(int n, String[] words) {
        HashSet<String> store = new HashSet<>();
        store.add(words[0]);
        int round = 1;
        int person = 1;
        for (int i = 1; i < words.length; i++) {
            person++;
            if (person > n) {
                person = 1;
                round++;
            }
            if (words[i - 1].charAt(words[i - 1].length() - 1) != words[i].charAt(0) || store.contains(words[i])) {
                return new int[]{person, round};
            }
            store.add(words[i]);

        }
        return new int[]{0, 0};
    }


}