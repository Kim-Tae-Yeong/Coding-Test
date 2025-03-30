import java.io.*;
import java.util.*;

public class Main_1738 {
  static int n, m;
  // path[i] : i번째 노드로 들어오는 노드 번호
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
    // 초기 배열은 가장 작은 값으로 설정
    int[] dist = new int[n + 1];
    Arrays.fill(dist, Integer.MIN_VALUE);
    // 시작 지점만 0으로 설정
    dist[1] = 0;

    path = new int[n + 1];

    // 프림 알고리즘 진행
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < m; j++) {
        Edge e = l.get(j);
        if (dist[e.from] != Integer.MIN_VALUE && dist[e.to] < dist[e.from] + e.cost) {
          dist[e.to] = dist[e.from] + e.cost;
          path[e.to] = e.from;
        }
      }
    }

    // 양의 싸이클 여부 확인
    boolean hasCycle = false;
    for (int i = 0; i < m; i++) {
      Edge e = l.get(i);
      // 양의 싸이클이 존재하면 cycle 배열에 집어넣음
      if (dist[e.from] != Integer.MIN_VALUE && dist[e.to] < dist[e.from] + e.cost) {
        cycle.add(e.to);
        hasCycle = true;
      }
    }

    // 양의 싸이클 여부에 따라 true / false 반환
    return (hasCycle ? true : false);
  }

  static boolean isInCycle() {
    boolean[] visited = new boolean[n + 1];

    // cycle에 포함된 노드로부터 이동할 수 있는 노드 확인
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

    // 시작 노드와 도착 노드 중 하나라도 포함되면 true 반환
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

    // 양의 싸이클이 존재하고, 시작 지점이나 도착 지점이 해당 사이클에 포함되면 무한대 값을 만들 수 있기 때문에 -1 출력
    if (hasPositiveCycle() && isInCycle()) {
      System.out.println(-1);
    }
    // 그게 아니라면 경로 출력
    else {
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
