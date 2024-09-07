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
  // 현재 수열을 저장
  public static Stack<Integer> s = new Stack<>();
  // 조건에 맞는 수열을 저장
  public static Set<Stack<Integer>> ans = new HashSet<>();
  // 수열을 만들 때 num 배열에서 원소를 중복해서 뽑으면 안되기 때문에 visited 배열 사용
  public static boolean[] visited;

  public static void combination () {
    if (s.size() == m) {
      // 현재 만든 수열이 기존에 만든 수열이 아니라면
      if (!ans.contains(s)) {
        // 정답에 현재 수열을 저장
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
      for (int i = 0; i < n; i++) {
        // num 배열에서 선택되지 않은 원소만 수열에 넣음
        if (!visited[i]) {
          s.add(num.get(i));
          visited[i] = true;
          combination();
          // 길이가 m인 수열이 만들어지면 맨 마지막에 넣은 원소 제거
          s.pop();
          visited[i] = false;
        }
      }
    }
  }
  public static void main (String[] args) throws IOException{
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/15663/15663.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] input = br.readLine().split(" ");
    n = Integer.parseInt(input[0]);
    m = Integer.parseInt(input[1]);
    visited = new boolean[n];

    String[] tmp = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      num.add(Integer.parseInt(tmp[i]));
    }
    num.sort(null);

    combination();

    br.close();
  }
}
