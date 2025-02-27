import java.io.*;
import java.util.*;

public class Main_1162 {
  static int N, M, K;
  // graph.get(i) : i번 노드에서 시작하는 간선 정보 저장
  static List<List<Edge>> graph = new ArrayList<>();
  // 우선순위 큐에 도착 노드까지 거리가 짧은 순으로 정렬
  static PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.cost, o2.cost));
  // distance[i][j] : i개 도로를 포장했을 때 j번 노드까지 걸리는 최소 거리
  static long[][] distance;
  static long ans = Long.MAX_VALUE;

  static void dijkstra() {
    pq.add(new Edge(1, 0, 0));

    while (!pq.isEmpty()) {
      Edge e = pq.poll();

      // 현재 노드에서 다음 노드로 이동하는 거리가 이전에 저장된 거리보다 길면 continue
      if (e.cost > distance[e.remove][e.to]) {
        continue;
      }

      // 현재 노드에서 출발하는 간선 정보 확인
      for (Edge next : graph.get(e.to)) {
        // 다음 노드로 이동할 때 해당 도로를 포장하지 않는 경우
        if (distance[e.remove][next.to] > distance[e.remove][e.to] + next.cost) {
          distance[e.remove][next.to] = distance[e.remove][e.to] + next.cost;
          pq.add(new Edge(next.to, distance[e.remove][next.to], e.remove));
        }
        // 다음 노드로 이동할 때 해당 도로를 포장하는 경우
        // 도로를 포장하면 해당 거리는 0이 되므로 다음 노드까지의 거리는 현재 노드까지의 거리와 같아짐
        // 우선순위 큐에는 포장한 도로 개수를 1 증가시켜서 저장
        if (e.remove < K && distance[e.remove + 1][next.to] > distance[e.remove][e.to]) {
          distance[e.remove + 1][next.to] = distance[e.remove][e.to];
          pq.add(new Edge(next.to, distance[e.remove + 1][next.to], e.remove + 1));
        }
      }
    }
  }

  static class Edge {
    int to;
    long cost;
    // 현재 노드까지 도달할 때 포장한 도로 개수
    int remove;

    public Edge(int to, long cost, int remove) {
      this.to = to;
      this.cost = cost;
      this.remove = remove;
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1162.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    for (int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      graph.get(from).add(new Edge(to, cost, 0));
      graph.get(to).add(new Edge(from, cost, 0));
    }

    distance = new long[K + 1][N + 1];
    for (int i = 0; i < K + 1; i++) {
      // 각 도로까지 거리를 최댓값으로 설정
      Arrays.fill(distance[i], Long.MAX_VALUE);
      // 시작 노드까지의 거리는 0으로 설정
      distance[i][1] = 0;
    }
    dijkstra();

    for (int i = 0; i < K + 1; i++) {
      ans = Math.min(ans, distance[i][N]);
    }

    System.out.println(ans);

    br.close();
  }
}
