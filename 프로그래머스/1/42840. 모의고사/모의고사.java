import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    

    public int[] solution(int[] answers) {
        int[] answer;
        int[] per1 = { 1, 2, 3, 4, 5 };
        int[] per2 = { 2, 1, 2, 3, 2, 4, 2, 5 };
        int[] per3 = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };
        int[] score = { 0, 0, 0 };
        List<Integer> ans = new ArrayList<>();
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
        ans.add(1);
        int maxScore = score[0];
        for (int i = 1; i < 3; i++) {
            if (maxScore == score[i]) {
                ans.add(i + 1);
            } else if (maxScore < score[i]) {
                maxScore = score[i];
                ans = new ArrayList<>();
                ans.add(i + 1);
            }
        }
        answer = ans.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}