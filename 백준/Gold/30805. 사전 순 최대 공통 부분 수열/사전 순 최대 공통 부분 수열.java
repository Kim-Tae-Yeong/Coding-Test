import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {
  private static int N, M, K;
  private static int[] A, B;
  private static int[][] dp;
  private static List<List<Stack<Integer>>> l = new ArrayList<>();
  private static StringBuilder sb = new StringBuilder();

  private static int check(Stack<Integer> s1, Stack<Integer> s2) {
    int length = Math.min(s1.size(), s2.size());
    for (int i = 0; i < length; i++) {
      if (s1.get(i) > s2.get(i)) {
        return 0;
      } else if (s1.get(i) < s2.get(i)) {
        return 1;
      }
    }
    if (length == s1.size()) {
      return 1;
    } else {
      return 0;
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./30805.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    A = new int[N];

    String[] a = br.readLine().split(" ");
    for (int i = 0; i < N; i++) {
      A[i] = Integer.parseInt(a[i]);
    }

    M = Integer.parseInt(br.readLine());
    B = new int[M];

    String[] b = br.readLine().split(" ");
    for (int i = 0; i < M; i++) {
      B[i] = Integer.parseInt(b[i]);
    }

    dp = new int[M + 1][N + 1];
    for (int i = 0; i < M + 1; i++) {
      List<Stack<Integer>> tmp1 = new ArrayList<>();
      l.add(tmp1);
      for (int j = 0; j < N + 1; j++) {
        Stack<Integer> tmp2 = new Stack<>();
        l.get(i).add(tmp2);
      }
    }

    for (int i = 1; i < M + 1; i++) {
      for (int j = 1; j < N + 1; j++) {
        if (B[i - 1] == A[j - 1]) {
          Stack<Integer> before = l.get(i - 1).get(j - 1);
          Stack<Integer> tmp = new Stack<>();
          while (true) {
            if (before.isEmpty() || B[i - 1] <= before.peek()) {
              break;
            }
            tmp.add(before.pop());
          }
          for (Integer elem : before) {
            l.get(i).get(j).add(elem);
          }
          l.get(i).get(j).add(B[i - 1]);
          dp[i][j] = l.get(i).get(j).size();
          while (!tmp.isEmpty()) {
            before.add(tmp.pop());
          }
        } else {
          Stack<Integer> tmp1 = l.get(i - 1).get(j);
          Stack<Integer> tmp2 = l.get(i).get(j - 1);
          int num = check(tmp1, tmp2);
          if (num == 0) {
            for (Integer elem : tmp1) {
              l.get(i).get(j).add(elem);
            }
            dp[i][j] = tmp1.size();
          } else {
            for (Integer elem : tmp2) {
              l.get(i).get(j).add(elem);
            }
            dp[i][j] = tmp2.size();
          }
        }
      }

    }

    // for (int[] row : dp) {
    // System.out.println(Arrays.toString(row));
    // }

    // for (List<Stack<Integer>> elem : l) {
    // System.out.println(elem);
    // }

    K = dp[M][N];
    System.out.println(K);
    if (K != 0) {
      for (Integer elem : l.get(M).get(N)) {
        sb.append(elem);
        sb.append(" ");
      }
      System.out.println(sb.toString());
    }

    br.close();
  }
}
