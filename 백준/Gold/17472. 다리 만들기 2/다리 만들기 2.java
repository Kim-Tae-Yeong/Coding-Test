import java.io.*;
import java.util.*;

public class Main_17472 {
  // num : 섬의 총 개수
  static int N, M, num;
  static int[][] map;
  static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
  // graph.get(i) : i번 노드에 연결된 간선 저장
  static List<List<Edge>> graph = new ArrayList<>();
  // viisted[i] : i번 노드 방문 여부
  static boolean[] visited;

  static class Edge {
    int to, cost;

    public Edge(int to, int cost) {
      this.to = to;
      this.cost = cost;
    }
  }

  static boolean isInBound(int row, int col) {
    return 0 <= row && row < N && 0 <= col && col < M;
  }

  // bfs를 이용해 현재 위치랑 연결된 모든 땅에 동일한 번호를 부여함
  static void divideIsland(int row, int col, int num) {
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[] { row, col });
    map[row][col] = num;

    while (!q.isEmpty()) {
      int[] pos = q.poll();
      int r = pos[0];
      int c = pos[1];

      map[r][c] = num;

      for (int[] d : dir) {
        int nr = r + d[0];
        int nc = c + d[1];
        if (isInBound(nr, nc) && map[nr][nc] == -1) {
          map[nr][nc] = num;
          q.add(new int[] { nr, nc });
        }
      }
    }
  }

  static void upDistance(int row, int col, int from) {
    int up = 0;
    int to = from;
    for (int i = 1; i < row + 1; i++) {
      if (map[row - i][col] == 0) {
        up++;
      } else {
        to = map[row - i][col];
        break;
      }
    }
    if (up > 1 && to != from) {
      graph.get(from).add(new Edge(to, up));
    }
  }

  static void downDistance(int row, int col, int from) {
    int down = 0;
    int to = from;
    for (int i = 1; i < N - row; i++) {
      if (map[row + i][col] == 0) {
        down++;
      } else {
        to = map[row + i][col];
        break;
      }
    }
    if (down > 1 && to != from) {
      graph.get(from).add(new Edge(to, down));
    }
  }

  static void leftDistance(int row, int col, int from) {
    int left = 0;
    int to = from;
    for (int i = 1; i < col + 1; i++) {
      if (map[row][col - i] == 0) {
        left++;
      } else {
        to = map[row][col - i];
        break;
      }
    }
    if (left > 1 && to != from) {
      graph.get(from).add(new Edge(to, left));
    }
  }

  static void rightDistance(int row, int col, int from) {
    int right = 0;
    int to = from;
    for (int i = 1; i < M - col; i++) {
      if (map[row][col + i] == 0) {
        right++;
      } else {
        to = map[row][col + i];
        break;
      }
    }
    if (right > 1 && to != from) {
      graph.get(from).add(new Edge(to, right));
    }
  }

  static void findDistance(int row, int col, int num) {
    upDistance(row, col, num);
    downDistance(row, col, num);
    leftDistance(row, col, num);
    rightDistance(row, col, num);
  }

  static int prim() {
    // 현재까지 방문한 노드에 연결된 간선 중 방문하지 않은 노드와 연결된 간선을 우선순위 큐에 저장
    // 간선의 비용을 오름차순으로 정렬
    PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
    // 시작 노드는 1로 설정(0번 가상 노드에서 1번 노드까지 비용이 0인 간선으로 연결)
    pq.add(new Edge(1, 0));
    // 다리의 최소 길이
    int mst = 0;
    // 간선의 개수
    int cnt = 0;

    while (!pq.isEmpty()) {
      Edge e = pq.poll();
      // 이전에 방문한 노드이면 continue
      if (visited[e.to]) {
        continue;
      }
      // 현재 노드 방문 여부 갱신
      visited[e.to] = true;
      mst += e.cost;
      // 간선 개수 추가
      cnt++;

      // 현재 노드에 연결된 간선 중 방문하지 않은 노드로 연결되는 간선을 우선순위 큐에 추가
      for (Edge next : graph.get(e.to)) {
        if (!visited[next.to]) {
          pq.add(next);
        }
      }
    }

    // 0번 가상 노드를 추가했기 때문에 간선의 개수가 섬의 개수와 같으면 모든 섬을 다 연결할 수 있음
    if (cnt == num) {
      return mst;
    } else {
      return -1;
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./17472.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    map = new int[N][M];

    // 초기에 바다는 0, 섬은 -1로 받음
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        map[i][j] = -Integer.parseInt(st.nextToken());
      }
    }

    // 각 섬에 1번부터 번호를 부여함
    num = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (map[i][j] == -1) {
          divideIsland(i, j, ++num);
        }
      }
    }

    for (int i = 0; i < num + 1; i++) {
      graph.add(new ArrayList<>());
    }

    // 현재 위치가 섬이면
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (map[i][j] != 0) {
          // 다른 섬과의 거리를 구함
          findDistance(i, j, map[i][j]);
        }
      }
    }

    visited = new boolean[num + 1];
    // 프림 알고리즘으로 다리 길이의 최솟값을 구함
    System.out.println(prim());
    br.close();
  }
}
