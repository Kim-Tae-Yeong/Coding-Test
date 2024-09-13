import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static int n, ans;
  public static String[][] map;
  // dp[r][c][0] : (r, c) 위치에 가로로 파이프를 설치할 수 경우
  // dp[r][c][1] : (r, c) 위치에 세로로 파이프를 설치할 수 경우
  // dp[r][c][2] : (r, c) 위치에 대각선으로 파이프를 설치할 수 경우
  public static int[][][] dp;
  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/17070/17070.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    map = new String[n][n];
    dp = new int[n + 1][n + 1][3];

    for (int r = 0; r < n; r++) {
      map[r] = br.readLine().split(" ");
    }

    // 배열의 첫 번째 행에 대한 정보
    for (int c = 2; c < n + 1; c++) {
      // 배열 중간에 벽이 있으면 해당 위치와 그 이후의 위치에는 파이프를 설치할 수 없음
      if (map[0][c - 1].equals("1")) {
        break;
      }
      // 첫 번째 행은 가로로 설치하는 방법 1가지 밖에 존재하지 않음
      dp[1][c][0] = 1;
    }

    // 두 번째 행부터
    for (int r = 2; r < n + 1; r++) {
      // 세 번째 열부터
      // 행과 상관없이 첫 번째와 두 번째 열에는 파이프를 설치할 수 없음
      for (int c = 3; c < n  + 1; c++) {
        // 현재 위치가 벽이면 continue
        if (map[r - 1][c - 1].equals("1")) {
          continue;
        }
        // 현재 위치(r, c)의 위쪽(r - 1, c) 또는 왼쪽(r, c - 1)에 벽이 있으면 현재 위치에는 대각선으로 파이프를 설치할 수 없음
        if (map[r - 2][c - 1].equals("1") || map[r - 1][c - 2].equals("1")) {
          dp[r][c][2] = 0;
        }
        // 그게 아니라면 (r - 1, c - 1) 위치에 파이프를 어떻게 설치하든 (r, c) 위치에 대각선으로 파이프를 설치할 수 있음
        // 때문에 dp[r - 1][c - 1]에 설치 가능한 모든 경우를 더해서 dp[r][c][2]에 저장
        else {
          dp[r][c][2] = dp[r - 1][c - 1][0] + dp[r - 1][c - 1][1] + dp[r - 1][c - 1][2];
        }
        // (r, c) 위치에 가로로 파이프를 설치할 수 있는 경우는 (r, c - 1) 위치에 파이프를 가로 또는 대각선으로 설치한 경우만 가능
        dp[r][c][0] = dp[r][c - 1][0] + dp[r][c - 1][2];
        // (r, c) 위치에 세로로 파이프를 설치할 수 있는 경우는 (r - 1, c) 위치에 파이프를 세로 또는 대각선으로 설치한 경우만 가능
        dp[r][c][1] = dp[r - 1][c][1] + dp[r - 1][c][2];
      }
    }

    ans = 0;
    // 마지막 위치에 설치 가능한 모든 경우를 더함
    for (int d = 0; d < 3; d++) {
      ans = ans + dp[n][n][d];
    }

    System.out.println(ans);

    br.close();
  }
}
