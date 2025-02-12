import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9663 {
  static int N, ans;
  // col : 현재까지 퀸이 놓인 col 표시
  // diag1 : 대각선(/)에 놓인 퀸들은 row + col 값이 같음
  // diag2 : 대각선(\)에 놓인 퀸들은 row - col 값이 같음(모든 값을 양수로 바꾸기 위해 N - 1을 더해줌)
  static boolean[] col, diag1, diag2;

  static void backTracking(int r) {
    // 모든 row를 탐색했으면
    if (r == N) {
      // 정답 개수 증가
      ans++;
      return;
    }
    for (int c = 0; c < N; c++) {
      // 현재 col or 같은 대각선에 퀸이 배치되어 있으면 continue
      if (col[c] || diag1[r + c] || diag2[r - c + (N - 1)]) {
        continue;
      }
      // 현재 col & 현재 위치 기준 대각선에 퀸을 배치함
      col[c] = diag1[r + c] = diag2[r - c + (N - 1)] = true;
      // 다음 row로 넘어감
      backTracking(r + 1);
      // 현재 col 탐색이 끝나면 배치된 퀸을 제거
      col[c] = diag1[r + c] = diag2[r - c + (N - 1)] = false;
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./9663.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    col = new boolean[N];
    // diag1 배열에는 row + col 값이 들어가기 때문에 최대 2N - 2까지 가능
    diag1 = new boolean[2 * N - 1];
    // diag2 배열에는 row - col + (N - 1) 값이 들어가기 때문에 최대 2N - 2까지 가능
    diag2 = new boolean[2 * N - 1];
    ans = 0;

    backTracking(0);

    System.out.println(ans);

    br.close();
  }
}
