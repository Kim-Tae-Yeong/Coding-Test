import java.io.*;
import java.util.*;

public class Main_1715 {
  static int N;
  // 카드 수가 적은 카드를 우선순위 큐 앞쪽에 배치
  // 앞쪽에서 비교를 한 카드는 이후에도 계속 비교를 해야하기 때문
  // A B C -> (A + B) + ((A + B) + C)
  static PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2));
  static int ans = 0;

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1715.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      pq.add(Integer.parseInt(st.nextToken()));
    }

    // 카드가 1 묶음이면 이미 정렬되어 있기 때문에 비교할 필요가 없음
    if (N == 1) {
      ans = 0;
    } else {
      while (true) {
        // 카드 수가 가장 적은 두 카드를 꺼내서 정렬함
        int tmp = pq.poll() + pq.poll();
        // 총 비교 횟수 증가
        ans += tmp;
        // 카드를 다 정렬했으면 break
        if (pq.isEmpty()) {
          break;
        }
        // 카드가 남아있다면 정렬한 카드 뭉치를 우선순위 큐에 집어넣음
        pq.add(tmp);
      }
    }

    System.out.println(ans);

    br.close();
  }
}
