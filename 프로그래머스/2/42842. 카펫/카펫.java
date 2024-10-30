import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    

    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        int total = brown + yellow;
        List<Integer> l = new ArrayList<>();
        for (int i = 1; i < total + 1; i++) {
            if (total % i == 0) {
                l.add(i);
            }
        }
        int length = (l.size() % 2 == 0) ? (l.size() / 2) : (l.size() / 2 + 1);
        for (int i = 0; i < length; i++) {
            int x = l.get((l.size() - 1) - i);
            int y = l.get(i);
            int[] tmp = new int[] { x, y };
            if (((x - 2) * (y - 2)) == yellow) {
                answer = tmp;
            }
        }
        return answer;
    }
}