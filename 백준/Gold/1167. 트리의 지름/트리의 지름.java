import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_1167 {
  static int V, farestNode;
  static int maxDistance = Integer.MIN_VALUE;
  static List<List<Edge>> graph = new ArrayList<>();
  static Stack<Edge> s = new Stack<>();
  static int[] distance;
  static boolean[] visited;

  static class Edge {
    int from, to, cost;

    public Edge(int from, int to, int cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
  }

  static void dfs() {
    if (!s.isEmpty()) {
      Edge edge = s.pop();
      distance[edge.to] = distance[edge.from] + edge.cost;
      if (distance[edge.to] > maxDistance) {
        farestNode = edge.to;
        maxDistance = distance[edge.to];
      }
      visited[edge.to] = true;
      for (Edge nextEdge : graph.get(edge.to)) {
        if (!visited[nextEdge.to]) {
          s.add(nextEdge);
          dfs();
        }
      }
      visited[edge.to] = false;
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1167.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    V = Integer.parseInt(st.nextToken());

    for (int i = 0; i < V + 1; i++) {
      graph.add(new ArrayList<>());
    }

    // 간선 정보 저장
    for (int i = 0; i < V; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      while (true) {
        int num = Integer.parseInt(st.nextToken());
        if (num == -1) {
          break;
        }
        int cost = Integer.parseInt(st.nextToken());
        graph.get(start).add(new Edge(start, num, cost));
      }
    }

    // 임의의 정점(여기서는 1번 정점)에서 가장 멀리 떨어진 정점을 찾음
    // 이 정점이 트리의 지름 중 한 정점이 됨
    distance = new int[V + 1];
    visited = new boolean[V + 1];
    s.add(new Edge(0, 1, 0));
    dfs();

    // 위에서 찾은 정점을 기준으로 가장 멀리 떨어진 정점 & 거리 찾기
    Arrays.fill(distance, 0);
    Arrays.fill(visited, false);
    maxDistance = Integer.MIN_VALUE;
    s.add(new Edge(0, farestNode, 0));
    dfs();

    System.out.println(maxDistance);

    br.close();
  }
}
