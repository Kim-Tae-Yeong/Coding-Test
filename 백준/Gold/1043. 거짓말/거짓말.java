import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
  public static int n, m, ans;
  // people : 최초로 거짓말을 알고 있는 사람
  public static List<Integer> people = new ArrayList<>();
  public static List<List<Integer>> total_party = new ArrayList<>();
  // graph : i번째 사람은 graph.get(i)에 있는 사람들과 관계가 있음
  // 즉 i번째 사람이 거짓말을 알고 있으면 graph.get(i)에 있는 사람 모두 거짓말을 알 수 있음
  public static List<List<Integer>> graph = new ArrayList<>();
  // check : 거짓말을 알고 있는 사람 전체
  // check[i]가 true이면 i번째 사람은 거짓말을 알고 있음
  public static boolean[] check;

  // 최초에 거짓말을 알고 있는 사람들과 graph를 통해 bfs를 이용해서 거짓말을 알 수 있는 사람을 모두 구함
  public static void bfs (int idx) {
    Queue<Integer> q = new LinkedList<>();
    q.add(idx);
    while (!q.isEmpty()) {
      int s = q.poll();
      for (int elem : graph.get(s)) {
        if (!check[elem]) {
          check[elem] = true;
          q.add(elem);
        }
      }
    }
  }

  public static void main (String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/1043/1043.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    n = Integer.parseInt(num[0]);
    m = Integer.parseInt(num[1]);

    for (int i = 0; i < n + 1; i++) {
      graph.add(new ArrayList<>());
    }
    
    String[] tmp = br.readLine().split(" ");
    check = new boolean[n + 1];
    
    // 최초에 거짓말을 알고 있는 사람들 정보 저장
    for (int i = 1; i < tmp.length; i++) {
      people.add(Integer.parseInt(tmp[i]));
      check[Integer.parseInt(tmp[i])] = true;
    }

    
    for (int i = 0; i < m; i++) {
      String[] input = br.readLine().split(" ");
      int length = Integer.parseInt(input[0]);
      // 각 party의 참여하는 사람들 정보 저장
      List<Integer> party = new ArrayList<>();
      for (int j = 1; j < length + 1; j++) {
        party.add(Integer.parseInt(input[j]));
      }
      total_party.add(party);
      // 각 사람이 어떤 사람들과 관계가 있는지 저장
      for (int j = 1; j < length; j++) {
        int start = Integer.parseInt(input[j]);
        for (int k = j + 1; k < length + 1; k++) {
          int end = Integer.parseInt(input[k]);
          graph.get(start).add(end);
          graph.get(end).add(start);
        }
      }
    }
    
    for (int elem : people) {
      bfs(elem);
    }

    // 모든 party에서 거짓말을 할 수 있다 가정
    ans = m;
    // 각 party에 대해서
    for (List<Integer> info : total_party) {
      for (int elem : info) {
        // 거짓말을 알 수 있는 사람이 있으면 거짓말을 할 수 있는 party 수를 하나 줄임
        if (check[elem]) {
          ans = ans - 1;
          break;
        }
      }
    }

    System.out.println(ans);
    
    br.close();
  }
}
