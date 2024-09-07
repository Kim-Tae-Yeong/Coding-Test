import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Main {
  public static int n, m;
  public static List<Integer> num = new ArrayList<>();
  public static Stack<Integer> s = new Stack<>();
  public static Set<Stack<Integer>> ans = new HashSet<>();

  public static void combination (int idx) {
    if (s.size() == m) {
      if (!ans.contains(s)) {
        ans.add(s);
        for (int i = 0; i < m; i++) {
          System.out.print(s.get(i));
          if (i != m - 1) {
            System.out.print(" ");
          }
        }
        System.out.println();
      }
    }
    else {
      for (int i = idx; i < n; i++) {
        s.add(num.get(i));
        combination(i);
        s.pop();
      }    
    }
  }

  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/15666/15666.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] input = br.readLine().split(" ");
    n = Integer.parseInt(input[0]);
    m = Integer.parseInt(input[1]);

    String[] tmp = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      num.add(Integer.parseInt(tmp[i]));
    }
    num.sort(null);

    combination(0);

    br.close();
  }
}
