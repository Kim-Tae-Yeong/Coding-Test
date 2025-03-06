import java.io.*;
import java.util.*;

public class Main {
  static int N, M, mst;
  static List<List<Edge>> graph = new ArrayList<>();
  static boolean[] visited;
  static PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

  static class Edge {
    int to, cost;

    public Edge(int to, int cost) {
      this.to = to;
      this.cost = cost;
    }
  }

  static void prim() {
    pq.add(new Edge(1, 0));

    while (!pq.isEmpty()) {
      Edge e = pq.poll();
      if (visited[e.to]) {
        continue;
      }
      visited[e.to] = true;
      mst += e.cost;

      for (Edge next : graph.get(e.to)) {
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