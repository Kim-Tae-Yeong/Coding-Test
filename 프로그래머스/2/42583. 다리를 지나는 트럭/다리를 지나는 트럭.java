import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<int[]> q = new LinkedList<>();
        int currentWeight = 0;
        int idx = 0;
        while (true) {
            if (q.isEmpty() && idx == truck_weights.length) {
                break;
            }
            if (!q.isEmpty()) {
                int[] info = q.peek();
                if (info[1] == bridge_length) {
                    q.poll();
                    currentWeight -= info[0];
                }
                for (int[] elem : q) {
                    elem[1] += 1;
                }
            }
            if (idx < truck_weights.length) {
                if (q.isEmpty() || currentWeight + truck_weights[idx] <= weight) {
                    currentWeight += truck_weights[idx];
                    q.add(new int[] { truck_weights[idx], 1 });
                    idx += 1;
                }
            }
            answer += 1;
        }
        return answer;
    }
}