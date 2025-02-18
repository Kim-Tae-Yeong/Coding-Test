import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int ans = 0, cnt = 0;
  static List<Integer> pos = new ArrayList<>();
  static List<Integer> neg = new ArrayList<>();

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1744.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());

    if (N == 1) {
      st = new StringTokenizer(br.readLine());
      System.out.println(Integer.parseInt(st.nextToken()));
    } else {
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        if (num == 0) {
          cnt++;
        } else if (num > 0) {
          pos.add(num);
        } else {
          neg.add(num);
        }
      }

      pos.sort((o1, o2) -> Integer.compare(o2, o1));
      for (int i = 0; i < pos.size() - 1; i += 2) {
        ans += Math.max(pos.get(i) * pos.get(i + 1), pos.get(i) + pos.get(i + 1));
      }
      if (pos.size() % 2 == 1) {
        ans += pos.get(pos.size() - 1);
      }

      neg.sort(null);
      for (int i = 0; i < neg.size() - 1; i += 2) {
        ans += neg.get(i) * neg.get(i + 1);
      }
      if (neg.size() % 2 == 1 && cnt == 0) {
        ans += neg.get(neg.size() - 1);
      }

      System.out.println(ans);
    }

    br.close();
  }
}