import java.io.*;
import java.util.*;

public class Main {
    static int n, m, u, v, w;
    static List<Edge> graph = new ArrayList<>();
    static int[] distance, path;
    static boolean[] visited;
    static final int INF = Integer.MIN_VALUE;

    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static boolean hasPositiveCycle() {
        distance[1] = 0;

        // 벨만-포드 알고리즘
        for (int i = 0; i < n - 1; i++) {
            for (Edge e : graph) {
                if (distance[e.from] != INF && distance[e.to] < distance[e.from] + e.cost) {
                    distance[e.to] = distance[e.from] + e.cost;
                    path[e.to] = e.from;
                }
            }
        }

        // 양의 사이클 확인 (사이클 내에서 도착점(n) 도달 가능 여부 추가 체크)
        Queue<Integer> queue = new LinkedList<>();
        boolean[] inCycle = new boolean[n + 1];

        for (Edge e : graph) {
            if (distance[e.from] != INF && distance[e.to] < distance[e.from] + e.cost) {
                inCycle[e.to] = true;
                queue.add(e.to);
            }
        }

        // BFS로 도착지점(n)까지 도달 가능 여부 확인
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == n) return true; // 도착지점에 도달 가능하면 -1 출력해야 함
            for (Edge e : graph) {
                if (e.from == cur && !inCycle[e.to]) {
                    inCycle[e.to] = true;
                    queue.add(e.to);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        distance = new int[n + 1];
        path = new int[n + 1];
        Arrays.fill(distance, INF);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            graph.add(new Edge(u, v, w));
        }

        if (hasPositiveCycle()) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            Deque<Integer> stack = new ArrayDeque<>();
            int cur = n;

            while (cur != 0) {
                stack.push(cur);
                cur = path[cur];
            }

            while (!stack.isEmpty()) {
                sb.append(stack.pop()).append(" ");
            }

            System.out.println(sb.toString().trim());
        }
        br.close();
    }
}