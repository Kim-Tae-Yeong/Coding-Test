import java.util.ArrayList;
import java.util.List;

class Solution {
    

    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        boolean[] still = new boolean[n + 2];
        for (int elem : lost) {
            still[elem] = true;
        }
        List<Integer> l = new ArrayList<>();
        for (int elem : reserve) {
            if (still[elem]) {
                answer += 1;
                still[elem] = false;
            } else {
                l.add(elem);
            }
        }
        l.sort(null);
        for (int elem : l) {
            if (still[elem - 1]) {
                answer += 1;
                still[elem - 1] = false;
            } else {
                if (still[elem + 1]) {
                    answer += 1;
                    still[elem + 1] = false;
                }
            }
        }
        return answer;
    }
}