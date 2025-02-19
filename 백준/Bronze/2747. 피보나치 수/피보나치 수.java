import java.io.*;
import java.util.*;

public class Main_2747 {
  static int n;

  // 피보나치 수를 재귀로 구하면 시간 초과
  static int fibonacci_recursive(int n) {
    if (n == 1 || n == 2) {
      return 1;
    } else {
      return (fibonacci_recursive(n - 1) + fibonacci_recursive(n - 2));
    }
  }

  static int fibonacci_dp(int n) {
    // 배열에 f(1)과 f(2) 값을 먼저 기록
    int[] num = new int[n + 1];
    num[0] = 0;
    num[1] = 1;
    if (n >= 2) {
      num[2] = 1;
    }
    // f(3)부터는 배열에 저장된 값을 이용해서 한번에 계산 & 배열에 기록
    for (int i = 3; i < n + 1; i++) {
      num[i] = num[i - 1] + num[i - 2];
    }
    return num[n];
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./2747.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());

    System.out.println(fibonacci_dp(n));
    br.close();
  }
}
