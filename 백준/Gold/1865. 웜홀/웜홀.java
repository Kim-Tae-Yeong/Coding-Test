import java.io.*;
import java.util.*;

public class Main_1865 {
  static int TC, N, M, W;
  static List<Edge> graph;
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
    // 초기 거리 배열 무한대로 설정
    Arrays.fill(distance, Long.MAX_VALUE);
    // 시작 노드가 정해지지 않았기 때문에 가상 노드(0번 노드)를 시작 노드로 설정
    // 1 ~ N까지 반복문을 돌면서 확인할수도 있지만 시간 초과 발생
    distance[0] = 0;
    // 0번 노드에서 1 ~ N번 노드까지 비용이 0인 간선 추가
    for (int i = 1; i < N + 1; i++) {
      graph.add(new Edge(0, i, 0));
    }
    // 가상 노드인 0번 노드가 추가되었기 때문에 총 노드 개수는 (N + 1)이 됨
    // 때문에 반복문을 N번 돌아야함
    for (int i = 0; i < N; i++) {
      for (Edge e : graph) {
        if (distance[e.from] != Long.MAX_VALUE && distance[e.to] > distance[e.from] + e.cost) {
          distance[e.to] = distance[e.from] + e.cost;
        }
      }
    }

    // 이후 한 번 더 반복문을 진행하면서 음의 사이클이 존재하는지 확인
    for (Edge e : graph) {
      if (distance[e.from] != Long.MAX_VALUE && distance[e.to] > distance[e.from] + e.cost) {
        return true;
      }
    }

    return false;
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1865.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    TC = Integer.parseInt(st.nextToken());

    for (int i = 0; i < TC; i++) {
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      W = Integer.parseInt(st.nextToken());

      // 테스트케이스마다 그래프와 거리 배열 초기화
      graph = new ArrayList<>();
      distance = new long[N + 1];

      // 거리(양방향)
      for (int j = 0; j < M; j++) {
        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());
        graph.add(new Edge(from, to, cost));
        graph.add(new Edge(to, from, cost));
      }

      // 웜홀(단방향)
      for (int j = 0; j < W; j++) {
        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());
        graph.add(new Edge(from, to, -cost));
      }

      if (!hasNegativeCycle()) {
        System.out.println("NO");
      } else {
        System.out.println("YES");
      }
    }
    br.close();
  }
}
