import java.util.TreeMap;

class Solution {
    

    public int solution(int[] people, int limit) {
        int answer = 0;
        // 각 무게를 가진 인원이 몇명인지 구함
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int elem : people) {
            tm.put(elem, tm.getOrDefault(elem, 0) + 1);
        }
        while (!tm.isEmpty()) {
            // 무게의 종류가 1개밖에 없으면
            if (tm.size() == 1) {
                // 해당 무게를 가진 사람이 1명밖에 없으면
                if (tm.get(tm.firstKey()) == 1) {
                    // 구명보트 하나 사용
                    answer += 1;
                    tm.remove(tm.firstKey());
                } 
                // 해당 무게를 가진 사람이 2명 이상이면
                else {
                    // 2명이 동시에 구명보트에 탈 수 없으면
                    if (2 * tm.firstKey() > limit) {
                        // 해당 무게를 가진 인원 수만큼 구명보트 사용
                        answer += tm.get(tm.firstKey());
                        tm.remove(tm.firstKey());
                    } 
                    // 2명이 동시에 구명보트에 탈 수 있으면
                    else {
                        // 해당 인원이 짝수이면 인원의 절반만큼 구명보트 사용
                        if (tm.get(tm.firstKey()) % 2 == 0) {
                            answer += (tm.get(tm.firstKey()) / 2);
                        }
                        // 홀수이면 (인원의 절반 + 1)만큼 구명보트 사용
                        else {
                            answer += (tm.get(tm.firstKey()) / 2) + 1;
                        }
                        tm.remove(tm.firstKey());
                    }
                }
            }
            // 무게의 종류가 2개 이상이면
            else {
                // 첫 번째 무게(가장 가벼운 무게) 구함
                int weight = tm.firstKey();
                // 가장 가벼온 무게와 가장 무거운 무게를 합친 무게가 limit보다 크면
                if (weight + tm.lastKey() > limit) {
                    // 가장 무거운 무게를 가진 인원은 구명보트를 같이 탈 수 없기 때문에 그 인원수만큼 구명보트 사용
                    answer += tm.get(tm.lastKey());
                    tm.remove(tm.lastKey());
                }
                // 가장 가벼운 무게의 인원과 가장 무거운 무게의 인원이 동시에 구명보트에 탈 수 있으면
                else {
                    // 두 인원의 수 중 더 작은 값을 가져옴
                    int cnt = Math.min(tm.get(tm.firstKey()), tm.get(tm.lastKey()));
                    // 그 인원수만큼 구명보트 사용
                    answer += cnt;
                    // 위에서 구한 인원수만큼 인원을 빼고 인원이 0명이 되면 remove
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
