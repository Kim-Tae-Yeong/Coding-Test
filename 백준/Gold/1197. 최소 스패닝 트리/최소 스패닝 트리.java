import java.io.*;
import java.util.*;

public class Main_1197 {
  // cnt : mst의 간선 개수
  static int V, E, cnt, mst;
  // 간선의 가중치를 오름차순 정렬
  static PriorityQueue<Edge> graph = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
  // parent[i] : i번째 노드의 부모 노드(i번째 노드와 연결된 노드 중 번호가 가장 작은 노드)
  static int[] parent;

  static class Edge {
    int from, to, cost;

    public Edge(int from, int to, int cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
  }

  static void kruskal() {
    while (true) {
      // 간선의 개수가 (V - 1)이면 break
      if (cnt == V - 1 || graph.isEmpty()) {
        break;
      }
      Edge e = graph.poll();
      // 현재 간선의 시작 노드와 도착 노드의 부모가 같을 때 해당 간선을 선택하면 싸이클 발생하기 때문에 continue
      if (parent[e.from] == parent[e.to]) {
        continue;
      }
      // 도착 노드의 부모 노드 번호가 더 클 떄
      else if (parent[e.from] < parent[e.to]) {
        // 도착 노드와 연결된 노드들(도착 노드와 같은 부모 노드를 갖는 노드들)의 부모 노드를 시작 노드의 부모 노드로 바꿔줌
        int beforeParent = parent[e.to];
        for (int i = 1; i < V + 1; i++) {
          if (parent[i] == beforeParent) {
            parent[i] = parent[e.from];
          }
        }
      }
      // 시작 노드의 부모 노드 번호가 더 클 떄
      else {
        // 시작 노드와 연결된 노드들(시작 노드와 같은 부모 노드를 갖는 노드들)의 부모 노드를 도착 노드의 부모 노드로 바꿔줌
        int beforeParent = parent[e.from];
        for (int i = 1; i < V + 1; i++) {
          if (parent[i] == beforeParent) {
            parent[i] = parent[e.to];
          }
        }
      }
      mst += e.cost;
      cnt++;
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1197.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    V = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());

    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());
      int A = Integer.parseInt(st.nextToken());
      int B = Integer.parseInt(st.nextToken());
      int C = Integer.parseInt(st.nextToken());
      graph.add(new Edge(A, B, C));
    }

    cnt = 0;
    mst = 0;
    parent = new int[V + 1];
    // 초기 각 노드들의 부모 노드는 자기 자신임
    for (int i = 1; i < V + 1; i++) {
      parent[i] = i;
    }
    kruskal();
    System.out.println(mst);

    br.close();
  }
}
