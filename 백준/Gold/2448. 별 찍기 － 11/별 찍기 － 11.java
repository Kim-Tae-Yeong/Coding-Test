import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Main {
  private static int n;
  private static char[][] map;

  private static void draw(int row, int col, int size) {
    if (size == 3) {
      map[row][col] = '*';
      map[row + 1][col - 1] = '*';
      map[row + 1][col + 1] = '*';
      map[row + 2][col - 2] = '*';
      map[row + 2][col - 1] = '*';
      map[row + 2][col] = '*';
      map[row + 2][col + 1] = '*';
      map[row + 2][col + 2] = '*';
    } else {
      size = size / 2;
      draw(row, col, size);
      draw(row + size, col - size, size);
      draw(row + size, col + size, size);
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new
    // FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/2448/2448.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    map = new char[n][2 * n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < 2 * n; j++) {
        map[i][j] = ' ';
      }
    }
    draw(0, n - 1, n);
    for (char[] row : map) {
      System.out.println(row);
    }
    br.close();
  }
}