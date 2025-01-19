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
  static int n, m, start, end;
  static List<List<Edge>> graph = new ArrayList<>();
  static int[] dist, path;

  static class Edge {
    int to, cost;

    public Edge(int to, int cost) {
      this.to = to;
      this.cost = cost;
    }
  }

  static void dijkstra(int start) {
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[start] = 0;
    PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
    pq.add(new Edge(start, 0));
    while (!pq.isEmpty()) {
      Edge edge = pq.poll();
      int current = edge.to;
      int currentCost = edge.cost;
      if (dist[current] < currentCost) {
        continue;
      }
      for (Edge e : graph.get(current)) {
        int newCost = currentCost + e.cost;
        if (newCost < dist[e.to]) {
          pq.add(new Edge(e.to, newCost));
          dist[e.to] = newCost;
          path[e.to] = current;
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./11779.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    m = Integer.parseInt(st.nextToken());

    dist = new int[n + 1];
    path = new int[n + 1];

    for (int i = 0; i < n + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      graph.get(from).add(new Edge(to, cost));
    }

    st = new StringTokenizer(br.readLine());
    start = Integer.parseInt(st.nextToken());
    end = Integer.parseInt(st.nextToken());

    dijkstra(start);
    System.out.println(dist[end]);
    int num = end;
    Stack<Integer> s = new Stack<>();
    while (num != 0) {
      s.add(num);
      num = path[num];
    }

    System.out.println(s.size());
    while (!s.empty()) {
      System.out.print(s.pop());
      System.out.print(' ');
    }

    br.close();
  }
}