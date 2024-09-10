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
        // dp의 첫번째 col은 board 값 그대로 가져옴
        if (j == 1) {
          dp[0][1] = board[0][0];
          dp[1][1] = board[1][0];
        }
        // 이후 col은 두 가지 경우를 비교
        // 1) 현재 col 바로 직전 col( = col - 1)에서 오는 경우 -> 현재 row의 대각선에 위치한 값에서 현재 값을 더함
        // 2) (col - 2)에서 오는 경우 -> 1)처럼 꼭 대각선에서 올 필요가 없기 때문에 (col - 2)에 위치한 값 중 더 큰 값에 현재 값을 더함
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
