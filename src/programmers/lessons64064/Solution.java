package programmers.lessons64064;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Pattern;

class Solution {
    HashMap<String, Integer> userMap = new HashMap<>();
    HashSet<Long> result = new HashSet<>();
    ArrayList<ArrayList<String>> matches = new ArrayList<>();
    public int solution(String[] user_id, String[] banned_id) {
        for (int i = 0; i < user_id.length; i++) {
            userMap.put(user_id[i], 1 << (i + 1));
        }
        for (String banned : banned_id) {
            matches(banned, user_id);
        }
        dfs(0, 0L);
        return result.size();
    }

    public void matches(String banned, String[] user_id) {
        matches.add(new ArrayList<>());
        String pattern = banned.replace('*', '.');
        for (String user : user_id) {
            if (Pattern.matches(pattern, user)) {
                matches.get(matches.size() - 1).add(user);
            }
        }
    }

    public void dfs(int idx, long check) {
        if (idx == matches.size()) {
            result.add(check);
            System.out.println();
            return;
        }
        for (String match : matches.get(idx)) {
            if(check >= (check ^ userMap.get(match))) continue;
            dfs(idx + 1, check | userMap.get(match));
        }
    }
}