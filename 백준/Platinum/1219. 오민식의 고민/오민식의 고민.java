import java.io.*;
import java.util.*;

public class Main {
  static int N, start, end, M;
  static List<Edge> graph = new ArrayList<>();
  static int[] city;
  static long[] money;

  static class Edge {
    int from, to;
    long cost;

    public Edge(int from, int to, long cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
  }

  static void bellmanFord() {
    money[start] = city[start];
    for (int i = 0; i < N - 1; i++) {
      for (Edge e : graph) {
        if (money[e.from] != Integer.MIN_VALUE && money[e.to] < money[e.from] + e.cost + city[e.to]) {
          money[e.to] = money[e.from] + e.cost + city[e.to];
        }
      }
    }
  }

  static boolean canReach() {
    return money[end] != Integer.MIN_VALUE ? true : false;
  }

  static boolean hasPositiveCycle() {
    Queue<Integer> q = new LinkedList<>();
    boolean[] cycle = new boolean[N];
    for (Edge e : graph) {
      if (money[e.from] != Integer.MIN_VALUE && money[e.to] < money[e.from] + e.cost + city[e.to] && !cycle[e.to]) {
        cycle[e.to] = true;
        q.offer(e.to);
      }
    }

    while (!q.isEmpty()) {
      int current = q.poll();
      if (!cycle[current]) {
        cycle[current] = true;
      }
      for (Edge e : graph) {
        if (e.from == current && !cycle[e.to]) {
          q.offer(e.to);
        }
      }
    }

    for (int i = 0; i < N; i++) {
      if (cycle[i] && money[i] != Integer.MIN_VALUE && cycle[end]) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1219.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    start = Integer.parseInt(st.nextToken());
    end = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      long w = Long.parseLong(st.nextToken());
      graph.add(new Edge(u, v, -w));
    }

    city = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      city[i] = Integer.parseInt(st.nextToken());
    }

    money = new long[N];
    Arrays.fill(money, Integer.MIN_VALUE);
    bellmanFord();

    if (!canReach()) {
      System.out.println("gg");
    } else {
      if (hasPositiveCycle()) {
        System.out.println("Gee");
      } else {
        System.out.println(money[end]);
      }
    }

    br.close();
  }
}
