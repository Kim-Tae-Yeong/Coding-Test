import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static long n;
  static long mod = 1000000007;

  static long fibonacci(long n) {
    if (n == 0) {
      return 0;
    }
    long[][] result = matrixPower(new long[][] { { 1, 1 }, { 1, 0 } }, n - 1);
    return result[0][0];
  }

  static long[][] matrixPower(long[][] matrix, long exp) {
    long[][] result = { { 1, 0 }, { 0, 1 } };
    long[][] base = matrix;

    while (exp > 0) {
      if ((exp & 1) == 1) {
        result = matrixMultiply(result, base);
      }
      base = matrixMultiply(base, base);
      exp >>= 1;
    }
    return result;
  }

  static long[][] matrixMultiply(long[][] a, long[][] b) {
    return new long[][] {
        { (a[0][0] * b[0][0] + a[0][1] * b[1][0]) % mod, (a[0][0] * b[0][1] + a[0][1] * b[1][1]) % mod },
        { (a[1][0] * b[0][0] + a[1][1] * b[1][0]) % mod, (a[1][0] * b[0][1] + a[1][1] * b[1][1]) % mod }
    };
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./11444.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Long.parseLong(st.nextToken());

    System.out.println(fibonacci(n));

    br.close();
  }
}
