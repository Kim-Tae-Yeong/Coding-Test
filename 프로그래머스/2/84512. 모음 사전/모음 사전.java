
class Solution {
    

    public int solution(String word) {
        int answer = 0;
        char[] ch = word.toCharArray();
        // 현재 단어가 A이면 1을 더함
        // A가 아닌 단어이면서 마지막(5번째) 단어이면 각 단어에 맞게 증가시킴
        // A가 아닌 단어이면서 마지막(5번쨰) 단어가 아니면 5 + 5 ^ 2 + 5 ^ 3 + ... 으로 더함
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == 'A') {
                answer += 1;
            } else if (ch[i] == 'E') {
                if (i == 4) {
                    answer += 2;
                } else {
                    // 해당 단어 이후에 아무 단어도 없는 경우를 포함하기 위해서 초기 값을 1로 설정함
                    int tmp = 1;
                    for (int j = 4 - i; j > 0; j--) {
                        tmp += Math.pow(5, j);
                    }
                    answer += (tmp + 1);
                }
            } else if (ch[i] == 'I') {
                if (i == 4) {
                    answer += 3;
                } else {
                    int tmp = 1;
                    for (int j = 4 - i; j > 0; j--) {
                        tmp += Math.pow(5, j);
                    }
                    answer += (2 * tmp + 1);
                }
            } else if (ch[i] == 'O') {
                if (i == 4) {
                    answer += 4;
                } else {
                    int tmp = 1;
                    for (int j = 4 - i; j > 0; j--) {
                        tmp += Math.pow(5, j);
                    }
                    answer += (3 * tmp + 1);
                }
            } else if (ch[i] == 'U') {
                if (i == 4) {
                    answer += 5;
                } else {
                    int tmp = 1;
                    for (int j = 4 - i; j > 0; j--) {
                        tmp += Math.pow(5, j);
                    }
                    answer += (4 * tmp + 1);
                }
            }
        }
        return answer;
    }
}
