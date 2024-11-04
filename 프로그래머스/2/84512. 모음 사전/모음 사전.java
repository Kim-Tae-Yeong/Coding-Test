
class Solution {
    

    public int solution(String word) {
        int answer = 0;
        char[] ch = word.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == 'A') {
                answer += 1;
            } else if (ch[i] == 'E') {
                if (i == 4) {
                    answer += 2;
                } else {
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
