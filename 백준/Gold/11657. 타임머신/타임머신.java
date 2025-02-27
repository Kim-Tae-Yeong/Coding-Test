import java.io.*;
import java.util.*;

public class Main {
  static int N, M;
  static List<Edge> graph = new ArrayList<>();
  static long[] distance;

  static class Edge {
    int from, to;
    long cost;

    public Edge(int from, int to, long cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
  }

  static boolean hasNegativeCycle() {
    boolean negativeCycle = false;
    Arrays.fill(distance, Long.MAX_VALUE);
    distance[1] = 0;
    for (int i = 0; i < N - 1; i++) {
      for (Edge e : graph) {
        if (distance[e.from] != Long.MAX_VALUE && distance[e.to] > distance[e.from] + e.cost) {
          distance[e.to] = distance[e.from] + e.cost;
        }
      }
    }

    for (Edge e : graph) {
      if (distance[e.from] != Long.MAX_VALUE && distance[e.to] > distance[e.from] + e.cost) {
        negativeCycle = true;
        break;
      }
    }

    return negativeCycle;
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./11657.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    distance = new long[N + 1];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      long cost = Long.parseLong(st.nextToken());
      graph.add(new Edge(from, to, cost));
    }

    if (!hasNegativeCycle()) {
      for (int i = 2; i < N + 1; i++) {
        if (distance[i] == Long.MAX_VALUE) {
          System.out.println(-1);
        } else {
          System.out.println(distance[i]);
        }
      }
    } else {
      System.out.println(-1);
    }
    br.close();
  }
}
