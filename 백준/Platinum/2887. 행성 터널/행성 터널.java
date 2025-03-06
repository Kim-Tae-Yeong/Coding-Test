import java.io.*;
import java.util.*;

public class Main {
  static int N;
  static List<int[]> planets = new ArrayList<>();
  static List<Edge> graph = new ArrayList<>();
  static int[] parent, rank;
  static long mst = 0;

  static class Edge {
    int from, to;
    long cost;

    public Edge(int from, int to, long cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
  }

  static int find(int x) {
    if (parent[x] == x) {
      return x;
    }
    return parent[x] = find(parent[x]);
  }

  static boolean union(int a, int b) {
    int rootA = find(a);
    int rootB = find(b);
    if (rootA == rootB) {
      return false;
    }

    if (rank[rootA] > rank[rootB]) {
      parent[rootB] = rootA;
    } else if (rank[rootA] < rank[rootB]) {
      parent[rootA] = rootB;
    } else {
      parent[rootB] = rootA;
      rank[rootA]++;
    }

    return true;
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./2887.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());

    parent = new int[N];
    rank = new int[N];
    for (int i = 0; i < N; i++) {
      parent[i] = i;
    }

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int z = Integer.parseInt(st.nextToken());
      planets.add(new int[] { x, y, z, i });
    }

    for (int d = 0; d < 3; d++) {
      final int dim = d;
      planets.sort((o1, o2) -> Integer.compare(o1[dim], o2[dim]));
      for (int i = 1; i < N; i++) {
        int[] p1 = planets.get(i - 1);
        int[] p2 = planets.get(i);
        graph.add(new Edge(p1[3], p2[3], Math.abs(p1[dim] - p2[dim])));
      }
    }

    graph.sort((o1, o2) -> Long.compare(o1.cost, o2.cost));

    for (Edge e : graph) {
      if (union(e.from, e.to)) {
        mst += e.cost;
      }
    }

    System.out.println(mst);
    br.close();
  }
}
