import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Main {
  private static int n, ans;
  private static int[][] map;

  private static boolean isInBound (int row, int col) {
    return (0 <= row && row < n && 0 <= col && col < n);
  }

  private static void insertQueen (int row, int col) {
    map[row][col] += 1;
    for (int i = 1; i < n; i++) {
      if (isInBound(row, col + i)) {
        map[row][col + i] += 1;
      }
      if (isInBound(row, col - i)) {
        map[row][col - i] += 1;
      }
      if (isInBound(row + i, col)) {
        map[row + i][col] += 1;
      }
      if (isInBound(row + i, col + i)) {
        map[row + i][col + i] += 1;
      }
      if (isInBound(row + i, col - i)) {   
        map[row + i][col - i] += 1;
      }
    }
  }

  private static void deleteQueen (int row, int col, int cnt) {
    map[row][col] -= 1;
    for (int i = 1; i < n; i++) {
      if (isInBound(row, col + i)) {
        map[row][col + i] -= 1;
      }
      if (isInBound(row, col - i)) {
        map[row][col - i] -= 1;
      }
      if (isInBound(row + i, col)) {
        map[row + i][col] -= 1;
      }
      if (isInBound(row + i, col + i)) {
        map[row + i][col + i] -= 1;
      }
      if (isInBound(row + i, col - i)) {   
        map[row + i][col - i] -= 1;
      }
    }
  }

  private static void queen(int row, int col, int cnt) {
    insertQueen(row, col);
    
    if (row == n - 1) {
      if (cnt == n) {
        ans = ans + 1;
      }
    }
    else {
      for (int i = 0; i < n; i++) {
        if (map[row + 1][i] == 0) {
          queen(row + 1, i, cnt + 1);
        }
      }
    }
    deleteQueen(row, col, cnt);
  }  

  public static void main (String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/9663/9663.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    map = new int[n][n];
    ans = 0;

    for (int col = 0; col < n; col++) {
      queen(0, col, 1);
    }
    
    System.out.println(ans);

    br.close();
  }
}
