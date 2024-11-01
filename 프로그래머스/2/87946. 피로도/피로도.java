
class Solution {
    

    private static int length;
    private static int answer = Integer.MIN_VALUE;

    private static void combination(int[][] dungeons, int x, int k, boolean[] check, int cnt) {
        // 현재까지 탐험하지 않은 던전 중 탐험할 수 있는 던전 확인
        for (int i = 0; i < length; i++) {
            if (k >= dungeons[i][0] && !check[i]) {
                check[i] = true;
                combination(dungeons, i, k - dungeons[i][1], check, cnt + 1);
            }
        }
        // 현재 상태에서 더 이상 탐험할 수 있는 던전이 없으면 최대 던전 개수 갱신
        answer = Math.max(answer, cnt);
        // 현재 던전의 방문 상태 false로 변경
        check[x] = false;
    }

    public int solution(int k, int[][] dungeons) {
        length = dungeons.length;
        // 던전의 시작 지점 정하기
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
