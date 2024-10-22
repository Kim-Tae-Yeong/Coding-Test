import java.util.*;

class Solution {
    private HashSet<Integer> h = new HashSet();
    public int solution(int[] nums) {
        int answer = 0;
        // 입력받은 배열에서 폰켓몬의 종류가 몇가지인지 구함
        for(int elem : nums) {
            h.add(elem);
        }
        // 폰켓몬의 종류가 N / 2보다 많으면 N / 2를 정답으로 취함
        if(h.size() > nums.length / 2) {
            answer = nums.length / 2;
        }
        // 그것보다 작을 경우 폰켓몬의 종류 개수를 정답으로 취함
        else {
            answer = h.size();
        }
        return answer;
    }
}
