import java.io.*;
import java.util.*;

public class Main_11780 {
  static int n, m;
  static int[][] map, path;
  static StringBuilder sb = new StringBuilder();
  static final int INF = 100_000_000;

  static void floydWarshall() {
    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < n + 1; j++) {
        for (int k = 1; k < n + 1; k++) {
          if (map[j][k] > map[j][i] + map[i][k]) {
            map[j][k] = map[j][i] + map[i][k];
            path[j][k] = i;
          }
        }
      }
    }
  }

  static List<Integer> getPath(int start, int end) {
    List<Integer> route = new ArrayList<>();

    // 맨 처음 route에 시작 노드를 넣음
    route.add(start);
    // route에 중간 경로 노드를 넣음
    findPath(start, end, route);
    // 마지막으로 route에 도착 노드를 넣음
    route.add(end);

    return route;
  }

  static void findPath(int start, int end, List<Integer> route) {
    // 두 노드 사이에 거치는 노드가 없을 때까지(두 노드가 직접 연결되어 있을 때까지) 함수 실행
    if (path[start][end] == 0) {
      return;
    }

    // start -> via 사이의 중간 경로 노드들을 재귀적으로 찾음
    int via = path[start][end];
    findPath(start, via, route);
    // 중간 경로 노드들을 다 찾으면 via를 route에 저장
    route.add(via);
    // 이후 via -> end 사이의 중간 경로 노드들을 재귀적으로 찾음
    findPath(via, end, route);
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./11780.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    m = Integer.parseInt(st.nextToken());

    map = new int[n + 1][n + 1];
    path = new int[n + 1][n + 1];
    for (int i = 0; i < n + 1; i++) {
      Arrays.fill(map[i], INF);
      map[i][i] = 0;
    }

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      // 이전에 저장된 비용과 새로운 비용 중 더 작은 값 선택
      map[u][v] = Math.min(map[u][v], cost);
    }

    floydWarshall();

    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < n + 1; j++) {
        // 거리가 INF면 갈 수 없는 곳이기 때문에 0을 저장
        sb.append(map[i][j] == INF ? "0" : map[i][j]).append(" ");
      }
      sb.append("\n");
    }

    // i : 시작 노드
    for (int i = 1; i < n + 1; i++) {
      // j : 도착 노드
      for (int j = 1; j < n + 1; j++) {
        // 시작 노드 == 도착 노드 or 두 노드 간 거리가 INF면(i에서 j로 갈 수 없으면) 0을 저장
        if (i == j || map[i][j] == INF) {
          sb.append("0\n");
        }
        // i에서 j로 갈 수 있으면 가는 경로를 찾음
        else {
          List<Integer> route = getPath(i, j);
          sb.append(route.size()).append(" ");
          for (int node : route) {
            sb.append(node).append(" ");
          }
          sb.append("\n");
        }
      }
    }
    System.out.println(sb);
    br.close();
  }
}
