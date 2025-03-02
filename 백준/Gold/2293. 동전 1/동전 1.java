import java.io.*;
import java.util.*;

public class Main {
  static int n, k;
  static int[] moneys;
  static int[] dp;

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./2293.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());

    moneys = new int[n];
    dp = new int[k + 1];

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      moneys[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(moneys);

    for (int money : moneys) {
      if (money > k) {
        break;
      }
      dp[money] += 1;
      for (int i = money; i < k + 1; i++) {
        dp[i] += dp[i - money];
      }
    }

    System.out.println(dp[k]);

    br.close();
  }
}
