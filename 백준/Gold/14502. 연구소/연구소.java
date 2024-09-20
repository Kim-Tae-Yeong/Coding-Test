import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Main {
  private static int n, m;
  private static int ans = Integer.MIN_VALUE;
  private static int[][] map;
  private static List<int[]> virus = new ArrayList<>();
  private static List<int[]> wall = new ArrayList<>();
  private static Stack<int[]> s = new Stack<>();
  private static boolean[][] visited;
  private static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

  private static boolean isInBound (int row, int col) {
    return (0 <= row && row < n && 0 <= col && col < m);
  }

  private static void bfs () {
    Queue<int[]> currentVirus = new LinkedList<>();
    for (int[] elem : virus) {
      currentVirus.add(elem);
    }
    while (!currentVirus.isEmpty()) {
      int[] pos = currentVirus.poll();
      int row = pos[0];
      int col = pos[1];
      for (int[] d : dir) {
        int nr = row + d[0];
        int nc = col + d[1];
        if (isInBound(nr, nc)) {
          if (map[nr][nc] == 0) {
            map[nr][nc] = 2;
            currentVirus.add(new int[] {nr, nc});
          }
        }
      }
    }
    int cnt = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (map[i][j] ==  0) {
          cnt += 1;
        }
      }
    }
    ans = Math.max(ans, cnt);
  }

  private static void clearMap () {
    map = new int[n][m];
    for (int[] elem : virus) {
      map[elem[0]][elem[1]] = 2;
    }
    for (int[] elem : wall) {
      map[elem[0]][elem[1]] = 1;
    }
  }

  private static void selectWall() {
    if (s.size() == 3) {
      for (int[] elem : s) {
        map[elem[0]][elem[1]] = 1;
      }
      bfs();
      clearMap();
    }
    else {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if (map[i][j] == 0 && !visited[i][j]) {
            s.add(new int[] {i, j});
            visited[i][j] = true;
            selectWall();
            s.pop();
            visited[i][j] = false;
          }
        }
      }
    }
  }

  public static void main (String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/14502/14502.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    n = Integer.parseInt(num[0]);
    m = Integer.parseInt(num[1]);

    map = new int[n][m];
    visited = new boolean[n][m];

    for (int i = 0; i < n; i++) {
      String[] input = br.readLine().split(" ");
      for (int j = 0; j < m; j++) {
        if (Integer.parseInt(input[j]) == 2) {
          virus.add(new int[] {i, j});
        }
        else if (Integer.parseInt(input[j]) == 1) {
          wall.add(new int[] {i, j});
        }
        map[i][j] = Integer.parseInt(input[j]);
      }
    }

    selectWall();

    System.out.println(ans);
    br.close();
  }
}
