import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
  public static int n, k;
  // ans[i] : i번째 위치를 방문하는데 걸리는 최소 시간
  public static int[] ans = new int[100001];
  // 움직일때마다 시간이 늘어나기 때문에 이전에 방문한 위치는 더이상 확인하지 않아도 됨
  public static boolean[] visited = new boolean[100001];
  // bfs을 위한 큐
  public static Queue<int[]> q = new LinkedList<>();

  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/13549/13549.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    n = Integer.parseInt(num[0]);
    k = Integer.parseInt(num[1]);

    // 시작 위치 및 시간을 큐에 넣음
    q.add(new int[] {n, 0});

    // 시작 위치 및 시간 정보 초기화
    ans[n] = 0;
    visited[n] = true;

    while (!q.isEmpty()) {
      int[] info = q.poll();
      int pos = info[0];
      int time = info[1];
      // 현재 위치가 0이 아니면 순간이동
      if (pos != 0) {
        for (int i = pos; i < 100001; i = i * 2) {
          if (!visited[i]) {
            // 순간이동은 시간이 걸리지 않기 때문에 현재 위치에 걸리는 시간과 동일함
            ans[i] = time;
            q.add(new int[] {i, time});
            visited[i] = true;
            // 순간이동으로 목표 지점에 도착하면 탐색 중지
            if (i == k) {
              break;
            }
          }
        }
      }
      // 현재 위치가 0이 아니면( = 뒤로 1칸 갈 수 있는 위치이면))
      if (pos != 0) {
        if (!visited[pos - 1]) {
          ans[pos - 1] = time + 1;
          q.add(new int[] {pos - 1, time + 1});
          visited[pos - 1] = true;
        }
        // 뒤로 1칸 갔을 때 목표 지점이면 탐색 중지
        if (pos - 1 == k) {
          break;
        }
      }
      // 현재 위치가 10000이 아니면( = 앞으로 1칸 갈 수 있는 위치이면)
      if (pos != 100000) {
        if (!visited[pos + 1]) {
          ans[pos + 1] = time + 1;
          q.add(new int[] {pos + 1, time + 1});
          visited[pos + 1] = true;
        }
        // 앞으로 1칸 갔을 때 목표 지점이면 탐색 중지
        if (pos + 1 == k) {
          break;
        }
      }
    }

    System.out.println(ans[k]);

    br.close();
  }
}
