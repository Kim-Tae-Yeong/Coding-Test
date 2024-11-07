import java.util.ArrayList;
import java.util.List;

class Solution {
    

    public int solution(int[][] routes) {
        int answer = 0;
        List<int[]> l = new ArrayList<>();
        for (int[] elem : routes) {
            l.add(elem);
        }
        l.sort((o1, o2) -> Integer.compare(o1[0], o2[0]));
        int start = l.get(0)[0];
        int end = l.get(0)[1];
        int idx = 1;
        while (true) {
            if (idx == l.size()) {
                break;
            }
            if (l.get(idx)[1] < end) {
                end = l.get(idx)[1];
            } else if (l.get(idx)[0] > end) {
                answer++;
                start = l.get(idx)[0];
                end = l.get(idx)[1];
            }
            idx++;
        }
        return answer + 1;
    }
}