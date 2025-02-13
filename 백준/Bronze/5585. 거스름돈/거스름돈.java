import java.util.*;
import java.io.*;

public class Main_5585 {
  static int money, remain;
  static int ans = 0;
  static int[] rests = { 500, 100, 50, 10, 5, 1 };

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./5585.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    money = Integer.parseInt(st.nextToken());
    remain = 1000 - money;

    // 600엔을 (100엔 * 6개)로 줄 수 있지만 (500엔 + 100엔)으로도 줄 수 있음
    // 즉 현재 거스름돈을 가장 큰 단위의 잔돈으로 나눔
    // 이후 나머지를 다시 다음 큰 단위의 동전으로 나누는 방식으로 최소 개수를 구함
    for (int rest : rests) {
      ans += (remain / rest);
      remain = remain % rest;
    }

    System.out.println(ans);

    br.close();
  }
}
