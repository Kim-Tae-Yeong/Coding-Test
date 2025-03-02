import java.io.*;
import java.util.*;

public class Main {
    static int M, N;
    static int[][] map, dp;
    static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    static boolean isInBound(int row, int col) {
        return 0 <= row && row < M && 0 <= col && col < N;
    }

    static int dfs(int row, int col) {
        if (row == M - 1 && col == N - 1) {
            return 1; // 목적지 도착
        }
        if (dp[row][col] != -1) {
            return dp[row][col]; // 이미 계산된 경로 수 반환
        }

        dp[row][col] = 0; // 방문 처리 후 경로 개수 초기화
        for (int[] d : dir) {
            int nr = row + d[0];
            int nc = col + d[1];
            if (isInBound(nr, nc) && map[nr][nc] < map[row][col]) {
                dp[row][col] += dfs(nr, nc);
            }
        }
        return dp[row][col];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        dp = new int[M][N]; // 방문 여부 + 경로 개수 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1; // -1로 초기화 (미방문 상태)
            }
        }

        System.out.println(dfs(0, 0));
        br.close();
    }
}