import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    

    public int[] solution(int[] answers) {
        int[] answer;
        int[] per1 = { 1, 2, 3, 4, 5 };
        int[] per2 = { 2, 1, 2, 3, 2, 4, 2, 5 };
        int[] per3 = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };
        // score[i] : (i + 1)번째 사람이 맞힌 정답의 개수
        int[] score = { 0, 0, 0 };
        List<Integer> ans = new ArrayList<>();
        // 각 사람이 몇 개의 정답을 맞췄는지 확인
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == per1[i % per1.length]) {
                score[0] += 1;
            }
            if (answers[i] == per2[i % per2.length]) {
                score[1] += 1;
            }
            if (answers[i] == per3[i % per3.length]) {
                score[2] += 1;
            }
        }
        // 첫 번째 사람 정답 배열에 추가
        ans.add(1);
        // 첫 번째 사람의 정답 개수를 최대 정답 개수로 설정
        int maxScore = score[0];
        // 이후의 사람 확인
        for (int i = 1; i < 3; i++) {
            // 최대 정답 개수와 같으면
            if (maxScore == score[i]) {
                // 정답 배열에 추가
                ans.add(i + 1);
            }
            // 최대 정답 개수보다 많으면
            else if (maxScore < score[i]) {
                // 최대 정답 개수 갱신
                maxScore = score[i];
                // 정답 배열 초기화
                ans = new ArrayList<>();
                // 정답 배열에 추가
                ans.add(i + 1);
            }
        }
        answer = ans.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}
