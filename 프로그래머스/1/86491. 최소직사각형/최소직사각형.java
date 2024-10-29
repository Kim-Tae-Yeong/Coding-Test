import java.util.PriorityQueue;

class Solution {
    

    public int solution(int[][] sizes) {
        int answer = 0;
        // 각 지갑의 w와 h를 w > h로 만들었을 때 w 기준 내림차순 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        for (int[] elem : sizes) {
            if (elem[0] > elem[1]) {
                pq.add(new int[] { elem[0], elem[1] });
            } else {
                pq.add(new int[] { elem[1], elem[0] });
            }
        }
        // 가장 긴 w 선택
        int[] first = pq.poll();
        int w = first[0];
        int h = first[1];
        // 그에 맞는 h 선택
        for (int[] elem : pq) {
            // 각 지갑의 w와 h 중 더 작은 값을 h로 설정
            int tmp = Math.min(elem[0], elem[1]);
            // 해당 h중 가장 큰 값을 구함
            h = Math.max(h, tmp);
        }
        answer = w * h;
        return answer;
    }
}
