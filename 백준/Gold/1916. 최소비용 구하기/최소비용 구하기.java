import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
  public static int n, m, start, end;
  public static List<List<int[]>> graph = new ArrayList<>();
  public static int[] ans;

  public static class Node implements Comparable<Node> {
    int idx, cost;

    public Node(int idx, int cost) {
      this.idx = idx;
      this.cost = cost;
    }

    // Override : 상위 클래스나 인터페이스에서 정의된 메서드를 하위 클래스에서 재정의하는 것
    // 즉 기존에 있던 compareTo가 아닌 사용자가 새롭게 정의한 compareTo 사용
    // 여기서는 비용을 기준으로 오름차순 정렬
    @Override
    public int compareTo(Node o) {
      // compareTo 메서드 반환값
      // 음수(-) : 현재 객체(this)가 비교 대상 객체(o)보다 작음
      // 0 : 현재 객체(this)와 비교 대상 객체(o)가 같음
      // 양수(+) : 현재 객체(this)가 비교 대상 객체(o)보다 큼
      return this.cost - o.cost;
    }
  }

  // 현재 문제는 그래프가 가중치를 가지고 있기 때문에 bfs보다는 다익스트라 알고리즘 사용
  // 다익스트라 : 가중치가 있는 그래프에서 주어진 출발점에서 다른 모든 노드까지의 최단 경로를 찾는 알고리즘, 이때 음의 가중치는 없어야 함
  // 동작 과정
  // 1. 출발 노드와 도착 노드 설정
  // 2. '최단 거리 테이블' 초기화
  // 3. 현재 위치한 노드의 인접 노드 중 방문하지 않은 노드를 구별하고, 방문하지 않은 노드 중 거리가 가장 짧은 노드 선택 & 그 노드를 방문 처리
  // 4. 해당 노드를 거쳐 다른 노드로 넘어가는 간선 비용(가중치)을 계산해 '최단 거리 테이블' 업데이트
  public static void dijkstra (int start) {
    // 우선순위 큐를 사용해 거리(비용)가 가장 작은 노드를 먼저 꺼낼 수 있도록 함
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.add(new Node(start, 0));
    ans[start] = 0;

    while (!pq.isEmpty()) {
      Node current = pq.poll();
      int currentIdx = current.idx;
      int currentCost = current.cost;

      // currentIdx의 노드를 이미 방문한 경우 continue
      if (currentCost > ans[currentIdx]) {
        continue;
      }

      for (int[] edge : graph.get(currentIdx)) {
        int next = edge[1];
        int cost = edge[2];

        // 현재 노드를 거쳐서 갔을 때 최단 거리일 경우
        if (ans[currentIdx] + cost < ans[next]) {
          // ans 배열의 Integer.MAX_VAULE로 초기화되어 있기 때문에 값이 바뀌면 해당 노드는 방문했다는 것을 알 수 있음
          // 이러면 visited 배열을 사용할 필요가 없음
          ans[next] = ans[currentIdx] + cost;
          pq.add(new Node(next, ans[next]));
        }
      }
    }
  }
  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/1916/1916.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    m = Integer.parseInt(br.readLine());

    for (int i = 0; i < n + 1; i++) {
      graph.add(new ArrayList<>());
    }

    ans = new int[n + 1];
    Arrays.fill(ans, Integer.MAX_VALUE);

    for (int i = 0; i < m; i++) {
      String[] input = br.readLine().split(" ");
      int start = Integer.parseInt(input[0]);
      int end = Integer.parseInt(input[1]);
      int cost = Integer.parseInt(input[2]);
      graph.get(start).add(new int[]{start, end, cost});
    }

    String[] info = br.readLine().split(" ");
    start = Integer.parseInt(info[0]);
    end = Integer.parseInt(info[1]);

    dijkstra(start);
    System.out.println(ans[end]);

    br.close();
  }  
}
