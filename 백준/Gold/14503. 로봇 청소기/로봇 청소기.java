import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Main {
  private static int n, m;
  private static int r, c, d;
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
      if (map[r][c] == 0) {
        map[r][c] = -1;
        ans += 1;
      }
      boolean turn = false;
      for (int[] elem : dir) {
        if (isInBound(r + elem[0], c + elem[1])) {
          if (map[r + elem[0]][c + elem[1]] == 0) {
            turn = true;
            break;
          }
        }
      }
      if (turn) {
        d = (d + 3) % 4;
        if (isInBound(r + dir[d][0], c + dir[d][1])) {
          if (map[r + dir[d][0]][c + dir[d][1]] == 0) {
            r = r + dir[d][0];
            c = c + dir[d][1];
          }
        }
      } else {
        if (isInBound(r + backDir[d][0], c + backDir[d][1])) {
          if (map[r + backDir[d][0]][c + backDir[d][1]] == 1) {
            break;
          }
          r = r + backDir[d][0];
          c = c + backDir[d][1];
        }
      }
    }
    System.out.println(ans);
    br.close();
  }
}
