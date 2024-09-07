import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
  public static int n;
  public static List<List<Integer>> graph;
  public static boolean[] visited;
  public static int[] ans;
  

  public static void bfs (int s) {
    visited[s] = true;
    Queue<Integer> q = new LinkedList<>();
    q.add(s);
    while (q.size() != 0) {
      int pos = q.poll();
      for (int elem : graph.get(pos)) {
        if (!visited[elem]) {
          visited[elem] = true;
          q.add(elem);
          ans[elem] = pos;
        }
      }
    }
  }

  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/11725/11725.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    graph = new ArrayList<>();
    for (int i = 0; i < n + 1; i++) {
      graph.add(new ArrayList<>());
    }
    visited = new boolean[n + 1];
    ans = new int[n + 1];

    for (int i = 1; i < n; i++) {
      String[] input = br.readLine().split(" ");
      int start = Integer.parseInt(input[0]);
      int end = Integer.parseInt(input[1]);
      graph.get(start).add(end);
      graph.get(end).add(start);
    }

    for (int i = 1; i < n + 1; i++) {
      bfs(i);
    }

    for (int i = 2; i < n + 1; i++) {
      System.out.println(ans[i]);
    }

    br.close();
  }
}
