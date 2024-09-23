import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Main {
  private static int n;
  private static long b;
  private static int[][] map;

  private static int[][] cal (int[][] a, int[][] b) {
    int[][] tmp = new int[n][n];
    int row = 0;
    int col = 0;
    while (true) {
      if (row == n) {
        break;
      }
      int elem = 0;
      for (int i = 0; i < n; i++) {
        elem += a[row][i] * b[i][col];
      }
      tmp[row][col] = elem % 1000;
      if (col == n - 1) {
        col = 0;
        row += 1;
      }
      else {
        col += 1;
      }
    }
    return tmp;
  }

  private static int[][] matrix (long idx) {
    if (idx == 1) {
      return map;
    }
    else {
      boolean check = false;
      if (idx % 2 == 1) {
        check = true;
      }
      idx = idx / 2;
      int[][] half = matrix(idx);
      int[][] ans = cal(half, half);
      if (check) {
        ans = cal(ans, map);
      }
      return ans;
    }
  }

  public static void main (String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/10830/10830.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    n = Integer.parseInt(num[0]);
    b = Long.parseLong(num[1]); 

    map = new int[n][n];

    for (int i = 0; i < n; i++) {
      String[] input = br.readLine().split(" ");
      for (int j = 0; j < n; j++) {
        map[i][j] = Integer.parseInt(input[j]);
      }
    }

    int[][] ans = matrix(b);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(ans[i][j] % 1000);
        System.out.print(" ");
      }
      System.out.println();
    }

    br.close();
  }
}
