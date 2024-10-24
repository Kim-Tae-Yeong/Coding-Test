import java.util.HashMap;
import java.util.Set;

class Solution {
    
    public String solution(String[] participant, String[] completion) {
      String answer;
      HashMap<String, Integer> h = new HashMap<>();

      for(String elem : participant) {
        h.put(elem, h.getOrDefault(elem, 0) + 1);
      }

      for(String elem : completion) {
        h.replace(elem, h.get(elem) - 1);
        if(h.get(elem).equals(0)) {
          h.remove(elem);
        }
      }

      answer = h.keySet().iterator().next();
      return answer;
    }
}