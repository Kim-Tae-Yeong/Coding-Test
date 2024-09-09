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
        // 삼각형의 가장 왼쪽 부분은 방법이 하나밖에 없음
        if (j == 0) {
          dp[i][j] = board.get(i).get(j) + dp[i - 1][j];
        }
        // 삼각형의 가장 오른쪽 부분은 방법이 하나밖에 없음
        else if (j == i) {
          dp[i][j] = board.get(i).get(j) + dp[i - 1][j - 1];
        }
        // 그 외 부분은 현재 위치를 (i ,j)라고 했을 때, (i - 1, j - 1)과 (i - 1, j) 두 곳에서 올 수 있음
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
