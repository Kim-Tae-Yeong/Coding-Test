import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    

    private static int[][] map;
    private static int answer = Integer.MAX_VALUE;

    private static void bfs(int pos, int n, int x, int y) {
        // cnt : 1번 송전탑과 연결된 송전탑의 개수(1번 송전탑 포함)
        int cnt = 1;
        // visited[i] : i번째 송전탑 탐색 여부 확인
        boolean[] visited = new boolean[n + 1];
        visited[pos] = true;
        Queue<Integer> q = new LinkedList<>();
        // 1번 송전탑과 연결되어 있는 송전탑을 q에 집어넣음
        for (int i = 0; i < n; i++) {
            if (map[pos][i] == 1 && !visited[i]) {
                // 만약 해당 연결이 없다고 가정한 연결이면 continue
                if ((pos == x && i == y) || (pos == y && i == x)) {
                    continue;
                }
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int next = q.poll();
            // q에 있는 송전탑을 방문하지 않았으면 방문 체크
            if (!visited[next]) {
                visited[next] = true;
                cnt += 1;
            }
            // 해당 송전탑과 연결되어 있는 송전탑 확인
            for (int i = 1; i < n + 1; i++) {
                if (map[next][i] == 1 && !visited[i]) {
                    // 만약 해당 연결이 없다고 가정한 연결이면 continue
                    if ((next == x && i == y) || (next == y && i == x)) {
                        continue;
                    }
                    q.add(i);
                }
            }
        }
        // 1번 송전탑과 연결된 송전탑의 개수(cnt)와 나머지(n - cnt)의 차이 중 가장 작은 경우를 구함
        answer = Math.min(answer, Math.abs(n - cnt - cnt));
    }

    public int solution(int n, int[][] wires) {
        // 송전탑의 연결 상태 확인
        map = new int[n + 1][n + 1];
        for (int[] elem : wires) {
            map[elem[0]][elem[1]] = 1;
            map[elem[1]][elem[0]] = 1;
        }
        // wires 배열에서 원소가 하나씩 없다고 가정하고 bfs 탐색
        for (int i = 0; i < n - 1; i++) {
            int x = wires[i][0];
            int y = wires[i][1];
            bfs(1, n, x, y);
        }
        return answer;
    }
}
