import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  static int N, M, start, end;
  static StringTokenizer st;
  static List<Edge> edges = new ArrayList<>();
  static int[] dist, path;
  static PriorityQueue<Edge> pq = new PriorityQueue<>();
  static Stack<Integer> s = new Stack<>();

  static class Edge implements Comparable<Edge> {
    int from, to, cost;

    public Edge(int from, int to, int cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
      return Integer.compare(this.cost, o.cost);
    }
  }

  static void dijkstra() {
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[start] = 0;

    for (Edge edge : edges) {
      if (edge.from == start) {
        pq.add(edge);
      }
    }

    while (!pq.isEmpty()) {
      Edge edge = pq.poll();
      if (dist[edge.to] > dist[edge.from] + edge.cost) {
        path[edge.to] = edge.from;
        dist[edge.to] = dist[edge.from] + edge.cost;
        for (Edge nextEdge : edges) {
          if (nextEdge.from == edge.to && dist[nextEdge.to] > dist[nextEdge.from] + nextEdge.cost) {
            pq.add(nextEdge);
          }
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./11779.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    M = Integer.parseInt(st.nextToken());

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      Edge edge = new Edge(from, to, cost);
      edges.add(edge);
    }

    st = new StringTokenizer(br.readLine());
    start = Integer.parseInt(st.nextToken());
    end = Integer.parseInt(st.nextToken());

    dist = new int[N + 1];
    path = new int[N + 1];

    dijkstra();
    System.out.println(dist[end]);
    int num = end;
    while (num != 0) {
      s.push(num);
      num = path[num];
    }
    System.out.println(s.size());
    while (!s.isEmpty()) {
      System.out.print(s.pop() + " ");
    }

    br.close();
  }
}
