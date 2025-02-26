import java.io.*;
import java.util.*;

public class Main_5972 {
  static int N, M;
  // graph.get(i) : i번 노드에서 시작하는 간선 정보 저장
  static List<List<Edge>> graph = new ArrayList<>();
  // cows[i] : i번 노드까지 가는데 필요한 여물의 최소 개수
  static int[] cows;
  // visited[i] : i번 노드 방문 여부
  static boolean[] visited;
  // 우선순위 큐를 여물의 개수 오름차순 정렬
  static PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

  // Edge 클래스로 도착 노드와 도착 노드까지 여물의 개수 저장
  static class Edge {
    int to, cost;

    public Edge(int to, int cost) {
      this.to = to;
      this.cost = cost;
    }
  }

  static void dijkstra() {
    // 시작 노드까지 필요한 여물의 개수는 0개
    pq.add(new Edge(1, 0));
    cows[1] = 0;

    // 우선순위 큐가 빌때까지 진행
    while (!pq.isEmpty()) {
      // 우선순위 큐에서 가장 앞에 저장된 정보를 뺌
      // 이를 통해 현재 갈 수 있는 노드 중 여물의 개수가 가장 적은 노드를 알 수 있음
      Edge e = pq.poll();

      // 만약 해당 노드가 이전에 방문한 노드면
      // 현재 여물의 개수보다 더 적은 여물의 개수로 해당 노드를 방문할 수 있음
      // 때문에 현재 탐색은 의미가 없음
      if (visited[e.to]) {
        continue;
      }
      // 방문 여부 갱신
      visited[e.to] = true;

      // 현재 방문한 노드의 인접한 노드를 탐색
      for (Edge next : graph.get(e.to)) {
        // 인접한 노드에 대하여 이전에 저장된 여물의 개수보다 더 적은 여물의 개수로 방문할 수 있으면
        if (cows[next.to] > cows[e.to] + next.cost) {
          // 여물의 개수 갱신
          cows[next.to] = cows[e.to] + next.cost;
          // 우선순위 큐에 해당 노드와 해당 노드까지 필요한 여물의 개수를 저장함
          pq.add(new Edge(next.to, cows[next.to]));
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./5972.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    for (int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    cows = new int[N + 1];
    // 초기에는 여물의 개수를 최댓값으로 설정
    Arrays.fill(cows, Integer.MAX_VALUE);
    visited = new boolean[N + 1];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      // 무방향 그래프이기 때문에 양쪽으로 간선 정보 저장
      graph.get(from).add(new Edge(to, cost));
      graph.get(to).add(new Edge(from, cost));
    }

    dijkstra();

    System.out.println(cows[N]);

    br.close();
  }
}
