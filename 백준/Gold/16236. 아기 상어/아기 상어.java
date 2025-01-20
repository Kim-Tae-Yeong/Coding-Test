import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int size = 2, cnt = 0, ans = 0;
  static int minTime = Integer.MAX_VALUE;
  static int[][] map;
  static boolean[][] visited;
  static int[] current = new int[2], minFish = new int[2];
  static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

  static int findFish() {
    int time = -1;
    minTime = Integer.MAX_VALUE;
    minFish[0] = -1;
    minFish[1] = -1;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (map[i][j] != 0 && map[i][j] < size) {
          time = reachFish(i, j);
          if (time == -1) {
            continue;
          }
          if (time < minTime) {
            minTime = time;
            minFish[0] = i;
            minFish[1] = j;
          } else if (time == minTime) {
            if (i < minFish[0]) {
              minFish[0] = i;
              minFish[1] = j;
            } else if (i == minFish[0]) {
              if (j < minFish[1]) {
                minFish[1] = j;
              }
            }
          }
        }
      }
    }
    return minTime;
  }

  static boolean isInBound(int row, int col) {
    return 0 <= row && row < N && 0 <= col && col < N;
  }

  static int reachFish(int row, int col) {
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[] { current[0], current[1], 0 });
    visited = new boolean[N][N];
    visited[current[0]][current[1]] = true;
    while (!q.isEmpty()) {
      int[] info = q.poll();
      int r = info[0], c = info[1], time = info[2];
      for (int[] d : dir) {
        int nr = r + d[0], nc = c + d[1];
        if (isInBound(nr, nc) && !visited[nr][nc]) {
          if (map[nr][nc] <= size) {
            if (nr == row && nc == col) {
              return time + 1;
            }
            visited[nr][nc] = true;
            q.add(new int[] { nr, nc, time + 1 });
          }
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./16236.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    map = new int[N][N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if (map[i][j] == 9) {
          current[0] = i;
          current[1] = j;
          map[i][j] = 0;
        }
      }
    }

    while (true) {
      int time = findFish();
      if (time == Integer.MAX_VALUE) {
        break;
      }
      ans = ans + time;
      cnt++;
      if (cnt == size) {
        size++;
        cnt = 0;
      }
      map[minFish[0]][minFish[1]] = 0;
      current[0] = minFish[0];
      current[1] = minFish[1];
    }

    System.out.println(ans);
    br.close();
  }
}
