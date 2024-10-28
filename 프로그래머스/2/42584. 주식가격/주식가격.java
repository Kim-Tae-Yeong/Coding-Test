import java.util.Arrays;

class Solution {
    

    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        // i번째 주식에 대해 (i + 1) ~ (price.length - 1) 주식까지 확인
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                // 다음 주식 값에 상관없이 1초 늘어남
                answer[i] += 1;
                // 다음 주식 값이 더 작으면 시간이 더 이상 늘어나지 않음
                if (prices[i] > prices[j]) {
                    break;
                }
            }
        }
        return answer;
    }
}
