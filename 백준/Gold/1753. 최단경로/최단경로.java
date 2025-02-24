import java.io.*;
import java.util.*;

public class Main_1753 {
  static int V, E, K;
  // graph.get(i) : i번 노드에서 출발하는 간선의 정보 저장
  static List<List<Edge>> graph = new ArrayList<>();
  // visited[i] : i번 노드 방문 여부
  static boolean[] visited;
  // distance[i] : 시작 노드에서 i번 노드까지의 최단 거리
  static int[] distance;
  // 다익스트라 알고리즘은 주로 우선순위 큐 사용해서 구현
  // 우선순위 큐는 시작 노드에서 도착 노드까지 거리가 짧은 순으로 정렬
  static PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

  static class Edge {
    int to;
    int cost;

    public Edge(int to, int cost) {
      this.to = to;
      this.cost = cost;
    }
  }

  static void dijkstra() {
    // 가장 먼저 우선순위 큐에 시작 노드까지 거리가 0인 간선 저장
    // 이렇게 하면 graph.get(K)에 저장된 모든 노드를 우선순위 큐에 저장할 필요가 없음
    // 예를 들어 (K, 1, 1)과 (K, 1, 2) 이 두 간선이 있을 때 (K, 1, 2) 간선은 저장할 필요가 없음
    pq.add(new Edge(K, 0));
    // 시작 노드까지의 거리는 0
    distance[K] = 0;

    // 우선순위 큐가 빌때까지 진행
    while (!pq.isEmpty()) {
      // 현재 우선순위 큐에 저장된 간선의 정보 중 도착 노드까지의 거리가 가장 짧은 간선을 가져옴
      Edge current = pq.poll();

      // 도착 노드의 방문 여부 확인
      if (visited[current.to]) {
        continue;
      }
      visited[current.to] = true;

      // 도착 노드에서 갈 수 있는 노드 확인
      for (Edge next : graph.get(current.to)) {
        // 도착 노드에서 갈 수 있는 노드를 a라고 했을 때
        // (이전에 저장된 시작 노드에서 a 노드까지의 거리)와 (시작 노드에서 도착 노드까지의 거리) + (도착 노드에서 a 노드까지의 거리) 비교
        // 후자의 값이 더 작으면(즉, 더 짧은 경로가 있다면) 거리를 갱신하고 해당 정보(a 노드, 갱신된 시작 노드에서 a 노드까지의 거리)를
        // 우선순위 큐에 저장
        if (distance[next.to] > distance[current.to] + next.cost) {
          distance[next.to] = distance[current.to] + next.cost;
          pq.add(new Edge(next.to, distance[next.to]));
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1753.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    V = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    K = Integer.parseInt(st.nextToken());

    for (int i = 0; i < V + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      graph.get(from).add(new Edge(to, cost));
    }

    visited = new boolean[V + 1];
    distance = new int[V + 1];
    // 가장 먼저 각 노드까지의 거리를 INF로 설정
    Arrays.fill(distance, Integer.MAX_VALUE);

    dijkstra();
    for (int i = 1; i < V + 1; i++) {
      if (distance[i] == Integer.MAX_VALUE) {
        System.out.println("INF");
      } else {
        System.out.println(distance[i]);
      }
    }
    br.close();
  }
}
