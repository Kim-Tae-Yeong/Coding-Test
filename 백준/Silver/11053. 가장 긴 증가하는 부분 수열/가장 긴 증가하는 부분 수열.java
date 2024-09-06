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

    // dp[i] : 1 ~ i번째까지 중 i를 포함한 최대 수열 길이
    int[] dp = new int[n + 1];
    dp[1] = 1;
    int ans = 1;

    for (int i = 2; i < n + 1; i++) {
      for (int j = i - 1; j > -1; j--) {
        if (num.get(i) > num.get(j)) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      ans = Math.max(ans, dp[i]);
    }
    
    System.out.println(ans);
    br.close();
  }  
}
