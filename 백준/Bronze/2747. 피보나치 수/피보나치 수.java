import java.io.*;
import java.util.*;

public class Main {
  static int n;

  // static int fibonacci(int n) {
  // if (n == 1 || n == 2) {
  // return 1;
  // } else {
  // return (fibonacci(n - 1) + fibonacci(n - 2));
  // }
  // }

  static int fibonacci(int n) {
    int[] num = new int[n + 1];
    num[0] = 0;
    num[1] = 1;
    if (n >= 2) {
      num[2] = 1;
    }
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

    System.out.println(fibonacci(n));
    br.close();
  }
}
