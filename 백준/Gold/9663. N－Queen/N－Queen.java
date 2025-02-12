import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, ans;
  static boolean[] col, diag1, diag2;

  static void backTracking(int r) {
    if (r == N) {
      ans++;
      return;
    }
    for (int c = 0; c < N; c++) {
      if (col[c] || diag1[r + c] || diag2[r - c + (N - 1)]) {
        continue;
      }
      col[c] = diag1[r + c] = diag2[r - c + (N - 1)] = true;
      backTracking(r + 1);
      col[c] = diag1[r + c] = diag2[r - c + (N - 1)] = false;
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./9663.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    col = new boolean[N];
    diag1 = new boolean[2 * N - 1];
    diag2 = new boolean[2 * N - 1];
    ans = 0;

    backTracking(0);

    System.out.println(ans);

    br.close();
  }
}