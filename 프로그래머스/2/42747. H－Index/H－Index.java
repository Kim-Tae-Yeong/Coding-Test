import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    

    public int solution(int[] citations) {
        int answer = 0;
        List<Integer> l = new ArrayList<>();
        for (int elem : citations) {
            l.add(elem);
        }
        l.sort(null);
        int idx = 0;
        while (true) {
            if (l.get(idx) == answer) {
                while (true) {
                    if (idx == citations.length || l.get(idx) != answer) {
                        break;
                    }
                    idx += 1;
                }
            }
            if (answer >= citations.length - idx) {
                break;
            }
            answer += 1;
        }
        return answer;
    }
}