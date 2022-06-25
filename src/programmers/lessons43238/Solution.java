package programmers.lessons43238;

class Solution {
    private long maxValue = 0;
    private long ans = Long.MAX_VALUE;
    public long solution(int n, int[] times) {
        for (int time : times) {
            maxValue = Math.max(maxValue, time);
        }

        long left = 0;
        long right = maxValue * n;

        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0;

            for (int time : times) {
                long people = mid / time;

                if (sum >= n) break;
                sum += people;
            }

            if (sum >= n) {
                ans = Math.min(ans, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

}