import java.io.*;
import java.util.*;

public class Main {
  static int N, L, R;
  static int[][] map;
  static boolean[][] visited;
  static List<int[]> l;
  static int[][] dir = { { -1, 0 }, { 1, 0 }, { -0, -1 }, { 0, 1 } };
  static int ans = 0;

  static boolean isInBound(int row, int col) {
    return 0 <= row && row < N && 0 <= col && col < N;
  }

  static boolean open(int row, int col) {
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[] { row, col });
    l = new ArrayList<>();
    visited[row][col] = true;
    l.add(new int[] { row, col });
    while (!q.isEmpty()) {
      int[] pos = q.poll();

      for (int[] d : dir) {
        int nr = pos[0] + d[0];
        int nc = pos[1] + d[1];
        if (isInBound(nr, nc)) {
          int gap = Math.abs(map[pos[0]][pos[1]] - map[nr][nc]);
          if (gap >= L && gap <= R && !visited[nr][nc]) {
            q.add(new int[] { nr, nc });
            visited[nr][nc] = true;
            l.add(new int[] { nr, nc });
          }
        }
      }
    }
    if (l.size() == 1) {
      return false;
    } else {
      return true;
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./16234.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    L = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());
    map = new int[N][N];
    visited = new boolean[N][N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    while (true) {
      boolean check = false;
      for (int i = 0; i < N; i++) {
        Arrays.fill(visited[i], false);
      }
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (!visited[i][j]) {
            if (open(i, j)) {
              for (int[] pos : l) {
              }
              int sum = 0;
              for (int[] pos : l) {
                sum += map[pos[0]][pos[1]];
              }
              for (int[] pos : l) {
                map[pos[0]][pos[1]] = (sum / l.size());
              }
              check = true;
            }
          }
        }
      }
      if (!check) {
        break;
      }
      ans++;
    }

    System.out.println(ans);

    br.close();
  }
}
