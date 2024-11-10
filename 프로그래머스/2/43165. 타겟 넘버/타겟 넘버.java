import java.util.LinkedList;
import java.util.Queue;

class Solution {
    

    public int solution(int[] numbers, int target) {
        int answer = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { numbers[0], 0 });
        q.add(new int[] { -numbers[0], 0 });

        int length = numbers.length;

        while (!q.isEmpty()) {
            int[] info = q.poll();
            int num = info[0];
            int idx = info[1];

            if (idx == length - 1) {
                if (num == target) {
                    answer++;
                }
            } else {
                q.add(new int[] { num + numbers[idx + 1], idx + 1 });
                q.add(new int[] { num - numbers[idx + 1], idx + 1 });
            }
        }
        return answer;
    }
}