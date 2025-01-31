import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int n, m;
  static int cnt = 0, maxSize = 0;
  static int[][] map;
  static boolean[][] visited;
  static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

  static boolean isInBound(int row, int col) {
    return 0 <= row && row < n && 0 <= col && col < m;
  }

  static int bfs(int row, int col) {
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[] { row, col });
    visited[row][col] = true;
    int size = 1;
    while (!q.isEmpty()) {
      int[] current = q.poll();
      int r = current[0];
      int c = current[1];
      for (int[] d : dir) {
        int nr = r + d[0];
        int nc = c + d[1];
        if (isInBound(nr, nc)) {
          if (map[nr][nc] == 1 && !visited[nr][nc]) {
            visited[nr][nc] = true;
            q.add(new int[] { nr, nc });
            size++;
          }
        }
      }
    }
    return size;
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1926.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    map = new int[n][m];
    visited = new boolean[n][m];

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < m; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (map[i][j] == 1 && !visited[i][j]) {
          cnt++;
          maxSize = Math.max(maxSize, bfs(i, j));
        }
      }
    }

    System.out.println(cnt);
    System.out.println(maxSize);
    br.close();
  }
}
