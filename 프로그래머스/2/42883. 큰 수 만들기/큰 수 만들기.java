class Solution {
    
    public String solution(String number, int k) {
        String answer = "";
        // 숫자를 탐색할 시작 index
        int start = 0;
        int length = number.length();
        // 만들 숫자의 총 자릿수
        int cnt = length - k;
        // number = "1942", k = 2에서 처음에 올 수 있는 숫자는 1, 9, 4까지만 가능
        // 즉 숫자를 끝까지 다 탐색하는 것이 아니라 남은 자릿수에 따라 숫자를 탐색하는 끝 값이 달라짐
        int end = length - cnt + 1;
        StringBuilder sb = new StringBuilder();
        while (true) {
            // 자릿수를 다 채웠으면 break
            if (cnt == 0) {
                break;
            }
            // 시작 위치에서 탐색 가능한 위치까지 탐색하면서 가장 큰 수 확인
            char max_num = '0';
            int idx = start;
            int tmp = 0;
            while (true) {
                char c = number.charAt(idx);
                // 가장 큰 수를 발견하면
                if (c > max_num) {
                    max_num = c;
                    // 시작 위치를 현재 위치의 다음 위치로 옮김
                    start = idx + 1;
                }
                idx += 1;
                tmp += 1;
                if (tmp == end) {
                    break;
                }
            }
            sb.append(max_num);
            // 숫자 1개를 확인할때마다 자릿수는 하나씩 감소
            cnt -= 1;
            // number = "1942", k = 2에서 9를 확인했을 때 이후에는 2가지(4, 2)를 탐색할 수 있음
            // length - start : 시작 위치 이후부터 남은 총 숫자의 개수
            // length - start - cnt + 1 : 위의 값 중 탐색 가능한 숫자의 개수
            end = length - start - cnt + 1;
        }
        answer = sb.toString();
        return answer;
    }
}
