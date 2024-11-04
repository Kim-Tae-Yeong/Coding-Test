import java.util.ArrayList;
import java.util.List;

class Solution {
    

    public int solution(int n, int[] lost, int[] reserve) {
        // 초기 정답은 전체 인원에서 체육복을 잃어버린 인원의 수
        int answer = n - lost.length;
        // n명 중 체육복을 잃어버린 인원 확인
        boolean[] still = new boolean[n + 2];
        for (int elem : lost) {
            still[elem] = true;
        }
        // 여분의 체육복이 있는 인원 중
        List<Integer> l = new ArrayList<>();
        for (int elem : reserve) {
            // 본인의 체육복을 잃어버린 인원이면
            if (still[elem]) {
                // 정답 증가
                answer += 1;
                // 잃어버린 인원 갱신
                still[elem] = false;
            } 
            // 그게 아니면
            else {
                // 배열에 추가
                l.add(elem);
            }
        }
        // 배열 정렬
        // 앞쪽에서 체육복을 잃어버린 인원은 앞쪽의 여분 있는 인원이 빌려줘야 더 많은 인원이 체육복을 가질 수 있음
        l.sort(null);
        // 이후 앞 뒤로 체육복 빌려줄 인원 확인
        for (int elem : l) {
            if (still[elem - 1]) {
                answer += 1;
                still[elem - 1] = false;
            } else {
                if (still[elem + 1]) {
                    answer += 1;
                    still[elem + 1] = false;
                }
            }
        }
        return answer;
    }
}
