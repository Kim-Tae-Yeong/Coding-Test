import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main_14889 {
  private static int n;
  private static int ans = Integer.MAX_VALUE;
  private static int[][] board;
  private static boolean[] checked;
  private static Stack<Integer> team1 = new Stack<>();

  private static void findAnswer(int x, int length, int start) {
    // 스타트 팀이 n / 2명보다 적으면
    if (length < (n / 2)) {
      for (int i = start; i < n + 1; i++) {
        // 한명씩 스타트 팀에 넣음
        team1.add(i);
        checked[i] = true;
        // 이후 함수 다시 실행
        findAnswer(i, length + 1, i + 1);
        // 직전에 넣은 사람을 뺌
        team1.pop();
        checked[i] = false;
      }
    }
    // 스타트 팀이 n / 2명이면
    else {
      int team1_score = 0;
      int team2_score = 0;
      // 스타트 팀에 들어가지 않은 사람들을 링크팀에 넣음
      List<Integer> team2 = new ArrayList<>();
      for (int i = 1; i < n + 1; i++) {
        if (!checked[i]) {
          team2.add(i);
        }
      }
      // 각 팀 점수 계산
      for (int i = 0; i < (n / 2); i++) {
        for (int j = 0; j < (n / 2); j++) {
          team1_score += board[team1.get(i)][team1.get(j)];
          team2_score += board[team2.get(i)][team2.get(j)];
        }
      }
      // 기존 각 팀의 차이와 현재 팀간의 차이 중 더 작은 값을 구함
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

    // 스타트팀에 1번을 넣음
    team1.add(1);
    checked[1] = true;
    findAnswer(1, 1, 2);

    System.out.println(ans);

    br.close();
  }
}
