import java.io.*;
import java.util.*;

public class Main_2887 {
  static int N;
  static List<int[]> planets = new ArrayList<>();
  static List<Edge> graph = new ArrayList<>();
  // parent[i] : i번 노드의 부모 노드
  // rank[i] : i번 노드를 루트로 하는 트리의 상대적인 랭크 값
  // 두 노드의 랭크 값을 비교해 랭크가 더 낮은 트리가 랭크가 큰 트리 밑으로 합쳐지게 함
  static int[] parent, rank;
  static long mst = 0;

  static class Edge {
    int from, to;
    long cost;

    public Edge(int from, int to, long cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
  }

  // x번 노드의 루트 노드를 찾음
  static int find(int x) {
    if (parent[x] == x) {
      return x;
    }
    return parent[x] = find(parent[x]);
  }

  // a번 노드와 b 노드를 합침
  static boolean union(int a, int b) {
    int rootA = find(a);
    int rootB = find(b);
    // 두 노드의 루트 노드가 같으면 이미 같은 트리에 속해있음
    if (rootA == rootB) {
      return false;
    }

    // 두 노드의 랭크 값을 비교해 더 작은 랭크를 가진 트리를 더 큰 랭크의 트리 밑에 합침
    if (rank[rootA] > rank[rootB]) {
      parent[rootB] = rootA;
    } else if (rank[rootA] < rank[rootB]) {
      parent[rootA] = rootB;
    }
    // 랭크가 같으면, 임의로 한쪽을 루트로 만들고 그 트리의 랭크를 1 증가시킴
    else {
      parent[rootB] = rootA;
      rank[rootA]++;
    }

    return true;
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./2887.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());

    parent = new int[N];
    rank = new int[N];
    for (int i = 0; i < N; i++) {
      parent[i] = i;
    }

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int z = Integer.parseInt(st.nextToken());
      planets.add(new int[] { x, y, z, i });
    }

    // 한 노드에서 다른 노드까지의 모든 거리를 간선으로 저장하면 메모리 초과(공간 복잡도 : O(N ^ 2))
    // 때문에 x축, y축, z축을 기준으로 정렬하여 인접한 노드간의 거리만 저장(공간 복잡도 : O(3N))
    // 한 노드에서 x축을 기준으로 가장 가까운 노드, y축을 기준으로 가장 가까운 노드, z축을 기준으로 가장 가까운 노드만 저장
    for (int d = 0; d < 3; d++) {
      final int dim = d;
      planets.sort((o1, o2) -> Integer.compare(o1[dim], o2[dim]));
      for (int i = 1; i < N; i++) {
        int[] p1 = planets.get(i - 1);
        int[] p2 = planets.get(i);
        graph.add(new Edge(p1[3], p2[3], Math.abs(p1[dim] - p2[dim])));
      }
    }

    // 저장된 간선을 비용을 오름차순으로 정렬
    graph.sort((o1, o2) -> Long.compare(o1.cost, o2.cost));

    // 크루스칼 알고리즘 적용
    for (Edge e : graph) {
      // 두 노드를 합칠 수 있으면 mst에 비용 추가
      if (union(e.from, e.to)) {
        mst += e.cost;
      }
    }

    System.out.println(mst);
    br.close();
  }
}
