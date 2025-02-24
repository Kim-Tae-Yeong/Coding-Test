import java.io.*;
import java.util.*;

public class Main {
  static int V, E, K;
  static List<List<Edge>> graph = new ArrayList<>();
  static boolean[] visited;
  static int[] distance;
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
    pq.add(new Edge(K, 0));
    distance[K] = 0;

    while (!pq.isEmpty()) {
      Edge current = pq.poll();

      if (visited[current.to]) {
        continue;
      }
      visited[current.to] = true;

      for (Edge next : graph.get(current.to)) {
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