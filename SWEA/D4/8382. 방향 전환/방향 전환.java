import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
  private static int t;

  private static boolean isInBound(int r, int c) {
    return 0 <= r && r < 201 && 0 <= c && c < 201;
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    t = Integer.parseInt(br.readLine());
    int[] ans = new int[t];

    for (int i = 0; i < t; i++) {
      String[] input = br.readLine().split(" ");
      Queue<int[]> q = new LinkedList<>();
      boolean[][][] visited = new boolean[201][201][2]; // 세 번째 차원으로 방향을 추가
      int[] start = { Integer.parseInt(input[0]) + 100, Integer.parseInt(input[1]) + 100 };
      int[] end = { Integer.parseInt(input[2]) + 100, Integer.parseInt(input[3]) + 100 };

      q.add(new int[] { start[0], start[1], 0, 0 });
      q.add(new int[] { start[0], start[1], 1, 0 });
      visited[start[0]][start[1]][0] = true;
      visited[start[0]][start[1]][1] = true;

      while (!q.isEmpty()) {
        int[] info = q.poll();
        int r = info[0], c = info[1], d = info[2], cnt = info[3];

        if (r == end[0] && c == end[1]) {
          ans[i] = cnt;
          break;
        }

        if (d == 0) {
          if (isInBound(r + 1, c) && !visited[r + 1][c][1]) {
            visited[r + 1][c][1] = true;
            q.add(new int[] { r + 1, c, 1, cnt + 1 });
          }
          if (isInBound(r - 1, c) && !visited[r - 1][c][1]) {
            visited[r - 1][c][1] = true;
            q.add(new int[] { r - 1, c, 1, cnt + 1 });
          }
        } else {
          if (isInBound(r, c + 1) && !visited[r][c + 1][0]) {
            visited[r][c + 1][0] = true;
            q.add(new int[] { r, c + 1, 0, cnt + 1 });
          }
          if (isInBound(r, c - 1) && !visited[r][c - 1][0]) {
            visited[r][c - 1][0] = true;
            q.add(new int[] { r, c - 1, 0, cnt + 1 });
          }
        }
      }
    }

    for (int i = 0; i < t; i++) {
      System.out.printf("#%d %d\n", i + 1, ans[i]);
    }
    br.close();
  }
}
