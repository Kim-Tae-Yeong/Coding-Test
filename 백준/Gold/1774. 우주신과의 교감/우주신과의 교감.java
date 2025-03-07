import java.io.*;
import java.util.*;

public class Main_1774 {
  static int N, M;
  // 우주신들의 위치 저장
  static List<int[]> gods = new ArrayList<>();
  // graph.get(i) : i번 노드와 연결된 간선 저장
  static List<List<Edge>> graph = new ArrayList<>();
  static boolean[] visited;

  static class Edge {
    int to;
    double cost;

    public Edge(int to, double cost) {
      this.to = to;
      this.cost = cost;
    }
  }

  static double prim() {
    // 현재까지 방문한 노드들에 연결된 간선 중 방문하지 않은 노드로 연결되는 간선을 우선순위 큐에 저장
    // 간선의 비용을 오름차순으로 정렬
    PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1.cost, o2.cost));
    // 시작 노드는 1로 설정
    pq.add(new Edge(1, 0));
    double mst = 0;

    while (!pq.isEmpty()) {
      Edge e = pq.poll();
      // 이전에 방문한 적이 있으면 continue
      if (visited[e.to]) {
        continue;
      }
      visited[e.to] = true;
      mst += e.cost;

      // 현재 노드에서 연결된 간선 중 방문하지 않은 노드로 연결되는 간선을 찾음
      for (Edge next : graph.get(e.to)) {
        if (!visited[next.to]) {
          pq.add(next);
        }
      }
    }

    return mst;
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1774.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    for (int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }
    visited = new boolean[N + 1];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      // 이전에 입력받은 우주신들과의 거리를 구해서 저장
      for (int[] god : gods) {
        double cost = Math.sqrt(Math.pow(Math.abs(x - god[0]), 2) + Math.pow(Math.abs(y - god[1]), 2));
        // 양방향으로 저장
        graph.get(i + 1).add(new Edge(god[2], cost));
        graph.get(god[2]).add(new Edge(i + 1, cost));
      }
      // 현재 우주신의 좌표 저장
      gods.add(new int[] { x, y, i + 1 });
    }

    // 입력받은 두 우주신은 이미 연결되어 있기 때문에 두 우주신 사이에 비용이 0인 간선 추가
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      graph.get(from).add(new Edge(to, 0));
      graph.get(to).add(new Edge(from, 0));
    }

    System.out.println(String.format("%.2f", prim()));

    br.close();
  }
}
