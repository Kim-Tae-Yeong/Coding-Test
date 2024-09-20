import java.io.BufferedReader;
// import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Main_14502 {
  private static int n, m;
  private static int ans = Integer.MIN_VALUE;
  private static int[][] map;
  // virus : 초기 바이러스 위치
  private static List<int[]> virus = new ArrayList<>();
  // wall : 초기 벽 위치
  private static List<int[]> wall = new ArrayList<>();
  // s : 새로 설치할 벽의 위치
  private static Stack<int[]> s = new Stack<>();
  private static boolean[][] visited;
  private static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

  private static boolean isInBound (int row, int col) {
    return (0 <= row && row < n && 0 <= col && col < m);
  }

  private static void bfs () {
    // 초기 바이러스 위치 저장
    Queue<int[]> currentVirus = new LinkedList<>();
    for (int[] elem : virus) {
      currentVirus.add(elem);
    }
    // 현재 설치된 벽을 기준으로 초기 바이러스로부터 퍼질 수 있는 바이러스 위치 표시
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
    // 바이러스가 모두 퍼진 이후 안전구역의 개수 확인
    int cnt = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (map[i][j] ==  0) {
          cnt += 1;
        }
      }
    }
    // 기존 안전구역의 개수와 현재 안전구역의 개수 중 더 큰 값 저장
    ans = Math.max(ans, cnt);
  }

  // 안전구역의 개수를 구한 후 지도를 초기 상태로 되돌림
  private static void clearMap () {
    map = new int[n][m];
    for (int[] elem : virus) {
      map[elem[0]][elem[1]] = 2;
    }
    for (int[] elem : wall) {
      map[elem[0]][elem[1]] = 1;
    }
  }

  // 벽 3개를 고름
  private static void selectWall() {
    // 벽 3개를 골랐으면
    if (s.size() == 3) {
      for (int[] elem : s) {
        // 지도에 벽 표시
        map[elem[0]][elem[1]] = 1;
      }
      // bfs로 바이러스를 퍼트린 후 안전구역 구함
      bfs();
      // 지도 초기화
      clearMap();
    }
    // 벽이 3개보다 적으면
    else {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          // 지도를 한칸씩 탐색하면서 현재 위치에 벽을 설치할 수 있는지 없는지 확인
          if (map[i][j] == 0 && !visited[i][j]) {
            // 벽을 설치할 수 있으면 해당 벽 선택
            s.add(new int[] {i, j});
            visited[i][j] = true;
            // 함수를 재귀적으로 실행
            selectWall();
            // 현재 위치에 벽을 설치한 후 탐색이 끝나면 벽 제거
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
