import java.io.*;
import java.util.*;

public class Main {
  static int N;
  static long[][] ans;

  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new FileReader("./2193.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    ans = new long[N + 1][2];
    ans[1][0] = 0;
    ans[1][1] = 1;

    for (int i = 2; i < N + 1; i++) {
      ans[i][0] = ans[i - 1][0] + ans[i - 1][1];
      ans[i][1] = ans[i - 1][0];
    }

    System.out.println(ans[N][0] + ans[N][1]);

    br.close();
  }
}
