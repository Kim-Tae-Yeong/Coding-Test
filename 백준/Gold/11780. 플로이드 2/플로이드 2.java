import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] dist, path;
    static final int INF = 100_000_000;

    static void floydWarshall() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        path[i][j] = k; // i에서 j로 가는 경로 중 k를 거침
                    }
                }
            }
        }
    }

    static List<Integer> getPath(int start, int end) {
        List<Integer> route = new ArrayList<>();
        if (dist[start][end] == INF) return route; // 경로 없음

        findPath(start, end, route);
        route.add(0, start);
        route.add(end);
        return route;
    }

    static void findPath(int start, int end, List<Integer> route) {
        if (path[start][end] == 0) return;
        int via = path[start][end];
        findPath(start, via, route);
        route.add(via);
        findPath(via, end, route);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        dist = new int[n + 1][n + 1];
        path = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dist[u][v] = Math.min(dist[u][v], cost);
        }

        floydWarshall();

        // 1. 최단 거리 행렬 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(dist[i][j] == INF ? "0 " : dist[i][j] + " ");
            }
            sb.append("\n");
        }

        // 2. 경로 출력
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j || dist[i][j] == INF) {
                    sb.append("0\n");
                } else {
                    List<Integer> route = getPath(i, j);
                    sb.append(route.size()).append(" ");
                    for (int node : route) sb.append(node).append(" ");
                    sb.append("\n");
                }
            }
        }

        System.out.print(sb);
        br.close();
    }
}