package programmers.lessons64064;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Pattern;

public class SolutionV2 {
    HashSet<HashSet<String>> result;
    ArrayList<ArrayList<String>> bannedList;


    public int solution(String[] user_id, String[] banned_id) {
        result = new HashSet<>();
        bannedList = new ArrayList<>();

        for (String bannedId : banned_id) {
            bannedList.add(getMatch(user_id, bannedId));
        }

        dfs(new HashSet<String>(), 0);

        return result.size();
    }

    private void dfs(HashSet<String> add, int depth) {
        if (depth == bannedList.size()) {
            result.add(new HashSet<>(add));
            return;
        }

        for (String userId : bannedList.get(depth)) {
            if (!add.contains(userId)) {
                add.add(userId);
                dfs(add, depth + 1);
                add.remove(userId);
            }
        }
    }

    private ArrayList<String> getMatch(String[] user_id, String bannedId) {
        String pattern = bannedId.replace('*', '.');
        ArrayList<String> list = new ArrayList<>();

        for (String userId : user_id) {
            if (Pattern.matches(pattern, userId)) {
                list.add(userId);
            }
        }
        return list;
    }
}
