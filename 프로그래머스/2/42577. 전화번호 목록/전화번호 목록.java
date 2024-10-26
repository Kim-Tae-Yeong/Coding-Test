import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    
    public Boolean solution(String[] phone_book) {
        Boolean answer = true;
        List<String> l = new ArrayList<>();
        for(String elem : phone_book) {
          l.add(elem);
        }
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