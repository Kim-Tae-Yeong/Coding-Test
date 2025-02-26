import java.io.*;
import java.util.*;

public class Main {
  static int N, M, K;
  static List<List<Edge>> graph = new ArrayList<>();
  static PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.cost, o2.cost));
  static long[][] distance;
  static long ans = Long.MAX_VALUE;

  static void dijkstra() {
    pq.add(new Edge(1, 0, 0));

    while (!pq.isEmpty()) {
      Edge e = pq.poll();

      if (e.cost > distance[e.remove][e.to]) {
        continue;
      }

      for (Edge next : graph.get(e.to)) {
        if (distance[e.remove][next.to] > distance[e.remove][e.to] + next.cost) {
          distance[e.remove][next.to] = distance[e.remove][e.to] + next.cost;
          pq.add(new Edge(next.to, distance[e.remove][next.to], e.remove));
        }
        if (e.remove < K && distance[e.remove + 1][next.to] > e.cost) {
          distance[e.remove + 1][next.to] = e.cost;
          pq.add(new Edge(next.to, e.cost, e.remove + 1));
        }
      }
    }
  }

  static class Edge {
    int to;
    long cost;
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
      Arrays.fill(distance[i], Long.MAX_VALUE);
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