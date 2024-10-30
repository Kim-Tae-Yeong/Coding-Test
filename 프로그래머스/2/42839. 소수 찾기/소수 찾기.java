import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    

    public int solution(String numbers) {
        int answer = 0;
        char[] num = numbers.toCharArray();
        int[] cnt = new int[10];
        List<Integer> l = new ArrayList<>();
        for (char c : num) {
            int n = Integer.parseInt(String.valueOf(c));
            cnt[n] += 1;
            l.add(n);
        }
        Collections.sort(l, Collections.reverseOrder());
        String tmp = "";
        for (int elem : l) {
            tmp += Integer.toString(elem);
        }
        int maxNum = Integer.parseInt(tmp);

        for (int i = 2; i < maxNum + 1; i++) {
            boolean prime = true;
            for (int j = 2; j < Math.floor(Math.sqrt(i)) + 1; j++) {
                if (i % j == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
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