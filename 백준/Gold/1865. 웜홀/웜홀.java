import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {
  private static int TC, N, M, W;
  private static List<Edge> edges;
  private static int[] time;
  private static StringBuilder sb = new StringBuilder();

  private static class Edge {
    int from, to, weight;

    Edge(int from, int to, int weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }
  }

  private static boolean bellmanFord() {
    Arrays.fill(time, Integer.MAX_VALUE);
    time[0] = 0;
    for (int i = 1; i < N; i++) {
      for (Edge edge : edges) {
        if (time[edge.from] != Integer.MAX_VALUE && time[edge.from] + edge.weight < time[edge.to]) {
          time[edge.to] = time[edge.from] + edge.weight;
        }
      }
    }
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
      time = new int[N + 1];
      for (int j = 1; j < N + 1; j++) {
        edges.add(new Edge(0, j, 0));
      }
      for (int j = 0; j < M; j++) {
        String[] input = br.readLine().split(" ");
        int S = Integer.parseInt(input[0]);
        int E = Integer.parseInt(input[1]);
        int T = Integer.parseInt(input[2]);
        edges.add(new Edge(S, E, T));
        edges.add(new Edge(E, S, T));
      }
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
