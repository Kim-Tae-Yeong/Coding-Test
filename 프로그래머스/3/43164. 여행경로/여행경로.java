import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class Solution {
    

    private static Map<String, Integer> nameToIdx = new HashMap<>();
    private static Map<Integer, String> idxToName = new HashMap<>();
    private static List<List<Integer>> l = new ArrayList<>();
    private static boolean[] check;
    private static Deque<String> tmp = new ArrayDeque<>();
    private static Deque<String> ans = new ArrayDeque<>();

    private static void bfs(int start, int length, String[][] tickets) {
        if (tmp.size() == length) {
            if (ans.isEmpty()) {
                ans.addAll(tmp);
            } else {
                Iterator<String> iterator1 = tmp.iterator();
                Iterator<String> iterator2 = ans.iterator();
                while (iterator1.hasNext() && iterator2.hasNext()) {
                    String elem1 = iterator1.next();
                    String elem2 = iterator2.next();
                    if (!elem1.equals(elem2)) {
                        if (elem1.compareTo(elem2) < 0) {
                            ans.clear();
                            ans.addAll(tmp);
                        }
                        break;
                    }
                }
            }
        } else {
            for (int elem : l.get(start)) {
                if (!check[elem]) {
                    check[elem] = true;
                    int idx = nameToIdx.get(tickets[elem][1]);
                    tmp.add(idxToName.get(idx));
                    bfs(idx, length, tickets);
                    check[elem] = false;
                    tmp.pollLast();
                }
            }
        }
    }

    public String[] solution(String[][] tickets) {
        String[] answer = tickets[0];

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
        for (int i = 0; i < nameToIdx.size(); i++) {
            List<Integer> tmp = new ArrayList<>();
            l.add(tmp);
        }

        int length = tickets.length;
        for (int i = 0; i < length; i++) {
            l.get(nameToIdx.get(tickets[i][0])).add(i);
        }

        check = new boolean[length];
        int start = nameToIdx.get("ICN");
        tmp.add(idxToName.get(start));
        bfs(start, length + 1, tickets);
        answer = ans.toArray(new String[0]);
        return answer;
    }
}