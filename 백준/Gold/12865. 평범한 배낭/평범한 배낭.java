import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  public static int n, k;
  // 물건을 무게를 기준으로 오름차순 정렬
  public static List<int[]> info = new ArrayList<>();
  // ans[r][c] : 0 ~ (r - 1)까지의 물건을 사용해서 무게 c를 만들었을 때 최대 가치
  public static int[][] ans;

  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/12865/12865.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    n = Integer.parseInt(num[0]);
    k = Integer.parseInt(num[1]);
    
    for (int i = 0; i < n; i++) {
      String[] input = br.readLine().split(" ");
      int w = Integer.parseInt(input[0]);
      int v = Integer.parseInt(input[1]);
      info.add(new int[] {w, v});
    }

    info.sort((o1, o2) -> o1[0] - o2[0]);

    ans = new int[n + 1][k + 1];

    // 각각의 물건에 대해서
    for (int i = 0; i < n; i++) {
      int currentWeigth = info.get(i)[0];
      int currentValue = info.get(i)[1];
      // 무게를 1씩 늘림
      for (int j = 1; j < k + 1; j++) {
        if (currentWeigth > j) {
          ans[i + 1][j] = ans[i][j];
        }
        else {
          ans[i + 1][j] = Math.max(ans[i][j - currentWeigth] + currentValue, ans[i][j]);
        }
      }
    }

    System.out.println(ans[n][k]);

    br.close();
  }
}
