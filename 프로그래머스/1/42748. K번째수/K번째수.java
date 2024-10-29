import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    

    public int[] solution(int[] array, int[][] commands) {
        int[] answer;
        answer = new int[commands.length];
        int cnt = 0;
        for (int[] elem : commands) {
            List<Integer> l = new ArrayList<>();
            int start = elem[0] - 1;
            int end = elem[1] - 1;
            int idx = elem[2] - 1;
            for (int i = start; i < end + 1; i++) {
                l.add(array[i]);
            }
            l.sort(null);
            answer[cnt] = l.get(idx);
            cnt += 1;
        }
        return answer;
    }
}