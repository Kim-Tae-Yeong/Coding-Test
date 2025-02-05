import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_9466 {
  static int t, n, ans;
  static int[] num;
  static boolean[] visited;
  static Deque<Integer> d = new ArrayDeque<>();

  static void dfs(int current) {
    // 현재 인원을 deque에 넣음
    d.add(current);
    // 방문 여부 갱신
    visited[current] = true;
    // 현재 인원이 선택한 인원을 방문하지 않은 경우 dfs 실행
    int next = num[current];
    if (!visited[next]) {
      dfs(next);
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./9466.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    t = Integer.parseInt(st.nextToken());

    for (int i = 0; i < t; i++) {
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      num = new int[n + 1];
      visited = new boolean[n + 1];

      st = new StringTokenizer(br.readLine());
      for (int j = 1; j < n + 1; j++) {
        num[j] = Integer.parseInt(st.nextToken());
      }

      ans = 0;

      for (int j = 1; j < n + 1; j++) {
        // 방문하지 않은 인원의 경우
        if (!visited[j]) {
          // 해당 인원에서 dfs를 시작하면서 deque에 인원을 넣음
          d = new ArrayDeque<>();
          dfs(j);
          // deque의 마지막 인원이 선택한 학생을 기록
          int start = num[d.peekLast()];
          while (true) {
            // deque의 모든 인원을 확인하면 break
            if (d.isEmpty()) {
              break;
            }
            // deque의 앞부터 인원을 확인하면서 위에서 기록한 인원이 deque에 있는지(사이클이 있는지) 확인
            int end = d.pollFirst();
            // 사이클이 발견되면 break
            if (start == end) {
              break;
            }
            // 사이클이 발견되기 전 인원은 팀이 없는 인원이기 때문에 정답을 하나씩 늘림
            ans++;
          }
        }
      }

      System.out.println(ans);
    }
    br.close();
  }
}
