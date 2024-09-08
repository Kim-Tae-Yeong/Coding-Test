import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static int n;
  public static List<List<Integer>> cost = new ArrayList<>();
  public static int[] dp = new int[3];
  public static int[] board = new int[3];
  public static int ans = Integer.MAX_VALUE;

  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/1149/1149.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());

    for (int i = 0; i < n; i++) {
      String[] input = br.readLine().split(" ");
      List<Integer> tmp = new ArrayList<>();
      for (int j = 0; j < 3; j++) {
        tmp.add(Integer.parseInt(input[j]));
      }
      cost.add(tmp);
    }

    // dp 배열을 이용해 cost[i]까지 가는 최소 비용을 구함
    // dfs를 사용하면 시간복잡도가 O(2 ^ n)이 되는데 n = 1000까지 가능하므로 시간초과 발생
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < 3; j++) {
        if (i == 0) {
          dp[j] = cost.get(0).get(j);
        }
        else {
          int tmp = Integer.MAX_VALUE;
          for (int k = 0; k < 3; k++) {
            if (j != k) {
              tmp = Math.min(tmp, board[k]);
            }
          dp[j] = tmp + cost.get(i).get(j);
          }
        }
      }
      for (int j = 0; j < 3; j++) {
        board[j] = dp[j];
      }
    }
    
    for (int i = 0; i < 3; i++) {
      ans = Math.min(ans, board[i]);
    }

    System.out.println(ans);

    br.close();
  }
}
