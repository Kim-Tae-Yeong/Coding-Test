import java.util.ArrayList;
import java.util.List;

class Solution {
    
    public Boolean solution(String[] phone_book) {
        Boolean answer = true;
        List<String> l = new ArrayList<>();
        for(String elem : phone_book) {
          l.add(elem);
        }
        // 전화번호부를 문자열 순으로 오름차순 정렬
        // 때문에 이웃된 두 전화번호만 비교하면 됨
        l.sort(null);
        for(int i = 0; i < phone_book.length - 1; i++) {
          if(l.get(i + 1).startsWith(l.get(i))) {
            answer = false;
            break;
          }
        }
        return answer;
    }
}
