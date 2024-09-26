import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
  private static int n, k, t;
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
    visited[n][0] = 0;
    visited[n][1] = 1;
    t = Integer.MAX_VALUE;
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
      if(visited[pos][0] == -1 || visited[pos][0] > time) {
        visited[pos][0] = time;
        visited[pos][1] = 1;
      }
      else if(visited[pos][0] == time) {
        visited[pos][1] += 1;
      }
      if(pos == k) {
        t = visited[pos][0];
      }
      if(time + 1 <= t) {
        if(pos + 1 < 100001) {
          if(visited[pos + 1][0] == -1 || visited[pos + 1][0] >= time + 1) {
            q.add(new int[] {pos + 1, time + 1});
          }
        }
        if(pos - 1 > -1) {
          if(visited[pos - 1][0] == -1 || visited[pos - 1][0] >= time + 1) {
            q.add(new int[] {pos - 1, time + 1});
          }
        }
        if(0 <= pos * 2 && pos * 2 < 100001) {
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
