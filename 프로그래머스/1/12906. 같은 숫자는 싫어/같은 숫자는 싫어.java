import java.util.Stack;

class Solution {
    

    public int[] solution(int[] arr) {
        int[] answer;
        Stack<Integer> s = new Stack<>();
        for (int elem : arr) {
            if (s.size() == 0 || !s.peek().equals(elem)) {
                s.add(elem);
            }
        }
        answer = s.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}