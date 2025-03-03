import java.io.*;
import java.util.*;

public class Main {
  static int N;
  static int idx = 1;
  static int[][] map;
  static int[][] ans;
  static PriorityQueue<int[]> pq;
  static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

  static boolean isInBound(int row, int col) {
    return 0 <= row && row < N && 0 <= col && col < N;
  }

  static int dijkstra() {
    pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
    pq.offer(new int[] { 0, 0, map[0][0] });
    ans[0][0] = map[0][0];

    while (!pq.isEmpty()) {
      int[] info = pq.poll();
      int row = info[0];
      int col = info[1];
      int cost = info[2];

      if (row == N - 1 && col == N - 1) {
        return cost;
      }

      if (ans[row][col] < cost) {
        continue;
      }

      for (int[] d : dir) {
        int nr = row + d[0];
        int nc = col + d[1];
        if (isInBound(nr, nc)) {
          int newCost = cost + map[nr][nc];
          if (newCost < ans[nr][nc]) {
            ans[nr][nc] = newCost;
            pq.offer(new int[] { nr, nc, newCost });
          }
        }
      }

    }
    return -1;
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./4485.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      if (N == 0) {
        break;
      }
      map = new int[N][N];
      ans = new int[N][N];
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
          ans[i][j] = Integer.MAX_VALUE;
        }
      }

      System.out.println("Problem " + idx + ": " + dijkstra());
      idx++;
    }
    br.close();
  }
}
