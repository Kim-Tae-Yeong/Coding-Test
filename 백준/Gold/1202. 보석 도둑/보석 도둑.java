import java.io.*;
import java.util.*;

public class Main {
  static int N, K;
  static List<int[]> jewels = new ArrayList<>();
  static int[] bags;
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

    jewels.sort((o1, o2) -> Integer.compare(o1[0], o2[0]));
    Arrays.sort(bags);

    int idx = 0;
    for (int i = 0; i < K; i++) {
      while (idx < N && jewels.get(idx)[0] <= bags[i]) {
        pq.offer(jewels.get(idx)[1]);
        idx++;
      }
      if (!pq.isEmpty()) {
        ans += pq.poll();
      }
    }

    System.out.println(ans);

    br.close();
  }
}
