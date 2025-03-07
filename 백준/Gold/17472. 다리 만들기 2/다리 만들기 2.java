import java.io.*;
import java.util.*;

public class Main {
  static int N, M, num;
  static int[][] map;
  static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
  static List<List<Edge>> graph = new ArrayList<>();
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
    PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
    pq.add(new Edge(1, 0));
    int mst = 0;
    int cnt = 0;

    while (!pq.isEmpty()) {
      Edge e = pq.poll();
      if (visited[e.to]) {
        continue;
      }
      visited[e.to] = true;
      mst += e.cost;
      cnt++;

      for (Edge next : graph.get(e.to)) {
        if (!visited[next.to]) {
          pq.add(next);
        }
      }
    }

    if (cnt == num - 1) {
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

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        map[i][j] = -Integer.parseInt(st.nextToken());
      }
    }

    num = 1;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (map[i][j] == -1) {
          divideIsland(i, j, num);
          num++;
        }
      }
    }

    for (int i = 0; i < num; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (map[i][j] != 0) {
          findDistance(i, j, map[i][j]);
        }
      }
    }

    visited = new boolean[num];
    System.out.println(prim());
    br.close();
  }
}
