import java.util.TreeMap;

class Solution {
    

    public int solution(int[] people, int limit) {
        int answer = 0;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int elem : people) {
            tm.put(elem, tm.getOrDefault(elem, 0) + 1);
        }
        while (!tm.isEmpty()) {
            if (tm.size() == 1) {
                if (tm.get(tm.firstKey()) == 1) {
                    answer += 1;
                    tm.remove(tm.firstKey());
                } else {
                    if (2 * tm.firstKey() > limit) {
                        answer += tm.get(tm.firstKey());
                        tm.remove(tm.firstKey());
                    } else {
                        if (tm.get(tm.firstKey()) % 2 == 0) {
                            answer += (tm.get(tm.firstKey()) / 2);
                        } else {
                            answer += (tm.get(tm.firstKey()) / 2) + 1;
                        }
                        tm.remove(tm.firstKey());
                    }
                }
            } else {
                int weight = tm.firstKey();
                if (weight + tm.lastKey() > limit) {
                    answer += tm.get(tm.lastKey());
                    tm.remove(tm.lastKey());
                } else {
                    int cnt = Math.min(tm.get(tm.firstKey()), tm.get(tm.lastKey()));
                    answer += cnt;
                    tm.replace(tm.firstKey(), tm.get(tm.firstKey()) - cnt);
                    if (tm.get(tm.firstKey()).equals(0)) {
                        tm.remove(tm.firstKey());
                    }
                    tm.replace(tm.lastKey(), tm.get(tm.lastKey()) - cnt);
                    if (tm.get(tm.lastKey()).equals(0)) {
                        tm.remove(tm.lastKey());
                    }
                }
            }
        }
        return answer;
    }
}