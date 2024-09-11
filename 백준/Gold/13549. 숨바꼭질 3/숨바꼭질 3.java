import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
  public static int n, k;
  public static int[] ans = new int[100001];
  public static boolean[] visited = new boolean[100001];
  public static Queue<int[]> q = new LinkedList<>();

  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/13549/13549.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    n = Integer.parseInt(num[0]);
    k = Integer.parseInt(num[1]);

    q.add(new int[] {n, 0});
    ans[n] = 0;
    visited[n] = true;

    while (!q.isEmpty()) {
      int[] info = q.poll();
      int pos = info[0];
      int time = info[1];
      if (pos != 0) {
        for (int i = pos; i < 100001; i = i * 2) {
          if (!visited[i]) {
            ans[i] = time;
            q.add(new int[] {i, time});
            visited[i] = true;
            if (i == k) {
              break;
            }
          }
        }
      }
      if (pos != 0) {
        if (!visited[pos - 1]) {
          ans[pos - 1] = time + 1;
          q.add(new int[] {pos - 1, time + 1});
          visited[pos - 1] = true;
        }
        if (pos - 1 == k) {
          break;
        }
      }
      if (pos != 100000) {
        if (!visited[pos + 1]) {
          ans[pos + 1] = time + 1;
          q.add(new int[] {pos + 1, time + 1});
          visited[pos + 1] = true;
        }
        if (pos + 1 == k) {
          break;
        }
      }
    }

    System.out.println(ans[k]);

    br.close();
  }
}
