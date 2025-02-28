import java.io.*;
import java.util.*;

public class Main {
  static int N;
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

    if (N == 1) {
      ans = 0;
    } else {
      while (true) {
        int tmp = pq.poll() + pq.poll();
        ans += tmp;
        if (pq.isEmpty()) {
          break;
        }
        pq.add(tmp);
      }
    }

    System.out.println(ans);

    br.close();
  }
}
