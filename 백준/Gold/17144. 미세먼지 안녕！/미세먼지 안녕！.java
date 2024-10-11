import java.io.BufferedReader;
// import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_17144 {
  private static int r, c, t, cnt;
  private static int ans = 0;
  private static int[][] map;
  // pos[0] : 공기청정기 머리
  // pos[1] : 공기청정기 몸통
  private static int[] pos = new int[] {-1, -1};
  // 미세먼지 위치와 확산되는 미세먼지 양 저장
  private static Queue<int[]> q = new LinkedList<>();
  // 미세먼지 확산 방향
  private static int[][] dir = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
  // 반시계방향 공기청정기 바람 방향
  private static int[][] counterClockWiseDir = new int[][] {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
  // 시계방향 공기청정기 바람 방향
  private static int[][] clockWiseDir = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  private static boolean isInBound(int row, int col) {
    return (0 <= row && row < r && 0 <= col && col < c);
  }

  private static void spreadDust() {
    // 각 위치마다 확산되는 미세먼지 양 확인
    for(int i = 0; i < r; i++) {
      for(int j = 0; j < c; j++) {
        // 미세먼지가 있으면
        if(map[i][j] > 0) {
          // 해당 위치와 확산되는 미세먼지 양 저장
          q.add(new int[] {i, j, map[i][j] / 5});
          // 현재 위치에서 어느 방향으로 확산할 수 있는지 확인
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
          // 현재 위치 미세먼지 양 변경
          map[i][j] = map[i][j] - ((map[i][j] / 5) * cnt);
        }
      }
    }

    // 미세먼지 확산
    while(!q.isEmpty()) {
      int[] info = q.poll();
      int row = info[0];
      int col = info[1];
      int tmp = info[2];
      
      // 확산 가능한 위치로 미세먼지 확산
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

  // 반시계방향 공기청정기
  private static void counterClockWise() {
    int idx = 0;
    int[] current = new int[] {pos[0], 0};
    int pre = 0;
    while(true) {
      // 주어진 범위에서 벗어나면 방향을 바꿈(오른쪽 -> 위쪽 -> 왼쪽 -> 아래쪽)
      if(!isInBound(current[0] + counterClockWiseDir[idx][0], current[1] + counterClockWiseDir[idx][1])) {
        idx += 1;
      }
      int nextRow = current[0] + counterClockWiseDir[idx][0];
      int nextCol = current[1] + counterClockWiseDir[idx][1];
      // 다음 위치가 공기청정기 머리이면 break
      if(nextRow == pos[0] && nextCol == 0) {
        break;
      }
      // 현재칸에 있는 미세먼지를 다음칸으로 옮김
      int tmp = map[nextRow][nextCol];
      map[nextRow][nextCol] = pre;
      pre = tmp;
      // 현재 위치를 옮김
      current[0] = nextRow;
      current[1] = nextCol;
    }
  }

  // 시계방향 공기청정기
  private static void clockWise() {
    int idx = 0;
    int[] current = new int[] {pos[1], 0};
    int pre = 0;
    while(true) {
      // 주어진 범위에서 벗어나면 방향을 바꿈(오른쪽 -> 아래쪽 -> 왼쪽 -> 위쪽)
      if(!isInBound(current[0] + clockWiseDir[idx][0], current[1] + clockWiseDir[idx][1])) {
        idx += 1;
      }
      int nextRow = current[0] + clockWiseDir[idx][0];
      int nextCol = current[1] + clockWiseDir[idx][1];
      // 다음 위치가 공기청정기 몸통이면 break
      if(nextRow == pos[1] && nextCol == 0) {
        break;
      }
      // 현재칸에 있는 미세먼지를 다음칸으로 옮김
      int tmp = map[nextRow][nextCol];
      map[nextRow][nextCol] = pre;
      pre = tmp;
      // 현재 위치를 옮김
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
        // 공기청정기 위치 확인
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
