import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    

    public int solution(int[][] jobs) {
        int answer = 0;
        // 시간이 t일때 실행되지 않은 요청을 실행시간이 짧은 순으로 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1]) {
                return Integer.compare(o1[0], o2[0]);
            } else {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        // 전체 요청을 시작 시간순으로 정렬
        List<int[]> l = new ArrayList<>();
        for (int[] elem : jobs) {
            l.add(elem);
        }
        l.sort((o1, o2) -> Integer.compare(o1[0], o2[0]));
        // idx : 어느 요청까지 큐에 들어갔는지 확인
        int idx = 0;
        // time : 전체 시간
        int time = 0;
        while (true) {
            // 큐가 비어있고, 모든 요청을 확인했으면 break
            if (pq.isEmpty() && idx == jobs.length) {
                break;
            }
            // 큐에 들어가지 않은 요청이 있다면
            if (idx < jobs.length) {
                // 현재 시간이 요청 시작 시간보다 크면
                if (time >= l.get(idx)[0]) {
                    // 넣을 수 있는 요청을 큐에 넣음
                    while (true) {
                        pq.add(l.get(idx));
                        idx += 1;
                        if (idx == jobs.length || time < l.get(idx)[0]) {
                            break;
                        }
                    }
                }
            }
            // 큐에 요청이 있으면
            if (!pq.isEmpty()) {
                // 실행 시간이 가장 짧은 요청을 뺌
                int[] currentProcess = pq.poll();
                // 해당 요청을 실행시키고 전체 시간을 실행 시간만큼 증가시킴
                time += currentProcess[1];
                answer += (time - currentProcess[0]);
            } else {
                // 큐에 요청이 없으면 시간을 1 증가시킴
                time += 1;
            }
        }
        return (answer / jobs.length);
    }
}
