import java.io.*;
import java.util.*;

public class Main_1202 {
  static int N, K;
  // 보석 정보 저장
  static List<int[]> jewels = new ArrayList<>();
  static int[] bags;
  // 현재 가방 무게까지 담을 수 있는 보석을 가치 기준 내림차순 정렬
  static PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
  static long ans = 0;

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1202.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    bags = new int[K];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int M = Integer.parseInt(st.nextToken());
      int V = Integer.parseInt(st.nextToken());
      jewels.add(new int[] { M, V });
    }

    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      int C = Integer.parseInt(st.nextToken());
      bags[i] = C;
    }

    // 보석을 무게 기준 오름차순 정렬
    jewels.sort((o1, o2) -> Integer.compare(o1[0], o2[0]));
    // 가방도 오름차순 정렬
    // 가방의 무게가 가벼운 가방부터 그 가방에 넣을 수 있는 최대 가치의 보석을 넣어야 함
    Arrays.sort(bags);

    // 현재 가방 무게에 담을 수 있는 보석들은 가방의 무게가 커지면 똑같이 담을 수 있음
    // 즉, 가방의 무게가 커지면 직전 가방에는 담지 못하지만 현재 가방에는 담을 수 있는 보석들만 우선순위 큐에 넣으면 됨
    // ex) 가방 무게 5 : (1, 2, 3) -> 가방 무게 10 : (1, 2, 3)(중복) + (6, 7, 9)
    int idx = 0;
    // 가방의 무게를 하나씩 확인
    for (int i = 0; i < K; i++) {
      // 현재 가방의 무게까지 넣을 수 있는 모든 보석을 우선순위 큐에 집어넣음
      while (idx < N && jewels.get(idx)[0] <= bags[i]) {
        pq.offer(jewels.get(idx)[1]);
        idx++;
      }
      // 우선순위 큐가 비어있지 않으면
      // 즉, 가방에 넣을 수 있는 보석이 있다면
      // 가치가 가장 큰 보석을 가방에 넣음
      if (!pq.isEmpty()) {
        ans += pq.poll();
      }
    }

    System.out.println(ans);

    br.close();
  }
}
