import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

  public static List<List<Integer>> board;
  public static int pos;

  public static void bfs (List<List<Integer>> board, int finish) {
    // 각 숫자를 만들 수 있는 최소 연산 횟수 기록
    int[] visited = new int[10000];
    visited[board.get(0).get(0)] = 0;
    while (true) {
      List<Integer> info = board.get(pos);
      int num = info.get(0);
      int cnt = info.get(3);
      
      // 연산 D
      List<Integer> d = new ArrayList<>();
      int dNum = (num * 2) % 10000;
      if (visited[dNum] == 0 || visited[dNum] > cnt + 1) {
        d.add(dNum);
        d.add(0);
        d.add(pos);
        d.add(cnt + 1);
        board.add(d);
        if (dNum == finish) {
          break;
        }
        visited[dNum] = cnt + 1;
      }

      // 연산 S
      List<Integer> s = new ArrayList<>();
      int sNum = (num == 0) ? 9999 : (num - 1);
      if (visited[sNum] == 0 || visited[sNum] > cnt + 1) {
        s.add(sNum);
        s.add(1);
        s.add(pos);
        s.add(cnt + 1);
        board.add(s);
        if (sNum == finish) {
          break;
        }
        visited[sNum] = cnt + 1;
      }

      // num 자리수 확인
      int idx = 1;
      int tmp = num;
      while (true) {
        tmp = tmp / 10;
        if (tmp == 0) {
          break;
        }
        else {
          idx = idx * 10;
        }
      }
    
      // 연산 L
      List<Integer> l = new ArrayList<>();
      int lNum;
      if (idx < 1000) {
        lNum = (num * 10);
      }
      else {
        int firstNum = num / idx;
        lNum = (num * 10 + firstNum) % (idx * 10);
      }
      if (visited[lNum] == 0 || visited[lNum] > cnt + 1) {
        l.add(lNum);
        l.add(2);
        l.add(pos);
        l.add(cnt + 1);
        board.add(l);
        if (lNum == finish) {
          break;
        }
        visited[lNum] = cnt + 1;
      }

      // 연산 R
      List<Integer> r = new ArrayList<>();
      int rNum;
      if (idx == 1) {
        rNum = num * 1000;
      }
      else {
        int lastNum = num % 10;
        rNum = ((10000 * lastNum) + num) / 10;
      }
      if (visited[rNum] == 0 || visited[rNum] > cnt + 1) {
        r.add(rNum);
        r.add(3);
        r.add(pos);
        r.add(cnt + 1);
        board.add(r);
        if (rNum == finish) {
          break;
        }
        visited[rNum] = cnt + 1;
      }

      pos += 1;
    }
  }

  public static void main (String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/9019/9019.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());

    for (int i = 0; i < t; i++) {
      String[] num = br.readLine().split(" ");
      int before = Integer.parseInt(num[0]);
      int after = Integer.parseInt(num[1]);

      board = new ArrayList<>();
      pos = 0;

      // queue에 [시작 숫자, 연산, 이전 index, 연산 횟수] 저장
      List<Integer> start = new ArrayList<>();
      start.add(before);
      start.add(-1);
      start.add(0);
      start.add(0);
      board.add(start);

      // 이후 원하는 숫자가 나올때까지 bfs 실행
      bfs(board, after);
      pos = board.size() - 1;

      // 원하는 숫자에서 시작 숫자까지 역추적하면서 연산 과정을 구함
      Stack<String> stack = new Stack<>();
      while (true) {
        if (board.get(pos).get(1) == -1) {
          break;
        }
        if (board.get(pos).get(1) == 0) {
          stack.push("D");
        }
        else if (board.get(pos).get(1) == 1) {
          stack.push("S");
        }
        else if (board.get(pos).get(1) == 2) {
          stack.push("L");
        }
        else if (board.get(pos).get(1) == 3) {
          stack.push("R");
        }
        pos = board.get(pos).get(2);
      }

      // 정답을 출력할때는 역추적한 과정을 원래대로 되돌림
      String ans = "";
      while (stack.size() != 0) {
        ans += stack.pop();
      }
      
      System.out.println(ans);
    }
    br.close();
  }  
}
