import java.util.Arrays;

class Solution {
    

    public int solution(int m, int n, int[][] puddles) {
        int MOD = 1000000007;
        int[][] dp = new int[n][m];
        
        // 웅덩이 위치 표시
        for (int[] puddle : puddles) {
            dp[puddle[1] - 1][puddle[0] - 1] = -1;
        }

        // 시작 위치 초기화
        dp[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j] == -1) {
                    dp[i][j] = 0; // 웅덩이 위치는 경로 수 0
                    continue;
                }
                if (i > 0) dp[i][j] = (dp[i][j] + dp[i - 1][j]) % MOD;
                if (j > 0) dp[i][j] = (dp[i][j] + dp[i][j - 1]) % MOD;
            }
        }

        return dp[n - 1][m - 1];
    }
}