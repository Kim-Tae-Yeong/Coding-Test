import java.util.LinkedList;
import java.util.Queue;

class Solution {
    

    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                visited[i] = true;
                while (!q.isEmpty()) {
                    int node = q.poll();
                    for (int j = 0; j < n; j++) {
                        if (computers[node][j] == 1 && !visited[j]) {
                            visited[j] = true;
                            q.add(j);
                        }
                    }
                }
                answer++;
            }
        }
        return answer;
    }
}