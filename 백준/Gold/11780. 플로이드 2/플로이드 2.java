import java.io.*;
import java.util.*;

public class Main {
  static int n, m;
  static int[][] map, path;
  static final int INF = 1_000_000_000;

  static void floydWarshall() {
    for (int k = 1; k < n + 1; k++) {
      for (int i = 1; i < n + 1; i++) {
        for (int j = 1; j < n + 1; j++) {
          if (map[i][j] > map[i][k] + map[k][j]) {
            map[i][j] = map[i][k] + map[k][j];
            path[i][j] = k;
          }
        }
      }
    }
  }

  static List<Integer> getPath(int start, int end) {
    List<Integer> route = new ArrayList<>();
    route.add(start);
    findPath(start, end, route);
    route.add(end);

    return route;
  }

  static void findPath(int start, int end, List<Integer> route) {
    if (path[start][end] == 0) {
      return;
    }

    int via = path[start][end];
    findPath(start, via, route);
    route.add(via);
    findPath(via, end, route);
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./11780.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    StringBuilder sb = new StringBuilder();
    n = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    m = Integer.parseInt(st.nextToken());

    map = new int[n + 1][n + 1];
    path = new int[n + 1][n + 1];
    for (int i = 0; i < n + 1; i++) {
      Arrays.fill(map[i], INF);
      map[i][i] = 0;
    }

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      map[a][b] = Math.min(c, map[a][b]);
    }

    floydWarshall();

    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < n + 1; j++) {
        sb.append(map[i][j] == INF ? "0" : map[i][j]).append(" ");
      }
      sb.append("\n");
    }

    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < n + 1; j++) {
        if (i == j || map[i][j] == INF) {
          sb.append("0\n");
        } else {
          List<Integer> route = getPath(i, j);
          sb.append(route.size()).append(" ");
          for (int node : route) {
            sb.append(node).append(" ");
          }
          sb.append("\n");
        }
      }
    }

    System.out.println(sb.toString());
    br.close();
  }
}