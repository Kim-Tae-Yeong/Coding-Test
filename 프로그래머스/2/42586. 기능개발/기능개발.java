import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    

    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer;
        List<Integer> ans = new ArrayList<>();
        int length = progresses.length;
        int idx = 0;
        int tmp = 0;
        while (true) {
            if (idx == length) {
                break;
            }
            for (int i = idx; i < length; i++) {
                progresses[i] += speeds[i];
            }
            tmp = 0;
            while (true) {
                if (progresses[idx] >= 100) {
                    tmp += 1;
                    idx += 1;
                    if (idx == length) {
                        break;
                    }
                } else {
                    break;
                }
            }
            if (tmp != 0) {
                ans.add(tmp);
            }
        }
        answer = ans.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}