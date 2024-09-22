import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.TreeMap;

public class Main {
  private static int n, d, k, c, extra, ans;
  private static List<Integer> sushi = new ArrayList<>();
  private static Deque<Integer> deque = new ArrayDeque<>();
  private static TreeMap<Integer, Integer> cnt = new TreeMap<>();
  public static void main (String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/2531/2531.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    n = Integer.parseInt(num[0]);
    d = Integer.parseInt(num[1]);
    k = Integer.parseInt(num[2]);
    c = Integer.parseInt(num[3]);

    for (int i = 0; i < n; i++) {
      sushi.add(Integer.parseInt(br.readLine()));
    }

    extra = 1;
    ans = 0;
    for (int i = 0; i < 2 * n; i++) {
      if (deque.size() == k) {
        int preSushi = deque.removeFirst();
        cnt.put(preSushi, cnt.get(preSushi) - 1);
        if (cnt.get(preSushi) == 0) {
          cnt.remove(preSushi);
          if (preSushi == c) {
            extra = 1;
          }
        }
      }
      int nextSushi = sushi.get(i % n);
      if (nextSushi == c) {
        extra = 0;
      }
      cnt.put(nextSushi, cnt.getOrDefault(nextSushi, 0) + 1);
      deque.add(nextSushi);
      ans = Math.max(ans, cnt.size() + extra);
    }

    System.out.println(ans);
    br.close();
  }  
}
