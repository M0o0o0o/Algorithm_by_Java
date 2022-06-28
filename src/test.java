import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class test {
    public static void main(String[] args) {
        List<Integer> lists = new ArrayList<>();
        for (int i = 0; i < 45; i++) {
            lists.add(i + 1);
        }
        Collections.shuffle(lists);

        int cnt = 0;
        for (int i = 0; i < lists.size(); i++) {
            System.out.print(lists.get(i) + " ");
            cnt++;
            if (cnt % 6 == 0) {
                System.out.println();
            }
        }
    }
}
