import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    

    public int solution(int[] priorities, int location) {
        int answer = 1;
        Queue<int[]> q = new LinkedList<>();
        List<Integer> l = new ArrayList<>();
        for (int elem : priorities) {
            q.add(new int[] { elem, 0 });
            l.add(elem);
        }
        l.sort((o1, o2) -> Integer.compare(o2, o1));
        int i = 0;
        int idx = 0;
        while (true) {
            int[] tmp = q.poll();
            if (tmp[1] == 0) {
                if (tmp[0] == l.get(i)) {
                    if (idx == location) {
                        break;
                    }
                    tmp[1] = 1;
                    i += 1;
                    answer += 1;
                }
            }
            q.add(tmp);
            idx = ((idx + 1) % priorities.length);
        }
        return answer;
    }
}