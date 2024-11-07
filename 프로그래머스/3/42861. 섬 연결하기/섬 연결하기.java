import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    

    public int solution(int n, int[][] costs) {
        int answer = 0;
        // 각 노드의 방문 여부 확인
        boolean[] visited = new boolean[n];
        // 각 노드에 연결된 간선의 비용을 오름차순으로 저장
        List<PriorityQueue<int[]>> l = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
            l.add(pq);
        }
        for (int[] elem : costs) {
            l.get(elem[0]).add(elem);
            l.get(elem[1]).add(elem);
        }
        // 현재 방문한 노드와 연결된 간선 중 비용이 가장 적은 간선을 찾음
        PriorityQueue<int[]> candidate = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
        // 시작 노드를 0으로 설정
        for (int[] elem : l.get(0)) {
            candidate.add(elem);
        }
        visited[0] = true;
        // cnt : 간선의 개수
        int cnt = 0;
        while (true) {
            // 간선의 개수가 n - 1이면 break
            // 노드이 n개일 때 n - 1개의 간선으로 모든 노드를 연결할 수 있음
            // n - 1보다 많은 간선을 가지면 최소 비용을 만족하지 않음
            if (cnt == n - 1) {
                break;
            }
            // 방문한 노드와 연결된 간선 중 비용이 가장 작은 간선을 확인
            int[] info = candidate.poll();
            int node1 = info[0];
            int node2 = info[1];
            int cost = info[2];
            // 만약 간선에 연결된 두 노드를 모두 방문했다면 현재 간선 이전에 더 적은 비용으로 두 노드를 연결할 수 있다는 의미
            if (visited[node1] && visited[node2]) {
                continue;
            }
            answer += cost;
            cnt++;
            visited[node1] = true;
            visited[node2] = true;
            // 첫 번째 노드(node1)와 연결된 간선을 candidate에 넣음
            // node1과 연결된 간선을 통해 다른 노드와 node2가 연결될 수 있음
            for (int[] elem : l.get(node1)) {
                candidate.add(elem);
            }
            // 위와 같은 방식으로 처리
            for (int[] elem : l.get(node2)) {
                candidate.add(elem);
            }
        }
        return answer;
    }
}
