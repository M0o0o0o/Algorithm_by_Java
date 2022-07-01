package programmers.lessons17676;


import java.util.ArrayList;

class Solution {
    public  int solution(String[] lines) {
        int ans = 0;
        ArrayList<Integer> start = new ArrayList<>();
        ArrayList<Integer> end = new ArrayList<>();

        for (String line : lines) {
            String[] time = line.split(" ");
            start.add(get_start_time(time[1], time[2]));
            end.add(get_time(time[1]));
        }
        for (int i = 0; i < lines.length; i++) {
            int cnt = 0;
            int curr = end.get(i);
            for (int j = i; j < lines.length; j++) {
                if (curr > start.get(j) - 1000) {
                    cnt += 1;
                }
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

    public  int get_start_time(String time, String duration) {
        int process = (int) (Double.parseDouble(duration.substring(0, duration.length() - 1)) * 1000);
        return get_time(time) - process + 1;
    }

    public  int get_time(String time) {
        int hour = Integer.parseInt(time.substring(0, 2)) * 3600;
        int minute = Integer.parseInt(time.substring(3, 5)) * 60;
        int sec = Integer.parseInt(time.substring(6, 8));
        int mill = Integer.parseInt(time.substring(9));
        return (hour + minute + sec) * 1000 + mill;
    }


}