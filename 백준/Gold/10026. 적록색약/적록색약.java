import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
  static int n;
  static String[][] board;
  static Boolean[][] visited;
  static Boolean[][] visited2;
  static int[] ans = new int[2];

  public static void bfs (int row, int col) {
    String start = board[row][col];
    visited[row][col] = true;
    Queue<List<Integer>> q = new LinkedList<>();
    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(row);
    coordinates.add(col);
    q.add(coordinates);
    int[][] dir = {
      {1, 0},
      {-1, 0},
      {0, 1},
      {0, -1}
    };
    while (q.size() != 0) {
      List<Integer> pos = q.poll();
      Integer r = pos.get(0);
      Integer c = pos.get(1);
      for (int[] d : dir) {
        int nr = r + d[0];
        int nc = c + d[1];
        if (0 <= nr && nr < n && 0 <= nc && nc < n) {
          if (board[nr][nc].equals(start) && !visited[nr][nc]) {
            visited[nr][nc] = true;
            List<Integer> tmp = new ArrayList<>();
            tmp.add(nr);
            tmp.add(nc);
            q.add(tmp);
          }
        }
      }
    }
  }

  public static void bfs2 (int row, int col) {
    visited[row][col] = true;
    Queue<List<Integer>> q = new LinkedList<>();
    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(row);
    coordinates.add(col);
    q.add(coordinates);
    int[][] dir = {
      {1, 0},
      {-1, 0},
      {0, 1},
      {0, -1}
    };
    while (q.size() != 0) {
      List<Integer> pos = q.poll();
      Integer r = pos.get(0);
      Integer c = pos.get(1);
      for (int[] d : dir) {
        int nr = r + d[0];
        int nc = c + d[1];
        if (0 <= nr && nr < n && 0 <= nc && nc < n) {
          if (!board[nr][nc].equals("B") && !visited[nr][nc]) {
            visited[nr][nc] = true;
            List<Integer> tmp = new ArrayList<>();
            tmp.add(nr);
            tmp.add(nc);
            q.add(tmp);
          }
        }
      }
    }
  }

  public static void main (String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/10026/10026.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    board = new String[n][n];
    for (int i = 0; i < n; i++) {
      char[] tmp = br.readLine().toCharArray();
      for (int j = 0; j < n; j++) {
        board[i][j] = Character.toString(tmp[j]);
      }
    }

    visited = new Boolean[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        visited[i][j] = false;
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (!visited[i][j]) {
          bfs(i, j);
          ans[0] += 1;
        }
      }
    }

    visited = new Boolean[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        visited[i][j] = false;
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (!visited[i][j]) {
          if (board[i][j].equals("B")) {
            bfs(i, j);
          }
          else {
            bfs2(i, j);
          }
          ans[1] += 1;
        }
      }
    }

    System.out.println(String.format("%d %d", ans[0], ans[1]));

    br.close();
  }
}