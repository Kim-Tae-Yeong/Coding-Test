import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    

    public int solution(int n, int[][] costs) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        List<PriorityQueue<int[]>> l = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
            l.add(pq);
        }
        for (int[] elem : costs) {
            l.get(elem[0]).add(elem);
            l.get(elem[1]).add(elem);
        }
        PriorityQueue<int[]> candidate = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
        for (int[] elem : l.get(0)) {
            candidate.add(elem);
        }
        visited[0] = true;
        int cnt = 0;
        while (true) {
            if (cnt == n - 1) {
                break;
            }
            int[] info = candidate.poll();
            int node1 = info[0];
            int node2 = info[1];
            int cost = info[2];
            if (visited[node1] && visited[node2]) {
                continue;
            }
            answer += cost;
            cnt++;
            visited[node1] = true;
            visited[node2] = true;
            for (int[] elem : l.get(node1)) {
                candidate.add(elem);
            }
            for (int[] elem : l.get(node2)) {
                candidate.add(elem);
            }
        }
        return answer;
    }
}