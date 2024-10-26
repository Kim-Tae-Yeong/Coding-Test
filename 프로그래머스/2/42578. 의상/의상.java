import java.util.HashMap;

class Solution {
    
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> h = new HashMap<>();
        // 카테고리별로 개수가 몇개인지 확인
        for(int i = 0; i < clothes.length; i++) {
          h.put(clothes[i][1], h.getOrDefault(clothes[i][1], 0) + 1);
        }
        // 카테고리별 개수에다 1을 더한 값(카테고리에서 아예 고르지 않는 경우)을 곱함
        for(int elem : h.values()) {
          answer *= (elem + 1);
        }
        // 모든 카테고리에서 고르지 않는 경우 1가지를 뺌
        return (answer - 1);
    }
}
