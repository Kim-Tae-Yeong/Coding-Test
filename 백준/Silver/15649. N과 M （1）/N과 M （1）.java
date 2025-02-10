import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static boolean[] visited;
  static int[] ans;

  static void backTracking(int cnt) {
    if (cnt == M) {
      for (int num : ans) {
        System.out.print(num);
        System.out.print(" ");
      }
      System.out.println();
      return;
    }
    for (int i = 1; i < N + 1; i++) {
      if (!visited[i]) {
        visited[i] = true;
        ans[cnt] = i;
        backTracking(cnt + 1);
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

    backTracking(0);

    br.close();
  }
}