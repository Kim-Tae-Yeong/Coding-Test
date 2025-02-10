import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_1260 {
  static int N, M, V;
  // graph.get(i) : i번째 노드와 연결된 노드들을 오름차순 정렬
  static List<List<Integer>> graph = new ArrayList<>();
  // visited[i] : i번째 노드 방문 여부 확인
  static boolean[] visited;
  // DFS를 위한 스택
  static Stack<Integer> s = new Stack<>();
  // BFS를 위한 큐
  static Queue<Integer> q = new LinkedList<>();

  static void dfs(int current) {
    // 현재 노드 방문
    visited[current] = true;
    // 스택에 현재 노드 저장
    s.add(current);
    // 현재 노드에서 갈 수 있는 노드 중
    for (int next : graph.get(current)) {
      // 방문하지 않은 노드면
      if (!visited[next]) {
        // DFS 재귀적으로 실행
        dfs(next);
      }
    }
  }

  static void bfs(int start) {
    Arrays.fill(visited, false);
    // 시작 노드 방문
    visited[start] = true;
    // 큐에 시작 노드 저장
    q.add(start);

    while (!q.isEmpty()) {
      // 큐에서 노드를 뺌
      int current = q.poll();
      // 해당 노드 출력
      System.out.print(current + " ");
      // 큐에서 뺀 노드에서 갈 수 있는 노드 중
      for (int next : graph.get(current)) {
        // 방문하지 않은 노드이면
        if (!visited[next]) {
          // 방문 기록 갱신
          visited[next] = true;
          // 큐에 추가
          q.add(next);
        }
      }
    }
    System.out.println();
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1260.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    V = Integer.parseInt(st.nextToken());

    for (int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    visited = new boolean[N + 1];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      // 무방향 그래프이기 때문에 양쪽으로 간선 정보 저장
      graph.get(from).add(to);
      graph.get(to).add(from);
    }

    // 노드 버놓가 작은 것부터 방문해야 하므로 오름차순 정렬
    for (int i = 1; i < N + 1; i++) {
      graph.get(i).sort(null);
    }

    // DFS 실행
    dfs(V);
    // 모든 노드 방문이 끝나면 출력
    for (int num : s) {
      System.out.print(num + " ");
    }
    System.out.println();

    // BFS 실행
    bfs(V);

    br.close();
  }
}
