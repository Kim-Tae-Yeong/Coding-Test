import java.util.LinkedList;
import java.util.Queue;

class Solution {
    
    
    private static int row, col;

    // 현재 위치가 maps 안에 들어있는지 확인
    private static boolean isInBound(int r, int c) {
        return 0 <= r && r < row && 0 <= c && c < col;
    }

    public int solution(int[][] maps) {
        int answer = -1;
        // 전체 row, col 저장
        row = maps.length;
        col = maps[0].length;
        // 각 위치 방문 여부
        boolean[][] visited = new boolean[row][col];

        // q에 시작 위치와 칸의 개수(1) 저장
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { 0, 0, 1 });
        // 시작 위치 방문 여부 갱신
        visited[0][0] = true;

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        while (!q.isEmpty()) {
            // q에 넣은 위치와 칸 수 확인
            int[] info = q.poll();
            int r = info[0];
            int c = info[1];
            int cnt = info[2];
            // 동, 서, 남, 북 방향 탐색
            for (int[] d : dir) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (isInBound(nr, nc)) {
                    // 벽이 없는 자리이면서 방문하지 않은 경우
                    if (maps[nr][nc] == 1 && !visited[nr][nc]) {
                        // 해당 위치가 도착지이면 (현재 칸수 + 1)을 정답으로 설정
                        if (nr == row - 1 && nc == col - 1) {
                            answer = cnt + 1;
                            break;
                        }
                        // 그 외의 경우 해당 위치와 (현재 칸수 + 1)을 q에 넣음
                        q.add(new int[] { nr, nc, cnt + 1 });
                        // 방문 여부 갱신
                        visited[nr][nc] = true;
                    }
                }
            }
            // 도착지에 도달했으면 break
            if (answer != -1) {
                break;
            }
        }
        return answer;
    }
}
