import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
  public static int n, m;
  public static List<Integer> num = new ArrayList<>();
  public static Stack<Integer> s = new Stack<>();
  public static boolean[] contain;

  public static void combination () {
    if (s.size() == m) {
      for (int i = 0; i < m; i++) {
        System.out.print(s.get(i));
        if (i != m - 1) {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
    else {
      for (int i = 0; i < n; i++) {
        if (!contain[i]) {
          s.add(num.get(i));
          contain[i] = true;
          combination();
          s.pop();
          contain[i] = false;
        }
      }
    }
  }
  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/15654/15654.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    String[] input = br.readLine().split(" ");
    n = Integer.parseInt(input[0]);
    m = Integer.parseInt(input[1]);
    String[] tmp = br.readLine().split(" ");

    for (int i = 0; i < n; i++) {
      num.add(Integer.parseInt(tmp[i]));
    }
    num.sort(null);

    contain = new boolean[n];

    combination();
    br.close();
  }  
}
