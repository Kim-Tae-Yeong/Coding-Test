import java.io.*;
import java.util.*;

public class Main_2660 {
  static int n, u, v, score, cnt;
  // distance[i][j] : i번째 회원과 j번째 사람과의 관계(거리)
  static int[][] distance;
  static List<Integer> l = new ArrayList<>();

  static void floydWarshall() {
    // 경유지 선택
    for (int i = 1; i < n + 1; i++) {
      // 시작 지점
      for (int j = 1; j < n + 1; j++) {
        // 도착 지점
        for (int k = 1; k < n + 1; k++) {
          // 시작 지점 or 도착 지점이 경유지면 continue
          if (i == j || i == k) {
            continue;
          }
          // 시작 지점에서 경유지를 가는 방법이 없으면 or 경유지에서 도착 지점을 가는 방법이 없으면 continue
          if (distance[j][i] == Integer.MAX_VALUE || distance[i][k] == Integer.MAX_VALUE) {
            continue;
          }
          // 직접 가는 방법과 거쳐서 가는 방법 중 더 작은 방법 선택
          distance[j][k] = Math.min(distance[j][k], distance[j][i] + distance[i][k]);
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./2660.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    distance = new int[n + 1][n + 1];
    for (int i = 0; i < n + 1; i++) {
      // 초기에는 각 사람들간의 거리를 무한대로 설정
      Arrays.fill(distance[i], Integer.MAX_VALUE);
      // 자기 자신과의 거리는 0으로 설정
      distance[i][i] = 0;
    }
    while (true) {
      st = new StringTokenizer(br.readLine());
      u = Integer.parseInt(st.nextToken());
      if (u == -1) {
        break;
      }
      v = Integer.parseInt(st.nextToken());

      distance[u][v] = 1;
      distance[v][u] = 1;

    }

    floydWarshall();

    score = Integer.MAX_VALUE;
    cnt = 0;
    for (int i = 1; i < n + 1; i++) {
      int tmp = Integer.MIN_VALUE;
      for (int j = 1; j < n + 1; j++) {
        // i번째 회원 기준으로 각 회원과의 거리 중 가장 먼 거리를 해당 회원의 점수로 설정
        tmp = Math.max(tmp, distance[i][j]);
      }
      if (tmp < score) {
        score = tmp;
        cnt = 1;
        l = new ArrayList<>();
        l.add(i);
      } else if (tmp == score) {
        cnt++;
        l.add(i);
      }
    }

    l.sort(null);

    System.out.println(score + " " + cnt);
    for (int num : l) {
      System.out.print(num + " ");
    }
    br.close();
  }
}
