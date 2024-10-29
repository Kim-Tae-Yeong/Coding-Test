import java.util.ArrayList;
import java.util.List;

class Solution {
    

    public String solution(int[] numbers) {
        String answer = "";
        List<String> l = new ArrayList<>();
        for (int elem : numbers) {
            l.add(Integer.toString(elem));
        }
        // 두 수 a와 b로 ab와 ba를 만들었을 때 더 큰 수 기준으로 정렬
        l.sort((o1, o2) -> {
            String tmp1 = o1 + o2;
            String tmp2 = o2 + o1;
            return tmp2.compareTo(tmp1);
        });
        // 맨 앞 수가 0이면 이후 모든 수가 0이기 때문에 정답은 0
        if (l.get(0).equals("0")) {
            answer = "0";
        } else {
            for (String elem : l) {
                answer += elem;
            }
        }
        return answer;
    }
}
