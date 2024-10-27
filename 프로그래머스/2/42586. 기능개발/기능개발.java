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
            // 하루씩 progress를 진행시킴
            for (int i = idx; i < length; i++) {
                progresses[i] += speeds[i];
            }
            tmp = 0;
            while (true) {
                // 0부터 시작해서 100이 넘는 progress까지의 개수를 구함
                if (progresses[idx] >= 100) {
                    tmp += 1;
                    idx += 1;
                    // 마지막 progress까지 확인했으면 break
                    if (idx == length) {
                        break;
                    }
                } else {
                    break;
                }
            }
            // 해당 날에 100이 넘는 progress가 있으면 정답에 추가
            if (tmp != 0) {
                ans.add(tmp);
            }
        }
        answer = ans.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}
