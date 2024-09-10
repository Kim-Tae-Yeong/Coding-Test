import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
  public static int n, m, start, end;
  public static List<List<int[]>> graph = new ArrayList<>();
  public static int[] ans;

  public static class Node implements Comparable<Node> {
    int idx, cost;

    public Node(int idx, int cost) {
      this.idx = idx;
      this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
      return this.cost - o.cost;
    }
  }

  public static void dijkstra (int start) {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.add(new Node(start, 0));
    ans[start] = 0;

    while (!pq.isEmpty()) {
      Node current = pq.poll();
      int currentIdx = current.idx;
      int currentCost = current.cost;

      if (currentCost > ans[currentIdx]) {
        continue;
      }

      for (int[] edge : graph.get(currentIdx)) {
        int next = edge[1];
        int cost = edge[2];

        if (ans[currentIdx] + cost < ans[next]) {
          ans[next] = ans[currentIdx] + cost;
          pq.add(new Node(next, ans[next]));
        }
      }
    }
  }
  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/1916/1916.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    m = Integer.parseInt(br.readLine());

    for (int i = 0; i < n + 1; i++) {
      graph.add(new ArrayList<>());
    }

    ans = new int[n + 1];
    Arrays.fill(ans, Integer.MAX_VALUE);

    for (int i = 0; i < m; i++) {
      String[] input = br.readLine().split(" ");
      int start = Integer.parseInt(input[0]);
      int end = Integer.parseInt(input[1]);
      int cost = Integer.parseInt(input[2]);
      graph.get(start).add(new int[]{start, end, cost});
    }

    String[] info = br.readLine().split(" ");
    start = Integer.parseInt(info[0]);
    end = Integer.parseInt(info[1]);

    dijkstra(start);
    System.out.println(ans[end]);

    br.close();
  }  
}
