import java.io.*;
import java.util.*;

public class Solution_1859 {

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1859.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int T = Integer.parseInt(st.nextToken());

    for (int i = 0; i < T; i++) {
      st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int[] arr = new int[N];

      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        arr[j] = Integer.parseInt(st.nextToken());
      }

      // 배열의 마지막 값을 최댓값으로 설정
      // 배열의 뒤부터 탐색하면서 현재 값이 최댓값보다 작으면 판매
      // 현재부터 끝까지 중 최댓값에 판매하는데 가장 큰 이익을 가져오기 때문
      // 현재 값이 최댓값보다 크거나 같으면 최댓값 갱신
      // 즉, 그 이전의 값들은 갱신한 시점에 팔야아 더 큰 이익을 가져옴
      // 이처럼 미래를 알고 있는 문제에서는 마지막부터 탐색하는 방법 사용
      int max = arr[N - 1];
      long ans = 0;
      for (int j = N - 1; j > -1; j--) {
        if (arr[j] < max) {
          ans += (max - arr[j]);
        } else {
          max = arr[j];
        }
      }

      System.out.println("#" + (i + 1) + " " + ans);
    }
    br.close();
  }
}
