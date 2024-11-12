import java.util.LinkedList;
import java.util.Queue;

class Solution {
    

    private static int row, col;

    private static boolean isInBound(int r, int c) {
        return 0 <= r && r < row && 0 <= c && c < col;
    }

    public int solution(int[][] maps) {
        int answer = -1;
        row = maps.length;
        col = maps[0].length;
        boolean[][] visited = new boolean[row][col];

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { 0, 0, 1 });
        visited[0][0] = true;

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        while (!q.isEmpty()) {
            int[] info = q.poll();
            int r = info[0];
            int c = info[1];
            int cnt = info[2];
            for (int[] d : dir) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (isInBound(nr, nc)) {
                    if (maps[nr][nc] == 1 && !visited[nr][nc]) {
                        if (nr == row - 1 && nc == col - 1) {
                            answer = cnt + 1;
                            break;
                        }
                        q.add(new int[] { nr, nc, cnt + 1 });
                        visited[nr][nc] = true;
                    }
                }
            }
            if (answer != -1) {
                break;
            }
        }
        return answer;
    }
}