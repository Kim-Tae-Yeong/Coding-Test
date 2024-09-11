import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  public static int n, k;
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

    // 물건을 무게를 기준으로 오름차순 정렬
    info.sort((o1, o2) -> o1[0] - o2[0]);

    ans = new int[n + 1][k + 1];

    // 각각의 물건에 대해서
    for (int i = 0; i < n; i++) {
      int currentWeigth = info.get(i)[0];
      int currentValue = info.get(i)[1];
      // 무게를 1씩 늘림
      for (int j = 1; j < k + 1; j++) {
        // 현재 물건의 무게가 가방의 무게보다 무거우면 현재 물건을 가방에 넣지 않음
        // 즉 현재 가방의 무게에 대해 이전 물건까지 계산한 값을 가져옴
        if (currentWeigth > j) {
          ans[i + 1][j] = ans[i][j];
        }
        // 그렇지 않으면( = 현재 물건을 가방에 넣을 수 있으면)
        // 현재 물건을 가방에 넣었을 때 가치와 넣지 않았을 때의 가치 중 더 큰 값을 가져옴
        else {
          ans[i + 1][j] = Math.max(ans[i][j - currentWeigth] + currentValue, ans[i][j]);
        }
      }
    }

    System.out.println(ans[n][k]);

    br.close();
  }
}
