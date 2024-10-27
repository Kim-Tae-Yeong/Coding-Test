import java.util.Stack;

class Solution {
    

    public Boolean solution(String s) {
        Boolean answer = true;
        Stack<Character> st = new Stack<>();
        char[] c = s.toCharArray();
        for (char ch : c) {
            if (ch == '(') {
                st.add(ch);
            } else {
                if (st.size() == 0) {
                    answer = false;
                    break;
                }
                st.pop();
            }
        }
        if (st.size() != 0) {
            answer = false;
        }
        return answer;
    }
}