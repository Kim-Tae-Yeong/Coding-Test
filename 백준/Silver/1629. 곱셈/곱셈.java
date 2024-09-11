import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static long A, B, C, ans;

  public static long cal (long a, long b, long c) {
    if (b == 0) {
      return 1;
    }
    long half = cal(a, b / 2, c);
    half = (half * half) % c;
    if (b % 2 == 1) {
      half = (half * a) % c;
    }
    return half;
  }

  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/1629/1629.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    A = Integer.parseInt(num[0]);
    B = Integer.parseInt(num[1]);
    C = Integer.parseInt(num[2]);

    ans = cal(A, B, C);
    System.out.println(ans);
    br.close();
  }
}