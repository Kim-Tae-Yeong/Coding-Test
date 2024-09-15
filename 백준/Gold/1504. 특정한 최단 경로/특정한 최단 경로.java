import java.io.BufferedReader;
// import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main_1504 {
  public static int n, e, ans;
  public static List<List<List<Integer>>> graph = new ArrayList<>();
  public static int[] visited;

  public static void dijkstra (int start) {
    // 출발 node에서 도착 node까지의 거리가 짧은 순으로 우선순위 큐 정렬
    PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
    // 큐에 {목적지, 시작 -> 목적지까지의 거리} 저장
    q.add(new int[] {start, 0});
    // visited : 목적지까지의 최소 거리 저장
    visited[start] = 0;
    while (!q.isEmpty()) {
      int[] pos = q.poll();
      int currentIdx = pos[0];
      int currentCost = pos[1];

      // 현재 큐에 들어있는 거리가 최소 거리보다 크면 해당 경로를 통해 이동하는 것은 의미가 없음
      // 큐는 노드간의 거리를 기준으로 졍렬되기 때문에 해당 코드를 사용하면 좀 더 빠르게 탐색 가능
      // ex.
      // 1 -> 2의 거리가 5일 때, (2, 5) 저장 -> visited[2] = 5 -> 현재 큐 : (2, 5)
      // 1 -> 3의 거리가 2일 때, (3, 2) 저장 -> visited[3] = 2 -> 현재 큐 : (3, 2), (2, 5) -> 노드간의 거리가 더 짧기 때문에 먼저 탐색
      // 3 -> 2의 거리가 2일 때, (2, 2 + 2) = (2, 4) 저장 -> visited[2] = 4 -> 현재 큐 : (2, 4), (2, 5) -> 노드간의 거리가 더 짧기 때문에 먼저 탐색
      // 이후 (2, 5)를 탐색할 때 이미 visited[2]는 4로 현재 경로보다 짧기 때문에 현재 경로는 탐색할 필요 X
      if (currentCost > visited[currentIdx]) {
        continue;
      }

      // 현재 위치를 기준으로 갈 수 있는 node 탐색
      for (List<Integer> elem : graph.get(currentIdx)) {
        int next = elem.get(1);
        int cost = elem.get(2);
        // 현재까지 저장된 최소 거리보다 현재 위치를 통해 이동하는 것이 더 짧은 경우
        if (visited[currentIdx] + cost < visited[next]) {
          // 최소 거리 갱신
          visited[next] = visited[currentIdx] + cost;
          // 큐에 목적지와 최소 거리 저장
          q.add(new int[] {next, visited[next]});
        }
      }
    }
  }
  public static void main (String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/1504/1504.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    n = Integer.parseInt(num[0]);
    e = Integer.parseInt(num[1]);

    for (int i = 0; i < n + 1; i++) {
      graph.add(new ArrayList<>());
    }

    // graph 저장
    List<Integer> tmp;
    for (int i = 0; i < e; i++) {
      String[] input = br.readLine().split(" ");
      int start = Integer.parseInt(input[0]);
      int end = Integer.parseInt(input[1]);
      int cost = Integer.parseInt(input[2]);

      tmp = new ArrayList<>();
      tmp.add(start);
      tmp.add(end);
      tmp.add(cost);
      graph.get(start).add(tmp);

      tmp = new ArrayList<>();
      tmp.add(end);
      tmp.add(start);
      tmp.add(cost);
      graph.get(end).add(tmp);
    }

    String[] info = br.readLine().split(" ");
    int v1 = Integer.parseInt(info[0]);
    int v2 = Integer.parseInt(info[1]);

    // 아이디어
    // v1과 v2를 반드시 거쳐야 하기 때문에 가능한 경우는 2가지밖에 존재하지 않음
    // 1. 1 -> v1 -> v2 -> n
    // 2. 1 -> v2 -> v1 -> n
    // 다익스트라를 사용해서 a -> b까지의 최소 거리를 구하면 이는 b -> a까지의 최소 거리와 동일함
    // 이를 이용하면 다익스트라 함수를 여러번 호출할 필요 X

    // v1을 기준으로 다익스트라 실행
    visited = new int[n + 1];
    Arrays.fill(visited, Integer.MAX_VALUE);
    dijkstra(v1);
    int start_to_v1 = visited[1];
    int v1_to_v2 = visited[v2];
    int v1_to_end = visited[n];
    
    // v2를 기준으로 다익스트라 실행
    visited = new int[n + 1];
    Arrays.fill(visited, Integer.MAX_VALUE);
    dijkstra(v2);
    int start_to_v2 = visited[1];
    int v2_to_end = visited[n];

    // v1과 v2를 반드시 거쳐야 하는데 두 node 사이에 경로가 없으면 -1 출력
    if (v1_to_v2 == Integer.MAX_VALUE) {
      System.out.println(-1);
    }
    // v1과 v2 사이의 길이 존재할 때
    else {
      int distance1;
      // 1 -> v1 or v2 -> n 중 경로가 없는 곳이 존재하면 1 -> v1 -> v2 -> n의 경로를 MAX_VALUE로 설정
      if (start_to_v1 == Integer.MAX_VALUE || v2_to_end == Integer.MAX_VALUE) {
        distance1 = Integer.MAX_VALUE;
      }
      // 그게 아니면 각각의 경로를 다 더함
      else {
        distance1 = start_to_v1 + v1_to_v2 + v2_to_end;
      }

      int distance2;
      // 1 -> v2 or v1 -> n 중 경로가 없는 곳이 존재하면 1 -> v2 -> v1 -> n의 경로를 MAX_VALUE로 설정
      if (start_to_v2 == Integer.MAX_VALUE || v1_to_end == Integer.MAX_VALUE) {
        distance2 = Integer.MAX_VALUE;
      }
      // 그게 아니면 각각의 경로를 다 더함
      else {
        distance2 = start_to_v2 + v1_to_v2 + v1_to_end;
      }

      // 1 -> v1 -> v2 -> n & 1 -> v2 -> v1 -> n 중 더 작은 경로를 선택
      ans = Math.min(distance1, distance2);

      // 만약 더 작은 경로가 MAX_VALUE이면( = 두 경우 모두 경로가 존재하지 않으면) -1 출력
      if (ans == Integer.MAX_VALUE) {
        System.out.println(-1);
      }
      // 아니면 더 작은 경로 출력
      else {
        System.out.println(ans);
      }
    }

    br.close();
  }
}
