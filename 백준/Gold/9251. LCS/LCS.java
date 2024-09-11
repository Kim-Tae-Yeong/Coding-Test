import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static String[] a, b;
  // dp[r][c] = 배열 a의 첫 번째부터 r번째까지 문자열 & 배열 b의 첫 번째부터 r번째까지 문자열 중 최장 공통 부분 수열 길이
  public static int[][] dp;

  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/9251/9251.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    a = br.readLine().split("");
    b = br.readLine().split("");
    dp = new int[a.length + 1][b.length + 1];
    
    for (int i = 1; i < a.length + 1; i++) {
      // 배열 a의 문자열을 하나씩 늘려가면서 배열 b의 첫 번째 문자와 같으면 해당 지점부터 최장 공통 부분 수열의 길이를 1로 설정
      if (a[i - 1].equals(b[0])) {
        dp[i][1] = 1;
      }
      else {
        dp[i][1] = dp[i - 1][1];
      }
    }
    for (int i = 1; i < b.length + 1; i++){
      // 배열 b의 문자열을 하나씩 늘려가면서 배열 a의 첫 번째 문자와 같으면 해당 지점부터 최장 공통 부분 수열의 길이를 1로 설정
      if (b[i - 1].equals(a[0])) {
        dp[1][i] = 1;
      }
      else {
        dp[1][i] = dp[1][i - 1];
      }
    }
    
    // 배열 a, b를 하나씩 늘려감
    for (int i = 2; i < a.length + 1; i++) {
      for (int j = 2; j < b.length + 1; j++) {
        // 배열 a의 i번째 글자와 배열 b의 j번째 글자가 같으면 dp[i - 1][j - 1]에 1을 더함
        // dp[i - 1][j - 1]은 배열 a의 (i - 1)번째까지와 배열 b의 (j - 1)번째까지 문자열의 최장 공통 부분 수열의 길이임
        // a[i]와 b[j]가 같기 때문에 최장 공통 부분 수열에 해당 글자를 더함 -> 길이가 1 늘어남
        if (a[i - 1].equals(b[j - 1])) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        }
        // 글자가 다르면 dp[i - 1][j]와 dp[i][j - 1] 둘 중 큰 값을 가져옴
        // dp[i - 1][j] = 배열 a의 (i - 1)번째까지와 배열 b의 j번째까지 문자열의 최장 공통 부분 수열 길이
        // dp[i][j - 1] = 배열 a의 i번째까지와 배열 b의 (j - 1)번째까지 문자열의 최장 공통 부분 수열 길이
        // 즉 a[i]와 b[j] 중 최장 공통 부분 수열에 추가했을 때 길이가 길어지는 글자를 택함
        else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    
    System.out.println(dp[a.length][b.length]);

    br.close();
  }  
}
