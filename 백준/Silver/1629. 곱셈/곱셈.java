import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static long a, b, c;

  public static long mod(long A, long B, long C) {
    if (B == 0) {
      return 1;
    }
    long half = mod(A, B / 2, C);
    half = (half * half) % C;

    if (B % 2 == 1) {
      half = (half * A) % C;
    }

    return half;
  }

  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/1629/1629.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] input = br.readLine().split(" ");
    a = Integer.parseInt(input[0]);
    b = Integer.parseInt(input[1]);
    c = Integer.parseInt(input[2]);
    
    System.out.println(mod(a, b, c));
    
    br.close();
  }
}
