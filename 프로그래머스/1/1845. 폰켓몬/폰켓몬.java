import java.util.*;

class Solution {
    private HashSet<Integer> h = new HashSet();
    public int solution(int[] nums) {
        int answer = 0;
        for(int elem : nums) {
            h.add(elem);
        }
        if(h.size() > nums.length / 2) {
            answer = nums.length / 2;
        }
        else {
            answer = h.size();
        }
        return answer;
    }
}