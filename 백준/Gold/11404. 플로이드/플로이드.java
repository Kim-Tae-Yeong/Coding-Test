import java.io.BufferedReader;
// import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_11404 {
  private static int n, m;
  // graph[i][j] : i에서 j로 한번에 가는 경로 중 최소 비용
  private static int[][] graph;
  // map[i][j] : i에서 j로 가는 경로 중 최소 비용
  private static int[][] map;
  private static StringBuilder ans = new StringBuilder();

  private static void dijkstra(int s) {
    Queue<int[]> q = new LinkedList<>();
    // s에서 도착할 수 있는 도시와 비용 저장
    for(int i = 1; i < n + 1; i++) {
      if(graph[s][i] != 0) {
        q.add(new int[] {s, i, graph[s][i]});
      }
    }
    while(!q.isEmpty()) {
      int[] info = q.poll();
      int start = info[0];
      int end = info[1];
      int cost = info[2];
      // 도착 도시에 한번도 방문을 하지 않았거나, 더 작은 비용으로 갈 수 있는 경우 비용 갱신
      if(map[s][end] == 0 || map[s][start] + cost < map[s][end]) {
        map[s][end] = map[s][start] + cost;
      }
      // 도착 도시에서 갈 수 있는 도시 탐색
      for(int i = 1; i < n + 1; i++) {
        // 시작 도시와 같으면 continue
        if(i == s) {
          continue;
        }
        if(graph[end][i] != 0) {
          // 갈 수 있는 도시 중 한번도 방문하지 않은 도시나, 더 작은 비용으로 갈 수 있는 경우 queue에 추가
          if(map[s][i] == 0 || map[s][end] + graph[end][i] < map[s][i]) {
            q.add(new int[] {end, i, graph[end][i]});
          }
        }
      }
    }
  }

  public static void main(String[] args) throws Exception{
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/11404/11404.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    m = Integer.parseInt(br.readLine());

    graph = new int[n + 1][n + 1];
    map = new int[n + 1][n + 1];

    for (int i = 0; i < m; i++) {
      String[] input = br.readLine().split(" ");
      int start = Integer.parseInt(input[0]);
      int end = Integer.parseInt(input[1]);
      int cost = Integer.parseInt(input[2]);
      // start에서 end로 가는 비용 중 최소 비용만 저장
      if(graph[start][end] == 0 || cost < graph[start][end]) {
        graph[start][end] = cost;
      }
    }
    
    for (int i = 1; i < n + 1; i++) {
      dijkstra(i);
    }

    for(int row = 1; row < n + 1; row++) {
      for(int col = 1; col < n + 1; col++) {
        ans.append(map[row][col]);
        ans.append(" ");
      }
      if(row != n) {
        ans.append("\n");
      }
    }

    System.out.println(ans.toString());

    br.close();
  }
}
