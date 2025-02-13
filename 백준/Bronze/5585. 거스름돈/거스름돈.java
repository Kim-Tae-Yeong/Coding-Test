import java.util.*;
import java.io.*;

public class Main {
  static int money, remain;
  static int ans = 0;
  static int[] rests = { 500, 100, 50, 10, 5, 1 };

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./5585.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    money = Integer.parseInt(st.nextToken());
    remain = 1000 - money;

    for (int rest : rests) {
      ans += (remain / rest);
      remain = remain % rest;
    }

    System.out.println(ans);

    br.close();
  }
}
