
class Solution {
    

    public int solution(String name) {
        int answer = 0;
        int idx = 0;
        int move = Integer.MAX_VALUE;
        for (int i = 0; i < name.length(); i++) {
            // 각 자리 알파벳의 최소 변환 횟수를 구함
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
            // 방향 전환은 최대 1번만 실행(2번 이상은 비효율적임)
            // 따라서 언제 방향 전환할지를 구함
            idx = i + 1;
            // 현재 위치(i)를 기준으로 A가 아닌 알파벳이 언제 나오는지 구함
            while (idx < name.length() && name.charAt(idx) == 'A') {
                idx += 1;
            }
            // 현재 위치까지 탐색(i) + 시작 위치까지 돌아감(i) + 위에서 구한 알파벳까지의 거리(length - idx)
            // 위 값을 이전 움직임과 비교
            move = Math.min(move, i * 2 + name.length() - idx);
            // 시작 위치에서 역방향을 먼저 탐색한 후 정방향을 탐색하는 경우
            move = Math.min(move, (name.length() - idx) * 2 + i);
        }
        return answer + move;
    }
}
