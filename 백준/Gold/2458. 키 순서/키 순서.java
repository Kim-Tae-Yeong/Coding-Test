import java.io.*;
import java.util.*;

public class Main_2458 {
  static int N, M, ans;
  static int[][] map;

  static void floydWarshall() {
    for (int i = 1; i < N + 1; i++) {
      map[i][i] = 0;
      for (int j = 1; j < N + 1; j++) {
        for (int k = 1; k < N + 1; k++) {
          if (i == j || i == k) {
            continue;
          }
          if (map[j][i] != Integer.MAX_VALUE && map[i][k] != Integer.MAX_VALUE) {
            map[j][k] = Math.min(map[j][k], map[j][i] + map[i][k]);
          }
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./2458.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N + 1][N + 1];
    for (int i = 0; i < N + 1; i++) {
      Arrays.fill(map[i], Integer.MAX_VALUE);
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      // 키가 작은 사람에서 큰 사람 방향으로만 이동할 수 있다고 가정
      map[u][v] = 1;
    }

    // 플로이드 - 워셜을 사용해 각 번호의 학생이 이동할 수 있는 학생들의 번호 확인
    floydWarshall();

    // 각 번호(i)의 학생들에 대해
    ans = N;
    for (int i = 1; i < N + 1; i++) {
      // i번째 학생에서 j번째 학생으로 이동할 수 없을 때(i번째 학생이 j번째 학생보다 작은지 모름)
      // j번째 학생에서 i번째 학생으로 이동할 수 없으면(j번째 학생이 i번째 학생보다 작은지 모름)
      // 두 학생간의 키를 비교할 수 없기 때문에 현재 학생의 정확한 순위를 매길 수 없음
      for (int j = 1; j < N + 1; j++) {
        if (map[i][j] == Integer.MAX_VALUE && map[j][i] == Integer.MAX_VALUE) {
          ans--;
          break;
        }
      }
    }

    System.out.println(ans);

    br.close();
  }
}
