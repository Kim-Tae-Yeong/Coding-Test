import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_14888 {
  static int N;
  static long maxAns = Integer.MIN_VALUE, minAns = Integer.MAX_VALUE;
  static Long[] num;
  static int[] cal;
  static Stack<Long> s = new Stack<>();

  static void backTracking(int idx) {
    // 모든 숫자를 사용했으면 최댓값, 최솟값 갱신
    if (idx == N - 1) {
      long ans = s.pop();
      maxAns = Math.max(maxAns, ans);
      minAns = Math.min(minAns, ans);
      return;
    }
    // stack에서 현재까지 계산된 수를 뻄
    long current = s.pop();
    // 다음 숫자
    long next = num[idx + 1];
    // 각 연산 기호에 대해 탐색
    for (int i = 0; i < 4; i++) {
      // 남아있는 연산 기호가 있으면
      if (cal[i] != 0) {
        // 연산 기호 하나 사용
        cal[i]--;
        // 연산을 실시한 후 stack에 넣음
        if (i == 0) {
          s.add(current + next);
        } else if (i == 1) {
          s.add(current - next);
        } else if (i == 2) {
          s.add(current * next);
        } else if (i == 3) {
          s.add(current / next);
        }
        // backTracking 실시
        backTracking(idx + 1);
        // backTracking 후 해당 연산 기호를 원래대로 돌려놓음
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
