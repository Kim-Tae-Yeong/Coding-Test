import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
  public static int n, m, ans;
  public static List<Integer> people = new ArrayList<>();
  public static List<List<Integer>> total_party = new ArrayList<>();
  public static List<List<Integer>> graph = new ArrayList<>();
  public static boolean[] check;

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
    
    for (int i = 1; i < tmp.length; i++) {
      people.add(Integer.parseInt(tmp[i]));
      check[Integer.parseInt(tmp[i])] = true;
    }

    
    for (int i = 0; i < m; i++) {
      String[] input = br.readLine().split(" ");
      int length = Integer.parseInt(input[0]);
      List<Integer> party = new ArrayList<>();
      for (int j = 1; j < length + 1; j++) {
        party.add(Integer.parseInt(input[j]));
      }
      total_party.add(party);
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

    ans = m;
    for (List<Integer> info : total_party) {
      for (int elem : info) {
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
