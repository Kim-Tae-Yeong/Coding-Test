import java.util.PriorityQueue;

class Solution {
    

    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2));
        for (int elem : scoville) {
            pq.add(elem);
        }
        while (true) {
            if (pq.peek() >= K) {
                break;
            }
            if (pq.size() == 1) {
                answer = -1;
                break;
            }
            pq.add(pq.poll() + pq.poll() * 2);
            answer += 1;
        }
        return answer;
    }
}