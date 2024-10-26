import java.util.HashMap;

class Solution {
    
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> h = new HashMap<>();
        for(int i = 0; i < clothes.length; i++) {
          h.put(clothes[i][1], h.getOrDefault(clothes[i][1], 0) + 1);
        }
        for(int elem : h.values()) {
          answer *= (elem + 1);
        }
        return (answer - 1);
    }
}