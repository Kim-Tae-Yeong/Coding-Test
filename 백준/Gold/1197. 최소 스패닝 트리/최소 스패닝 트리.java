import java.io.*;
import java.util.*;

public class Main {
  static int V, E, cnt, mst;
  static PriorityQueue<Edge> graph = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
  static int[] parent;

  static class Edge {
    int from, to, cost;

    public Edge(int from, int to, int cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
  }

  static void kruskal() {
    while (true) {
      if (cnt == V - 1 || graph.isEmpty()) {
        break;
      }
      Edge e = graph.poll();
      if (parent[e.from] == parent[e.to]) {
        continue;
      } else if (parent[e.from] < parent[e.to]) {
        int beforeParent = parent[e.to];
        for (int i = 1; i < V + 1; i++) {
          if (parent[i] == beforeParent) {
            parent[i] = parent[e.from];
          }
        }
      } else {
        int beforeParent = parent[e.from];
        for (int i = 1; i < V + 1; i++) {
          if (parent[i] == beforeParent) {
            parent[i] = parent[e.to];
          }
        }
      }
      mst += e.cost;
      cnt++;
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1197.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    V = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());

    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());
      int A = Integer.parseInt(st.nextToken());
      int B = Integer.parseInt(st.nextToken());
      int C = Integer.parseInt(st.nextToken());
      graph.add(new Edge(A, B, C));
    }

    cnt = 0;
    mst = 0;
    parent = new int[V + 1];
    for (int i = 1; i < V + 1; i++) {
      parent[i] = i;
    }
    kruskal();
    System.out.println(mst);

    br.close();
  }
}
