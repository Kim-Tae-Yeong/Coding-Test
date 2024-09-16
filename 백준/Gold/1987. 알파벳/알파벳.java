import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Main {
  public static int R, C, ans;
  public static int[][] map;
  public static boolean[] check = new boolean[26];
  public static int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

  public static boolean isInBound (int row, int col) {
    return (0 <= row && row < R && 0 <= col && col < C);
  }

  public static void backTracking (int row, int col, int cnt) {
    for (int[] d : dir) {
      int nr = row + d[0];
      int nc = col + d[1];
      if (isInBound(nr, nc)) {
        int next = map[nr][nc];
        if (!check[next]) {
          check[next] = true;
          backTracking(nr, nc, cnt + 1);
          check[next] = false;
        }
        else {
          ans = Math.max(ans, cnt);
        }
      }
    }
    ans = Math.max(ans, cnt);
  }
  public static void main (String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/1987/1987.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    R = Integer.parseInt(num[0]);
    C = Integer.parseInt(num[1]);

    map = new int[R][C];

    for (int i = 0; i < R; i++) {
      // map[i] = br.readLine().split("");
      String input = br.readLine();
      for (int j = 0; j < C; j++) {
        map[i][j] = input.charAt(j) - 'A';
      }
    }

    ans = Integer.MIN_VALUE;

    // char start = map[0][0].toCharArray()[0];
    int start = map[0][0];    
    check[start] = true;
    backTracking(0, 0, 1);

    System.out.println(ans);

    br.close();
  }
}
