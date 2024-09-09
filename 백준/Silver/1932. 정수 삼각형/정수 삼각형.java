import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static int n;
  public static List<List<Integer>> board = new ArrayList<>();
  public static int[][] dp;
  public static int ans = Integer.MIN_VALUE;
  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/1932/1932.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    for (int i = 0; i < n; i++) {
      String[] input = br.readLine().split(" ");
      List<Integer> tmp = new ArrayList<>();
      for (String elem : input) {
        tmp.add(Integer.parseInt(elem));
      }
      board.add(tmp);
    }

    dp = new int[n][n];

    dp[0][0] = board.get(0).get(0);

    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i + 1; j++) {
        if (j == 0) {
          dp[i][j] = board.get(i).get(j) + dp[i - 1][j];
        }
        else if (j == i) {
          dp[i][j] = board.get(i).get(j) + dp[i - 1][j - 1];
        }
        else {
          dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + board.get(i).get(j);
        }
      }
    }

    for (int i = 0; i < n; i++) {
      ans = Math.max(ans, dp[n - 1][i]);
    }

    System.out.println(ans);

    br.close();
  }
}
