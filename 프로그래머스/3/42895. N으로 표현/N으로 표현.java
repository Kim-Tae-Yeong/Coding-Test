import java.util.ArrayList;
import java.util.List;

class Solution {
    

    public int solution(int N, int number) {
        int answer = -1;
        // l.get(i) : N을 i번 사용해서 만들 수 있는 숫자
        List<List<Integer>> l = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            List<Integer> tmp = new ArrayList<>();
            l.add(tmp);
        }
        // N을 1번 사용해서 만들 수 있는 수는 N밖에 없음
        l.get(1).add(N);
        // number와 N이 동일하면 정답은 1
        if (N == number) {
            answer = 1;
        } 
        // 그 외의 경우
        else {
            // N을 2번부터 8번까지 사용하는 경우 확인
            for (int i = 2; i < 9; i++) {
                int tmp = l.get(i - 1).get(0);
                // N을 i번 사용해서 NN...N을 만듦
                if (tmp * 10 + N == number) {
                    answer = i;
                    break;
                }
                l.get(i).add(tmp * 10 + N);
                // N을 i번 이용해서 만드는 것은 (1, i - 1), (2, i - 2), ...의 경우를 모두 더한 것임
                for (int j = 1; j < i / 2 + 1; j++) {
                    int idx1 = j;
                    int idx2 = i - j;
                    for (int elem1 : l.get(idx1)) {
                        // number를 만들 수 있으면 break
                        if (answer != -1) {
                            break;
                        }
                        for (int elem2 : l.get(idx2)) {
                            if (elem1 + elem2 == number) {
                                answer = i;
                                break;
                            }
                            l.get(i).add(elem1 + elem2);
                            
                            if (elem1 - elem2 == number) {
                                answer = i;
                                break;
                            }
                            l.get(i).add(elem1 - elem2);

                            if (elem2 - elem1 == number) {
                                answer = i;
                                break;
                            }
                            l.get(i).add(elem2 - elem1);

                            if (elem1 * elem2 == number) {
                                answer = i;
                                break;
                            }
                            l.get(i).add(elem1 * elem2);

                            if (elem2 != 0) {
                                if (elem1 / elem2 == number) {
                                    answer = i;
                                    break;
                                }
                                l.get(i).add(elem1 / elem2);
                            }

                            if (elem1 != 0) {
                                if (elem2 / elem1 == number) {
                                    answer = i;
                                    break;
                                }
                                l.get(i).add(elem2 / elem1);
                            }
                        }
                    }
                }
                if (answer != -1) {
                    break;
                }
            }
        }
        return answer;
    }
}
