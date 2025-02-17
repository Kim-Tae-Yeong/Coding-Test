import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  static int N, L;
  static int ans = 0;
  static List<Integer> l = new ArrayList<>();
  static boolean check = false;
  static double end;

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1449.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    L = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      l.add(Integer.parseInt(st.nextToken()));
    }

    l.sort(null);

    for (int i = 0; i < N; i++) {
      if (l.get(i) > end) {
        ans++;
        end = l.get(i) - 0.5 + L;
      }
    }

    System.out.println(ans);

    br.close();
  }
}
