import java.util.ArrayList;
import java.util.List;

class Solution {
    

    public int solution(int[][] routes) {
        int answer = 0;
        List<int[]> l = new ArrayList<>();
        for (int[] elem : routes) {
            l.add(elem);
        }
        // 출발 지점 기준 오름차순 정렬
        l.sort((o1, o2) -> Integer.compare(o1[0], o2[0]));
        // 첫 자동차의 출발, 도착 지점 확인
        // 이는 첫 자동차와 겹치는 자동차들의 시작, 도착 지점임
        int start = l.get(0)[0];
        int end = l.get(0)[1];
        int idx = 1;
        // 현재 자동차와 이전 자도차가 겹치는지 안겹치는지 확인
        while (true) {
            // 모든 자동차를 확인하면 break
            if (idx == l.size()) {
                break;
            }
            // 현재 자동차의 도착 지점이 이전 자동차의 도착 지점 이전이면 겹치는 부분의 도착 지점을 다음 자동차의 도착 지점으로 갱신
            if (l.get(idx)[1] < end) {
                end = l.get(idx)[1];
            }
            // 현재 자동차의 시작 지점이 겹치는 부분의 도착 지점보다 크면
            else if (l.get(idx)[0] > end) {
                // 이전까지 겹치는 부분의 자동차는 하나의 감시카메라만 사용하면 됨
                answer++;
                // 현재 자동차와 시작, 도착 지점을 새로 겹치는 부분의 시작, 도착 지점으로 갱신
                start = l.get(idx)[0];
                end = l.get(idx)[1];
            }
            idx++;
        }
        // while문에서 마지막 이전 차량까지의 감시카메라 개수를 구했기 때문에 마지막 차량에 대한 감시카메라를 더해줌
        return answer + 1;
    }
}
