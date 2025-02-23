import java.io.*;
import java.util.*;

public class Main_2225 {
  static int N, K;
  // dp[K][N] : K개의 숫자를 사용해 N를 만드는 경우의 수
  static long[][] dp;

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./2225.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    dp = new long[K + 1][N + 1];

    // 1 ~ K개의 숫자를 이용해서 0을 만드는 경우는 1가지밖에 없음
    for (int row = 1; row < K + 1; row++) {
      dp[row][0] = 1;
    }

    // 숫자 1개를 이용해서 1 ~ N을 만드는 경우는 1가지밖에 없음
    for (int col = 0; col < N + 1; col++) {
      dp[1][col] = 1;
    }

    // 사용하는 숫자를 2 ~ K까지 늘림
    for (int row = 2; row < K + 1; row++) {
      // 만드는 숫자를 1 ~ N까지 탐색
      for (int col = 1; col < N + 1; col++) {
        // 숫자 row개를 사용해서 col을 만드는 경우
        // dp[row - 1][0] * dp[1][col]
        // : 숫자 (row - 1)개를 사용해 0을 만드는 경우 * 숫자 1개를 사용해 col을 만드는 경우(0 + col)
        // dp[row - 1][1] * dp[1][col - 1]
        // : 숫자 (row - 1)개를 사용해 1을 만드는 경우 * 숫자 1개를 사용해 (col - 1)을 만드는 경우(1 + (col - 1))
        // ...
        // dp[row - 1][col - 1] * dp[1][1]
        // : 숫자 (row - 1)개를 사용해 (col - 1)을 만드는 경우 * 숫자 1개를 사용해 1을 만드는 경우(col - 1 + 1)
        // dp[row - 1][col] * dp[1][0]
        // : 숫자 (row - 1)개를 사용해 col을 만드는 경우 * 숫자 1개를 사용해 0을 만드는 경우(col + 0)
        // 위 경우를 다 더하면 dp[row][col]을 구할 수 있음
        for (int cnt = 0; cnt < col + 1; cnt++) {
          dp[row][col] += dp[row - 1][cnt] * dp[1][col - cnt] % 1000000000;
        }
      }
    }

    System.out.println(dp[K][N] % 1000000000);

    br.close();
  }
}
