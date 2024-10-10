import java.io.BufferedReader;
// import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main_14938 {
  private static int n, m, r;
  private static int ans = Integer.MIN_VALUE;
  private static int[] item;
  private static List<List<int[]>> graph = new ArrayList<>();

  private static void findMaxItems(int s) {
    // {시작 위치, 도착 위치, cost} 저장
    // cost 오름차순 정렬
    PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
    // visited[i] : 시작 위치에서 i까지 도달하는 최소 cost
    int[] visited = new int[n + 1];
    for(int[] elem : graph.get(s)) {
      // cost가 수색 범위 이하인 도착 위치만 저장
      if(elem[2] <= m) {
        pq.add(elem);
      }
    }
    while(!pq.isEmpty()) {
      int[] info = pq.poll();
      int current = info[0];
      int next = info[1];
      int cost = info[2];
      
      // 도착 위치가 시작 위치면 continue
      if(next == s) {
        continue;
      }

      // 도착 위치까지 가는 cost가 수색 범위 이하일 때
      if(visited[current] + cost <= m) {
        // 도착 위치에 처음 도달하거나 기존에 도달한 cost보다 작으면 도착 위치 cost 변경
        if(visited[next] == 0 || visited[current] + cost < visited[next]) {
          visited[next] = visited[current] + cost; 
        }
      }
      
      // 도착 위치를 시작 위치로 하여 갈 수 있는 도착 위치 확인
      for(int[] elem : graph.get(next)) {
        if(visited[elem[0]] + elem[2] <= m) {
          if(visited[elem[1]] == 0 || visited[elem[0]] + elem[2] < visited[elem[1]]) {
            pq.add(elem);
          }
        }
      }
    }

    // 시작 위치의 item부터
    int tmp = item[s];
    for(int i = 1; i < n + 1; i++) {
      // i 위치에 도달할 수 있으면 해당 위치의 item 추가
      if(visited[i] != 0) {
        tmp += item[i];
      }
    }

    // 시작 위치에 따른 item 수 중 가장 큰 값을 가져옴
    ans = Math.max(ans, tmp);
  }

  public static void main(String[] agrs) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/14938/14938.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    n = Integer.parseInt(num[0]);
    m = Integer.parseInt(num[1]);
    r = Integer.parseInt(num[2]);

    item = new int[n + 1];
    String[] cnt = br.readLine().split(" ");
    for(int i = 1; i < n + 1; i++) {
      item[i] = Integer.parseInt(cnt[i - 1]);
    }

    for(int i = 0; i < n + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for(int i = 0; i < r; i++) {
      String[] input = br.readLine().split(" ");
      int start = Integer.parseInt(input[0]);
      int end = Integer.parseInt(input[1]);
      int cost = Integer.parseInt(input[2]);
      graph.get(start).add(new int[] {start, end, cost});
      graph.get(end).add(new int[] {end, start, cost});
    }

    for(int i = 1; i < n + 1; i++) {
      findMaxItems(i);
    }

    System.out.println(ans);

    br.close();
  }
}
