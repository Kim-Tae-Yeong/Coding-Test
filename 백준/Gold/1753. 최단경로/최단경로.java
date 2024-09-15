import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
  public static int V, E, K;
  public static List<List<int[]>> graph = new ArrayList<>();
  public static int[] distance;

  public static void dijkstra (int k) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
    pq.add(new int[] {k, 0});
    distance[k] = 0;
    while (!pq.isEmpty()) {
      int[] current = pq.poll();
      int currentIdx = current[0];
      int currentCost = current[1];

      if (currentCost > distance[currentIdx]) {
        continue;
      }

      for (int[] elem : graph.get(currentIdx)) {
        int next = elem[0];
        int cost = elem[1];
        if (distance[currentIdx] + cost < distance[next]) {
          distance[next] = distance[currentIdx] + cost;
          pq.add(new int[] {next, distance[next]});
        }
      }
    }
  }
  public static void main (String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/1753/1753.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    V = Integer.parseInt(num[0]);
    E = Integer.parseInt(num[1]);

    K = Integer.parseInt(br.readLine());

    for (int i = 0; i < V + 1; i++) {
      graph.add(new ArrayList<>());
    }

    distance = new int[V + 1];
    Arrays.fill(distance, Integer.MAX_VALUE);

    for (int i = 0; i < E; i++) {
      String[] input = br.readLine().split(" ");
      int u = Integer.parseInt(input[0]);
      int v = Integer.parseInt(input[1]);
      int w = Integer.parseInt(input[2]);
      graph.get(u).add(new int[] {v, w});
    }

    dijkstra(K);


    for (int i = 1; i < V + 1; i++) {
      if (distance[i] == Integer.MAX_VALUE) {
        System.out.println("INF");
      }
      else {
        System.out.println(distance[i]);
      }
    }

    br.close();
  }
}
