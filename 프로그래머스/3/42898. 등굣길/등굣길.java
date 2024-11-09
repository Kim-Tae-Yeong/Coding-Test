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
                    // 웅덩이 위치는 올 수 있는 경우가 0
                    dp[i][j] = 0; 
                    continue;
                }
                // row가 1 이상일떄
                if (i > 0) {
                    // 현재 위치에 올 수 있는 경우 + 위에서 내려오는 경우
                    // 이 경우에는 dp[i][j]가 0이므로 dp[i][j]는 위에서 내려오는 경우
                    dp[i][j] = (dp[i][j] + dp[i - 1][j]) % MOD;
                }
                // col이 1 이상일 때
                if (j > 0) {
                    // 현재 위치에 올 수 있는 경우 + 왼쪽에서 오는 경우
                    // 앞서 dp[i][j]에 위에서 내려오는 경우를 넣어놨기 때문에 그 경우에 왼쪽에서 오는 경우를 더하면 총 경우가 나옴
                    dp[i][j] = (dp[i][j] + dp[i][j - 1]) % MOD;
                }
            }
        }

        return dp[n - 1][m - 1];
    }
}
