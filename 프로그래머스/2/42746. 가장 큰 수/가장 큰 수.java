import java.util.ArrayList;
import java.util.List;

class Solution {
    

    public String solution(int[] numbers) {
        String answer = "";
        List<String> l = new ArrayList<>();
        for (int elem : numbers) {
            l.add(Integer.toString(elem));
        }
        l.sort((o1, o2) -> {
            String tmp1 = o1 + o2;
            String tmp2 = o2 + o1;
            return tmp2.compareTo(tmp1);
        });
        if (l.get(0).equals("0")) {
            answer = "0";
        } else {
            for (String elem : l) {
                answer += elem;
            }
        }
        return answer;
    }
}