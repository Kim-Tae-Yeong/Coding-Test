import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
  public static int n, m;
  public static Stack<Integer> s = new Stack<>();

  public static void combination (int start, int cnt) {
    if (s.size() == cnt) {
      for (int i = 0; i < cnt; i++) {
        System.out.print(s.get(i));
        if (i != cnt - 1) {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
    else {
      for (int i = start; i < n + 1; i++) {
        s.add(i);
        combination(i, cnt);
        s.pop();
      }
    }
  }
  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/15652/15652.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] input = br.readLine().split(" ");
    n = Integer.parseInt(input[0]);
    m = Integer.parseInt(input[1]);

    combination(1, m);

    br.close();
  }  
}
