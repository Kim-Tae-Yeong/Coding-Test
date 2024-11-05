class Solution {
    
    public String solution(String number, int k) {
        String answer = "";
        int start = 0;
        int length = number.length();
        int cnt = length - k;
        int end = length - cnt + 1;
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (cnt == 0) {
                break;
            }
            char max_num = '0';
            int idx = start;
            int tmp = 0;
            while (true) {
                char c = number.charAt(idx);
                if (c > max_num) {
                    max_num = c;
                    start = idx + 1;
                }
                idx += 1;
                tmp += 1;
                if (tmp == end) {
                    break;
                }
            }
            sb.append(max_num);
            cnt -= 1;
            end = length - start - cnt + 1;
        }
        answer = sb.toString();
        return answer;
    }
}
