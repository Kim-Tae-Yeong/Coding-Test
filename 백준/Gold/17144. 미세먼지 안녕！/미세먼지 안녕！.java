import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
  private static int r, c, t, cnt;
  private static int ans = 0;
  private static int[][] map;
  private static int[] pos = new int[] {-1, -1};
  private static Queue<int[]> q = new LinkedList<>();
  private static int[][] dir = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
  private static int[][] counterClockWiseDir = new int[][] {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
  private static int[][] clockWiseDir = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  private static boolean isInBound(int row, int col) {
    return (0 <= row && row < r && 0 <= col && col < c);
  }

  private static void spreadDust() {
    for(int i = 0; i < r; i++) {
      for(int j = 0; j < c; j++) {
        if(map[i][j] > 0) {
          q.add(new int[] {i, j, map[i][j] / 5});
          cnt = 0;
          for(int[] d : dir) {
            int nr = i + d[0];
            int nc = j + d[1];
            if(isInBound(nr, nc)) {
              if(map[nr][nc] != -1) {
                cnt += 1;
              }
            }
          }
          map[i][j] = map[i][j] - ((map[i][j] / 5) * cnt);
        }
      }
    }

    while(!q.isEmpty()) {
      int[] info = q.poll();
      int row = info[0];
      int col = info[1];
      int tmp = info[2];
      
      for(int[] d : dir) {
        int nr = row + d[0];
        int nc = col + d[1];
        if(isInBound(nr, nc)) {
          if(map[nr][nc] != -1) {
            map[nr][nc] += tmp;
          }
        }
      }
    }
  }

  private static void counterClockWise() {
    int idx = 0;
    int[] current = new int[] {pos[0], 0};
    int pre = 0;
    while(true) {
      if(!isInBound(current[0] + counterClockWiseDir[idx][0], current[1] + counterClockWiseDir[idx][1])) {
        idx += 1;
      }
      int nextRow = current[0] + counterClockWiseDir[idx][0];
      int nextCol = current[1] + counterClockWiseDir[idx][1];
      if(nextRow == pos[0] && nextCol == 0) {
        break;
      }
      int tmp = map[nextRow][nextCol];
      map[nextRow][nextCol] = pre;
      pre = tmp;
      current[0] = nextRow;
      current[1] = nextCol;
    }
  }

  private static void clockWise() {
    int idx = 0;
    int[] current = new int[] {pos[1], 0};
    int pre = 0;
    while(true) {
      if(!isInBound(current[0] + clockWiseDir[idx][0], current[1] + clockWiseDir[idx][1])) {
        idx += 1;
      }
      int nextRow = current[0] + clockWiseDir[idx][0];
      int nextCol = current[1] + clockWiseDir[idx][1];
      if(nextRow == pos[1] && nextCol == 0) {
        break;
      }
      int tmp = map[nextRow][nextCol];
      map[nextRow][nextCol] = pre;
      pre = tmp;
      current[0] = nextRow;
      current[1] = nextCol;
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/17144/17144.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    r = Integer.parseInt(num[0]);
    c = Integer.parseInt(num[1]);
    t = Integer.parseInt(num[2]);

    map = new int[r][c];

    for(int i = 0; i < r; i++) {
      String[] input = br.readLine().split(" ");
      for(int j = 0; j < c; j++) {
        map[i][j] = Integer.parseInt(input[j]);
        if(map[i][j] == -1) {
          if(pos[0] == -1) {
            pos[0] = i;
          }
          else {
            pos[1] = i;
          }
        }
      }
    }

    for(int i = 0; i < t; i++) {
      spreadDust();
      counterClockWise();
      clockWise();
    }

    for(int i = 0; i < r; i++) {
      for(int j = 0; j < c; j++) {
        if(map[i][j] > 0) {
          ans += map[i][j];
        }
      }
    }

    System.out.println(ans);

    br.close();
  }
}
