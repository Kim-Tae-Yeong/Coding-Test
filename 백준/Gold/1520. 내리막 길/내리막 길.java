import java.io.*;
import java.util.*;

public class Main_1520 {
  static int M, N;
  static int[][] map;
  // dp[i][j] = (i, j)에서 (M - 1, N - 1)까지 가는 경우의 수
  static int[][] dp;
  static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

  static boolean isInBound(int row, int col) {
    return 0 <= row && row < M && 0 <= col && col < N;
  }

  static int dfs(int row, int col) {
    // 도착 지점이면 1 반환
    // 도착 지점에서 도착 지점까지 가는 경우는 1가지 밖에 없기 때문
    if (row == M - 1 && col == N - 1) {
      return 1;
    }

    // 이전에 계산된 위치이면 해당 위치의 경우의 수 그대로 반환
    if (dp[row][col] != -1) {
      return dp[row][col];
    }

    // 방문하지 않은 위치면
    dp[row][col] = 0;
    for (int[] d : dir) {
      int nr = row + d[0];
      int nc = col + d[1];
      // 현재 위치에서 상하좌우 탐색을 했을 때 범위 안이면서 이동할 수 있는 곳이면
      if (isInBound(nr, nc) && map[row][col] > map[nr][nc]) {
        // 현재 위치에 (이동할 수 있는 위치 -> 도착 지점까지의 경우의 수)를 더함
        dp[row][col] += dfs(nr, nc);
      }
    }

    // 현재 위치로 올 수 있는 모든 경우를 더한 다음 반환하기 때문에 해당 경우의 수는 바뀌지 않음
    return dp[row][col];
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1520.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    M = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken());
    map = new int[M][N];
    dp = new int[M][N];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        // 방문 여부 초기화
        dp[i][j] = -1;
      }
    }

    System.out.println(dfs(0, 0));

    br.close();
  }
}
