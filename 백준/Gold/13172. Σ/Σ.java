import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  static int M;
  static long N, S;
  static int mod = 1000000007;

  static long modInverse(long num) {
    long result = 1;
    long power = mod - 2;
    while (power > 0) {
      if ((power & 1) == 1) {
        result = (result * num) % mod;
      }
      num = (1L * num * num) % mod;
      power >>= 1;
    }
    return result;
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./13172.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    M = Integer.parseInt(st.nextToken());
    long ans = 0;

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      N = Long.parseLong(st.nextToken());
      S = Long.parseLong(st.nextToken());

      long inverse = modInverse(N);
      ans = (ans + (S * inverse % mod)) % mod;
    }

    System.out.println(ans);

    br.close();
  }
}
