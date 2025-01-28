import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_13172 {
  static int M;
  static long N, S;
  static int mod = 1000000007;

  // 거듭제곱 분할 정복을 이용해 빠른 연산 가능
  // 구해야 하는 값 : num ^ 1000000005
  // num ^ 1000000005 = num x (num ^ 1000000004) = num x ((num ^ 500000002) ^ 2)
  static long modInverse(long num) {
    long result = 1;
    long power = mod - 2;
    while (power > 0) {
      // power가 홀수일 때 누적한 num을 결과에 곱해줌
      if ((power & 1) == 1) {
        result = (result * num) % mod;
      }
      // num을 거듭제곱 형태로 계산
      // power를 2로 나눠줌
      // 비트 연산을 사용하면 좀 더 빠르게 연산 가능
      num = (num * num) % mod;
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

      // N에 대한 역원을 구함
      long inverse = modInverse(N);
      ans = (ans + (S * inverse % mod)) % mod;
    }

    System.out.println(ans);

    br.close();
  }
}
