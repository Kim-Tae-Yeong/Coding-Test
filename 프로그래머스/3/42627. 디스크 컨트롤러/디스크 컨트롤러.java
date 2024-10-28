import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    

    public int solution(int[][] jobs) {
        int answer = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1]) {
                return Integer.compare(o1[0], o2[0]);
            } else {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        List<int[]> l = new ArrayList<>();
        for (int[] elem : jobs) {
            l.add(elem);
        }
        l.sort((o1, o2) -> Integer.compare(o1[0], o2[0]));
        int idx = 0;
        int time = 0;
        while (true) {
            if (pq.isEmpty() && idx == jobs.length) {
                break;
            }
            if (idx < jobs.length) {
                if (time >= l.get(idx)[0]) {
                    while (true) {
                        pq.add(l.get(idx));
                        idx += 1;
                        if (idx == jobs.length || time < l.get(idx)[0]) {
                            break;
                        }
                    }
                }
            }
            if (!pq.isEmpty()) {
                int[] currentProcess = pq.poll();
                time += currentProcess[1];
                answer += (time - currentProcess[0]);
            } else {
                time += 1;
            }
        }
        return (answer / jobs.length);
    }
}