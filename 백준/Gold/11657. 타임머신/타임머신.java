import java.io.*;
import java.util.*;

public class Main_11657 {
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
    // 음의 사이클 존재 여부
    boolean negativeCycle = false;
    // 초기 각 노드까지의 거리는 무한대로 설정
    Arrays.fill(distance, Long.MAX_VALUE);
    // 시작 노드까지의 거리는 0으로 설정
    distance[1] = 0;
    // (정점 개수 - 1)번 반복
    for (int i = 0; i < N - 1; i++) {
      // 각 반복마다 모든 간선 확인
      for (Edge e : graph) {
        // 시작 노드까지의 거리가 무한대가 아니고 도착 노드까지의 거리가 이전에 저장된 거리보다 더 짧다면
        if (distance[e.from] != Long.MAX_VALUE && distance[e.to] > distance[e.from] + e.cost) {
          // 도착 노드까지의 최단 거리 갱신
          distance[e.to] = distance[e.from] + e.cost;
        }
      }
    }

    // (정점 개수 - 1)번 반복 후 한 번 더 반복문 진행
    for (Edge e : graph) {
      // 이 과정에서 최단 거리가 또 다시 갱신되면 음의 사이클 존재
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

    // 음의 사이클이 존재하지 않으면 각 노드까지의 최단 거리 출력
    if (!hasNegativeCycle()) {
      for (int i = 2; i < N + 1; i++) {
        if (distance[i] == Long.MAX_VALUE) {
          System.out.println(-1);
        } else {
          System.out.println(distance[i]);
        }
      }
    }
    // 음의 사이클 존재시 -1 출력
    else {
      System.out.println(-1);
    }
    br.close();
  }
}
