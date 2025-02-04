import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  static int w, h, ans;
  static int[][] map;
  static boolean[][] visited;
  static Stack<int[]> s;
  static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

  static boolean isInBound(int row, int col) {
    return 0 <= row && row < h && 0 <= col && col < w;
  }

  static void dfs() {
    if (!s.isEmpty()) {
      int[] pos = s.pop();
      int row = pos[0];
      int col = pos[1];
      visited[row][col] = true;
      for (int[] d : dir) {
        int nr = row + d[0];
        int nc = col + d[1];
        if (isInBound(nr, nc) && map[nr][nc] == 1 && !visited[nr][nc]) {
          s.add(new int[] { nr, nc });
          dfs();
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./4963.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      w = Integer.parseInt(st.nextToken());
      h = Integer.parseInt(st.nextToken());

      if (w == 0 && h == 0) {
        break;
      }

      map = new int[h][w];
      visited = new boolean[h][w];
      ans = 0;
      s = new Stack<>();

      for (int i = 0; i < h; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < w; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
          if (map[i][j] == 1 && !visited[i][j]) {
            s.add(new int[] { i, j });
            dfs();
            ans++;
          }
        }
      }

      System.out.println(ans);
    }
    br.close();
  }
}
