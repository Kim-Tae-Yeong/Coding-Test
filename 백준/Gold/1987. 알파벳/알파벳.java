import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
  public static int R, C, ans;
  public static String[][] map;
  public static boolean[][] visited;
  public static boolean[] check = new boolean[26];
  public static int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
  public static Stack<int[]> s = new Stack<>();

  public static boolean isInBound (int row, int col) {
    return (0 <= row && row < R && 0 <= col && col < C);
  }

  public static void backTracking (int row, int col) {
    for (int[] d : dir) {
      int nr = row + d[0];
      int nc = col + d[1];
      if (isInBound(nr, nc)) {
        if (!visited[nr][nc]) {
          char alphabet = map[nr][nc].toCharArray()[0];
          if (!check[(int)alphabet - 65]) {
            check[(int)alphabet - 65] = true;
            visited[nr][nc] = true;
            s.add(new int[] {nr, nc});
            backTracking(nr, nc);
            s.pop();
            check[(int)alphabet - 65] = false;
            visited[nr][nc] = false;
          }
          else {
            ans = Math.max(ans, s.size());
          }
        }
      }
    }
    ans = Math.max(ans, s.size());
  }
  public static void main (String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/1987/1987.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    R = Integer.parseInt(num[0]);
    C = Integer.parseInt(num[1]);

    map = new String[R][C];
    visited = new boolean[R][C];

    for (int i = 0; i < R; i++) {
      map[i] = br.readLine().split("");
    }

    ans = Integer.MIN_VALUE;

    char start = map[0][0].toCharArray()[0];
    check[(int)start - 65] = true;
    visited[0][0] = true;
    s.add(new int[] {0, 0});
    backTracking(0, 0);

    System.out.println(ans);

    br.close();
  }
}
