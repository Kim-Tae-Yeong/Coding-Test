import java.io.*;
import java.util.*;

public class Main {
  static int M, N;
  static int[][] map;
  static int[][] dp;
  static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

  static boolean isInBound(int row, int col) {
    return 0 <= row && row < M && 0 <= col && col < N;
  }

  static int dfs(int row, int col) {
    if (row == M - 1 && col == N - 1) {
      return 1;
    }
    if (dp[row][col] != -1) {
      return dp[row][col];
    }

    dp[row][col] = 0;
    for (int[] d : dir) {
      int nr = row + d[0];
      int nc = col + d[1];
      if (isInBound(nr, nc) && map[row][col] > map[nr][nc]) {
        dp[row][col] += dfs(nr, nc);
      }
    }
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
        dp[i][j] = -1;
      }
    }

    System.out.println(dfs(0, 0));

    br.close();
  }
}
