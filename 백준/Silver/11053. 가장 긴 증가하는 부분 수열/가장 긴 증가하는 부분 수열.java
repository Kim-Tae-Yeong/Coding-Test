import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/11053/11053.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int n = Integer.parseInt(br.readLine());
    String[] input = br.readLine().split(" ");
    List<Integer> num = new ArrayList<>();
    num.add(0);
    for (int i = 0; i < n; i++) {
      num.add(Integer.parseInt(input[i]));
    }

    // dp[i] : 1 ~ i번째까지 중 num[i]를 포함한 최대 수열 길이
    int[] dp = new int[n + 1];
    dp[1] = 1;
    // 최대 수열 길이
    int ans = 1;

    for (int i = 2; i < n + 1; i++) {
      for (int j = i - 1; j > -1; j--) {
        // num[i]를 기준으로 num[0] ~ num[i - 1]까지 비교
        if (num.get(i) > num.get(j)) {
          // num[i]가 num[j]보다 크면 기존 dp[i]와 (dp[j]( = num[j]를 포함한 j까지의 최대 수열 길이) + 1) 중 큰 값을 dp[i]로 설정
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      // num[i]를 포함한 최대 수열 길이를 구했으면, 해당 수열의 길이와 num[i]를 포함하지 않은 최대 수열의 길이 중 더 큰 값이 정답으로 설정
      ans = Math.max(ans, dp[i]);
    }
    
    System.out.println(ans);
    br.close();
  }  
}
