import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
  public static int n, e, ans;
  public static List<List<List<Integer>>> graph = new ArrayList<>();
  public static int[] visited;

  public static void dijkstra (int start) {
    PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
    q.add(new int[] {start, 0});
    visited[start] = 0;
    while (!q.isEmpty()) {
      int[] pos = q.poll();
      int currentIdx = pos[0];
      int currentCost = pos[1];

      if (currentCost > visited[currentIdx]) {
        continue;
      }

      for (List<Integer> elem : graph.get(currentIdx)) {
        int next = elem.get(1);
        int cost = elem.get(2);
        if (visited[currentIdx] + cost < visited[next]) {
          visited[next] = visited[currentIdx] + cost;
          q.add(new int[] {next, visited[next]});
        }
      }
    }
  }
  public static void main (String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/1504/1504.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    n = Integer.parseInt(num[0]);
    e = Integer.parseInt(num[1]);

    for (int i = 0; i < n + 1; i++) {
      graph.add(new ArrayList<>());
    }

    List<Integer> tmp;
    for (int i = 0; i < e; i++) {
      String[] input = br.readLine().split(" ");
      int start = Integer.parseInt(input[0]);
      int end = Integer.parseInt(input[1]);
      int cost = Integer.parseInt(input[2]);

      tmp = new ArrayList<>();
      tmp.add(start);
      tmp.add(end);
      tmp.add(cost);
      graph.get(start).add(tmp);

      tmp = new ArrayList<>();
      tmp.add(end);
      tmp.add(start);
      tmp.add(cost);
      graph.get(end).add(tmp);
    }

    String[] info = br.readLine().split(" ");
    int v1 = Integer.parseInt(info[0]);
    int v2 = Integer.parseInt(info[1]);

    visited = new int[n + 1];
    Arrays.fill(visited, Integer.MAX_VALUE);
    dijkstra(v1);
    int start_to_v1 = visited[1];
    int v1_to_v2 = visited[v2];
    int v1_to_end = visited[n];
    
    visited = new int[n + 1];
    Arrays.fill(visited, Integer.MAX_VALUE);
    dijkstra(v2);
    int start_to_v2 = visited[1];
    int v2_to_end = visited[n];

    if (v1_to_v2 == Integer.MAX_VALUE) {
      System.out.println(-1);
    }
    else {
      int distance1;
      if (start_to_v1 == Integer.MAX_VALUE || v2_to_end == Integer.MAX_VALUE) {
        distance1 = Integer.MAX_VALUE;
      }
      else {
        distance1 = start_to_v1 + v1_to_v2 + v2_to_end;
      }

      int distance2;
      if (start_to_v2 == Integer.MAX_VALUE || v1_to_end == Integer.MAX_VALUE) {
        distance2 = Integer.MAX_VALUE;
      }
      else {
        distance2 = start_to_v2 + v1_to_v2 + v1_to_end;
      }

      ans = Math.min(distance1, distance2);

      if (ans == Integer.MAX_VALUE) {
        System.out.println(-1);
      }
      else {
        System.out.println(ans);
      }
    }

    br.close();
  }
}
