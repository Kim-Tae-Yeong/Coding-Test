import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static long left = 0, right = Integer.MIN_VALUE, ans = 0;
  static long[] trees;

  static long getRemainTree(long size) {
    long sum = 0;
    for (long tree : trees) {
      if (tree > size) {
        sum += (tree - size);
      }
    }
    return sum;
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./2805.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    trees = new long[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      trees[i] = Integer.parseInt(st.nextToken());
      right = Math.max(right, trees[i]);
    }

    while (left <= right) {
      long mid = (left + right) / 2;
      long remain = getRemainTree(mid);

      if (remain >= M) {
        ans = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    System.out.println(ans);

    br.close();
  }
}
