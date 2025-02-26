import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static List<List<Edge>> graph = new ArrayList<>();
    static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.cost));
    static long[][] distance;
    static long ans = Long.MAX_VALUE;

    static void dijkstra() {
        pq.add(new Edge(1, 0, 0));

        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            if (e.cost > distance[e.remove][e.to]) continue;  // 기존 거리보다 크면 무시

            for (Edge next : graph.get(e.to)) {
                long newCost = distance[e.remove][e.to] + next.cost;

                // 도로를 그대로 이용하는 경우
                if (distance[e.remove][next.to] > newCost) {
                    distance[e.remove][next.to] = newCost;
                    pq.add(new Edge(next.to, newCost, e.remove));
                }

                // 도로를 없애는 경우 (최대 K번 가능)
                if (e.remove < K && distance[e.remove + 1][next.to] > distance[e.remove][e.to]) {
                    distance[e.remove + 1][next.to] = distance[e.remove][e.to];
                    pq.add(new Edge(next.to, distance[e.remove + 1][next.to], e.remove + 1));
                }
            }
        }
    }

    static class Edge {
        int to;
        long cost;
        int remove;

        public Edge(int to, long cost, int remove) {
            this.to = to;
            this.cost = cost;
            this.remove = remove;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Edge(to, cost, 0));
            graph.get(to).add(new Edge(from, cost, 0));
        }

        distance = new long[K + 1][N + 1];
        for (int i = 0; i <= K; i++) {
            Arrays.fill(distance[i], Long.MAX_VALUE);
        }
        distance[0][1] = 0;  // 출발점 초기화

        dijkstra();

        for (int i = 0; i <= K; i++) {
            ans = Math.min(ans, distance[i][N]);
        }

        System.out.println(ans);
        br.close();
    }
}