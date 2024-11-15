import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    

    public int solution(int n, int[][] vertex) {
        int answer = 0;
        List<List<Integer>> l = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            List<Integer> tmp = new ArrayList<>();
            l.add(tmp);
        }
        for (int[] elem : vertex) {
            l.get(elem[0]).add(elem[1]);
            l.get(elem[1]).add(elem[0]);
        }
        int[] visitied = new int[n + 1];
        Queue<int[]> q = new LinkedList<>();
        for (int elem : l.get(1)) {
            q.add(new int[] { elem, 1 });
        }
        while (!q.isEmpty()) {
            int[] info = q.poll();
            int idx = info[0];
            int cnt = info[1];
            if (visitied[idx] == 0) {
                visitied[idx] = cnt;
                for (int elem : l.get(idx)) {
                    if (elem != 1 && visitied[elem] == 0) {
                        q.add(new int[] { elem, cnt + 1 });
                    }
                }
            }
        }
        int maxNum = Integer.MIN_VALUE;
        for (int elem : visitied) {
            if (maxNum == elem) {
                answer++;
            } else if (maxNum < elem) {
                maxNum = elem;
                answer = 1;
            }
        }
        return answer;
    }
}