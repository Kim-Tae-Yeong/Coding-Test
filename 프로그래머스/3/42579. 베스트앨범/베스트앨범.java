import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

class Solution {
    

    public int[] solution(String[] genres, int[] plays) {
        int[] answer;
        // h에 {장르 : [[idx, plays[idx]], ..., [총 plays]] 값 저장
        HashMap<String, Stack<List<Integer>>> h = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            List<Integer> elem = new ArrayList<>();
            List<Integer> total = new ArrayList<>();
            if (!h.containsKey(genres[i])) {
                h.put(genres[i], new Stack<>());
                elem.add(i);
                elem.add(plays[i]);
                total.add(plays[i]);
            } else {
                elem.add(i);
                elem.add(plays[i]);
                int tmp = h.get(genres[i]).pop().get(0);
                tmp += plays[i];
                total.add(tmp);
            }
            h.get(genres[i]).add(elem);
            h.get(genres[i]).add(total);
        }
        List<Stack<List<Integer>>> l = new ArrayList<>();
        for (Stack<List<Integer>> elem : h.values()) {
            l.add(elem);
        }
        // 총 plays 기준 오름차순 정렬
        l.sort((o1, o2) -> o2.peek().get(0) - o1.peek().get(0));
        List<Integer> ans = new ArrayList<>();
        for (Stack<List<Integer>> elem : l) {
            // 총 plays 값 제거
            elem.pop();
            elem.sort((o1, o2) -> {
                if (!o1.get(1).equals(o2.get(1))) {
                    // 해당 장르에서 play 기준 오름차순 정렬
                    return Integer.compare(o1.get(1), o2.get(1));
                } else {
                    // play 횟수가 동일하면 idx 기준 내림차순 정렬
                    return Integer.compare(o2.get(0), o1.get(0));
                }
            });
            // 해당 장르의 곡이 2개 이상이면 길이를 2, 아니면 1로 설정
            int length = (elem.size() > 2) ? 2 : elem.size();
            for (int i = 0; i < length; i++) {
                ans.add(elem.pop().get(0));
            }
        }
        answer = ans.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}
