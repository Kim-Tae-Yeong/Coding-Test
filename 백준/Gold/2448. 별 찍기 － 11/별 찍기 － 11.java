import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Main {
  private static int n;
  private static char[][] map;
  private static StringBuilder sb = new StringBuilder();

  private static void drawStar (int row, int col, int size) {
    if (size == 3) {
      map[row][col] = '*';
      map[row + 1][col - 1] = '*';
      map[row + 1][col + 1] = '*';
      map[row + 2][col - 2] = '*';
      map[row + 2][col - 1] = '*';
      map[row + 2][col] = '*';
      map[row + 2][col + 1] = '*';
      map[row + 2][col + 2] = '*';
      return ;
    }
    int s = size / 2;
    drawStar(row, col, s);
    drawStar(row + s, col - s, s);
    drawStar(row + s, col + s, s);
  }
  public static void main (String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/2448/2448.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    map = new char[n][n * 2 - 1];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < 2 * n - 1; j++) {
        map[i][j] = ' ';
      }
    }

    drawStar(0, n - 1, n);
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < 2 * n - 1; j++) {
        sb.append(map[i][j]);
      }
      if (i != n - 1) {
        sb.append("\n");
      }
    }

    System.out.println(sb.toString());

    br.close();
  }  
}
