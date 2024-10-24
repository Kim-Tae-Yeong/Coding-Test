import java.util.HashMap;
import java.util.Set;

class Solution {
    
    public String solution(String[] participant, String[] completion) {
      String answer;
      // h : <참여자 이름, 인원 수>
      HashMap<String, Integer> h = new HashMap<>();

      // 참여자 정보 저장
      for(String elem : participant) {
        h.put(elem, h.getOrDefault(elem, 0) + 1);
      }

      // 참여자 중 완주자가 누구인지 확인
      for(String elem : completion) {
        h.replace(elem, h.get(elem) - 1);
        // 해당 이름을 가진 참여자가 모두 완주를 했으면 hashmap에서 제거
        if(h.get(elem).equals(0)) {
          h.remove(elem);
        }
      }

      // 참여자 중 완주하지 못한 인원은 1명만 존재함
      // 때문에 hashmap의 key 값을 set으로 바꾸면 원소는 1개만 존재함
      // .iterator().next()를 이용하여 set에 들어있는 원소에 접근
      answer = h.keySet().iterator().next();
      return answer;
    }
}
