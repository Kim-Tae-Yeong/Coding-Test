import java.io.*;
import java.util.*;

public class Main_2206 {
  private static int N, M;
  private static int[][] map;
  private static boolean[][][] visited;
  private static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

  private static class State {
    int row, col, wall, cnt;

    public State(int row, int col, int wall, int cnt) {
      this.row = row;
      this.col = col;
      this.wall = wall;
      this.cnt = cnt;
    }
  }

  private static boolean isInBound(int row, int col) {
    return 0 <= row && row < N && 0 <= col && col < M;
  }

  private static int bfs() {
    Queue<State> q = new LinkedList<>();
    q.add(new State(0, 0, 0, 1));
    visited[0][0][0] = true;

    while (!q.isEmpty()) {
      State cur = q.poll();
      int row = cur.row;
      int col = cur.col;
      int wall = cur.wall;
      int cnt = cur.cnt;

      // bfs는 레벨 단위 탐색을 기반으로 하기 때문에 가장 먼저 도착 지점에 도달하는 경우가 최단거리가 됨
      if (row == N - 1 && col == M - 1) {
        return cnt;
      }

      for (int[] d : dir) {
        int nr = row + d[0];
        int nc = col + d[1];

        if (isInBound(nr, nc)) {
          if (map[nr][nc] == 0 && !visited[nr][nc][wall]) {
            visited[nr][nc][wall] = true;
            q.add(new State(nr, nc, wall, cnt + 1));
          } else if (map[nr][nc] == 1 && wall == 0 && !visited[nr][nc][1]) {
            visited[nr][nc][1] = true;
            q.add(new State(nr, nc, 1, cnt + 1));
          }
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./2206.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    map = new int[N][M];
    // 벽을 부순 경우와 부수지 않은 상태를 구분하여 방문을 기록함
    visited = new boolean[N][M][2];

    for (int i = 0; i < N; i++) {
      String line = br.readLine();
      for (int j = 0; j < M; j++) {
        map[i][j] = line.charAt(j) - '0';
      }
    }

    System.out.println(bfs());
    br.close();
  }
}
