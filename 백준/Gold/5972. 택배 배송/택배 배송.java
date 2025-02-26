import java.io.*;
import java.util.*;

public class Main {
  static int N, M;
  static List<List<Edge>> graph = new ArrayList<>();
  static int[] cows;
  static boolean[] visited;
  static PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

  static class Edge {
    int to, cost;

    public Edge(int to, int cost) {
      this.to = to;
      this.cost = cost;
    }
  }

  static void dijkstra() {
    pq.add(new Edge(1, 0));
    cows[1] = 0;

    while (!pq.isEmpty()) {
      Edge e = pq.poll();

      if (visited[e.to]) {
        continue;
      }
      visited[e.to] = true;

      for (Edge next : graph.get(e.to)) {
        if (cows[next.to] > cows[e.to] + next.cost) {
          cows[next.to] = cows[e.to] + next.cost;
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
    Arrays.fill(cows, Integer.MAX_VALUE);
    visited = new boolean[N + 1];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      graph.get(from).add(new Edge(to, cost));
      graph.get(to).add(new Edge(from, cost));
    }

    dijkstra();

    System.out.println(cows[N]);

    br.close();
  }
}
