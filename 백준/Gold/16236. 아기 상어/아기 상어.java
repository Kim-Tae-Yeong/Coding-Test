import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16236 {
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
        // 현재 상어 크기보다 작은 물고기를 발견하면
        if (map[i][j] != 0 && map[i][j] < size) {
          // 해당 물고기까지 걸리는 시간 탐색
          time = reachFish(i, j);
          // 시간이 -1이면(해당 물고기에 도달하지 못하면) continue
          if (time == -1) {
            continue;
          }
          // 이전에 기록된 시간보다 더 짧은 시간이 있다면
          // 즉 더 가까운 거리에 먹을 수 있는 물고기가 있으면
          if (time < minTime) {
            // 최소 시간 및 물고기 위치 갱신
            minTime = time;
            minFish[0] = i;
            minFish[1] = j;
          }
          // 같은 시간으로 측정된 물고기가 있으면
          else if (time == minTime) {
            // 조건에 맞게 위치 변경
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
      // 먹을 수 있는 물고기가 있는지 & 해당 물고기에 도달할 수 있는지 확인
      int time = findFish();
      if (time == Integer.MAX_VALUE) {
        break;
      }
      // 해당 물고기까지 걸리는 시간 추가
      ans = ans + time;
      // 먹은 물고기 수 증가
      cnt++;
      // 먹은 물고기 수가 현재 물고기 크기랑 같아지면
      if (cnt == size) {
        // 크기 증가
        size++;
        // 먹은 물고기 수 초기화
        cnt = 0;
      }
      // 현재 먹은 물고기 위치 0으로 변경
      map[minFish[0]][minFish[1]] = 0;
      // 상어 위치 변경
      current[0] = minFish[0];
      current[1] = minFish[1];
    }

    System.out.println(ans);
    br.close();
  }
}
