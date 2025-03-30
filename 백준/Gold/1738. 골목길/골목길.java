import java.io.*;
import java.util.*;

public class Main {
  static int n, m;
  static int[] path;
  static List<Edge> l = new ArrayList<>();
  static List<Integer> cycle = new ArrayList<>();

  static class Edge {
    int from, to, cost;

    public Edge(int from, int to, int cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
  }

  static boolean hasPositiveCycle() {
    int[] dist = new int[n + 1];
    Arrays.fill(dist, Integer.MIN_VALUE);
    dist[1] = 0;

    path = new int[n + 1];

    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < m; j++) {
        Edge e = l.get(j);
        if (dist[e.from] != Integer.MIN_VALUE && dist[e.to] < dist[e.from] + e.cost) {
          dist[e.to] = dist[e.from] + e.cost;
          path[e.to] = e.from;
        }
      }
    }

    boolean hasCycle = false;
    for (int i = 0; i < m; i++) {
      Edge e = l.get(i);
      if (dist[e.from] != Integer.MIN_VALUE && dist[e.to] < dist[e.from] + e.cost) {
        cycle.add(e.to);
        hasCycle = true;
      }
    }

    return (hasCycle ? true : false);
  }

  static boolean isInCycle() {
    boolean[] visited = new boolean[n + 1];

    for (Integer elem : cycle) {
      if (!visited[elem]) {
        visited[elem] = true;
        for (Edge e : l) {
          if (elem == e.from) {
            visited[e.to] = true;
          }
        }
      }
    }

    return (visited[1] || visited[n] ? true : false);
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1738.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      l.add(new Edge(u, v, w));
    }

    if (hasPositiveCycle() && isInCycle()) {
      System.out.println(-1);
    } else {
      Stack<Integer> s = new Stack<>();
      int current = n;
      while (current != 0) {
        s.add(current);
        current = path[current];
      }
      while (!s.isEmpty()) {
        System.out.print(s.pop() + " ");
      }
    }
    br.close();
  }
}