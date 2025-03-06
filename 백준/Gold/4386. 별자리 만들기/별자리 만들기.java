import java.io.*;
import java.util.*;

public class Main {
  static int n;
  static List<double[]> l = new ArrayList<>();
  static List<Edge> graph = new ArrayList<>();
  static int[] parent;
  static double mst = 0;

  static class Edge {
    int from, to;
    double cost;

    public Edge(int from, int to, double cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
  }

  static void kruskal() {
    for (Edge e : graph) {
      if (parent[e.from] == parent[e.to]) {
        continue;
      } else if (parent[e.from] < parent[e.to]) {
        mst += e.cost;
        int beforeParent = parent[e.to];
        for (int i = 1; i < n + 1; i++) {
          if (parent[i] == beforeParent) {
            parent[i] = parent[e.from];
          }
        }
      } else {
        mst += e.cost;
        int beforeParent = parent[e.from];
        for (int i = 1; i < n + 1; i++) {
          if (parent[i] == beforeParent) {
            parent[i] = parent[e.to];
          }
        }
      }
    }

  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./4386.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      double x = Double.parseDouble(st.nextToken());
      double y = Double.parseDouble(st.nextToken());
      for (double[] pos : l) {
        double cost = Math
            .sqrt(Math.abs(x - pos[0]) * Math.abs(x - pos[0]) + Math.abs(y - pos[1]) * Math.abs(y - pos[1]));
        graph.add(new Edge(i, (int) pos[2], cost));
      }
      l.add(new double[] { x, y, i });
    }

    graph.sort((o1, o2) -> Double.compare(o1.cost, o2.cost));
    parent = new int[n + 1];
    for (int i = 1; i < n + 1; i++) {
      parent[i] = i;
    }
    kruskal();

    System.out.println(String.format("%.2f", mst));
    br.close();
  }
}
