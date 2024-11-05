
class Solution {
    

    public int solution(String name) {
        int answer = 0;
        int idx = 0;
        int move = Integer.MAX_VALUE;
        for (int i = 0; i < name.length(); i++) {
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
            idx = i + 1;
            while (idx < name.length() && name.charAt(idx) == 'A') {
                idx += 1;
            }
            move = Math.min(move, i * 2 + name.length() - idx);
            move = Math.min(move, (name.length() - idx) * 2 + i);
        }
        return answer + move;
    }
}