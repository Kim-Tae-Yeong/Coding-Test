import java.util.*;
import java.io.*;

public class Main_2638 {
  static int N, M;
  static int[][] map;
  static boolean[][] visited;
  static int[] dx = { -1, 1, 0, 0 };
  static int[] dy = { 0, 0, -1, 1 };

  static int simulate() {
    int time = 0;

    while (true) {
      // 매 시간마다 방문 여부 초기화
      visited = new boolean[N][M];
      // 치즈가 없으면 종료
      if (!hasCheese())
        break;

      // 외부 공기를 표시
      markAir(0, 0);

      // 치즈 녹이기
      meltCheese();

      time++;
    }

    return time;
  }

  static boolean hasCheese() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (map[i][j] == 1)
          return true;
      }
    }
    return false;
  }

  static void markAir(int x, int y) {
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[] { x, y });
    visited[x][y] = true;
    // (0, 0) 지점을 외부 공기로 표시
    map[x][y] = -1;

    // BFS를 이용해서 (0, 0) 지점에서 퍼질 수 있는 외부 공기를 표현
    // 이를 통해 치즈가 외부 공기와 맞닿아 있는지 확인
    while (!queue.isEmpty()) {
      int[] current = queue.poll();
      int cx = current[0], cy = current[1];

      for (int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny] && map[nx][ny] <= 0) {
          visited[nx][ny] = true;
          map[nx][ny] = -1;
          queue.offer(new int[] { nx, ny });
        }
      }
    }
  }

  static void meltCheese() {
    List<int[]> toMelt = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (map[i][j] == 1) {
          int airCount = 0;
          for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == -1) {
              airCount++;
            }
          }

          if (airCount >= 2) {
            toMelt.add(new int[] { i, j });
          }
        }
      }
    }

    for (int[] cheese : toMelt) {
      map[cheese[0]][cheese[1]] = 0;
    }
  }

  public static void main(String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("./2638.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(simulate());

    br.close();
  }

}
