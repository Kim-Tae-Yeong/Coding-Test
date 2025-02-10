import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15649 {
  static int N, M;
  // visited[i] : 현재 경우에 i가 포함됨
  static boolean[] visited;
  static int[] ans;

  static void backTracking(int cnt) {
    // M개를 모두 고르면 출력
    if (cnt == M) {
      for (int num : ans) {
        System.out.print(num + " ");
      }
      System.out.println();
      return;
    }
    // 현재 경우에서 1부터 N까지 숫자 탐색
    for (int i = 1; i < N + 1; i++) {
      // i가 현재 경우에 포함되지 않은 수이면
      if (!visited[i]) {
        // 현재 경우에 i를 넣음
        visited[i] = true;
        ans[cnt] = i;
        // 다시 백트래킹 실시
        backTracking(cnt + 1);
        // i가 포함된 모든 경우 탐색이 끝나면 i를 현재 경우에서 제외시킴
        visited[i] = false;
      }
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./15649.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    visited = new boolean[N + 1];
    ans = new int[M];

    // M개를 선택할 때까지 백트래킹 실시
    backTracking(0);

    br.close();
  }
}
