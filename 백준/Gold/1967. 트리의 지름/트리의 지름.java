import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  public static int n, leafNode, ans;
  public static List<List<int[]>> graph = new ArrayList<>();
  public static boolean[] visited;

  private static void dfs(int node, int cnt) {
    visited[node] = true;
    for (int[] elem : graph.get(node)) {
      if (!visited[elem[0]]) {
        dfs(elem[0], cnt + elem[1]);
      }
    }
    visited[node] = false;
    ans = Math.max(ans, cnt);
  }

  public static void main (String[] args) throws Exception {
//    BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/1967/1967.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());

    for (int i = 0; i < n + 1; i++) {
      graph.add(new ArrayList<>());
    }

    visited = new boolean[n + 1];
    ans = Integer.MIN_VALUE;

    for (int i = 0; i < n - 1; i++) {
      String[] input = br.readLine().split(" ");
      int start = Integer.parseInt(input[0]);
      int end = Integer.parseInt(input[1]);
      int cost = Integer.parseInt(input[2]);
      graph.get(start).add(new int[] {end, cost});
      graph.get(end).add(new int[] {start, cost});
    }

    for (int i = leafNode; i < n + 1; i++) {
      dfs(i, 0);
    }

    System.out.println(ans);

    br.close();
  }

}
