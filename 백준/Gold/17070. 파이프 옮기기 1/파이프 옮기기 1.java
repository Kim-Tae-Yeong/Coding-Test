import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static int n, ans;
  public static String[][] map;
  public static int[][][] dp;
  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/17070/17070.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    map = new String[n][n];
    dp = new int[n + 1][n + 1][3];

    for (int r = 0; r < n; r++) {
      map[r] = br.readLine().split(" ");
    }

    for (int c = 2; c < n + 1; c++) {
      if (map[0][c - 1].equals("1")) {
        break;
      }
      dp[1][c][0] = 1;
    }

    for (int r = 2; r < n + 1; r++) {
      for (int c = 3; c < n  + 1; c++) {
        if (map[r - 1][c - 1].equals("1")) {
          continue;
        }
        if (map[r - 2][c - 1].equals("1") || map[r - 1][c - 2].equals("1")) {
          dp[r][c][0] = dp[r][c - 1][0] + dp[r][c - 1][2];
          dp[r][c][1] = dp[r - 1][c][1] + dp[r - 1][c][2];
          dp[r][c][2] = 0;
        }
        else {
          dp[r][c][0] = dp[r][c - 1][0] + dp[r][c - 1][2];
          dp[r][c][1] = dp[r - 1][c][1] + dp[r - 1][c][2];
          dp[r][c][2] = dp[r - 1][c - 1][0] + dp[r - 1][c - 1][1] + dp[r - 1][c - 1][2];
        }
      }
    }

    ans = 0;
    for (int d = 0; d < 3; d++) {
    ans = ans + dp[n][n][d];
    }

    System.out.println(ans);

    br.close();
  }
}
