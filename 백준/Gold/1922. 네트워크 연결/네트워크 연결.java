import java.io.*;
import java.util.*;

public class Main_1922 {
  static int N, M, mst;
  // graph.get(i) : i번 노드와 연결된 간선 저장
  static List<List<Edge>> graph = new ArrayList<>();
  // visited[i] : i번 노드 방문 여부 확인
  static boolean[] visited;
  // 우선순위 큐에 간선의 비용을 오름차순으로 정렬
  static PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

  static class Edge {
    int to, cost;

    public Edge(int to, int cost) {
      this.to = to;
      this.cost = cost;
    }
  }

  static void prim() {
    // 시작 노드를 1로 설정
    pq.add(new Edge(1, 0));

    while (!pq.isEmpty()) {
      Edge e = pq.poll();
      // 이전에 방문한 적이 있으면 continue
      if (visited[e.to]) {
        continue;
      }
      // 방문 여부 갱신
      visited[e.to] = true;
      // 현재 비용을 mst에 추가
      mst += e.cost;

      // 현재 노드에 연결된 간선 확인
      for (Edge next : graph.get(e.to)) {
        // 방문하지 않은 노드로 가는 간선이면 우선순위 큐에 추가
        if (!visited[next.to]) {
          pq.add(next);
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1922.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    M = Integer.parseInt(st.nextToken());

    for (int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      // 무방향 그래프이기 때문에 양쪽 노드에 저장
      graph.get(a).add(new Edge(b, c));
      graph.get(b).add(new Edge(a, c));
    }

    visited = new boolean[N + 1];
    mst = 0;
    prim();

    System.out.println(mst);
    br.close();
  }
}
