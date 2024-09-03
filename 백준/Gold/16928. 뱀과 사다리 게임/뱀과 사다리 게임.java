import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
  static int n, m;
  static List<List<Integer>> ladders = new ArrayList<>();
  static List<List<Integer>> snakes = new ArrayList<>();
  static int[] dp = new int[101];

  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/16928/16928.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] input = br.readLine().split(" ");
    n = Integer.parseInt(input[0]);
    m = Integer.parseInt(input[1]);
    
    for (int i = 0; i < n; i++) {
      String[] ladder = br.readLine().split(" ");
      List<Integer> ladderElem = new ArrayList<>();
      ladderElem.add(Integer.parseInt(ladder[0]));
      ladderElem.add(Integer.parseInt(ladder[1]));
      ladders.add(ladderElem);
    }

    for (int i = 0; i < m; i++) {
      String[] snake = br.readLine().split(" ");
      List<Integer> snakeElem = new ArrayList<>();
      snakeElem.add(Integer.parseInt(snake[0]));
      snakeElem.add(Integer.parseInt(snake[1]));
      snakes.add(snakeElem);
    }

    Queue<List<Integer>> infos = new LinkedList<>();
    for (int i = 2; i < 8; i++) {
      List<Integer> info = new ArrayList<>();
      info.add(i);
      info.add(1);
      infos.add(info);
      dp[i] = 1;
    }

    while (infos.size() != 0) {
      List<Integer> currentInfo = infos.poll();
      int pos = currentInfo.get(0);
      int cnt = currentInfo.get(1);
      if (pos == 100) {
        continue;
      }
      boolean check = false;

      for (List<Integer> elem : ladders) {
        if (elem.get(0).equals(pos)) {
          List<Integer> ladderInfo = new ArrayList<>();
          ladderInfo.add(elem.get(1));
          ladderInfo.add(cnt);
          infos.add(ladderInfo);
          dp[elem.get(1)] = cnt;
          check = true;
          break;
        }
      }
      
      
      for (List<Integer> elem : snakes) {
        if (elem.get(0).equals(pos)) {
          List<Integer> snakeInfo = new ArrayList<>();
          snakeInfo.add(elem.get(1));
          snakeInfo.add(cnt);
          infos.add(snakeInfo);
          dp[elem.get(1)] = cnt;
          check = true;
          break;
        }
      }
      
      if (!check) {
        for (int i = 1; i < 7; i++) {
          if (pos + i <= 100) {
            if(dp[pos + i] == 0 || (cnt + 1) < dp[pos + i]) {
              List<Integer> diceInfo = new ArrayList<>();
              diceInfo.add(pos + i);
              diceInfo.add(cnt + 1);
              infos.add(diceInfo);
              dp[pos + i] = cnt + 1;
            }
          }
        }
      }
    }

    System.out.println(dp[100]);

    br.close();
  }
}
