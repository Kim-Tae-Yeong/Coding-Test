import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        // q : {다리에 올라간 트럭의 무게, 이동한 거리}
        Queue<int[]> q = new LinkedList<>();
        // currentWeight : 현재 다리에 올라간 트럭의 총 무게
        int currentWeight = 0;
        // idx : 입력받은 배열에서 어느 트럭까지 다리에 올렸나 확인
        int idx = 0;
        while (true) {
            // 다리에 올라간 트럭이 없고, 모든 트럭을 확인한 경우 break
            if (q.isEmpty() && idx == truck_weights.length) {
                break;
            }
            // 다리에 트럭이 있다면
            if (!q.isEmpty()) {
                // 맨 앞 트럭 확인
                int[] info = q.peek();
                // 맨 앞 트럭이 모두 다 이동했다면
                if (info[1] == bridge_length) {
                    // 해당 트럭을 다리에서 내림
                    q.poll();
                    // 다리의 트럭 무게 갱신
                    currentWeight -= info[0];
                }
                // 그 외의 트럭들을 한칸씩 이동시킴
                for (int[] elem : q) {
                    elem[1] += 1;
                }
            }
            // 아직 올라가지 않은 트럭이 있다면
            if (idx < truck_weights.length) {
                // 다리가 비어있거나 다음 트럭을 다리에 올릴 수 있으면
                if (q.isEmpty() || currentWeight + truck_weights[idx] <= weight) {
                    // 트럭을 다리에 올림
                    currentWeight += truck_weights[idx];
                    q.add(new int[] { truck_weights[idx], 1 });
                    // 다음 트럭 확인
                    idx += 1;
                }
            }
            answer += 1;
        }
        return answer;
    }
}
