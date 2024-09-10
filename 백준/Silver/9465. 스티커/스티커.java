import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static int t, n, ans;
  public static int[][] board;
  public static int[][] dp;
  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/9465/9465.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    t = Integer.parseInt(br.readLine());

    for (int i = 0; i < t; i++) {
      n = Integer.parseInt(br.readLine());
      board = new int[2][n];
      dp = new int[2][n + 1];

      for (int j = 0; j < 2; j++) {
        String[] input = br.readLine().split(" ");
        for (int k = 0; k < n; k++) {
          board[j][k] = Integer.parseInt(input[k]);
        }
      }

      for (int j = 1; j < n + 1; j++) {
        if (j == 1) {
          dp[0][1] = board[0][0];
          dp[1][1] = board[1][0];
        }
        else {
          dp[0][j] = Math.max(dp[1][j - 1] + board[0][j - 1], Math.max(dp[0][j - 2], dp[1][j - 2]) + board[0][j - 1]);
          dp[1][j] = Math.max(dp[0][j - 1] + board[1][j - 1], Math.max(dp[0][j - 2], dp[1][j - 2]) + board[1][j - 1]);
        }
      }
        
      System.out.println(Math.max(dp[0][n], dp[1][n]));
    }
    br.close();
  }  
}
