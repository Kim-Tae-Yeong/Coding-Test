import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    

    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        int total = brown + yellow;
        List<Integer> l = new ArrayList<>();
        // brown과 yellow의 총 개수의 약수를 구함
        for (int i = 1; i < total + 1; i++) {
            if (total % i == 0) {
                l.add(i);
            }
        }
        // 약수의 개수가 짝수이면 (약수 개수 / 2), 홀수이면 (약수 개수 / 2) + 1로 길이 설정
        int length = (l.size() % 2 == 0) ? (l.size() / 2) : (l.size() / 2 + 1);
        // 구한 약수를 통해 총 개수를 가지는 모든 grid를 구함
        for (int i = 0; i < length; i++) {
            // x : grid의 가로
            int x = l.get((l.size() - 1) - i);
            // y : grid의 세로
            int y = l.get(i);
            int[] tmp = new int[] { x, y };
            // grid의 맨 위, 맨 아래, 맨 오른쪽, 맨 왼쪽만 brown이고 나머지는 모두 yellow임
            // 이를 통해 가능한 grid 중 정답을 구함
            if (((x - 2) * (y - 2)) == yellow) {
                answer = tmp;
            }
        }
        return answer;
    }
}
