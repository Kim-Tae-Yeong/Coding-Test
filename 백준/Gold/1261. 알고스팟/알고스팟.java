import java.io.*;
import java.util.*;

public class Main_1261 {
  static int M, N;
  static int[][] map;
  // ans[i][j] = (0, 0)에서 (i, j)까지 부수는 벽의 최소 개수
  static int[][] ans;
  static Queue<int[]> q = new LinkedList<>();
  // 우선순위 큐에 (row, col, cnt)로 저장
  // (0, 0)에서 (row, col)까지 가는데 부수는 벽의 최소 개수가 cnt
  // cnt 기준 오름차순 정렬
  static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
  static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

  static boolean isInBound(int row, int col) {
    return 0 <= row && row < N && 0 <= col && col < M;
  }

  static int bfs() {
    q.add(new int[] { 0, 0, 0 });
    ans[0][0] = 0;

    while (!q.isEmpty()) {
      int[] pos = q.poll();
      int row = pos[0];
      int col = pos[1];
      int cnt = pos[2];
      for (int[] d : dir) {
        int nr = row + d[0];
        int nc = col + d[1];
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

  static int dijkstra() {
    pq.offer(new int[] { 0, 0, 0 });
    ans[0][0] = 0;

    while (!pq.isEmpty()) {
      int[] info = pq.poll();
      int row = info[0];
      int col = info[1];
      int cnt = info[2];

      // 도착 지점이면 cnt 반환
      if (row == N - 1 && col == M - 1) {
        return cnt;
      }

      // cnt가 이전에 저장된 벽의 최소 개수보다 크면 continue
      if (ans[row][col] < cnt) {
        continue;
      }

      for (int[] d : dir) {
        int nr = row + d[0];
        int nc = col + d[1];
        if (isInBound(nr, nc)) {
          int newCnt = cnt + map[nr][nc];
          // 다음 위치까지의 벽의 최소 개수가 이전에 저장된 벽의 최소 개수보다 작으면
          if (newCnt < ans[nr][nc]) {
            // 저장된 값 갱신
            ans[nr][nc] = newCnt;
            // 우선순위 큐에 갱신된 정보를 집어넣음
            pq.offer(new int[] { nr, nc, newCnt });
          }
        }
      }
    }

    // 만약 도달할 수 없으면 -1 반환
    return -1;
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
        ans[i][j] = Integer.MAX_VALUE;
      }
    }

    // System.out.println(bfs());
    System.out.println(dijkstra());
    br.close();
  }
}
