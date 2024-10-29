import java.util.PriorityQueue;

class Solution {
    

    public int solution(int[][] sizes) {
        int answer = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        for (int[] elem : sizes) {
            if (elem[0] > elem[1]) {
                pq.add(new int[] { elem[0], elem[1] });
            } else {
                pq.add(new int[] { elem[1], elem[0] });
            }
        }
        int[] first = pq.poll();
        int w = first[0];
        int h = first[1];
        for (int[] elem : pq) {
            int tmp = Math.min(elem[0], elem[1]);
            h = Math.max(h, tmp);
        }
        answer = w * h;
        return answer;
    }
}