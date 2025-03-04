import java.io.*;
import java.util.*;

public class Main_1738 {
  static int n, m, u, v, w;
  static List<Edge> graph = new ArrayList<>();
  // distance[i] : 시작 노드에서 i번째 노드까지 가는데 최대 비용
  // path[i] : i번째 노드로 들어노는 노드 번호
  static int[] distance, path;

  static class Edge {
    int from, to, cost;

    public Edge(int from, int to, int cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
  }

  static boolean hasPositiveCycle() {
    // 시작 노드까지의 거리는 0으로 설정
    distance[1] = 0;
    // 벨만 - 포드 실행
    for (int i = 0; i < n - 1; i++) {
      for (Edge e : graph) {
        if (distance[e.from] != Integer.MIN_VALUE && distance[e.to] < distance[e.from] + e.cost) {
          distance[e.to] = distance[e.from] + e.cost;
          path[e.to] = e.from;
        }
      }
    }

    // 양의 사이클이 있는지 확인
    // 있다면 큐에 사이클을 이루는 노드 저장
    Queue<Integer> q = new LinkedList<>();
    boolean[] cycle = new boolean[n + 1];
    for (Edge e : graph) {
      if (distance[e.from] != Integer.MIN_VALUE && distance[e.to] < distance[e.from] + e.cost) {
        cycle[e.to] = true;
        q.offer(e.to);
      }
    }

    // 해당 사이클로부터 bfs를 통해 도착 노드까지 갈 수 있는지 확인
    while (!q.isEmpty()) {
      int current = q.poll();
      if (current == n) {
        return true;
      }
      for (Edge e : graph) {
        if (e.from == current && !cycle[e.to]) {
          cycle[e.to] = true;
          q.offer(e.to);
        }
      }
    }

    return false;
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1738.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    distance = new int[n + 1];
    path = new int[n + 1];
    Arrays.fill(distance, Integer.MIN_VALUE);

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      u = Integer.parseInt(st.nextToken());
      v = Integer.parseInt(st.nextToken());
      w = Integer.parseInt(st.nextToken());
      graph.add(new Edge(u, v, w));
    }

    if (hasPositiveCycle()) {
      System.out.println(-1);
    }
    // 양의 사이클이 없다면
    else {
      StringBuilder sb = new StringBuilder();
      Deque<Integer> d = new ArrayDeque<>();

      // 도착 지점부터 시작 지점까지 경로를 역추적
      int prev = n;
      while (prev != 0) {
        d.push(prev);
        prev = path[prev];
      }

      // 역추적한 경로를 원래대로 출력
      while (!d.isEmpty()) {
        sb.append(d.pop()).append(" ");
      }

      System.out.println(sb.toString().trim());
    }
    br.close();
  }
}
