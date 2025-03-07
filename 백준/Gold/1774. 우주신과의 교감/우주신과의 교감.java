import java.io.*;
import java.util.*;

public class Main {
  static int N, M;
  static List<int[]> gods = new ArrayList<>();
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
    PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1.cost, o2.cost));
    pq.add(new Edge(1, 0));
    double mst = 0;

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
      for (int[] god : gods) {
        double cost = Math.sqrt(Math.pow(Math.abs(x - god[0]), 2) + Math.pow(Math.abs(y - god[1]), 2));
        graph.get(i + 1).add(new Edge(god[2], cost));
        graph.get(god[2]).add(new Edge(i + 1, cost));
      }
      gods.add(new int[] { x, y, i + 1 });
    }

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