import java.io.*;
import java.util.*;

public class Main_4386 {
  static int n;
  static List<double[]> l = new ArrayList<>();
  static List<Edge> graph = new ArrayList<>();
  static int[] parent;
  static double mst = 0;

  static class Edge {
    int from, to;
    double cost;

    public Edge(int from, int to, double cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
  }

  static void kruskal() {
    for (Edge e : graph) {
      // 두 노드의 부모 노드가 같으면 continue
      if (parent[e.from] == parent[e.to]) {
        continue;
      }
      // 시작 노드의 부모 노드가 도착 노드의 부모 노드보다 작으면
      else if (parent[e.from] < parent[e.to]) {
        mst += e.cost;
        // 도착 노드와 연결된 모든 노드의 부모 노드를 시작 노드의 부모 노드로 바꿈
        int beforeParent = parent[e.to];
        for (int i = 1; i < n + 1; i++) {
          if (parent[i] == beforeParent) {
            parent[i] = parent[e.from];
          }
        }
      }
      // 도착 노드의 부모 노드가 시작 노드의 부모 노드보다 작으면
      else {
        mst += e.cost;
        // 시작 노드와 연결된 모든 노드의 부모 노드를 도착 노드의 부모 노드로 바꿈
        int beforeParent = parent[e.from];
        for (int i = 1; i < n + 1; i++) {
          if (parent[i] == beforeParent) {
            parent[i] = parent[e.to];
          }
        }
      }
    }

  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./4386.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      // 현재 좌표
      double x = Double.parseDouble(st.nextToken());
      double y = Double.parseDouble(st.nextToken());
      // 이전에 들어온 좌표들에 대해
      for (double[] pos : l) {
        // 두 좌표간의 거리를 구해 배열에 저장
        double cost = Math
            .sqrt(Math.abs(x - pos[0]) * Math.abs(x - pos[0]) + Math.abs(y - pos[1]) * Math.abs(y - pos[1]));
        graph.add(new Edge(i, (int) pos[2], cost));
      }
      // 현재 좌표를 배열에 저장
      l.add(new double[] { x, y, i });
    }

    // 비용을 오름차순으로 정렬
    graph.sort((o1, o2) -> Double.compare(o1.cost, o2.cost));
    // parent[i] : i번 노드의 부모 노드(i번 노드와 연결된 노드 중 번호가 가장 작은 노드)
    parent = new int[n + 1];
    // 초기에는 부모 노드가 자기 자신이 됨
    for (int i = 1; i < n + 1; i++) {
      parent[i] = i;
    }
    kruskal();

    System.out.println(String.format("%.2f", mst));
    br.close();
  }
}
