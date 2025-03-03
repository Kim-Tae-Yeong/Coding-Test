import java.io.*;
import java.util.*;

public class Main_4485 {
  static int N;
  static int idx = 1;
  static int[][] map;
  // ans[i][j] : (0, 0)에서 (i, j)로 가는데 잃어버린 최소 금액
  static int[][] ans;
  static PriorityQueue<int[]> pq;
  static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

  static boolean isInBound(int row, int col) {
    return 0 <= row && row < N && 0 <= col && col < N;
  }

  static int dijkstra() {
    // 잃어버린 금액을 오름차순으로 정렬
    pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
    pq.offer(new int[] { 0, 0, map[0][0] });
    ans[0][0] = map[0][0];

    while (!pq.isEmpty()) {
      int[] info = pq.poll();
      int row = info[0];
      int col = info[1];
      int cost = info[2];

      // 도착 지점이면 잃어버린 금액 반환
      if (row == N - 1 && col == N - 1) {
        return cost;
      }

      // 이전에 저장된 값보다 잃어버리는 비용이 크면 continue
      if (ans[row][col] < cost) {
        continue;
      }

      for (int[] d : dir) {
        int nr = row + d[0];
        int nc = col + d[1];
        if (isInBound(nr, nc)) {
          int newCost = cost + map[nr][nc];
          // (현재까지 잃어버린 비용 + 다음 지점에서 잃어버리는 비용)이 이전에 저장된 값보다 작으면
          if (newCost < ans[nr][nc]) {
            // 잃어버리는 최소 비용 갱신
            ans[nr][nc] = newCost;
            // 갱신된 정보를 우선순위 큐에 넣음
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
