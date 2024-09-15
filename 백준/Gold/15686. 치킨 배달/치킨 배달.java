import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {
  public static int n, m, tmp;
  public static List<int[]> house = new ArrayList<>();
  public static List<int[]> chicken = new ArrayList<>();
  public static Stack<int[]> s = new Stack<>();
  public static int ans = Integer.MAX_VALUE;

  public static void findMinDistance (int start) {
    if (s.size() == m) {
      tmp = 0;
      for (int[] h : house) {
        int minDistance = Integer.MAX_VALUE;
        for (int[] c : s) {
          minDistance = Math.min(minDistance, Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]));
        }
        tmp = tmp + minDistance;
      }
      ans = Math.min(ans, tmp);
    }
    else {
      for (int i = start; i < chicken.size(); i++) {
        s.add(new int[] {chicken.get(i)[0], chicken.get(i)[1]});
        findMinDistance(i + 1);
        s.pop();
      }
    }
  }

  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/15686/15686.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    n = Integer.parseInt(num[0]);
    m = Integer.parseInt(num[1]);

    for (int i = 0; i < n; i++) {
      String[] input = br.readLine().split(" ");
      for (int j = 0; j < n; j++) {
        if (Integer.parseInt(input[j]) == 1) {
          house.add(new int[] {i, j});
        }
        else if (Integer.parseInt(input[j]) == 2) {
          chicken.add(new int[] {i, j});
        }
      }
    }

    findMinDistance(0);

    System.out.println(ans);

    br.close();
  }
}
