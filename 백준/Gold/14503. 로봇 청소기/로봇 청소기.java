import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Main {
  private static int n, m;
  // 각각 현재 로봇의 row, column, direction
  private static int r, c, d;
  // map[r][c] = 0(청소되지 않음), -1(청소됨), 1(벽)
  private static int[][] map;
  private static int[][] dir = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
  private static int[][] backDir = new int[][] { { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 } };
  private static int ans = 0;

  private static boolean isInBound(int r, int c) {
    return 0 <= r && r < n && 0 <= c && c < m;
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new
    // FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/14503/14053.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    n = Integer.parseInt(num[0]);
    m = Integer.parseInt(num[1]);
    map = new int[n][m];

    String[] startInfo = br.readLine().split(" ");
    r = Integer.parseInt(startInfo[0]);
    c = Integer.parseInt(startInfo[1]);
    d = Integer.parseInt(startInfo[2]);

    for (int i = 0; i < n; i++) {
      String[] input = br.readLine().split(" ");
      for (int j = 0; j < m; j++) {
        map[i][j] = Integer.parseInt(input[j]);
      }
    }

    while (true) {
      // 현재 칸이 청소되어 있지 않으면 청소함
      if (map[r][c] == 0) {
        map[r][c] = -1;
        ans += 1;
      }
      // 현재 위치 기준 동서남북 위치 탐색
      boolean turn = false;
      for (int[] elem : dir) {
        if (isInBound(r + elem[0], c + elem[1])) {
          // 동서남북 위치 중 청소되지 않은 곳이 존재함
          if (map[r + elem[0]][c + elem[1]] == 0) {
            turn = true;
            break;
          }
        }
      }
      // 동서남북 위치 중 청소되지 않은 곳이 존재하면
      if (turn) {
        // 반시계 방향으로 회전
        d = (d + 3) % 4;
        // 만약 회전한 위치가 청소되지 않은 곳이면 해당 위치로 이동
        if (isInBound(r + dir[d][0], c + dir[d][1])) {
          if (map[r + dir[d][0]][c + dir[d][1]] == 0) {
            r = r + dir[d][0];
            c = c + dir[d][1];
          }
        }
      }
      // 동서남북 위치 중 청소되지 않은 곳이 없으면
      else {
        // 현재 위치에서 뒤로 갔을 때 벽이면 break
        if (isInBound(r + backDir[d][0], c + backDir[d][1])) {
          if (map[r + backDir[d][0]][c + backDir[d][1]] == 1) {
            break;
          }
          // 벽이 아니면 뒤로 이동함
          r = r + backDir[d][0];
          c = c + backDir[d][1];
        }
      }
    }
    System.out.println(ans);
    br.close();
  }
}
