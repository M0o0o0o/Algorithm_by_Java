package programmers.lessons17680;

import java.util.LinkedList;

class Solution {
    public   int solution(int cacheSize, String[] cities) {
        int answer = 0;
        LinkedList<String> cache = new LinkedList<>();
        for (String city : cities) {
            city = city.toUpperCase();
            int idx = cache.indexOf(city);
            if (idx == -1) { // cache miss
                answer += 5;
                if (cache.size() == cacheSize) {
                    cache.pollFirst();
                }
                if (cache.size() < cacheSize) {
                    cache.add(city);
                }
                continue;
            }
            answer += 1;
            cache.remove(idx);
            cache.add(city);

        }
        return answer;
    }
}