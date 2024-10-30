import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    

    public int solution(String numbers) {
        int answer = 0;
        char[] num = numbers.toCharArray();
        // 입력받은 string에 0 ~ 9가 각각 몇개 있는지 확인
        int[] cnt = new int[10];
        List<Integer> l = new ArrayList<>();
        for (char c : num) {
            int n = Integer.parseInt(String.valueOf(c));
            cnt[n] += 1;
            l.add(n);
        }
        // 입력받은 string의 각 숫자를 배열에 넣은 후 내림차순 정렬
        // 이를 통해 만들 수 있는 가장 큰 수 확인
        Collections.sort(l, Collections.reverseOrder());
        String tmp = "";
        for (int elem : l) {
            tmp += Integer.toString(elem);
        }
        int maxNum = Integer.parseInt(tmp);

        // 2 ~ maxNum 중 소수를 구함
        for (int i = 2; i < maxNum + 1; i++) {
            boolean prime = true;
            for (int j = 2; j < Math.floor(Math.sqrt(i)) + 1; j++) {
                if (i % j == 0) {
                    prime = false;
                    break;
                }
            }
            // 만약 소수이면
            if (prime) {
                // 해당 소수가 0 ~ 9가 몇개 들어있는지 확인
                boolean contain = true;
                int[] tmpCnt = new int[10];
                int candidate = i;
                while (true) {
                    if (candidate == 0) {
                        break;
                    }
                    tmpCnt[candidate % 10] += 1;
                    candidate = candidate / 10;
                }
                // 해당 소수가 가지고 있는 각 숫자의 개수 중 기준보다 큰 값이 있으면 그 소수는 만들 수 없음
                for (int j = 0; j < 10; j++) {
                    if (tmpCnt[j] > 0) {
                        if (cnt[j] == 0 || tmpCnt[j] > cnt[j]) {
                            contain = false;
                            break;
                        }
                    }
                }
                if (contain) {
                    answer += 1;
                }
            }
        }

        return answer;
    }
}
