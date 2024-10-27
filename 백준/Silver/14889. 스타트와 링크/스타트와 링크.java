import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
  private static int n;
  private static int ans = Integer.MAX_VALUE;
  private static int[][] board;
  private static boolean[] checked;
  private static Stack<Integer> team1 = new Stack<>();

  private static void findAnswer(int x, int length, int start) {
    if (length < (n / 2)) {
      for (int i = start; i < n + 1; i++) {
        team1.add(i);
        checked[i] = true;
        findAnswer(i, length + 1, i + 1);
        team1.pop();
        checked[i] = false;
      }
    } else {
      int team1_score = 0;
      int team2_score = 0;
      List<Integer> team2 = new ArrayList<>();
      for (int i = 1; i < n + 1; i++) {
        if (!checked[i]) {
          team2.add(i);
        }
      }
      for (int i = 0; i < (n / 2); i++) {
        for (int j = 0; j < (n / 2); j++) {
          team1_score += board[team1.get(i)][team1.get(j)];
          team2_score += board[team2.get(i)][team2.get(j)];
        }
      }
      ans = Math.min(ans, Math.abs(team1_score - team2_score));
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new
    // FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/14889/14889.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    board = new int[n + 1][n + 1];
    checked = new boolean[n + 1];

    for (int i = 1; i < n + 1; i++) {
      String[] input = br.readLine().split(" ");
      for (int j = 0; j < n; j++) {
        board[i][j + 1] = Integer.parseInt(input[j]);
      }
    }

    team1.add(1);
    checked[1] = true;
    findAnswer(1, 1, 2);

    System.out.println(ans);

    br.close();
  }
}
