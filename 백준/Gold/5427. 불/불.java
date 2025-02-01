import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int tc, w, h;
  static char[][] map;
  static boolean[][] visited;
  static Queue<int[]> fires, sanggeun;
  static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
  static StringBuilder sb = new StringBuilder();

  static boolean isInBound(int row, int col) {
    return 0 <= row && row < h && 0 <= col && col < w;
  }

  static void spreadFire(int currentTime) {
    while (!fires.isEmpty()) {
      int[] current = fires.peek();
      int row = current[0];
      int col = current[1];
      int time = current[2];

      if (time > currentTime) {
        break;
      } else {
        fires.poll();
      }

      for (int[] d : dir) {
        int nr = row + d[0];
        int nc = col + d[1];
        if (isInBound(nr, nc) && map[nr][nc] == '.') {
          map[nr][nc] = '*';
          fires.add(new int[] { nr, nc, time + 1 });
        }
      }
    }
  }

  static boolean checkFire(int row, int col) {
    for (int[] d : dir) {
      int nr = row + d[0];
      int nc = col + d[1];
      if (isInBound(nr, nc) && map[nr][nc] == '*') {
        return true;
      }
    }
    return false;
  }

  static void bfs() {
    int currentTime = 0;
    while (!sanggeun.isEmpty()) {
      int[] current = sanggeun.poll();
      int row = current[0];
      int col = current[1];
      int time = current[2];

      if (time > currentTime && !fires.isEmpty()) {
        spreadFire(currentTime);
        currentTime++;
      }

      for (int[] d : dir) {
        int nr = row + d[0];
        int nc = col + d[1];
        if (!isInBound(nr, nc)) {
          sb.append(time + 1);
          sb.append("\n");
          return;
        } else {
          if (map[nr][nc] == '.' && !visited[nr][nc] && !checkFire(nr, nc)) {
            map[nr][nc] = '@';
            map[row][col] = '.';
            visited[nr][nc] = true;
            sanggeun.add(new int[] { nr, nc, time + 1 });
          }
        }
      }
    }
    sb.append("IMPOSSIBLE");
    sb.append("\n");
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./5427.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    tc = Integer.parseInt(st.nextToken());

    for (int i = 0; i < tc; i++) {
      st = new StringTokenizer(br.readLine());
      w = Integer.parseInt(st.nextToken());
      h = Integer.parseInt(st.nextToken());

      map = new char[h][w];
      visited = new boolean[h][w];
      fires = new LinkedList<>();
      sanggeun = new LinkedList<>();

      for (int row = 0; row < h; row++) {
        st = new StringTokenizer(br.readLine());
        String input = st.nextToken().toString();
        for (int col = 0; col < w; col++) {
          map[row][col] = input.charAt(col);
          if (map[row][col] == '@') {
            sanggeun.add(new int[] { row, col, 0 });
            visited[row][col] = true;
          } else if (map[row][col] == '*') {
            fires.add(new int[] { row, col, 0 });
          }
        }
      }
      bfs();
    }

    System.out.println(sb.toString());

    br.close();
  }
}
