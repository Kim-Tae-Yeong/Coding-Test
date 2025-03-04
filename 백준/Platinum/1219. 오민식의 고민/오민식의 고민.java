import java.io.*;
import java.util.*;

public class Main_1219 {
  static int N, start, end, M;
  static List<Edge> graph = new ArrayList<>();
  // city[i] : i번째 도시에 가면 벌 수 있는 돈
  static int[] city;
  // money[i] : 시작 도시에서 i번째 도시에 갔을 때 벌 수 있는 최대 돈
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
    // 큐에 양의 싸이클을 이루는 도시 번호 저장
    Queue<Integer> q = new LinkedList<>();
    boolean[] cycle = new boolean[N];
    for (Edge e : graph) {
      if (money[e.from] != Integer.MIN_VALUE && money[e.to] < money[e.from] + e.cost + city[e.to] && !cycle[e.to]) {
        cycle[e.to] = true;
        q.offer(e.to);
      }
    }

    // 양의 싸이클을 이루는 도시에서 bfs를 이용해 갈 수 있는 도시 확인
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

    // 각 도시 탐색
    for (int i = 0; i < N; i++) {
      // 현재 도시가 양의 싸이클에 포함됨 && 시작 도시에서 현재 도시까지 갈 수 있음 && 도착 도시도 양의 싸이클에 포함됨
      // 결국 시작 도시 -> 양의 싸이클 -> 도착 도시로 갈 수 있는지 확인
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
    // 벨만 - 포드 실행
    bellmanFord();

    // 도착 지점에 도달할 수 있는지 확인
    if (!canReach()) {
      System.out.println("gg");
    } else {
      // 양의 사이클이 있는지 확인
      if (hasPositiveCycle()) {
        System.out.println("Gee");
      } else {
        System.out.println(money[end]);
      }
    }

    br.close();
  }
}
