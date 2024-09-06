import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
  public static int n, m;
  public static Stack<Integer> ans = new Stack<>();

  public static void combination (int start, int cnt) {
    if (ans.size() == cnt) {
      for (int i = 0; i < cnt; i++) {
        System.out.print(ans.get(i));
        if (i != cnt - 1) {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
    else {
      for (int i = start; i < n + 1; i++) {
        ans.add(i);
        combination(i + 1, cnt);
        ans.pop();
      }
    }
  }
  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/15650/15650.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    String[] input = br.readLine().split(" ");
    n = Integer.parseInt(input[0]);
    m = Integer.parseInt(input[1]);

    combination(1, m);
    
    br.close();
  } 
}
