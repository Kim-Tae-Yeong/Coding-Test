import java.io.*;
import java.util.*;

public class Main_2293 {
  static int n, k;
  static int[] moneys;
  // dp[i] : i원을 만드는 경우의 수
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

    // 가지고 있는 돈 오름차순 정렬
    Arrays.sort(moneys);

    // 가지고 있는 돈 확인
    for (int money : moneys) {
      // 가지고 있는 돈이 목표 금액보다 크면 break
      if (money > k) {
        break;
      }
      // money를 만들 수 있는 경우 1 증가(money 하나로만 만드는 경우)
      dp[money] += 1;
      // money보다 큰 돈(i)을 만드는 경우 확인
      for (int i = money + 1; i < k + 1; i++) {
        // i = (i - money) + money
        // money로 money를 만들 수 있는 경우는 1가지 밖에 없음
        // 때문에 dp[i](i을 만드는 경우)에 dp[i - money](i - money를 만드는 경우)만 더해주면 됨
        dp[i] += dp[i - money];
      }
    }

    System.out.println(dp[k]);

    br.close();
  }
}
