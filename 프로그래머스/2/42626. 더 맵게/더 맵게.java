import java.util.PriorityQueue;

class Solution {
    

    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2));
        for (int elem : scoville) {
            pq.add(elem);
        }
        while (true) {
            // 가장 맵지 않은 음식의 스코빌이 K를 넘으면 break
            if (pq.peek() >= K) {
                break;
            }
            // 음식이 하나밖에 남지 않으면, 해당 음식으로 스코빌을 K를 넘길 수 없기 때문에 -1 return
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
