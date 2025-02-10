import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  static int N, M, V;
  static List<List<Integer>> graph = new ArrayList<>();
  static boolean[] visited;
  static Stack<Integer> s = new Stack<>();
  static Queue<Integer> q = new LinkedList<>();

  static void dfs(int current) {
    visited[current] = true;
    s.add(current);
    for (int next : graph.get(current)) {
      if (!visited[next]) {
        dfs(next);
      }
    }
  }

  static void bfs(int start) {
    Arrays.fill(visited, false);
    visited[start] = true;
    q.add(start);

    while (!q.isEmpty()) {
      int current = q.poll();
      System.out.print(current + " ");
      for (int next : graph.get(current)) {
        if (!visited[next]) {
          visited[next] = true;
          q.add(next);
        }
      }
    }
    System.out.println();
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1260.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    V = Integer.parseInt(st.nextToken());

    for (int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    visited = new boolean[N + 1];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      graph.get(from).add(to);
      graph.get(to).add(from);
    }

    for (int i = 1; i < N + 1; i++) {
      graph.get(i).sort(null);
    }

    dfs(V);
    for (int num : s) {
      System.out.print(num + " ");
    }
    System.out.println();

    bfs(V);

    br.close();
  }
}
