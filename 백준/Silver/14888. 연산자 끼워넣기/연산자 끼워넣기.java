import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static long maxAns = Integer.MIN_VALUE, minAns = Integer.MAX_VALUE;
  static Long[] num;
  static int[] cal;
  static Stack<Long> s = new Stack<>();

  static void backTracking(int idx) {
    if (idx == N - 1) {
      long ans = s.pop();
      maxAns = Math.max(maxAns, ans);
      minAns = Math.min(minAns, ans);
      return;
    }
    long current = s.pop();
    long next = num[idx + 1];
    for (int i = 0; i < 4; i++) {
      if (cal[i] != 0) {
        cal[i]--;
        if (i == 0) {
          s.add(current + next);
        } else if (i == 1) {
          s.add(current - next);
        } else if (i == 2) {
          s.add(current * next);
        } else if (i == 3) {
          s.add(current / next);
        }
        backTracking(idx + 1);
        cal[i]++;
      }
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./14888.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    num = new Long[N];
    cal = new int[4];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      num[i] = Long.parseLong(st.nextToken());
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < 4; i++) {
      cal[i] = Integer.parseInt(st.nextToken());
    }

    s.add(num[0]);
    backTracking(0);

    System.out.println(maxAns);
    System.out.println(minAns);

    br.close();
  }
}
