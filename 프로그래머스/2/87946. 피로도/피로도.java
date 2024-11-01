
class Solution {
    

    private static int length;
    private static int answer = Integer.MIN_VALUE;

    private static void combination(int[][] dungeons, int x, int k, boolean[] check, int cnt) {
        for (int i = 0; i < length; i++) {
            if (k >= dungeons[i][0] && !check[i]) {
                check[i] = true;
                combination(dungeons, i, k - dungeons[i][1], check, cnt + 1);
            }
        }
        answer = Math.max(answer, cnt);
        check[x] = false;
    }

    public int solution(int k, int[][] dungeons) {
        length = dungeons.length;
        for (int i = 0; i < length; i++) {
            boolean[] check = new boolean[length];
            if (k >= dungeons[i][0]) {
                check[i] = true;
                combination(dungeons, i, k - dungeons[i][1], check, 1);
            }
        }
        return answer;
    }
}