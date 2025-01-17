import java.io.*;
import java.util.*;

public class Main {
    static int N, M, start, end;
    static List<List<Edge>> graph;
    static int[] dist, path;
    static PriorityQueue<Edge> pq;
    static Stack<Integer> s = new Stack<>();

    static class Edge implements Comparable<Edge> {
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);  // 비용 오름차순 정렬
        }
    }

    static void dijkstra() {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int current = edge.to;
            int currentCost = edge.cost;

            // 최적 거리보다 크다면 무시
            if (dist[current] < currentCost) continue;

            for (Edge next : graph.get(current)) {
                int newCost = dist[current] + next.cost;
                if (newCost < dist[next.to]) {
                    dist[next.to] = newCost;
                    path[next.to] = current;
                    pq.add(new Edge(next.to, newCost));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Edge(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        path = new int[N + 1];
        pq = new PriorityQueue<>();

        dijkstra();

        System.out.println(dist[end]);

        int num = end;
        while (num != 0) {
            s.push(num);
            num = path[num];
        }
        System.out.println(s.size());
        while (!s.isEmpty()) {
            System.out.print(s.pop() + " ");
        }

        br.close();
    }
}
