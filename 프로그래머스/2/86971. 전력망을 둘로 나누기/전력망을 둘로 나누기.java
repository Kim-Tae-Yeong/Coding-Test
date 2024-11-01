import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    

    private static int[][] map;
    private static int answer = Integer.MAX_VALUE;

    private static void bfs(int pos, int n, int x, int y) {
        int cnt = 1;
        boolean[] visited = new boolean[n + 1];
        visited[pos] = true;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (map[pos][i] == 1 && !visited[i]) {
                if ((pos == x && i == y) || (pos == y && i == x)) {
                    continue;
                }
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int next = q.poll();
            if (!visited[next]) {
                visited[next] = true;
                cnt += 1;
            }
            for (int i = 1; i < n + 1; i++) {
                if (map[next][i] == 1 && !visited[i]) {
                    if ((next == x && i == y) || (next == y && i == x)) {
                        continue;
                    }
                    q.add(i);
                }
            }
        }
        answer = Math.min(answer, Math.abs(n - cnt - cnt));
    }

    public int solution(int n, int[][] wires) {
        map = new int[n + 1][n + 1];
        for (int[] elem : wires) {
            map[elem[0]][elem[1]] = 1;
            map[elem[1]][elem[0]] = 1;
        }
        for (int i = 0; i < n - 1; i++) {
            int x = wires[i][0];
            int y = wires[i][1];
            bfs(1, n, x, y);
        }
        return answer;
    }
}