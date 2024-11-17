import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
  private static int N, M, K, X;
  private static List<List<Integer>> graph = new ArrayList<>();
  private static int[] dis;
  private static Queue<int[]> q = new LinkedList<>();
  private static List<Integer> answer = new ArrayList<>();

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new
    // FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/18352/18352.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    N = Integer.parseInt(num[0]);
    M = Integer.parseInt(num[1]);
    K = Integer.parseInt(num[2]);
    X = Integer.parseInt(num[3]);

    for (int i = 0; i < N + 1; i++) {
      List<Integer> tmp = new ArrayList<>();
      graph.add(tmp);
    }

    // 그래프 정보 저장
    for (int i = 0; i < M; i++) {
      String[] input = br.readLine().split(" ");
      int start = Integer.parseInt(input[0]);
      int end = Integer.parseInt(input[1]);
      graph.get(start).add(end);
    }

    // dis[i] : 시작 노드에서 i까지의 최단 거리
    dis = new int[N + 1];

    // q에 시작 지점에서 갈 수 있는 노드 저장
    for (int elem : graph.get(X)) {
      q.add(new int[] { elem, 1 });
    }

    while (!q.isEmpty()) {
      int[] info = q.poll();
      int node = info[0];
      int cnt = info[1];
      // 이전에 방문한 적 없고 시작 노드가 아닌 노드에 대해 최단 거리 갱신
      if (dis[node] == 0 && node != X) {
        dis[node] = cnt;
        // 최단 거리가 K이면 정답에 저장
        if (cnt == K) {
          answer.add(node);
        }
        // 현재 노드에서 갈 수 있는 노드 중 방문한 적 없고 시작 노드가 아닌 노드 탐색
        for (int elem : graph.get(node)) {
          if (dis[elem] == 0 && elem != X) {
            q.add(new int[] { elem, cnt + 1 });
          }
        }
      }
    }

    if (answer.isEmpty()) {
      System.out.println(-1);
    } else {
      Collections.sort(answer);
      for (int elem : answer) {
        System.out.println(elem);
      }
    }

    br.close();
  }
}
