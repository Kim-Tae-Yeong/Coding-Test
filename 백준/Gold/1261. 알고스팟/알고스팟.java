import java.io.*;
import java.util.*;

public class Main {
  static int M, N;
  static int[][] map;
  static int[][] ans;
  static Queue<int[]> q = new LinkedList<>();
  static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

  static boolean isInBound(int row, int col) {
    return 0 <= row && row < N && 0 <= col && col < M;
  }

  static int bfs() {
    q.add(new int[] { 0, 0, 0 });
    for (int i = 0; i < N; i++) {
      Arrays.fill(ans[i], Integer.MAX_VALUE);
    }
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
          if (map[nr][nc] == 1 && cnt + 1 < ans[nr][nc]) {
            ans[nr][nc] = cnt + 1;
            q.add(new int[] { nr, nc, cnt + 1 });
          } else if (map[nr][nc] == 0 && cnt < ans[nr][nc]) {
            ans[nr][nc] = cnt;
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