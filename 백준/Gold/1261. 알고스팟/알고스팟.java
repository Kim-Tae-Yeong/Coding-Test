import java.io.*;
import java.util.*;

public class Main_1261 {
  static int M, N;
  static int[][] map;
  // ans[i][j] = (0, 0)에서 (i, j)까지 부수는 벽의 최소 개수
  static int[][] ans;
  static Queue<int[]> q = new LinkedList<>();
  static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

  static boolean isInBound(int row, int col) {
    return 0 <= row && row < N && 0 <= col && col < M;
  }

  static int bfs() {
    q.add(new int[] { 0, 0, 0 });
    // 초기에는 부수는 벽의 개수 무한대로 설정
    for (int i = 0; i < N; i++) {
      Arrays.fill(ans[i], Integer.MAX_VALUE);
    }
    // 시작 지점은 0으로 설정
    ans[0][0] = 0;

    while (!q.isEmpty()) {
      int[] pos = q.poll();
      int row = pos[0];
      int col = pos[1];
      int cnt = pos[2];
      for (int[] d : dir) {
        int nr = row + d[0];
        int nc = col + d[1];
        // 경계 안에 있을 때
        if (isInBound(nr, nc)) {
          // 다음 지점이 벽이고, 이전에 저장된 벽의 최소 개수보다 (현재까지 부수는 벽의 최소 개수 + 1)이 더 작으면
          if (map[nr][nc] == 1 && cnt + 1 < ans[nr][nc]) {
            // 해당 위치의 벽의 최소 개수 갱신
            ans[nr][nc] = cnt + 1;
            // 해당 위치에서 다시 상하좌우 탐색을 하기 위해 큐에 집어넣음
            q.add(new int[] { nr, nc, cnt + 1 });
          }
          // 다음 지점이 벽이 아니고, 이전에 저장된 벽의 최소 개수보다 현재 지점까지 벽의 최소 개수가 더 작으면
          else if (map[nr][nc] == 0 && cnt < ans[nr][nc]) {
            // 해당 위치의 벽의 최소 개수 갱신
            ans[nr][nc] = cnt;
            // 해당 위치에서 다시 상하좌우 탐색을 위해 큐에 집어넣음
            q.add(new int[] { nr, nc, cnt });
          }
        }
      }
    }
    return ans[N - 1][M - 1];
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1261.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    M = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken());
    map = new int[N][M];
    ans = new int[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      String input = st.nextToken();
      for (int j = 0; j < M; j++) {
        map[i][j] = input.charAt(j) - '0';
      }
    }

    System.out.println(bfs());
    br.close();
  }
}
