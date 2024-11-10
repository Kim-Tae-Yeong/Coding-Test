import java.util.LinkedList;
import java.util.Queue;

class Solution {
    

    public int solution(int[] numbers, int target) {
        int answer = 0;
        Queue<int[]> q = new LinkedList<>();
        // q : {i번째 숫자까지 이용해 만들 수 있는 숫자, i}
        // 초기값 설정
        q.add(new int[] { numbers[0], 0 });
        q.add(new int[] { -numbers[0], 0 });

        int length = numbers.length;

        while (!q.isEmpty()) {
            int[] info = q.poll();
            int num = info[0];
            int idx = info[1];

            // 마지막 index까지 확인하면
            if (idx == length - 1) {
                // 해당 숫자가 target과 같으면 정답 증가
                if (num == target) {
                    answer++;
                }
            } 
            // 그 이전 index이면
            else {
                // 이전 숫자까지 이용해 만든 숫자 + 현재 숫자, 다음 index 저장
                q.add(new int[] { num + numbers[idx + 1], idx + 1 });
                // 이전 숫자까지 이용해 만든 숫자 - 현재 숫자, 다음 index 저장
                q.add(new int[] { num - numbers[idx + 1], idx + 1 });
            }
        }
        return answer;
    }
}
