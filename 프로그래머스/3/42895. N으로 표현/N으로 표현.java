import java.util.ArrayList;
import java.util.List;

class Solution {
    

    public int solution(int N, int number) {
        int answer = -1;
        List<List<Integer>> l = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            List<Integer> tmp = new ArrayList<>();
            l.add(tmp);
        }
        l.get(1).add(N);
        if (N == number) {
            answer = 1;
        } else {

            for (int i = 2; i < 9; i++) {
                int tmp = l.get(i - 1).get(0);
                if (tmp * 10 + N == number) {
                    answer = i;
                    break;
                }
                l.get(i).add(tmp * 10 + N);
                for (int j = 1; j < i / 2 + 1; j++) {
                    int idx1 = j;
                    int idx2 = i - j;
                    for (int elem1 : l.get(idx1)) {
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