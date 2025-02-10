import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2805 {
  static int N, M;
  // left : 나무 최소 길이
  // right : 나무 최대 길이
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
      // 나무 중간 길이를 구함
      long mid = (left + right) / 2;
      // 중간 길이로 나무들을 짤랐을 때 얼마나 얻을 수 있는지 확인
      long remain = getRemainTree(mid);

      // M보다 많거나 같게 얻을 수 있으면
      if (remain >= M) {
        // 현재 길이 저장
        ans = mid;
        // 최소 길이를 늘림
        left = mid + 1;
      }
      // M보다 적게 얻으면
      else {
        // 최대 길이를 줄임
        right = mid - 1;
      }
    }

    System.out.println(ans);

    br.close();
  }
}
