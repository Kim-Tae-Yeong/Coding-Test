import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  static int N, M, start, end;
  static StringTokenizer st;
  static List<List<Edge>> graph = new ArrayList<>();
  // dist[i] : 시작 지점에서 i까지 가는 최소 비용
  // path[i] : i로 가기 직전에 방문한 노드
  static int[] dist, path;
  // 다익스트라를 적용할 떄는 우선순위 큐 사용
  static PriorityQueue<Edge> pq = new PriorityQueue<>();
  static Stack<Integer> s = new Stack<>();

  static class Edge implements Comparable<Edge> {
    int to, cost;

    public Edge(int to, int cost) {
      this.to = to;
      this.cost = cost;
    }

    // cost가 낮은 순으로 정렬
    @Override
    public int compareTo(Edge o) {
      return Integer.compare(this.cost, o.cost);
    }
  }

  static void dijkstra() {
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[start] = 0;
    // 시작 지점까지 가는 비용이 0인 Edge 추가
    pq.add(new Edge(start, 0));

    while (!pq.isEmpty()) {
      Edge edge = pq.poll();
      int current = edge.to;
      int currentCost = edge.cost;
      // 등호를 넣으면 같은 비용에 다른 경로를 무시해버림
      if (dist[current] < currentCost) {
        continue;
      }
      for (Edge next : graph.get(current)) {
        int nextCost = dist[current] + next.cost;
        if (nextCost < dist[next.to]) {
          dist[next.to] = nextCost;
          path[next.to] = current;
          pq.add(new Edge(next.to, nextCost));
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./11779.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    M = Integer.parseInt(st.nextToken());

    for (int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      graph.get(from).add(new Edge(to, cost));
    }

    st = new StringTokenizer(br.readLine());
    start = Integer.parseInt(st.nextToken());
    end = Integer.parseInt(st.nextToken());

    dist = new int[N + 1];
    path = new int[N + 1];

    dijkstra();
    System.out.println(dist[end]);
    int num = end;
    while (num != 0) {
      s.push(num);
      num = path[num];
    }
    System.out.println(s.size());
    while (!s.isEmpty()) {
      System.out.print(s.pop() + " ");
    }

    br.close();
  }
}
