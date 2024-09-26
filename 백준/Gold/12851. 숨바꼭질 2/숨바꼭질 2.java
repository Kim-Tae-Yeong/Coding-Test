import java.io.BufferedReader;
// import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_12851 {
  private static int n, k, t;
  // visited[i][0] : i에 위치할 수 있는 최소 시간
  // visited[i][1] : 최소 시간에 도달할 수 있는 경우의 수
  private static int[][] visited;
  private static Queue<int[]> q = new LinkedList<>();
  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/12851/12851.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] input = br.readLine().split(" ");
    n = Integer.parseInt(input[0]);
    k = Integer.parseInt(input[1]);

    visited = new int[100001][2];
    for(int i = 0; i < 100001; i++) {
      visited[i][0] = -1;
    }

    // 시작 위치에 도달할 수 있는 최소 시간 : 0
    visited[n][0] = 0;
    // 해당 경우의 수 : 1
    visited[n][1] = 1;

    // t : k에 도달하는 최소 시간
    // 이를 이용해서 t보다 시간이 오래 걸리는 경우는 계산하지 않음
    t = Integer.MAX_VALUE;

    // 시작 위치를 기준으로 + 1, - 1, * 2 위치의 시간과 경우의 수를 queue에 넣음
    if(n + 1 < 100001) {
      q.add(new int[] {n + 1, 1});
    }
    if(n - 1 > -1) {
      q.add(new int[] {n - 1, 1});
    }
    if(0 < n * 2 && n * 2 < 100001) {
      q.add(new int[] {n * 2, 1});
    }

    while(!q.isEmpty()) {
      int[] info = q.poll();
      int pos = info[0];
      int time = info[1];

      // 현재 위치를 한번도 방문하지 않았거나, 더 적은 시간에 도달할 수 있으면 최소 시간과 경우의 수 갱신
      if(visited[pos][0] == -1 || visited[pos][0] > time) {
        visited[pos][0] = time;
        visited[pos][1] = 1;
      }
      // 현재 위치에 최소 시간과 동일하게 도달하면 경우의 수를 1가지 늘림
      else if(visited[pos][0] == time) {
        visited[pos][1] += 1;
      }

      // 목표 지점에 도달하면 기준 최소 시간 갱신
      if(pos == k) {
        t = visited[pos][0];
      }

      // 목표 지점에 도달하는 최소 시간보다 오래 걸리는 경우는 계산에서 제외
      if(time + 1 <= t) {
        if(pos + 1 < 100001) {
          // (현재 지점 + 1) 위치에 방문하지 않았거나, 기존에 (현재 위치 + 1) 위치에 저장된 최소 시간보다 (현재 지점까지의 시간 + 1)이 작거나 같으면 queue에 넣음
          if(visited[pos + 1][0] == -1 || visited[pos + 1][0] >= time + 1) {
            q.add(new int[] {pos + 1, time + 1});
          }
        }
        if(pos - 1 > -1) {
          // (현재 지점 - 1) 위치에 방문하지 않았거나, 기존에 (현재 위치 - 1) 위치에 저장된 최소 시간보다 (현재 지점까지의 시간 + 1)이 작거나 같으면 queue에 넣음
          if(visited[pos - 1][0] == -1 || visited[pos - 1][0] >= time + 1) {
            q.add(new int[] {pos - 1, time + 1});
          }
        }
        if(0 <= pos * 2 && pos * 2 < 100001) {
          // (현재 지점 * 2) 위치에 방문하지 않았거나, 기존에 (현재 위치 * 2) 위치에 저장된 최소 시간보다 (현재 지점까지의 시간 + 1)이 작거나 같으면 queue에 넣음
          if(visited[pos * 2][0] == -1 || visited[pos * 2][0] >= time + 1) {
            q.add(new int[] {pos * 2, time + 1});
          }
        }
      }
    }

    System.out.println(visited[k][0]);
    System.out.println(visited[k][1]);
    br.close();
  }
}
