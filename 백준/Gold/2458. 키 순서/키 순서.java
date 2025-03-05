import java.io.*;
import java.util.*;

public class Main {
  static int N, M, ans;
  static int[][] map;

  static void floydWarshall() {
    for (int i = 1; i < N + 1; i++) {
      map[i][i] = 0;
      for (int j = 1; j < N + 1; j++) {
        for (int k = 1; k < N + 1; k++) {
          if (i == j || i == k) {
            continue;
          }
          if (map[j][i] != Integer.MAX_VALUE && map[i][k] != Integer.MAX_VALUE) {
            map[j][k] = Math.min(map[j][k], map[j][i] + map[i][k]);
          }
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./2458.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N + 1][N + 1];
    for (int i = 0; i < N + 1; i++) {
      Arrays.fill(map[i], Integer.MAX_VALUE);
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      map[u][v] = 1;
    }

    floydWarshall();

    ans = N;
    for (int i = 1; i < N + 1; i++) {
      for (int j = 1; j < N + 1; j++) {
        if (map[i][j] == Integer.MAX_VALUE && map[j][i] == Integer.MAX_VALUE) {
          ans--;
          break;
        }
      }
    }

    System.out.println(ans);

    br.close();
  }
}
