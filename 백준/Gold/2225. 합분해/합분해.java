import java.io.*;
import java.util.*;

public class Main {
  static int N, K;
  static long[][] dp;

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./2225.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    dp = new long[K + 1][N + 1];

    for (int row = 1; row < K + 1; row++) {
      dp[row][0] = 1;
    }

    for (int col = 0; col < N + 1; col++) {
      dp[1][col] = 1;
    }

    for (int row = 2; row < K + 1; row++) {
      for (int col = 1; col < N + 1; col++) {
        for (int cnt = 0; cnt < col + 1; cnt++) {
          dp[row][col] += dp[row - 1][cnt] * dp[1][col - cnt] % 1000000000;
        }
      }
    }

    System.out.println(dp[K][N] % 1000000000);

    br.close();
  }
}
