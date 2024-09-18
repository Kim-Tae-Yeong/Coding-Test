import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1967 {
  public static int n, ans;
  // graph.get(i) : [i에서 출발해서 도착하는 Node, 해당 cost]
  public static List<List<int[]>> graph = new ArrayList<>();
  // visited[i] : i번째 node 방문 여부 확인
  public static boolean[] visited;

  // dfs를 이용해 한 node에서 다른 node까지 가는 cost 계산
  private static void dfs(int node, int cnt) {
    // 현재 node 방문 여부 true
    visited[node] = true;
    for (int[] elem : graph.get(node)) {
      if (!visited[elem[0]]) {
        dfs(elem[0], cnt + elem[1]);
      }
    }
    // 현재 node에서 갈 수 있는 모든 node를 처리하면 방문 여부 false
    visited[node] = false;
    // 한 node에서 다른 node까지 가는 경로 중 최댓값 구함
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

    for (int i = 1; i < n + 1; i++) {
      dfs(i, 0);
    }

    System.out.println(ans);

    br.close();
  }

}
