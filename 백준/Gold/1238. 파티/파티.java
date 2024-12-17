import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
  private static int N, M, X;
  private static int ans = Integer.MIN_VALUE;
  private static List<List<int[]>> l = new ArrayList<>();
  private static Queue<int[]> q = new LinkedList<>();
  private static int[] goDis, backDis;

  private static void back(int end) {
    for (int[] elem : l.get(end)) {
      q.add(elem);
    }
    while (!q.isEmpty()) {
      int[] info = q.poll();
      int current = info[0];
      int next = info[1];
      int cost = info[2];
      if (next == end) {
        continue;
      }
      if (backDis[next] == 0 || backDis[current] + cost < backDis[next]) {
        backDis[next] = backDis[current] + cost;
        for (int[] elem : l.get(next)) {
          if (backDis[elem[1]] == 0 || backDis[elem[0]] + elem[2] < backDis[elem[1]]) {
            q.add(elem);
          }
        }
      }
    }
  }

  private static void go(int start) {
    Arrays.fill(goDis, 0);
    for (int[] elem : l.get(start)) {
      q.add(elem);
    }
    while (!q.isEmpty()) {
      int[] info = q.poll();
      int current = info[0];
      int next = info[1];
      int cost = info[2];
      if (next == start) {
        continue;
      }
      if (goDis[next] == 0 || goDis[current] + cost < goDis[next]) {
        goDis[next] = goDis[current] + cost;
        for (int[] elem : l.get(next)) {
          if (goDis[elem[1]] == 0 || goDis[elem[0]] + elem[2] < goDis[elem[1]]) {
            q.add(elem);
          }
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1238.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    N = Integer.parseInt(num[0]);
    M = Integer.parseInt(num[1]);
    X = Integer.parseInt(num[2]);

    for (int i = 0; i < N + 1; i++) {
      List<int[]> tmp = new ArrayList<>();
      l.add(tmp);
    }

    for (int i = 0; i < M; i++) {
      String[] input = br.readLine().split(" ");
      int start = Integer.parseInt(input[0]);
      int end = Integer.parseInt(input[1]);
      int cost = Integer.parseInt(input[2]);
      l.get(start).add(new int[] { start, end, cost });
    }

    goDis = new int[N + 1];
    backDis = new int[N + 1];
    back(X);

    for (int i = 1; i < N + 1; i++) {
      go(i);
      ans = Math.max(ans, goDis[X] + backDis[i]);
    }

    System.out.println(ans);
    br.close();
  }
}
