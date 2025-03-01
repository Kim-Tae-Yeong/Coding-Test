import java.io.*;
import java.util.*;

public class Main_16234 {
  static int N, L, R;
  static int[][] map;
  static boolean[][] visited;
  static List<int[]> l;
  static int[][] dir = { { -1, 0 }, { 1, 0 }, { -0, -1 }, { 0, 1 } };
  static int ans = 0;

  static boolean isInBound(int row, int col) {
    return 0 <= row && row < N && 0 <= col && col < N;
  }

  static boolean open(int row, int col) {
    // bfs를 이용해 연합이 될 수 있는 국가 확인
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[] { row, col });
    // 배열에는 연합 국가들의 위치 저장
    l = new ArrayList<>();
    visited[row][col] = true;
    l.add(new int[] { row, col });
    while (!q.isEmpty()) {
      int[] pos = q.poll();

      // 현재 위치에서 상하좌우 확인
      for (int[] d : dir) {
        int nr = pos[0] + d[0];
        int nc = pos[1] + d[1];
        // 경계 밖으로 넘어가는지 확인
        if (isInBound(nr, nc)) {
          // 두 국가 간의 인구수 차이 계산
          int gap = Math.abs(map[pos[0]][pos[1]] - map[nr][nc]);
          // 인구수 차이가 조건에 맞고 인접 국가를 방문하지 않았으면
          if (gap >= L && gap <= R && !visited[nr][nc]) {
            // 큐에 저장
            q.add(new int[] { nr, nc });
            // 방문 여부 갱신
            visited[nr][nc] = true;
            // 인접 국가 배열에 저장
            l.add(new int[] { nr, nc });
          }
        }
      }
    }
    // 배열의 크기가 1이면 국경선이 열리지 않았다는 의미이므로 false
    if (l.size() == 1) {
      return false;
    }
    // 배열의 크기가 1 이상이면 국경선이 열렸다는 의미이므로 true
    else {
      return true;
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./16234.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    L = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());
    map = new int[N][N];
    visited = new boolean[N][N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    while (true) {
      // 국경선이 열리는지 확인
      boolean check = false;
      // 날짜가 지날때마다 방문 여부 초기화
      for (int i = 0; i < N; i++) {
        Arrays.fill(visited[i], false);
      }
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          // 현재 위치에 방문했는지 확인
          if (!visited[i][j]) {
            // 현재 위치를 기준으로 국경선이 열릴 수 있는지
            if (open(i, j)) {
              // 국경선이 열리면 열린 국가들의 평균 인구수로 연합 국가들의 인구를 맞춤
              int sum = 0;
              for (int[] pos : l) {
                sum += map[pos[0]][pos[1]];
              }
              for (int[] pos : l) {
                map[pos[0]][pos[1]] = (sum / l.size());
              }
              // 국경선이 열렸음을 표시
              check = true;
            }
          }
        }
      }
      if (!check) {
        break;
      }
      ans++;
    }

    System.out.println(ans);

    br.close();
  }
}
