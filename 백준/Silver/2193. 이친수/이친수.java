import java.io.*;
import java.util.*;

public class Main_2193 {
  static int N;
  // ans[i][0] : i자리 이친수 중 끝자리가 0인 경우의 수
  // ans[i][1] : i자리 이친수 중 끝자리가 1인 경우의 수
  static long[][] ans;

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./2193.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    ans = new long[N + 1][2];
    // 1자리 이친수는 1밖에 없음
    ans[1][0] = 0;
    ans[1][1] = 1;

    for (int i = 2; i < N + 1; i++) {
      // i자리 이친수 중 끝자리가 0인 경우는 (i - 1)자리 이친수의 끝자리가 0 or 1 둘 다 가능(11만 나오지 않으면 되기 때문)
      // 때문에 (i - 1)자리 이친수의 끝자리가 0인 경우와 1인 경우를 더해줌
      ans[i][0] = ans[i - 1][0] + ans[i - 1][1];
      // i자리 이친수 중 끝자리가 1인 경우는 (i - 1)자리 이친수의 끝자리가 1이면 안됨(11이 나옴)
      // 떄문에 (i - 1)자리 이친수가 0인 경우만 가져옴
      ans[i][1] = ans[i - 1][0];
    }

    // N자리 이친수 중 끝자리가 0인 경우와 1인 경우를 더해 정답 출력
    System.out.println(ans[N][0] + ans[N][1]);

    br.close();
  }
}
