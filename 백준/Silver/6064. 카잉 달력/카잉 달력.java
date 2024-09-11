import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static int t, m, n, x, y, ans;
  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/6064/6064.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    t = Integer.parseInt(br.readLine());
    for (int i = 0; i < t; i++) {
      String[] input = br.readLine().split(" ");
      m = Integer.parseInt(input[0]);
      n = Integer.parseInt(input[1]);
      x = Integer.parseInt(input[2]);
      y = Integer.parseInt(input[3]);

      while (true) {
        if (x > m * n) {
          ans = -1;
          break;
        }
        if ((((x - 1) % n) + 1) == y) {
          ans = x;
          break;
        }
        x = x + m;
      }
      System.out.println(ans);
    }
    br.close();
  }
}
