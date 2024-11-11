import java.util.LinkedList;
import java.util.Queue;

class Solution {
    

    public int solution(int n, int[][] computers) {
        int answer = 0;
        // 각 노드의 방문 여부 확인
        boolean[] visited = new boolean[n];
        // 0번 노드부터 마지막 노드까지 확인
        for (int i = 0; i < n; i++) {
            // 현재 노드를 방문하지 않았다면
            if (!visited[i]) {
                Queue<Integer> q = new LinkedList<>();
                // q에 노드 추가
                q.add(i);
                // 현재 노드 방문
                visited[i] = true;
                // 현재 노드에서 갈 수 있는 노드 중 방문하지 않은 노드 방문
                while (!q.isEmpty()) {
                    int node = q.poll();
                    for (int j = 0; j < n; j++) {
                        if (computers[node][j] == 1 && !visited[j]) {
                            visited[j] = true;
                            q.add(j);
                        }
                    }
                }
                // 네트워크 수 증가
                answer++;
            }
        }
        return answer;
    }
}
