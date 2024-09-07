import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
  public static long a, b;
  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/16953/16953.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] input = br.readLine().split(" ");
    a = Integer.parseInt(input[0]);
    b = Integer.parseInt(input[1]);
    long ans = -1;

    Queue<List<Long>> q = new LinkedList<>();
    List<Long> start = new ArrayList<>();
    start.add(a);
    start.add(Integer.toUnsignedLong(1));
    q.add(start);

    while (q.size() != 0) {
      List<Long> info = q.poll();
      long num = info.get(0);
      long cnt = info.get(1);

      List<Long> first = new ArrayList<>();
      first.add(2 * num);
      first.add(cnt + 1);
      if (2 * num == b) {
        ans = cnt + 1;
        break;
      }
      else if (2 * num < b) {
        q.add(first);
      }

      List<Long> second = new ArrayList<>();
      second.add(num * 10 + 1);
      second.add(cnt + 1);
      if (num * 10 + 1 == b) {
        ans = cnt + 1;
        break;
      }
      else if (num * 10 + 1 < b) {
        q.add(second);
      }
    }

    System.out.println(ans);
    br.close();
  }
}
