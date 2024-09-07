import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static int n, m;
  public static List<List<Integer>> board = new ArrayList<>();
  public static int ans = 0;
  public static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

  public static void dfs(int row, int col, int depth, int sum, boolean[][] visited) {
    if (depth == 4) {
      ans = Math.max(ans, sum);
      return;
    }

    for (int[] dir : directions) {
      int nr = row + dir[0];
      int nc = col + dir[1];
      if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc]) {
        visited[nr][nc] = true;
        dfs(nr, nc, depth + 1, sum + board.get(nr).get(nc), visited);
        visited[nr][nc] = false;  // 백트래킹
      }
    }
  }

  // ㅗ, ㅜ, ㅏ, ㅓ 모양을 처리하는 함수
  public static void checkSpecialShape(int row, int col) {
    // 4방향에서 3개의 블록을 선택하는 방식
    if (row >= 1 && col >= 1 && col < m - 1) { // ㅗ 모양
      int tmp = board.get(row).get(col) + board.get(row - 1).get(col) +
                board.get(row).get(col - 1) + board.get(row).get(col + 1);
      ans = Math.max(ans, tmp);
    }
    if (row < n - 1 && col >= 1 && col < m - 1) { // ㅜ 모양
      int tmp = board.get(row).get(col) + board.get(row + 1).get(col) +
                board.get(row).get(col - 1) + board.get(row).get(col + 1);
      ans = Math.max(ans, tmp);
    }
    if (row >= 1 && row < n - 1 && col >= 1) { // ㅏ 모양
      int tmp = board.get(row).get(col) + board.get(row - 1).get(col) +
                board.get(row + 1).get(col) + board.get(row).get(col - 1);
      ans = Math.max(ans, tmp);
    }
    if (row >= 1 && row < n - 1 && col < m - 1) { // ㅓ 모양
      int tmp = board.get(row).get(col) + board.get(row - 1).get(col) +
                board.get(row + 1).get(col) + board.get(row).get(col + 1);
      ans = Math.max(ans, tmp);
    }
  }

  public static void main(String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/14500/14500.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    String[] input = br.readLine().split(" ");
    n = Integer.parseInt(input[0]);
    m = Integer.parseInt(input[1]);

    // 입력받기
    for (int i = 0; i < n; i++) {
      String[] b = br.readLine().split(" ");
      List<Integer> tmp = new ArrayList<>();
      for (String elem : b) {
        tmp.add(Integer.parseInt(elem));
      }
      board.add(tmp);
    }

    // DFS 탐색
    boolean[][] visited = new boolean[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        visited[i][j] = true;
        dfs(i, j, 1, board.get(i).get(j), visited);
        visited[i][j] = false;

        // ㅗ, ㅜ, ㅏ, ㅓ 모양 체크
        checkSpecialShape(i, j);
      }
    }

    // 정답 출력
    System.out.println(ans);
    br.close();
  }
}
