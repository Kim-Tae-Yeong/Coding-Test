import java.io.*;
import java.util.*;

public class Main {
  static int n, u, v, score, cnt;
  static int[][] distance;
  static List<Integer> l = new ArrayList<>();

  static void floydWarshall() {
    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < n + 1; j++) {
        for (int k = 1; k < n + 1; k++) {
          if (i == j || i == k) {
            continue;
          }
          if (distance[j][i] == Integer.MAX_VALUE || distance[i][k] == Integer.MAX_VALUE) {
            continue;
          }
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
      Arrays.fill(distance[i], Integer.MAX_VALUE);
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
