import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class Solution {
    

    // 공항 이름을 Integer로 바꿈
    private static Map<String, Integer> nameToIdx = new HashMap<>();
    // 숫자로 바꾼 공항 이름을 String으로 다시 바꿈
    private static Map<Integer, String> idxToName = new HashMap<>();
    // 각 공항에서 출발하는 항공권을 저장
    private static List<List<Integer>> l = new ArrayList<>();
    // 해당 항공권 사용 여부
    private static boolean[] check;
    private static Deque<String> tmp = new ArrayDeque<>();
    private static Deque<String> ans = new ArrayDeque<>();

    private static void bfs(int start, int length, String[][] tickets) {
        // 모든 항공권을 사용했을 때
        if (tmp.size() == length) {
            // 정답이 비어있으면 현재 경로를 정답으로 저장
            if (ans.isEmpty()) {
                ans.addAll(tmp);
            } 
            // 그 외의 경우
            else {
                // 이전 정답과 현재 경로를 하나씩 탐색
                Iterator<String> iterator1 = tmp.iterator();
                Iterator<String> iterator2 = ans.iterator();
                while (iterator1.hasNext() && iterator2.hasNext()) {
                    String elem1 = iterator1.next();
                    String elem2 = iterator2.next();
                    // 현재 경로가 알파벳 순으로 더 앞서면 정답을 현재 경로로 바꿈
                    if (!elem1.equals(elem2)) {
                        if (elem1.compareTo(elem2) < 0) {
                            ans.clear();
                            ans.addAll(tmp);
                        }
                        break;
                    }
                }
            }
        }
        // 남아있는 항공권이 있으면
        else {
            // 현재 공항에서 사용할 수 있는 항공권 탐색
            for (int elem : l.get(start)) {
                if (!check[elem]) {
                    check[elem] = true;
                    int idx = nameToIdx.get(tickets[elem][1]);
                    tmp.add(idxToName.get(idx));
                    // 해당 항공권을 포함하고 bfs 실행
                    bfs(idx, length, tickets);
                    check[elem] = false;
                    tmp.pollLast();
                }
            }
        }
    }

    public String[] solution(String[][] tickets) {
        String[] answer = tickets[0];

        // 각 공항을 Integer로, 해당 Integer를 통해 다시 공항으로 바꿀 수 있도록 저장
        int idx = 0;
        for (String[] elem : tickets) {
            if (!nameToIdx.containsKey(elem[0])) {
                nameToIdx.put(elem[0], idx);
                idxToName.put(idx, elem[0]);
                idx++;
            }
            if (!nameToIdx.containsKey(elem[1])) {
                nameToIdx.put(elem[1], idx);
                idxToName.put(idx, elem[1]);
                idx++;
            }
        }

        // 각 공항마다 배열을 넣어 해당 공항에서 출발하는 항공권을 넣음
        for (int i = 0; i < nameToIdx.size(); i++) {
            List<Integer> tmp = new ArrayList<>();
            l.add(tmp);
        }
        int length = tickets.length;
        for (int i = 0; i < length; i++) {
            l.get(nameToIdx.get(tickets[i][0])).add(i);
        }

        check = new boolean[length];
        // 시작은 인천이기 때문에 인천의 index를 찾음
        int start = nameToIdx.get("ICN");
        // 인천을 넣고 bfs 탐색 시작
        tmp.add(idxToName.get(start));
        bfs(start, length + 1, tickets);
        answer = ans.toArray(new String[0]);
        return answer;
    }
}
