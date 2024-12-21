import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main_1865 {
  private static int TC, N, M, W;
  private static List<Edge> edges;
  private static int[] time;
  private static StringBuilder sb = new StringBuilder();

  // List<List<int[]>>로 받으면 메모리 초과 -> Edge class를 만들어서 저장
  private static class Edge {
    int from, to, weight;

    Edge(int from, int to, int weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }
  }

  // 벨만포드 알고리즘 이용해서 음의 사이클 찾기
  private static boolean bellmanFord() {
    // 초기에 각 노드까지 거리(시간)는 무한대로 설정
    Arrays.fill(time, Integer.MAX_VALUE);
    // 시작 노드는 0으로 설정(가상 노드)
    time[0] = 0;

    // 벨만 포드는 총 (N - 1)번 실행
    // 이는 한 노드에서 다른 노드까지 도달하는데 최대 (N - 1)개 간선만 있으면 되기 때문
    // 즉 1번만에 갈 수 있는 노드, 2번만에 갈 수 있는 노드, ... 이렇게 탐색하는 방식
    for (int i = 1; i < N; i++) {
      // 각 실행마다 간선 탐색
      for (Edge edge : edges) {
        // from 노드에 갈 수 있고, from 노드에서 weight 만큼 이동했을 때 기존 to 노드에 저장된 거리보다 짧으면 거리 갱신
        if (time[edge.from] != Integer.MAX_VALUE && time[edge.from] + edge.weight < time[edge.to]) {
          time[edge.to] = time[edge.from] + edge.weight;
        }
      }
    }

    // 이후 모든 간선을 다시 한번 탐색했을 때 더 짧아지는 노드가 있다면 음의 사이클이 있다는 의미
    for (Edge edge : edges) {
      if (time[edge.from] != Integer.MAX_VALUE && time[edge.from] + edge.weight < time[edge.to]) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1865.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    TC = Integer.parseInt(br.readLine());
    for (int i = 0; i < TC; i++) {
      String[] num = br.readLine().split(" ");
      N = Integer.parseInt(num[0]);
      M = Integer.parseInt(num[1]);
      W = Integer.parseInt(num[2]);
      edges = new ArrayList<>();

      // 각 노드를 시작노드로 설정해서 벨만포드 실행 -> 시간이 너무 오래걸림
      // 때문에 가상의 0번 노드를 만들고 이 노드에서 각 노드까지 비용이 0인 간선을 만듬
      // 이렇게 하면 벨만포드를 한번만 실행해도 됨
      time = new int[N + 1];
      for (int j = 1; j < N + 1; j++) {
        edges.add(new Edge(0, j, 0));
      }

      // 도로 정보 저장
      for (int j = 0; j < M; j++) {
        String[] input = br.readLine().split(" ");
        int S = Integer.parseInt(input[0]);
        int E = Integer.parseInt(input[1]);
        int T = Integer.parseInt(input[2]);
        edges.add(new Edge(S, E, T));
        edges.add(new Edge(E, S, T));
      }

      // 웜홀 정보 저장
      for (int j = 0; j < W; j++) {
        String[] input = br.readLine().split(" ");
        int S = Integer.parseInt(input[0]);
        int E = Integer.parseInt(input[1]);
        int T = Integer.parseInt(input[2]);
        edges.add(new Edge(S, E, -T));
      }

      if (bellmanFord()) {
        sb.append("YES\n");
      } else {
        sb.append("NO\n");
      }
    }
    System.out.println(sb.toString());
    br.close();
  }
}
