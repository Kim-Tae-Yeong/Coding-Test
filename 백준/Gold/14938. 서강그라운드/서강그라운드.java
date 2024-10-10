import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
  private static int n, m, r;
  private static int ans = Integer.MIN_VALUE;
  private static int[] item;
  private static List<List<int[]>> graph = new ArrayList<>();

  private static void findMaxItems(int s) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
    // {시작, 도착, cost} 저장
    int[] visited = new int[n + 1];
    for(int[] elem : graph.get(s)) {
      if(elem[2] <= m) {
        pq.add(elem);
      }
    }
    while(!pq.isEmpty()) {
      int[] info = pq.poll();
      int current = info[0];
      int next = info[1];
      int cost = info[2];
      
      if(next == s) {
        continue;
      }

      if(visited[current] + cost <= m) {
        if(visited[next] == 0 || visited[current] + cost < visited[next]) {
          visited[next] = visited[current] + cost; 
        }
      }
      
      for(int[] elem : graph.get(next)) {
        if(visited[elem[0]] + elem[2] <= m) {
          if(visited[elem[1]] == 0 || visited[elem[0]] + elem[2] < visited[elem[1]]) {
            pq.add(elem);
          }
        }
      }
    }
    int tmp = item[s];
    for(int i = 1; i < n + 1; i++) {
      if(visited[i] != 0) {
        tmp += item[i];
      }
    }
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