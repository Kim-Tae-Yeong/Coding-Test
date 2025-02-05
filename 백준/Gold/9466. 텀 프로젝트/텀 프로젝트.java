import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  static int t, n, ans;
  static int[] num;
  static boolean[] visited;
  static Deque<Integer> d = new ArrayDeque<>();

  static void dfs(int current) {
    d.add(current);
    visited[current] = true;
    int next = num[current];
    if (!visited[next]) {
      dfs(next);
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./9466.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    t = Integer.parseInt(st.nextToken());

    for (int i = 0; i < t; i++) {
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      num = new int[n + 1];
      visited = new boolean[n + 1];

      st = new StringTokenizer(br.readLine());
      for (int j = 1; j < n + 1; j++) {
        num[j] = Integer.parseInt(st.nextToken());
      }

      ans = 0;

      for (int j = 1; j < n + 1; j++) {
        if (!visited[j]) {
          d = new ArrayDeque<>();
          dfs(j);
          int start = num[d.peekLast()];
          while (true) {
            if (d.isEmpty()) {
              break;
            }
            int end = d.pollFirst();
            if (start == end) {
              break;
            }
            ans++;
          }
        }
      }

      System.out.println(ans);
    }
    br.close();
  }
}
