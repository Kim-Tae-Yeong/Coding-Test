import java.util.TreeMap;

class Solution {
    

    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        // TreeMap : key 값이 오름차순 정렬된 map
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (String elem : operations) {
            String[] cmd = elem.split(" ");
            if (cmd[0].equals("I")) {
                tm.put(Integer.parseInt(cmd[1]), tm.getOrDefault(Integer.parseInt(cmd[1]), 0) + 1);
            } else if (!tm.isEmpty()) {
                if (cmd[1].equals("1")) {
                    tm.replace(tm.lastKey(), tm.get(tm.lastKey()) - 1);
                    if (tm.get(tm.lastKey()).equals(0)) {
                        tm.remove(tm.lastKey());
                    }
                } else {
                    tm.replace(tm.firstKey(), tm.get(tm.firstKey()) - 1);
                    if (tm.get(tm.firstKey()).equals(0)) {
                        tm.remove(tm.firstKey());
                    }
                }
            }
        }
        if (!tm.isEmpty()) {
            answer[0] = tm.lastKey();
            answer[1] = tm.firstKey();
        }
        return answer;
    }
}
