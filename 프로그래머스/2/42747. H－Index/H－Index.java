import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    

    public int solution(int[] citations) {
        // answer : 논문 인용 횟수
        int answer = 0;
        List<Integer> l = new ArrayList<>();
        for (int elem : citations) {
            l.add(elem);
        }
        // 논문 인용 횟수 오름차순 정렬
        l.sort(null);
        // idx : 현재 확인한 논문의 index
        int idx = 0;
        while (true) {
            // 현재 answer(= h)가 배열의 논문 인용 횟수와 같으면
            if (l.get(idx) == answer) {
                // 다음 논문 인용 횟수로 넘어감
                while (true) {
                    if (idx == citations.length || l.get(idx) != answer) {
                        break;
                    }
                    idx += 1;
                }
            }
            // answer만큼 인용한 논문의 개수가 (전체 논문 - answer이하만큼 인용한 논문) 이상이면 break
            if (answer >= citations.length - idx) {
                break;
            }
            // answer 1 증가
            answer += 1;
        }
        return answer;
    }
}
